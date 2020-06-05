package com.webservices.rest.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Set;

@Provider
public class ExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {

        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder responseBody = new StringBuilder();

        violations.forEach(violation -> responseBody.append("Value: " + violation.getInvalidValue() +
                " failed validation because: " + violation.getMessage()));
        Response respondWith = Response.status(400, responseBody.toString()).type(MediaType.APPLICATION_JSON).build();
        return respondWith;
    }
}
