package cn.springlogic.message.jpa.repository;

import cn.springlogic.message.jpa.entity.Message;
import cn.springlogic.message.jpa.entity.projection.MessageFullProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by kinginblue on 2017/5/2.
 */
@RepositoryRestResource(path = "message:message", excerptProjection = MessageFullProjection.class)
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("select distinct m from Message m, Send s where s in elements(m.sends) and s.user.id = :userId and m.type = :type")
    @RestResource(path = "user-type")
    Page<Message> findByUserAndType(@Param("userId") Integer userId, @Param("type") Integer type, Pageable pageable);

    @Query("select distinct m from Message m, Send s where s in elements(m.sends) and s.user.id = :userId")
    @RestResource(exported = false)
    List<Message> findByUser(@Param("userId") Integer userId);

}
