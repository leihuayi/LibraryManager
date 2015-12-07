import java.util.Date;

public class Card implements java.io.Serializable{
	private CardType type;
	private Date expirationDate;
	public CardType getType() {
		return type;
	}
	public void setType(CardType type) {
		this.type = type;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public Card() {
		this.type=CardType.standard;
	}
	
}
