package com.park.ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.ssm.dao.InnerUserDao;
import com.park.ssm.entity.InnerUser;

@Service
public class InnerUserServiceImpl implements InnerUserService {
	@Autowired
	private InnerUserDao innerUserDao;

	@Override
	public InnerUser findInnerUserByNickname(String nickname) {//根据昵称查询员工
		// TODO Auto-generated method stub
		return innerUserDao.getInnerUserByNickname(nickname);
	}

	@Override
	public int insertInnerUser(InnerUser innerUser) {//如果插入成功，则返回插入的记录数。否则返回0
		// TODO Auto-generated method stub
		int result=innerUserDao.addInnerUser(innerUser);
		if(result>0) {
			return result;
		}else {
			return 0;
		}
		
	}

	@Override
	public InnerUser findInnerUser(String nickname, String password) {//登陆时调用这个方法，昵称和用户名匹配才能登陆
		// TODO Auto-generated method stub
		return innerUserDao.getInnerUser(nickname, password);
	}

	@Override
	public int dropInnerUserByNickname(String nickname) {//如果删除成功，返回记录数，否则，返回0
		// TODO Auto-generated method stub
		int result=innerUserDao.deleteInnerUserByNickname(nickname);
		if(result>0) {
			return 1;
		}else {
			return 0;
		}
		
	}

	@Override
	public int changeInnerUserByNickname(InnerUser innerUser, String nickname) {//修改成功则返回修改的记录总数，否则返回0
		// TODO Auto-generated method stub
		int result=innerUserDao.updateInnerUserByNickname(innerUser, nickname);
		if(result>0) {
			return result;
		}else {
			return 0;
		}

	}

}
