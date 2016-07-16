package com.zulvani.impl;

import org.apache.thrift.TException;
import com.zulvani.MultiplyService;;

public class MultiplyServiceImpl implements MultiplyService.Iface {

	@Override
	public int multiply(int n1, int n2) throws TException {
		return n1 * n2;
	}
}