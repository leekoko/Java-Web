package myvisit;

import java.util.Date;

public class Visitor {
	int id;
	int userId;
	Date visitTime;
	Date leftTime;
	String ip;
	String comeFrom;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	public Date getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(Date leftTime) {
		this.leftTime = leftTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getComeFrom() {
		return comeFrom;
	}
	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}
	
}
