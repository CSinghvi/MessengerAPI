//package org.koushik.javabrains.messenger.exception;
//
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
//import javax.ws.rs.ext.ExceptionMapper;
//import javax.ws.rs.ext.Provider;
//
//import org.koushik.javabrains.messenger.model.ErrorMessage;
//
///**
// * Its same as datanotfoundException class, difference is that
// * it is generic, means it is applicable for all exceptions
// * in this the generic in ExceptionMapper is Throwable not custom Exception
// * @author M1035998
// *
// */
////@Provider //we are disabling it know to check new exception handling method 
//			//which is Web Application Exception
//public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
//
//	@Override
//	public Response toResponse(Throwable ex) {
//		ErrorMessage errorMessage=new ErrorMessage(ex.getMessage(),
//				500,"www.google.com");
//		return Response.status(Status.INTERNAL_SERVER_ERROR)
//				.entity(errorMessage).build();
//		}
//
//	}
