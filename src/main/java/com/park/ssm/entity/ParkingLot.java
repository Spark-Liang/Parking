package com.park.ssm.entity;

import java.util.Set;

import com.park.ssm.annotation.UnEditableField;
import com.park.ssm.entity.type.ParkingLotState;

/**
 * @param 
 * <ul> 
 * <li>id 停车场id
 * <p>业务相关属性 :
 * <li> totalPositionNum 停车场总停车位数目（不可修改）
 * <li> currentPrice 当前停车场停车费用
 * <li> state 当前停车场状态 ,包含ACTIVE和INACTIVE
 * <p>业务无关的详细信息:
 * <li> name 停车场名称（不能重复）
 * <li> location 停车场位置
 * <li> cost 停车场成本
 * <p>其他信息:
 * <li> parkingPositions 停车场对应停车位的引用
 * @author ASNPHXJ
 * 
 *
 */
public class ParkingLot {
	@UnEditableField
	private Integer id;
	//业务相关
	@UnEditableField
	private int totalPositionNum;
	private double currentPrice;
	private ParkingLotState state;
	
	//业务无关的详细信息
	private String name;
	private String location;
	private Double cost;
	private Set<ParkingPosition> parkingPositions;
	
	public ParkingLot() {}
	public ParkingLot(Integer id,int totalPositionNum) {
		this.id=id;
		this.totalPositionNum=totalPositionNum;
	}
	

	
	public Integer getId() {
		return id;
	}
	public int getTotalPositionNum() {
		return totalPositionNum;
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
