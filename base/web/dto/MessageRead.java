package cn.springlogic.message.base.web.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by admin on 2017/6/19.
 */
@Data
public class MessageRead {

    private Integer status;

    private List<Integer> ids;
}
