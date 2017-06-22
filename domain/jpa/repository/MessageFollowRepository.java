package cn.springlogic.message.domain.jpa.repository;

import cn.springlogic.message.domain.jpa.entity.MessageFollow;
import cn.springlogic.message.domain.jpa.entity.projection.MessageFollowProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kinginblue on 2017/6/12.
 */
@RepositoryRestResource(path = "message:message-follow", excerptProjection = MessageFollowProjection.class)
public interface MessageFollowRepository extends JpaRepository<MessageFollow, Integer> {

    @Query("select distinct m from MessageFollow m, Send s where s in elements(m.sends) and s.user.id = :userId order by m.createTime desc")
    @RestResource(path = "user")
    Page<MessageFollow> findByUser(@Param("userId") Integer userId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("delete from MessageFollow m where m.follow.id =?1")
    void delByFollowId(Integer followId);

    @Query("select m from MessageFollow m where m.follow.id =:followId")
    MessageFollow findByFollowId(@Param("followId")Integer followId);
}
