package com.park.ssm.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.park.ssm.dao.ParkingLotDao;
import com.park.ssm.dao.ParkingLotDao.CONDITION;
import com.park.ssm.dao.ParkingPositionDao;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.entity.ParkingPosition;
import com.park.ssm.entity.type.ParkingLotState;
import com.park.ssm.service.ParkingLotService;
import com.park.ssm.util.PersistentUtil;


@Service
public class ParkingLotServiceImpl implements ParkingLotService {
	@Autowired
	private ParkingLotDao parkingLotDao;
	@Autowired
	private ParkingPositionDao parkingPositionDao;
	
	private Logger logger=LogManager.getLogger(this.getClass());
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveParkingLot(ParkingLot parkingLot){
		ParkingLot parkingLotInDB=parkingLotDao.loadParkingLotByName(parkingLot.getName());
		if(parkingLotInDB!=null) {
			throw new RuntimeException("数据库内部已经存在同名的停车场");
		}
		
		if(1==parkingLotDao.insertParkingLot(parkingLot)) {
			Integer totalPositionNum=parkingLot.getTotalPositionNum();
			Integer id=parkingLot.getId();
			List<Integer> ids=new LinkedList<>();
			for(int i=0;i<totalPositionNum;i++) {
				ids.add(id);
			}
			if(totalPositionNum==parkingPositionDao.insertParkingPosition(ids)) {
				return;
			}
		}
		throw new RuntimeException("数据库内部添加停车场事务异常，停车位更新失败");
	}
	
	@Override
	public boolean isExistingName(String name) {
		// TODO Auto-generated method stub
		return null==parkingLotDao.loadParkingLotByName(name);
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	@Override
	public List<ParkingLot> listParkingLot(Map<String, Object> conditions, Integer pageNum, Integer pageSize,boolean withPosition) {
		// TODO Auto-generated method stub
		
		conditions.put(CONDITION.STATES.getName(), new LinkedList() {
			{add(ParkingLotState.ACTIVE);}
		});
		if(pageNum==null) {
			pageNum=1;
		}
		if(pageSize==null) {
			pageSize=20;
		}
		List<ParkingLot> resList=null;
		List<ParkingLot> ListInDB=parkingLotDao.listParkingLot(conditions, pageNum, pageSize);
		if(withPosition==false) {
			resList=new ArrayList<>(ListInDB.size());
			try {
				for(ParkingLot parkingLot:ListInDB) {
					ParkingLot tmpParking=new ParkingLot();
					PersistentUtil.merge(tmpParking, parkingLot, ParkingLot.class);
					resList.add(tmpParking);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.info(e);
			}
		}else {
			resList=ListInDB;
		}
		return resList;
	}
	
	@Override
	public int countParkingLot(Map<String, Object> conditions) {
		// TODO Auto-generated method stub
		return parkingLotDao.countParkingLot(conditions);
	}

	@Override
	@Transactional
	public void updateParkingLot(ParkingLot parkingLot){
		// TODO Auto-generated method stub
		if(1!=parkingLotDao.updateParkingLot(parkingLot)) {
			throw new RuntimeException("数据库内部更新失败");
		}
	}

	
	
	@Override
	@Transactional
	public void deleteParkingLot(Integer lotId){
		ParkingLot parkingLot=parkingLotDao.loadParkingLotByIdForUpdate(lotId);
		if(parkingLot.getState().equals(ParkingLotState.INACTIVE)) {
			throw new RuntimeException("删除一个不存在的停车场");
		}
		List<ParkingPosition> parkingPositions=parkingLot.getParkingPositions();
		for(ParkingPosition parkingPosition:parkingPositions) {
			if(parkingPosition.getAccountId()!=null) {
				throw new RuntimeException("停车场中还存在正在使用的停车卡，不能进行删除");
			}
		}
		
		int totalPosition=parkingLot.getTotalPositionNum();
		if(1==parkingLotDao.deleteParkingLot(parkingLot.getId())) {
			if(totalPosition==parkingPositionDao.deleteParkingPositionByLotId(parkingLot.getId())) {
				return;
			}
		}else {
			//抛出异常并自动回滚
			throw new RuntimeException("内部数据库错误，删除事务不完整，删除失败");
		}
		
	}

	@Override
	@Transactional
	public List<Integer> listDeleteParkingLot(List<Integer> ids) {
		List<Integer> resList=new LinkedList<>();
		for(Integer id:ids) {
			deleteParkingLot(id);
		}
		return resList;
	}


	@SuppressWarnings("unused")
	private void logException(Object message) {
		if(logger!=null) {
			logger.info(message);
		}
	}


}
