package cc.wubo.jpa.entity.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="idcards")
public class IdCard {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="card_num")
	private String cardNum;
	/**
	 * mappedBy： 
	 * 1.一般用在被拥有方
	 * 2.@OneToOne设置了mappedBy 是不会在本表中加外键字段的，
	 * 3.设置了mappedBy 会忽略@JoinColumn
	 */
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="idCard")
	@JoinColumn(name="pid")
	private Person person;
	public Integer getId() { 
		return id;
	}
	public void setId(Integer id) {
		this.id = id;  
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	@Override
	public String toString() {
		return "IdCard [id=" + id + ", cardNum=" + cardNum + "";
	}
	
	
}
