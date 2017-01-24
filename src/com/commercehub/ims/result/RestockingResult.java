package com.commercehub.ims.result;
/**
 * Result object for action restock.
 * @author Praveen
 *
 */
public class RestockingResult implements IResult{
	String productId;
	int newValue;
	
	public RestockingResult(String productId, int newValue) {
		this.productId = productId;
		this.newValue = newValue;
	}
	
	public String toString(){
		return "The new stock of product " + productId + " is : " + newValue; 
	}

}
