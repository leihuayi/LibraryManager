package lms;

import java.util.Date;

public class Borrowing implements Observable{
	private LibraryItem item;
	// the member is the observer
	private Member member;
	// date when the member does the borrowing
	private Date borrowingDate;
	//date when the member gets the item;
	private Date effectiveBorrowingDate; 
	//date when the member returns the borrowing
	private Date returnDate;
	
	
	public Date getEffectiveBorrowingDate() {
		return effectiveBorrowingDate;
	}
	public void setEffectiveBorrowingDate(Date effectiveBorrowingDate) {
		this.effectiveBorrowingDate = effectiveBorrowingDate;
	}
	public LibraryItem getItem() {
		return item;
	}
	public void setItem(LibraryItem item) {
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
	public Borrowing(LibraryItem item, Member member, Date borrowingDate, Date returnDate) {
		super();
		this.item = item;
		this.member = member;
		this.borrowingDate = borrowingDate;
		this.returnDate = returnDate;
	}
	@Override
	public void notifyObserverDelay() {
		// TODO Auto-generated method stub
		member.update(item.getTitle());
	}
	@Override
	public void notifyObserverItemBorrowable() {
		// TODO Auto-generated method stub
		member.secondUpdate(item.getTitle());
	}
	
		
}
