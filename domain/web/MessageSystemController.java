package cn.springlogic.message.domain.web;

import cn.springlogic.message.base.jpa.entity.Message;
import cn.springlogic.message.base.jpa.entity.Send;
import cn.springlogic.message.base.jpa.repository.MessageRepository;
import cn.springlogic.message.base.jpa.repository.SendRepository;
import cn.springlogic.user.jpa.entity.User;
import cn.springlogic.user.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by admin on 2017/6/16.
 */
@Controller
@RequestMapping("api/message/message-system")
public class MessageSystemController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private SendRepository sendRepository;

    /***
     * 发送系统消息
     * @param message
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<Message> sendSystemMsg(@RequestBody Message message){
        /*待优化*/
        try {
            Page<User> all=userRepository.findAll(new PageRequest(0,100));
            int totalPages = all.getTotalPages();
            Message save = messageRepository.save(message);
            for (int i = 0; i < totalPages; i++) {
                all=userRepository.findAll(new PageRequest(i,100));
                List<User> content = all.getContent();
                //content 不可修改的list集合
                for (User user : content) {
                    Send send=new Send();
                    send.setStatus(Send.EnumSendStatus.UNREAD.val);
                    send.setUser(user);
                    send.setMessage(save);
                    sendRepository.save(send);
                }
            }
            return ResponseEntity.ok(save);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
