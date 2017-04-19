package cc.wubo.jpa.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.RepositoryDefinition;

import cc.wubo.jpa.entity.User;
//@NoRepositoryBean  不会生成代理Repository类
@RepositoryDefinition(domainClass=User.class,idClass=Integer.class)
public interface UserRepositoryAnno {

}
