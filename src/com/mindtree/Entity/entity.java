package com.mindtree.Entity;

import java.sql.Date;
import java.util.Objects;

public class entity implements Comparable<entity>{

	private int Id;
	private Date date;
	private String state;
	private String district;
	private String tested;
	private String confirmed;
	private String recovered;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(Id, confirmed, date, district, recovered, state, tested);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		entity other = (entity) obj;
		return Id == other.Id && Objects.equals(confirmed, other.confirmed) && Objects.equals(date, other.date)
				&& Objects.equals(district, other.district) && Objects.equals(recovered, other.recovered)
				&& Objects.equals(state, other.state) && Objects.equals(tested, other.tested);
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getTested() {
		return tested;
	}
	public void setTested(String tested) {
		this.tested = tested;
	}
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}
	public String getRecovered() {
		return recovered;
	}
	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}
	public entity(int id, Date date, String state, String district, String tested, String confirmed, String recovered) {
		super();
		Id = id;
		this.date = date;
		this.state = state;
		this.district = district;
		this.tested = tested;
		this.confirmed = confirmed;
		this.recovered = recovered;
	}
	@Override
	public String toString() {
		return " Id=" + Id + ", date=" + date + ", state=" + state + ", district=" + district + ", tested="
				+ tested + ", confirmed=" + confirmed + ", recovered=" + recovered;
	}
	public String tString() {
		return date+" | "+state+" | "+confirmed;
	}
	
 public int compareTo(entity a)
    {
       
            return this.date.compareTo(a.date);
        
   
    
    }
}
