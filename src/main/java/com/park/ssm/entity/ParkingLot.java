package com.park.ssm.entity;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.park.ssm.annotation.Permission;
import com.park.ssm.annotation.Permission.Type;
import com.park.ssm.annotation.UnEditableField;
import com.park.ssm.entity.type.ParkingLotState;

@JsonIgnoreProperties(value= {"handler","serialVersionUID"},ignoreUnknown = true)
@Permission(value= {Type.ADMIN,Type.MANAGER,Type.OPERATOR})
public class ParkingLot implements Serializable{
	/**
	 */
	private static final long serialVersionUID = 4364826856691783045L;
	
	@UnEditableField
	@Permission(value= {},haveControl=false)
	private Integer id;
	//业务相关
	@UnEditableField
	private int totalPositionNum;
	private double currentPrice;
	private ParkingLotState state=ParkingLotState.ACTIVE;
	
	//业务无关的详细信息
	@Permission(value= {},haveControl=false)
	private String name;
	@Permission(value= {},haveControl=false)
	private String location;
	private Double cost;
	private List<ParkingPosition> parkingPositions;
	
	public ParkingLot() {}
	public ParkingLot(Integer id,int totalPositionNum) {
		this.id=id;
		this.totalPositionNum=totalPositionNum;
	}
	

	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public int getTotalPositionNum() {
		return totalPositionNum;
	}
	
	public void setTotalPositionNum(int totalPositionNum) {
		this.totalPositionNum = totalPositionNum;
	}
	
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public ParkingLotState getState() {
		return state;
	}
	public void setState(ParkingLotState state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<ParkingPosition> getParkingPositions() {
		return parkingPositions;
	}
	public void setParkingPositions(List<ParkingPosition> parkingPositions) {
		this.parkingPositions = parkingPositions;
	}
	
	@Override
	public String toString() {
		return "ParkingLot [id=" + id + ", totalPositionNum=" + totalPositionNum + ", currentPrice=" + currentPrice
				+ ", cost=" + cost + ", state=" + state + ", name=" + name + ", location=" + location
				+ ", parkingPositions=" + (parkingPositions!=null?parkingPositions.hashCode():null) + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		long temp;
		temp = Double.doubleToLongBits(currentPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + totalPositionNum;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ParkingLot))
			return false;
		ParkingLot other = (ParkingLot) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (Double.doubleToLongBits(currentPrice) != Double.doubleToLongBits(other.currentPrice))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (state != other.state)
			return false;
		if (totalPositionNum != other.totalPositionNum)
			return false;
		return true;
	}
	
	
	

}
