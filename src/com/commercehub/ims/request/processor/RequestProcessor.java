package com.commercehub.ims.request.processor;

import java.util.Queue;

import com.commercehub.ims.request.IRequest;
import com.commercehub.ims.request.queue.RequestQueue;
import com.commercehub.ims.result.IResult;
/**
 * Thread class which process requests. 
 * The threads run infinitely, waiting on queue as long as it is empty.
 * Once a request has been put into queue, threads are notified to start workers.
 * @author Praveen
 *
 */
public class RequestProcessor implements Runnable {
	Thread worker = null;
	private RequestQueue requestQueue;
	
	public RequestProcessor(RequestQueue requestQueue) {
		this.requestQueue = requestQueue;
	}

	public void start(){
		worker = new Thread(this);
		worker.start();
	}
	
	/**
	 * waits till the queue is empty.
	 * Removes the request from the queue and processes it.
	 */
	@Override
	public void run() {
		Queue<IRequest> queue = this.requestQueue.getRequestQueue();
		while(true){
			synchronized(queue){
				while(queue.isEmpty()){
					try{
						queue.wait();
					} catch(InterruptedException exception){
						
					}
				}
				
				IRequest request = queue.poll();
				IResult result = null;
				try{
					result = request.processRequest();
				} catch(IllegalArgumentException iae){
					System.out.println(iae.getMessage());
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
				
				if(result != null)
					System.out.println(result.toString());
			}
		}
		
	}

}
