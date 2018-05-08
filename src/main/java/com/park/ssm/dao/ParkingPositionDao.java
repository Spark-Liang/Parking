package com.park.ssm.dao;

import java.util.*;

import com.park.ssm.entity.ParkingPosition;

public interface ParkingPositionDao {
	
	public abstract List<ParkingPosition> loadParkPositionByLotId(int ParkingLotId);
}
