package com.zulvani.server;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

import com.zulvani.AdditionService;
import com.zulvani.MultiplyService;
import com.zulvani.impl.AdditionServiceImpl;
import com.zulvani.impl.MultiplyServiceImpl;

/**
 * Thanks for:
 * http://stackoverflow.com/questions/19614648/service-multiplexing-using-apache-thrift
 * 
 * 
 */
public class ThriftServer {

	public static void StartMultiplexedProcessor(){
		TMultiplexedProcessor processor = new TMultiplexedProcessor();
		processor.registerProcessor("AdditionService",
				new AdditionService.Processor<AdditionServiceImpl>(new AdditionServiceImpl()));
		processor.registerProcessor("MultiplyService",
				new MultiplyService.Processor<MultiplyServiceImpl>(new MultiplyServiceImpl()));
		
		try {
			TServerTransport serverTransport = new TServerSocket(9090);
			TTransportFactory factory = new TFramedTransport.Factory();
			TServer.Args args = new TServer.Args(serverTransport);
			args.processor(processor);
			args.transportFactory(factory);
			TServer server = new TSimpleServer(args);
			
			System.out.println("Starting the simple server with multiplexing processor...");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
	
	public static void StartsimpleServer(AdditionService.Processor<AdditionServiceImpl> processor) {
		try {
			TServerTransport serverTransport = new TServerSocket(9090);
			TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

			// Use this for a multithreaded server
			// TServer server = new TThreadPoolServer(new
			// TThreadPoolServer.Args(serverTransport).processor(processor));

			System.out.println("Starting the simple server...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		StartsimpleServer(new AdditionService.Processor<AdditionServiceImpl>(new AdditionServiceImpl()));
		StartMultiplexedProcessor();
	}

}
