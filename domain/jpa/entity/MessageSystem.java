package cn.springlogic.message.domain.jpa.entity;

import cn.springlogic.message.base.jpa.entity.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * Created by kinginblue on 2017/6/12.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class MessageSystem extends Message {

}
