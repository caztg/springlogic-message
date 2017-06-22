package cn.springlogic.message.base.jpa.repository;

import cn.springlogic.message.base.jpa.entity.Send;
import cn.springlogic.message.base.jpa.entity.projection.SendFullProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kinginblue on 2017/5/2.
 */
@RepositoryRestResource(path = "message:send", excerptProjection = SendFullProjection.class)
public interface SendRepository extends JpaRepository<Send, Integer> {

    @Modifying
    @Transactional
    @Query("update Send s set s.status=:status where s.id in (:ids)")
    int readMsg(@Param("ids")List ids,@Param("status")Integer status);

}
