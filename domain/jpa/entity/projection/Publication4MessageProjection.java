package cn.springlogic.message.domain.jpa.entity.projection;

import cn.springlogic.blog.jpa.entity.rest.ArticleProjection;
import cn.springlogic.social.jpa.entity.Publication;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by kinginblue on 2017/6/12.
 */
@Projection(name = "4Message", types = {Publication.class})
public interface Publication4MessageProjection {

    Integer getId();

    ArticleProjection getArticle();

    Date getCreateTime();

}
