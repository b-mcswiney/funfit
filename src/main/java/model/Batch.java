package model;

import java.util.Date;

public class Batch {
	int bid;
	Date starttime;
	Date endtime;
	String trainerName;

	public Batch() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Batch(int bid, Date starttime, Date endtime, String trainerName) {
		super();
		this.bid = bid;
		this.starttime = starttime;
		this.endtime = endtime;
		this.trainerName = trainerName;
	}
	
	public Batch(Date starttime, Date endtime, String trainerName) {
		super();
		this.starttime = starttime;
		this.endtime = endtime;
		this.trainerName = trainerName;
	}

	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	@Override
	public String toString() {
		return "Batch [bid=" + bid + ", starttime=" + starttime + ", endtime=" + endtime + ", trainerName="
				+ trainerName + "]";
	}
}
