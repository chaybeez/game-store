import java.util.ArrayList;
import java.util.Date;

public class CompletedOrder{
	private String username;
	private String date;
	private Date delDate;
	private String orderid;
	private ArrayList<OrderItem> oi;
	
	public CompletedOrder(String username, String date, Date delDate, String orderid, ArrayList<OrderItem> oi){
		this.username=username;
		this.date=date;
		this.delDate=delDate; 
		this.orderid=orderid;
		this.oi = oi;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public Date getDelDate() {
		return delDate;
	}
	
	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}
	
	public String getOrderid() {
		return orderid;
	}
	
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	public ArrayList<OrderItem> getOrderItem() {
		return oi;
	}
	
	public void setOrderItem(ArrayList<OrderItem> oi) {
		this.oi = oi;
	}
	
}