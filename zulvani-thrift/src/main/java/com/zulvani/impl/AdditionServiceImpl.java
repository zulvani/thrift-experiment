package com.zulvani.impl;

import org.apache.thrift.TException;

import com.zulvani.AdditionService;

public class AdditionServiceImpl implements AdditionService.Iface {

	@Override
	public int add(int n1, int n2) throws TException {
		return n1 + n2;
	}
}
