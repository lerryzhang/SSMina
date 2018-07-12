package com.small.cell.server.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.small.cell.server.dao.AlarmDao;

import com.small.cell.server.service.AlarmService;

@Service("alarmService")
public class AlarmServiceImpl implements AlarmService {

	@Resource
	public AlarmDao alarmDao;

	@Override
	public int selectAlarmCountByDay(String ptime) {
		// TODO Auto-generated method stub
		return alarmDao.selectAlarmCountByDay(ptime);
	}

}
