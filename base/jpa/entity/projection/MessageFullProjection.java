package cn.springlogic.message.base.jpa.entity.projection;

import cn.springlogic.blog.jpa.entity.rest.UserProjection;
import cn.springlogic.message.base.jpa.entity.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by kinginblue on 2017/5/2.
 */
@Projection(name = "full", types = {Message.class})
public interface MessageFullProjection {

    Integer getId();

    Integer getType();

    String getText();

    String getTitle();

    Date getCreateTime();

    UserProjection getUser();

    /*List<Send> getSends();*/
    @Value("#{target.sends.get(0)}")
    Send4MessageProjection getSend();

}
