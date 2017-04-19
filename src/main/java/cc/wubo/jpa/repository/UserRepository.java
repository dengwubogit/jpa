package cc.wubo.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import cc.wubo.jpa.entity.User;
@org.springframework.stereotype.Repository
public interface UserRepository extends CrudRepository<User, Integer>{

}
