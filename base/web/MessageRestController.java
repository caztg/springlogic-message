package cn.springlogic.message.base.web;

import cn.springlogic.message.base.jpa.entity.Message;
import cn.springlogic.message.base.jpa.entity.Send;
import cn.springlogic.message.base.jpa.repository.MessageRepository;
import cn.springlogic.message.base.web.dto.MessageUnRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by kinginblue on 2017/5/2.
 */
@RepositoryRestController
@RequestMapping(value = "message:message")
public class MessageRestController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private PagedResourcesAssembler pagedResourcesAssembler;

    @ResponseBody
    @GetMapping(value = "/search/custom-unread")
    public ResponseEntity<PagedResources<MessageUnRead>> customSearchUnreadCount(@RequestParam("userId") Integer userId) {

        List<Message> messageList = messageRepository.findByUser(userId);

        Map<Integer, List<Message>> groupMessage = messageList.stream().collect(Collectors.groupingBy(Message::getType));

        List<MessageUnRead> messageUnReadList = groupMessage.entrySet().stream()
                .map(data -> {
                    Integer type = data.getKey();
                    Long unReadCount = data.getValue().stream()
                            .mapToLong(m -> m.getSends().stream().filter(f -> f.getStatus() == Send.EnumSendStatus.UNREAD.val).count())// .count() [the result equals to] .collect(Collectors.counting())
                            .sum();

                    MessageUnRead dto = new MessageUnRead();
                    dto.setType(type);
                    dto.setUnReadCount(unReadCount);
                    return dto;
                }).collect(Collectors.toList());

        Page<MessageUnRead> fakePage = new PageImpl<>(messageUnReadList);

        return ResponseEntity.ok(pagedResourcesAssembler.toResource(fakePage));
    }

}
