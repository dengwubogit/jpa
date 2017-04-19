package cc.wubo.jpa.repository.onetoone;

import org.springframework.data.repository.CrudRepository;

import cc.wubo.jpa.entity.onetoone.Person;

public interface PersonRepository extends CrudRepository<Person, Integer>{

}
