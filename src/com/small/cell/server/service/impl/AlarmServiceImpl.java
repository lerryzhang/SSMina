package com.small.cell.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.small.cell.server.dao.AlarmDao;

import com.small.cell.server.pojo.Alarm;

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

	@Override
	public void saveAlarm(Alarm alarm) {
		// TODO Auto-generated method stub
		alarmDao.saveAlarm(alarm);
	}

	@Override
	public List<Alarm> listAlarm(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return alarmDao.listAlarm(pageNo, pageSize);
	}

	@Override
	public int selectAlarmCount() {
		// TODO Auto-generated method stub
		return alarmDao.selectAlarmCount();
	}

}
