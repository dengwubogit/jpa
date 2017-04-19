package cc.wubo.jpa.entity.onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="idcards")
public class IdCard {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="card_num")
	private String cardNum;
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
	@Override
	public String toString() {
		return "IdCard [id=" + id + ", cardNum=" + cardNum + "]";
	}
	
}
