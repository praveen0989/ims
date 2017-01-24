package com.commercehub.ims.database;

import com.commercehub.ims.exception.InsufficientStockException;
import com.commercehub.ims.result.PickingResult;
import com.commercehub.ims.result.RestockingResult;

/***
 * Representation of the Database functions to be carried out.
 * I have created another interface to separate out database interaction from app logic.
 * This looks like a duplicate but can be used to handle exceptions better.
 * Also, this can be populated with more methods to interact with database. 
 * @author Praveen
 *
 */
public interface IDatabase {
	public PickingResult atomicPickProduct(String productId, int amountToPick) throws InsufficientStockException;
	public RestockingResult atomicRestockProduct(String productId, int amountToRestock);
}
