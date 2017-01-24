package com.commercehub.ims.result;
/**
 * Result object for operation pick.
 * @author Praveen
 *
 */
public class PickingResult implements IResult{
	
	String productId;
	int newValue;
	
	public PickingResult(String productId, int newValue) {
		this.productId = productId;
		this.newValue = newValue;
	}
	
	@Override
	public String toString(){
		return "The new value of the product : " + productId + " is " + String.valueOf(newValue);
	}

}
