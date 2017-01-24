package com.commercehub.ims.database;

import java.util.concurrent.ConcurrentHashMap;
import com.commercehub.ims.exception.InsufficientStockException;
import com.commercehub.ims.result.PickingResult;
import com.commercehub.ims.result.RestockingResult;
/**
 * Concurrent HashMap is used as a datastore. The interface can be implemented by a differnt
 * class to actually interact with database. Concurrent HashMap almost gives an item level locking,
 * which is quite similar to the row-level locking of the database.
 * @author Praveen
 *
 */
public class IMSDatabase implements IDatabase{
	private ConcurrentHashMap<String, Integer> products;
	/**
	 * Constructor which takes in a datastore.
	 * @param products Datastore object containing product and quantity info.
	 */
	public IMSDatabase(ConcurrentHashMap<String, Integer> products){
		if(products == null || products.size() == 0){
			throw new IllegalArgumentException("Initial Inventory cannot be empty.");
		}
		this.products = products;
	}
	/**Check if amount to pick is available.
	 *	If yes, obtain a row level lock.
	 *	Update amountToPick.Commit
	 * 
	 */
	public synchronized PickingResult atomicPickProduct(String productId, int amountToPick) 
	throws InsufficientStockException {
		//
		if(productId == null || !this.products.containsKey(productId)){
			throw new IllegalArgumentException("Illegal ProductId.");
		}
		if(amountToPick < 0){
			throw new IllegalArgumentException("Illegal amount to pick.");
		}
		
		if(amountToPick > this.products.get(productId)){
			throw new InsufficientStockException(productId, amountToPick - this.products.get(productId));
		}
		
		int newValue = this.products.get(productId) - amountToPick;
		this.products.put(productId, newValue);
				
		PickingResult result = new PickingResult(productId, newValue);
		return result;
		
	}
	/**
	 * 	Check if space is available.
	 *  If yes, obtain a row level lock. Update amountToPick.Commit
	 * 
	 */
	public synchronized RestockingResult atomicRestockProduct(String productId,
			int amountToRestock){
		
		if(productId == null || !this.products.containsKey(productId)){
			throw new IllegalArgumentException("Illegal ProductId.");
		}
		if(amountToRestock < 0){
			throw new IllegalArgumentException("Illegal amount to pick.");
		}
		
		int newValue = this.products.get(productId) + amountToRestock;
		this.products.put(productId, newValue);
				
		RestockingResult result = new RestockingResult(productId, newValue);
		return result;		
	}
	
}
