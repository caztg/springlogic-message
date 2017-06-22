package cn.springlogic.message.domain.jpa.entity.projection;

import cn.springlogic.social.jpa.entity.Follow;
import cn.springlogic.user.jpa.entity.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by kinginblue on 2017/6/12.
 */
@Projection(name = "4Message", types = {Follow.class})
public interface Follow4MessageProjection {

    Integer getId();

    String getMemo();

    User getUser();

    User getFollowUser();

    Date getCreateTime();

    String getStatus();

    Boolean getEachOther();

}
