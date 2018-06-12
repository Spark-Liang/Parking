package com.park.ssm.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.park.ssm.entity.type.AccountState;

/**
 * 账号状态日志的Bean
 * @author ASNPHXJ
 *
 */
public class AccountStateLog {
	private Long id;
	private Long accountId;
	private AccountState state;
	@DateTimeFormat(pattern="YYYY-MM-DD")
	private Date startTime;
	@DateTimeFormat(pattern="YYYY-MM-DD")
	private Date endTime;
	private Long billId;
	public Long getId() {
		return id;
	}
	public Long getAccountId() {
		return accountId;
	}
	public AccountState getState() {
		return state;
	}
	public Date getStartTime() {
		return startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public Long getBillId() {
		return billId;
	}
	
	@Override
	public String toString() {
		return "AccountStateLog [id=" + id + ", accountId=" + accountId + ", state=" + state + ", startTime="
				+ startTime + ", endTime=" + endTime + ", billId=" + billId + "]";
	}
	
	
	
}
