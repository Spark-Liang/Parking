package com.park.ssm.service.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.park.ssm.dao.ParkingLotDao;
import com.park.ssm.dao.ParkingLotDao.CONDITION;
import com.park.ssm.dao.ParkingPositionDao;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.entity.type.ParkingLotState;
import com.park.ssm.service.ParkingLotService;
import com.park.ssm.util.PersistentUtil;


@Service
public class ParkingLotServiceImpl implements ParkingLotService {
	@Autowired
	private ParkingLotDao parkingLotDao;
	@Autowired
	private ParkingPositionDao parkingPositionDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveParkingLot(ParkingLot parkingLot) throws SQLException{
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
		throw new SQLException("error in saveParkingLot.");
	}
	
	@Override
	public boolean isExistingName(String name) {
		// TODO Auto-generated method stub
		return null==parkingLotDao.loadParkingLotByName(name);
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	@Override
	public List<ParkingLot> listParkingLot(Map<String, Object> conditions, Integer pageNum, Integer pageSize) {
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
		try {
			resList=parkingLotDao.listParkingLot(conditions, pageNum, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resList;
	}
	

	@Override
	@Transactional
	public void updateParkingLot(ParkingLot parkingLot) throws SQLException {	
		//加载数据库中的parkingLot，替换不同的属性后更新
		ParkingLot parkingLotInDB=parkingLotDao.loadParkingLotByIdForUpdate(parkingLot.getId());
		Map<String, Object> different=null;
		try {
			different=PersistentUtil.different(parkingLotInDB, parkingLot);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new SQLException(e);
		}
		//更新属性到数据库
		if(different!=null) {
			if(1!=parkingLotDao.updateParkingLot(different)) {
				throw new SQLException("update ParkingLot failed.ParkingLot :"+parkingLot);
			}
		}else {
			//把数据库中的数据合并到parkingLot中返回到上一层
			try {
				PersistentUtil.merge(parkingLot, parkingLotInDB);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
	
	@Override
	@Transactional
	public void deleteParkingLot(Integer lotId) throws SQLException{
		
		ParkingLot parkingLot=parkingLotDao.loadParkingLotByIdForUpdate(lotId);

		int totalPosition=parkingLot.getTotalPositionNum();
		if(1==parkingLotDao.deleteParkingLot(parkingLot.getId())) {
			if(totalPosition==parkingPositionDao.deleteParkingPositionByLotId(parkingLot.getId())) {
				return;
			}
		}
		//抛出异常并自动回滚
		throw new SQLException("error in deleteParkingLot. Lotid is "+lotId);
	}

	@Override
	public List<Integer> listDeleteParkingLot(List<Integer> ids) {
		List<Integer> resList=new LinkedList<>();
		for(Integer id:ids) {
			try {
				deleteParkingLot(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				resList.add(id);
				e.printStackTrace();
			}
		}
		return resList;
	}


}
