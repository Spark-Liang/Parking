package com.park.ssm.entity;

import java.util.Set;

import com.park.ssm.entity.type.ParkingLotState;

public class ParkingLot {
	private Integer id;
	private String name;
	private String location;
	private Double cost;
	private ParkingLotState state;
	private Set<ParkingPosition> parkingPositions;
	
	public ParkingLot() {}
	
	

	public Integer getId() {
		return id;
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
	public Set<ParkingPosition> getParkingPositions() {
		return parkingPositions;
	}
	public void setParkingPositions(Set<ParkingPosition> parkingPositions) {
		this.parkingPositions = parkingPositions;
	}

	@Override
	public String toString() {
		return "ParkingLot [id=" + id + ", name=" + name + ", location=" + location + ", cost=" + cost + ", state="
				+ state + ", parkingPositions=" + parkingPositions + "]";
	}

}
