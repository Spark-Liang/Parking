package com.park.ssm.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.park.ssm.dao.InnerUserDao;
import com.park.ssm.entity.InnerUser;

public class InnerUserServiceImpl implements InnerUserService {
	@Autowired
	private InnerUserDao innerUserDao;

	@Override
	public InnerUser findInnerUserByNickname(String nickname) {
		// TODO Auto-generated method stub
		return innerUserDao.getInnerUserByNickname(nickname);
	}

	@Override
	public int insertInnerUser(InnerUser innerUser) {
		// TODO Auto-generated method stub
		int result=innerUserDao.addInnerUser(innerUser);
		if(result>0) {
			return result;
		}else {
			return 0;
		}
		
	}

	@Override
	public InnerUser findInnerUser(String nickname, String password) {
		// TODO Auto-generated method stub
		return innerUserDao.getInnerUser(nickname, password);
	}

	@Override
	public int dropInnerUserByNickname(String nickname) {
		// TODO Auto-generated method stub
		int result=innerUserDao.deleteInnerUserByNickname(nickname);
		if(result>0) {
			return 1;
		}else {
			return 0;
		}
		
	}

	@Override
	public int changeInnerUserByNickname(InnerUser innerUser, String nickname) {
		// TODO Auto-generated method stub
		int result=innerUserDao.updateInnerUserByNickname(innerUser, nickname);
		if(result>0) {
			return result;
		}else {
			return 0;
		}

	}

}
