package cn.springlogic.message.base.jpa.entity;

import cn.springlogic.user.jpa.entity.User;
import lombok.Data;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by kinginblue on 2017/5/2.
 */
@Entity
@Data
public class Send {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer status;

    @Column(name = "create_time", nullable = false)
    @CreationTimestamp
    private Date createTime;

    // 消息接收者
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;

    /**
     * Send 发送状态枚举器
     */
    public static enum EnumSendStatus {
        UNREAD(1), READ(2);

        public final int val;
        EnumSendStatus(int val) {
            this.val = val;
        }
    }

}
