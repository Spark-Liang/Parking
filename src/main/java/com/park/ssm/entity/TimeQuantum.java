package com.park.ssm.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 时间段类，startTime表示开始时间，endTime表示结束时间
 * @author ASNPHXJ
 *
 */
@JsonIgnoreProperties(value= {"handler","serialVersionUID"},ignoreUnknown = true)
public class TimeQuantum implements Comparable<TimeQuantum> {
	@DateTimeFormat(pattern="YYYY-MM-DD")
	private Date startTime;
	@DateTimeFormat(pattern="YYYY-MM-DD")
	private Date endTime;
	
	public Date getStartTime() {
		return startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	
	@Override
	public String toString() {
		return "TimeQuantum [startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
	@Override
	public int compareTo(TimeQuantum o) {
		// TODO Auto-generated method stub
		return startTime.compareTo(o.getStartTime());
	}
	
	
}
