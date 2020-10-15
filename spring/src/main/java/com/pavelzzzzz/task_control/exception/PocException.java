package com.pavelzzzzz.task_control.exception;

public class PocException extends Exception {
  public PocException() {
  }

  public PocException(String message) {
    super(message);
  }

  public PocException(String message, Throwable cause) {
    super(message, cause);
  }

  public PocException(Throwable cause) {
    super(cause);
  }

  public PocException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
