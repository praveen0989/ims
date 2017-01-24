package com.commercehub.ims.request;

import com.commercehub.ims.InventoryManagementSystem;
import com.commercehub.ims.result.RestockingResult;
/***
 * This is a request of type restock.
 * Again, this is a separate class because it allows us to add new features.
 * @author Praveen
 *
 */
public class RestockingRequest extends AbstractRequest {
	String productId;
	int amountToRestock;
	
	public RestockingRequest(String productId, int amountToRestock, InventoryManagementSystem system) {
		super(system);
		this.productId = productId;
		this.amountToRestock = amountToRestock;
	}

	@Override
	public RestockingResult processRequest() {
		return system.restockProduct(productId, amountToRestock);
	}

}
