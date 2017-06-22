package cn.springlogic.message.domain.jpa.entity.projection;

import cn.springlogic.message.base.jpa.entity.projection.MessageFullProjection;
import cn.springlogic.message.domain.jpa.entity.MessagePublicationComment;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by kinginblue on 2017/6/12.
 */
@Projection(name = "full", types = {MessagePublicationComment.class})
public interface MessagePublicationCommentProjection extends MessageFullProjection {

    Publication4MessageProjection getPublication();

}
