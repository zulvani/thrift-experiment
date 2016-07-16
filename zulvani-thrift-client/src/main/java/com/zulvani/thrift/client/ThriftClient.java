package com.zulvani.thrift.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

import com.zulvani.AdditionService;
import com.zulvani.MultiplyService;

public class ThriftClient {

	public static void main(String[] args) {

		try {
			TFramedTransport transport;

			transport = new TFramedTransport(new TSocket("localhost", 9090));
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);

			TMultiplexedProtocol mp = new TMultiplexedProtocol(protocol, "AdditionService");
			AdditionService.Client client = new AdditionService.Client(mp);
			
			TMultiplexedProtocol mpMultiply = new TMultiplexedProtocol(protocol, "MultiplyService");
			MultiplyService.Client clientMultiply = new MultiplyService.Client(mpMultiply);
			
			System.out.println(client.add(100, 200));
			System.out.println(clientMultiply.multiply(100, 200));

			transport.close();
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException x) {
			x.printStackTrace();
		}
	}

}
