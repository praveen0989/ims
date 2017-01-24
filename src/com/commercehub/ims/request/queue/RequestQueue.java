package com.commercehub.ims.request.queue;

import java.util.LinkedList;
import java.util.Queue;

import com.commercehub.ims.InventoryManagementSystem;
import com.commercehub.ims.request.IRequest;
import com.commercehub.ims.request.PickingRequest;
import com.commercehub.ims.request.RestockingRequest;
/**
 * Queue class containing the actual request queue.
 * It has two methods to add different types of request objects.
 * This is not quite extendible as I am running out of time before deadline.
 * Again, a single addRequest method could have been better.
 * @author Praveen
 *
 */
public class RequestQueue {
	
	private  Queue<IRequest> requestQueue = null;
	private InventoryManagementSystem system = null;
	
	public RequestQueue(InventoryManagementSystem system){
		this.system = system;
		this.requestQueue = new LinkedList<IRequest>();
	}
	
	public Queue<IRequest> getRequestQueue(){
		return requestQueue;
	}
	
	public void setInventoryManagementSystem(InventoryManagementSystem system){
		this.system = system;
	}
	
	public void addPickRequest(String productId, int amountToPick){
		IRequest request = new PickingRequest(productId, amountToPick, system);
		synchronized (requestQueue) {
			requestQueue.add(request);
			requestQueue.notify();
		}
		
	}
	
	public void addRestockRequest(String productId, int amountToPick){
		IRequest request = new RestockingRequest(productId, amountToPick, system);
		synchronized (requestQueue) {
			requestQueue.add(request);
			requestQueue.notify();
		}
	}
}
