package cc.wubo.jpa.entity;

public class BookInfo {
	private Integer type;
	private double maxPrice;
	private double sumPrice;
	public BookInfo(Integer type, double maxPrice, double sumPrice) {
		this.type = type;
		this.maxPrice = maxPrice;
		this.sumPrice = sumPrice;
	}
	public BookInfo() {
	}
	@Override
	public String toString() {
		return "BookInfo [type=" + type + ", maxPrice=" + maxPrice
				+ ", sumPrice=" + sumPrice + "]";
	}
	
	
}
