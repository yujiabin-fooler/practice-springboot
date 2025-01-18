package com.jiabin.rs.api.practice.exception;

import java.util.Map;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class PackExceptionMapper implements ExceptionMapper<Throwable> {

  @Override
  public Response toResponse(Throwable exception) {
    return Response.status(500)
        .entity(Map.of("code", -1, "message", exception.getMessage()))
        .build() ;
  }
}
