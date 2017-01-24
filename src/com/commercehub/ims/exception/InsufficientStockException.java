package com.commercehub.ims.exception;
/**
 * Checked exception class to handle insufficient products available.
 * @author Praveen
 *
 */
public class InsufficientStockException extends Exception{

	private static final long serialVersionUID = -3621262342051111395L;
	String productId;
	int items;
	
	/**
	 * Constructor.
	 * @param productId ID of the product to be picked
	 * @param items Amount of product short
	 */
	public InsufficientStockException(String productId, int items) {
		this.productId = productId;
		this.items = items;
	}
	
	/***
	 * Prints a readable message of the exception.
	 */
	@Override
	public String getMessage(){
		return "We are short of " + this.productId + " by " + this.items + ".";
	}


}
