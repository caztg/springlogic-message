package cn.springlogic.message.domain.web;

import cn.springlogic.message.domain.jpa.entity.MessageFollow;
import cn.springlogic.message.domain.jpa.repository.MessageFollowRepository;
import cn.springlogic.social.jpa.entity.Follow;
import cn.springlogic.social.jpa.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kinginblue on 2017/6/12.
 */
@RepositoryRestController
@RequestMapping(value = "message:message-follow")
public class MessageFollowController {

    @Autowired
    private MessageFollowRepository messageFollowRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private PagedResourcesAssembler pagedResourcesAssembler;

    @ResponseBody
    @GetMapping(value = "/search/custom-user")
    public ResponseEntity<PagedResources<PersistentEntityResource>> customMessageFollowPage(@RequestParam("userId") Integer userId,
                                                                                            Pageable pageable,
                                                                                            PersistentEntityResourceAssembler resourceAssembler) {

        Page<MessageFollow> messageFollowPage = messageFollowRepository.findByUser(userId, pageable);

        Page<MessageFollow> customMessageFollowPage = messageFollowPage.map(new CustomFollowConverter(followRepository));

        return ResponseEntity.ok(pagedResourcesAssembler.toResource(customMessageFollowPage, resourceAssembler));
    }

    /**
     *转换器类
     */
    public static final class CustomFollowConverter implements Converter<MessageFollow, MessageFollow> {

        private FollowRepository followRepository;

        public CustomFollowConverter(FollowRepository followRepository) {
            this.followRepository = followRepository;
        }

        @Override
        public MessageFollow convert(MessageFollow source) {
            Follow follow = source.getFollow();
            // 查找判断是否相互关注
            if (null != follow && null != follow.getUser() && null != follow.getFollowUser()) {
                Follow reverseFollow = followRepository.findByuserIdAndFollowUserId(follow.getFollowUser().getId(), follow.getUser().getId());
                follow.setEachOther(null != reverseFollow);
            }
            return source;
        }
    }

}
