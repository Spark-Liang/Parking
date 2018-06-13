package com.park.ssm.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.park.ssm.dao.AccountDao;
import com.park.ssm.dao.BillDao;
import com.park.ssm.dao.ParkingPositionDao;
import com.park.ssm.entity.Account;
import com.park.ssm.entity.Bill;
import com.park.ssm.entity.ParkingPosition;
import com.park.ssm.entity.type.ParkingPositionState;
import com.park.ssm.service.BillService;

/**
 * billService实现类
 * @author ASNPHX4
 *
 */
@Service("billService")
public class BillServiceImpl implements BillService {
	@Autowired
	private BillDao billDao;
	@Autowired
	private ParkingPositionDao parkingPositionDao;
	@Autowired
	private AccountDao accountDao;
	
	@Override
	public List<Bill> listBillById(Long userId,Long accountId,Integer lotId,Integer pageNum,Integer pageSize) {
		// TODO Auto-generated method stub
		//System.out.println("-----------------------"+billDao.listBillById(userId, accountId, lotId, pageNum, pageSize));
		return billDao.listBillById(userId, accountId, lotId, pageNum, pageSize);
	}
	
	@Override
	public int insertBill(Bill bill) {
		// TODO Auto-generated method stub
		int result=0;
		try {
			result=billDao.addBill(bill);
		}catch(Exception e) {
			result=0;
		}
		return result;
	}

	@Override
	@Transactional
	public int normalPayBill(Bill bill) {
		// TODO Auto-generated method stub
		StringBuilder errorMessage=new StringBuilder();
		int result=billDao.updateIsPaid(bill);
		if(result>0) {
			return result;
		}else {
			errorMessage.append("支付失败,请重试");
			throw new RuntimeException(errorMessage.toString());
		}
	}

	@Override
	@Transactional
	public Long bookPositionBeforePayBill(Long accountId) {
		Map<String,Object> conditions=new HashMap<>();
		Account account=accountDao.loadAccountByIdForUpdate(accountId);
		if(account==null) {
			throw new RuntimeException("输入错误：没有找到对应的账号，请输入对应的卡号!");
		}
		conditions.put("parkingLotId",account.getParkingLot().getId());
		List<ParkingPositionState> states=new LinkedList<>();
		states.add(ParkingPositionState.UNOCCUPIED);
		conditions.put("state", states);
		List<ParkingPosition> parkingPositions=parkingPositionDao.listParkingPosition(conditions, null, null);
		//检查是否有空停车位
		RuntimeException exception=new RuntimeException("错误提示该账号对应的停车场没有对应的停车位!");
		if(parkingPositions == null || parkingPositions.isEmpty()) {
			throw exception;
		}
		Long bookedPositionId=null;
		ParkingPosition parkingPositionInDB=null;
		for(ParkingPosition parkingPosition:parkingPositions) {
			bookedPositionId=parkingPosition.getParkingLotId();
			parkingPositionInDB=parkingPositionDao.loadParkingPositionByIdForUpdate(bookedPositionId);
			if(parkingPositionInDB.getState().equals(ParkingPositionState.UNOCCUPIED)) {
				break;
			}else {
				bookedPositionId=null;
			}
		}
		if(bookedPositionId==null) {
			throw exception;
		}
		//找到对应停车位之后占用停车位
		parkingPositionInDB.setState(ParkingPositionState.ReadyForOCCUPIED);
		parkingPositionDao.updateParkingPosition(bookedPositionId, parkingPositionInDB);
		return bookedPositionId;
	}

	@Override
	public void unBookPosition(Long parkingPositonId) {
		// TODO Auto-generated method stub
		ParkingPosition parkingPositionInDB=parkingPositionDao.loadParkingPositionByIdForUpdate(parkingPositonId);
		parkingPositionInDB.setState(ParkingPositionState.UNOCCUPIED);
		parkingPositionDao.updateParkingPosition(parkingPositonId, parkingPositionInDB);
	}

	@Override
	public void finishBillPaying(Long accountId, Long parkingPositionId) {
		// TODO Auto-generated method stub
		Map<String,Object> result=new HashMap<>();
		result.put("positionId",parkingPositionId);
		result.put("accountId", accountId);
		accountDao.resumeCard(result);
		Integer flag=(Integer) result.get("flag");
		if(flag != 0) {
			throw new RuntimeException("系统内部错误");
		}
	}
}
