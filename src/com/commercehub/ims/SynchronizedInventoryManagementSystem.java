package com.commercehub.ims;

import com.commercehub.ims.database.IMSDatabase;
import com.commercehub.ims.exception.InsufficientStockException;
import com.commercehub.ims.result.PickingResult;
import com.commercehub.ims.result.RestockingResult;

/**
 *Implementation of the core interface. 
 * This class provides a synchronized implementation for the methods.
 * */

public class SynchronizedInventoryManagementSystem implements
		InventoryManagementSystem {
	
	private IMSDatabase database;
	
	public SynchronizedInventoryManagementSystem(IMSDatabase database){
		this.database = database;
	}
	
	/**
	 * Picks product from the inventory. This is basically a synchronized call.
	 * This eventually calls the atomic operation.
	 * @param productId ID of the product to be picked
	 * @param amountToPick Amount of product to be picked
	 * 
	 * */
	@Override
	public synchronized PickingResult pickProduct(String productId, int amountToPick) {
		PickingResult result = null;
		try{
			result = this.database.atomicPickProduct(productId, amountToPick);
		} catch(InsufficientStockException exception){
			System.out.println(exception.getMessage());
		}
		return result;
	}

	/**
	 * Restores product to the inventory. This is basically a synchronized call.
	 * This eventually calls the atomic operation.
	 * @param productId ID of the product to be re-stocked
	 * @param amountToPick Amount of product to be re-stocked
	 * 
	 * */
	@Override
	public synchronized RestockingResult restockProduct(String productId, int amountToRestock) {
		RestockingResult result = null;
		result = this.database.atomicRestockProduct(productId, amountToRestock);
		return result;
	}

}
