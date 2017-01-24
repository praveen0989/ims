package com.commercehub.ims.request;

import com.commercehub.ims.InventoryManagementSystem;
import com.commercehub.ims.result.PickingResult;
/**
 * A request of type Pick/ get objects from IMS.
 * This is a separate class as we can incorporate priority in requests.
 * @author Praveen
 *
 */
public class PickingRequest extends AbstractRequest {
	String productId;
	int amountToPick;

	public PickingRequest(String productId, int amountToPick, InventoryManagementSystem system) {
		super(system);
		this.productId = productId;
		this.amountToPick = amountToPick;
	}

	@Override
	public PickingResult processRequest() {
		return system.pickProduct(productId, amountToPick);
	}

}
