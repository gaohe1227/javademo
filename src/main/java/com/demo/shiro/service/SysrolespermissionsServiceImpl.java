package com.demo.shiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.BaseDao;
import com.demo.service.BaseServiceImpl;
import com.demo.shiro.dao.SysrolespermissionsDao;
import com.demo.shiro.entity.Syspermissions;
@Service
public class SysrolespermissionsServiceImpl extends BaseServiceImpl<Syspermissions> implements  SysrolespermissionsService{
	@Autowired
	private SysrolespermissionsDao sysrolespermissionsdao;
	/**
	*方法描述：初始化dao，覆盖BaseServiceImpl类中的泛型dao
	*创建人：mail.zhangyong@gmail.com
	*创建时间：2016年6月23日 10:44:18
	**/
	@Override
	public BaseDao<Syspermissions> getBaseDao(){
		return sysrolespermissionsdao;
	}
}
