package cn.springlogic.message.domain.jpa.repository;

import cn.springlogic.message.domain.jpa.entity.MessagePublicationFavor;
import cn.springlogic.message.domain.jpa.entity.MessageSystem;
import cn.springlogic.message.domain.jpa.entity.projection.MessageSystemProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by kinginblue on 2017/6/12.
 */
@RepositoryRestResource(path = "message:message-system", excerptProjection = MessageSystemProjection.class)
public interface MessageSystemRepository extends JpaRepository<MessageSystem, Integer> {

    @Query("select distinct m from MessageSystem m, Send s where s in elements(m.sends) and s.user.id = :userId order by m.createTime desc")
    @RestResource(path = "user")
    Page<MessageSystem> findByUser(@Param("userId") Integer userId, Pageable pageable);

}
