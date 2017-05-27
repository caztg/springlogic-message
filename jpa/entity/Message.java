package cn.springlogic.message.jpa.entity;

import cn.springlogic.user.jpa.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by kinginblue on 2017/5/2.
 */
@Entity
@Data
public class Message {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer type;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(name = "create_time", nullable = false)
    @CreationTimestamp
    private Date createTime;

    // 消息发送者
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "message")
    private List<Send> sends;

}
