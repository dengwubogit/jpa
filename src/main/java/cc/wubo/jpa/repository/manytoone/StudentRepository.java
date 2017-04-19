package cc.wubo.jpa.repository.manytoone;

import org.springframework.data.repository.CrudRepository;

import cc.wubo.jpa.entity.manytoone.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>{

}
