package cc.wubo.jpa.service.onetoone;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.wubo.jpa.entity.onetoone.IdCard;
import cc.wubo.jpa.entity.onetoone.Person;
import cc.wubo.jpa.repository.onetoone.IdCardRepository;

@Service
public class PersonService {
	@Autowired
	EntityManager em;
	@Transactional
	public IdCard getIdCard(IdCardRepository idCardRepository,Integer id) {
	  return	idCardRepository.findOne(id);
	}
	
	@Transactional
	public void lazy() {
		Person person = em.find(Person.class, 1);
		System.out.println(person.getIdCard());
	}
	
}
