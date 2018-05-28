package com.park.ssm.service;

public interface ParkTerminalService {
	
	
	/**
	 * 处理对应停车卡在停车场的停车请求，并返回失败原因
	 * @param parkingLotId
	 * @param cardId
	 * @return reason(String)： null代表停车成功，not null 代表失败并返回提示信息
	 */
	public abstract String park(Integer parkingLotId,Long cardId);
	
	public abstract String pick(Integer parkingLotId,Long cardId);
	
}
