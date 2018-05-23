package com.park.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.ssm.dao.InnerUserDao;
import com.park.ssm.entity.InnerUser;
import com.park.ssm.service.InnerUserService;

@Service("innerUserService")
public class InnerUserServiceImpl implements InnerUserService {
	@Autowired
	private InnerUserDao innerUserDao;

	/**
	 * 根据昵称查询员工
	 */
	@Override
	public InnerUser findInnerUserByNickname(String nickname) {
		// TODO Auto-generated method stu
		return innerUserDao.getInnerUserByNickname(nickname);
	}

	/**
	 * 如果插入成功，则返回插入的记录数。否则返回0
	 */
	@Override
	public int insertInnerUser(InnerUser innerUser) {
		// TODO Auto-generated method stub
		try {
			int result = innerUserDao.addInnerUser(innerUser);
			if (result > 0) {
				return result;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return -1;
		}

	}

	/**
	 * 登陆时调用这个方法，昵称和用户名匹配才能登陆
	 */
	@Override
	public InnerUser findInnerUser(String nickname, String password, int typeflag) {
		// TODO Auto-generated method stub
		return innerUserDao.getInnerUser(nickname, password, typeflag);
	}

	/**
	 * // 如果删除成功，返回记录数，否则，返回0
	 */
	@Override
	public int dropInnerUserByNickname(String nickname) {
		// TODO Auto-generated method stub
		try {
			int result = innerUserDao.deleteInnerUserByNickname(nickname);
			if (result > 0) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {

			return -1;
		}

	}

	/**
	 * 修改成功则返回修改的记录总数，否则返回0
	 */
	@Override
	public int changeInnerUserByNickname(InnerUser innerUser) {
		// TODO Auto-generated method stub
		try {
			int result = innerUserDao.updateInnerUserByNickname(innerUser);
			if (result > 0) {
				return result;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 根据typeflag查找InnerUser
	 */
	@Override
	public List<InnerUser> findInnerUserByTypeflag() {
		// TODO Auto-generated method stub
		return innerUserDao.getInnerUserByTypeflag();
	}

	@Override
	public String findSaltByNickname(String nickname) {
		// TODO Auto-generated method stub
		return innerUserDao.getSaltByNickname(nickname);
	}

	
}
