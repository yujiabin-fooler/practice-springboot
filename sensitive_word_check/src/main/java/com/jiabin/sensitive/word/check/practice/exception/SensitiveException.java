package com.jiabin.sensitive.word.check.practice.exception;

public class SensitiveException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public SensitiveException() {
    super();
  }

  public SensitiveException(String message, Throwable cause) {
    super(message, cause);
  }

  public SensitiveException(String message) {
    super(message);
  }

  public SensitiveException(Throwable cause) {
    super(cause);
  }
}
