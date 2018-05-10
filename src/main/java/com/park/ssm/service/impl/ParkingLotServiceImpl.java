package com.park.ssm.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.park.ssm.dao.ParkingLotDao;
import com.park.ssm.dao.ParkingPositionDao;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.service.ParkingLotService;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
	@Autowired
	private ParkingLotDao parkingLotDao;
	@Autowired
	private ParkingPositionDao parkingPositionDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean saveParkingLot(ParkingLot parkingLot) {
		if(1==parkingLotDao.insertParkingLot(parkingLot)) {
			Integer totalPositionNum=parkingLot.getTotalPositionNum();
			Integer id=parkingLot.getId();
			List<Integer> ids=new LinkedList<>();
			for(int i=0;i<totalPositionNum;i++) {
				ids.add(id);
			}
			if(totalPositionNum==parkingPositionDao.insertParkingPosition(ids)) {
				return true;
			}
		}
		throw new RuntimeException("error in saveParkingLot");
	}
		
	

	@Override
	public boolean deleteParkingLot(ParkingLot parkingLot) {
		return 1==parkingLotDao.deleteParkingLot(parkingLot.getId());
	}

	@Override
	public List<ParkingLot> listDeleteParkingLot(List<ParkingLot> parkingLots) {
		ArrayList<Integer> ids=new ArrayList<>(parkingLots.size());
		for(ParkingLot parkingLot:parkingLots) {
			ids.add(parkingLot.getId());
		}
		if(parkingLots.size()==parkingLotDao.listDeleteParkingLot(ids)) {
			return null;
		}else {
			return parkingLotDao.listActiveLotByIdList(ids);
		}
	}

}
