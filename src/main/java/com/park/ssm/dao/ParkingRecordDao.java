package com.park.ssm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.park.ssm.entity.ParkingRecord;

@MapperScan
public interface ParkingRecordDao {
	/**
	 * 查看使用量
	 * @param lotId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ParkingRecord> countUsage(@Param("lotId") Integer lotId, @Param("startTime") Date startTime,
			@Param("endTime") Date endTime);
}
