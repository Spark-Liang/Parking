package com.park.ssm.entity;

import java.util.Set;

import com.park.ssm.entity.type.ParkingLotState;

public class ParkingLot {
	private Integer id;
	//业务相关
	private int totalPositionNum;
	private double currentPrice;
	private ParkingLotState state;
	
	//业务无关的详细信息
	private String name;
	private String location;
	private Double cost;
	private Set<ParkingPosition> parkingPositions;
	
	public ParkingLot() {}
	public ParkingLot(int id) {
		this.id=id;
	}
	

	
	public Integer getId() {
		return id;
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
	public Set<ParkingPosition> getParkingPositions() {
		return parkingPositions;
	}
	public void setParkingPositions(Set<ParkingPosition> parkingPositions) {
		for(ParkingPosition tmp:parkingPositions) {
			if(tmp.getParkingLot()==null) {
				tmp.setParkingLot(this);
			}
		}
		this.parkingPositions = parkingPositions;
	}
	
	@Override
	public String toString() {
		return "ParkingLot [id=" + id + ", totalPositionNum=" + totalPositionNum + ", currentPrice=" + currentPrice
				+ ", cost=" + cost + ", state=" + state + ", name=" + name + ", location=" + location
				+ ", parkingPositions=" + parkingPositions.hashCode() + "]";
	}

}
