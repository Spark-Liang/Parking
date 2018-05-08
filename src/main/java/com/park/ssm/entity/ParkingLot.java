package com.park.ssm.entity;

import java.util.Set;

public class ParkingLot {
	private Integer id;
	private String name;
	private String location;
	private Double cost;
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
	public String getlocation() {
		return location;
	}
	public void setlocation(String location) {
		this.location = location;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Set<ParkingPosition> getlocations() {
		return parkingPositions;
	}
	public void setLocations(Set<ParkingPosition> parkingPositions) {
		this.parkingPositions = parkingPositions;
	}

	@Override
	public String toString() {
		return "ParkingLot [id=" + id + ", name=" + name + ", location=" + location + ", cost=" + cost + ", parkingPositions="
				+ parkingPositions + "]";
	}
	
	
	
}
