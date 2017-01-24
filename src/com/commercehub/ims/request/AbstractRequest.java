package com.commercehub.ims.request;

import com.commercehub.ims.InventoryManagementSystem;
import com.commercehub.ims.result.IResult;
/**
 * Abstract class representiing a request in the request queue.
 * @author Praveen
 *
 */
public abstract class AbstractRequest implements IRequest {
	InventoryManagementSystem system;
	
	public AbstractRequest(InventoryManagementSystem system){
		this.system = system;
	}
	
	public abstract IResult processRequest();	
}
