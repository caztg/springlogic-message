package cn.springlogic.message.base.web;

import cn.springlogic.message.base.jpa.repository.SendRepository;
import cn.springlogic.message.base.web.dto.MessageRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by admin on 2017/6/19.
 */
@RequestMapping("api/message:send")
@Controller
public class MessageController {

    @Autowired
    private SendRepository sendRepository;

    @Transactional
    @RequestMapping(value = "custom-read", method = RequestMethod.PATCH, consumes = "application/json")
    public ResponseEntity<Void> readMsg(@RequestBody MessageRead messageRead) {


        try {
            sendRepository.readMsg(messageRead.getIds(), messageRead.getStatus());

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
