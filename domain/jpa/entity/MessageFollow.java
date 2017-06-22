package cn.springlogic.message.domain.jpa.entity;

import cn.springlogic.message.base.jpa.entity.Message;
import cn.springlogic.social.jpa.entity.Follow;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by kinginblue on 2017/6/12.
 * 关注消息
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class MessageFollow extends Message {

    @OneToOne
    @JoinColumn(name = "follow_id")
    private Follow follow;

}
