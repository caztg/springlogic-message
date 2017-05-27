package cn.springlogic.message.jpa.repository;

import cn.springlogic.message.jpa.entity.Send;
import cn.springlogic.message.jpa.entity.projection.SendFullProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by kinginblue on 2017/5/2.
 */
@RepositoryRestResource(path = "message:send", excerptProjection = SendFullProjection.class)
public interface SendRepository extends JpaRepository<Send, Integer> {

}
