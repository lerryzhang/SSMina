package com.small.cell.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.small.cell.server.pojo.Alarm;

public interface AlarmDao {

	int selectAlarmCountByDay(String ptime);

	void saveAlarm(Alarm alarm);

	int selectAlarmCount();

	List<Alarm> listAlarm(@Param("pageNo") int pageNo,
			@Param("pageSize") int pageSize);
}
