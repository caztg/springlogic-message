package cn.springlogic.message.base.jpa.entity.projection;

import cn.springlogic.blog.jpa.entity.rest.UserProjection;
import cn.springlogic.message.base.jpa.entity.Message;
import cn.springlogic.message.base.jpa.entity.Send;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by kinginblue on 2017/5/2.
 */
@Projection(name = "full", types = {Send.class})
public interface SendFullProjection {

    Integer getId();

    Integer getStatus();

    Date getCreateTime();

    UserProjection getUser();

    Message getMessage();

}
