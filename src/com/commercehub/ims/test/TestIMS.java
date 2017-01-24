package com.commercehub.ims.test;

import java.util.concurrent.ConcurrentHashMap;
import com.commercehub.ims.SynchronizedInventoryManagementSystem;
import com.commercehub.ims.database.IMSDatabase;
import com.commercehub.ims.request.processor.RequestProcessor;
import com.commercehub.ims.request.queue.RequestQueue;
/**
 * Some tests.
 * Again, didnt have time to incorporate junit.
 * @author Praveen
 *
 */
class ThreadingTest implements Runnable{
	Thread t = null;
	RequestQueue queue;
	int i;
	
	public ThreadingTest(RequestQueue queue, int i) {
		this.queue = queue;
		this.i = i;
	}
	@Override
	public void run() {
		queue.addPickRequest("P1", i);
	}
	
	public void start(){
		t = new Thread(this);
		t.start();
	}
}

public class TestIMS {
	
	public static void main(String[] args){
		
		ConcurrentHashMap<String, Integer> products = new ConcurrentHashMap<String, Integer>();
		for(int i = 0; i < 10; i++){
			products.put("P" + String.valueOf(i), i * 20);
		}
		IMSDatabase database = new IMSDatabase(products);
		SynchronizedInventoryManagementSystem system = new SynchronizedInventoryManagementSystem(database);
		RequestQueue requestQueue = new RequestQueue(system);
		for(int i =0; i < 2; i++){
			RequestProcessor processor = new RequestProcessor(requestQueue);
			processor.start();
		}
		
		//sanity check
		requestQueue.addPickRequest("P1", 10);
		requestQueue.addRestockRequest("P1", 30);
		
		
		//Test invalid arguments
		requestQueue.addPickRequest("ABC", 10);
		requestQueue.addPickRequest("P1", -10);
		
		//Test checked exceptions
		requestQueue.addPickRequest("P1", 1000);
		
		//Add 100 items of P1
		requestQueue.addRestockRequest("P1", 100);
		
		//Threading test
		for(int i = 0; i < 10; i++){
			ThreadingTest test = new ThreadingTest(requestQueue, i + 10);
			test.start();
		}

	}
	
}
