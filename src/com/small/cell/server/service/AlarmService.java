package com.small.cell.server.service;

import java.util.List;

import com.small.cell.server.pojo.Alarm;


public interface AlarmService {
	public int selectAlarmCountByDay(String ptime);
	 public void saveAlarm(Alarm alarm);
	 
	 public List<Alarm> listAlarm(int pageNo,int pageSize);
	 
	 public int selectAlarmCount();
}
