package org.koushik.javabrains.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.koushik.javabrains.messenger.model.ErrorMessage;

/**
 * This is the mapper class for datanotfound custom exception class
 * this class  maps the error class to the response
 ** @author M1035998
 * */
//@provider registers the class with JAX-RS
@Provider
//ExceptionMapper has to be made generic and provide that class which needs to be mapped
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	//this method is implemented due to ExceptionMapper
	//it takes exception as param and returns the response
	@Override
	public Response toResponse(DataNotFoundException ex) {
		//we are providing the error detils which will be shown
		ErrorMessage errorMessage=new ErrorMessage(ex.getMessage(),
				404,"www.google.com");
		//This is how a resonse is created
		return Response.status(Status.NOT_FOUND)
					.entity(errorMessage).build();
	}

}
