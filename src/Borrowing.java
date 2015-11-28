
public class Borrowing {
	private Item item;
	private Member member;
	private Date borrowingDate;
	private Date returnDate;
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Date getBorrowingDate() {
		return borrowingDate;
	}
	public void setBorrowingDate(Date borrowingDate) {
		this.borrowingDate = borrowingDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Borrowing(Item item, Member member, Date borrowingDate, Date returnDate) {
		super();
		this.item = item;
		this.member = member;
		this.borrowingDate = borrowingDate;
		this.returnDate = returnDate;
	}
	
}
