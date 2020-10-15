package com.pavelzzzzz.task_control.exception;

public class PocEntityIsDeletedException extends PocException {

  public PocEntityIsDeletedException() {
  }

  public PocEntityIsDeletedException(String message) {
    super(message);
  }

  public PocEntityIsDeletedException(String message, Throwable cause) {
    super(message, cause);
  }

  public PocEntityIsDeletedException(Throwable cause) {
    super(cause);
  }

  public PocEntityIsDeletedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
