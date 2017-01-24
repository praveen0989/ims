package com.commercehub.ims.request;

import com.commercehub.ims.result.IResult;
/**
 * Interface that defines how a request should be submitted to the IMS.
 * @author Praveen
 *
 */
public interface IRequest {

	IResult processRequest();

}
