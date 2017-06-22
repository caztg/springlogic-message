package cn.springlogic.message.domain.jpa.entity;

import cn.springlogic.message.base.jpa.entity.Message;
import cn.springlogic.social.jpa.entity.Publication;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * Created by kinginblue on 2017/6/12.
 * 话题点赞消息
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class MessagePublicationFavor extends Message {

   // @OneToOne
   // @JoinColumn(name = "publication_id")
   // private Publication publication;

    private Integer publicationId;

    @Transient
    private Publication publication;
}
