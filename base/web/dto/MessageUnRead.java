package cn.springlogic.message.base.web.dto;

import lombok.Data;

/**
 * Created by kinginblue on 2017/5/2.
 *
 * 未读消息数 DTO
 */
@Data
public class MessageUnRead {

    private Integer type;

    private Long unReadCount;

}
