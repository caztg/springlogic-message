package cn.springlogic.message.domain.jpa.repository;

import cn.springlogic.message.domain.jpa.entity.MessagePublicationComment;
import cn.springlogic.message.domain.jpa.entity.projection.MessagePublicationCommentProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by kinginblue on 2017/6/12.
 */
@RepositoryRestResource(path = "message:message-publication-comment", excerptProjection = MessagePublicationCommentProjection.class)
public interface MessagePublicationCommentRepository extends JpaRepository<MessagePublicationComment, Integer> {

    @Query("select distinct m from MessagePublicationComment m, Send s where s in elements(m.sends) and s.user.id = :userId order by m.createTime desc")
    @RestResource(path = "user")
    Page<MessagePublicationComment> findByUser(@Param("userId") Integer userId, Pageable pageable);

    /*
    @Modifying
    @Transactional
    @Query("delete from MessagePublicationComment m where m.publication.id =?1")
    void delByPublicationId(Integer publicationId);

    @Query("select m from MessagePublicationComment m where m.publication.id =:publicationId")
    List<MessagePublicationComment> findByPublicationId(@Param("publicationId")Integer publicationId);
    */
}
