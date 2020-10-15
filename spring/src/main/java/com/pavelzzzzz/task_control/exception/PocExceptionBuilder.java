package com.pavelzzzzz.task_control.exception;

public class PocExceptionBuilder {

  public static PocNotFoundException createPocNotFoundException(Class entityClass, Object id) {
    String message = String.format(
        "%s with id = %s not found.", entityClass.getSimpleName(), id.toString());
    return new PocNotFoundException(message);
  }

  public static PocNotFoundException createPocNotFoundException(String entityClass, Object id) {
    String message = String.format(
        "%s with id = %s not found.", entityClass, id.toString());
    return new PocNotFoundException(message);
  }

  public static PocEntityIsDeletedException createEntityIsDeletedException(Class entityClass, Object id) {
    String message = String.format(
        "%s with id = %s was deleted.", entityClass.getSimpleName(), id.toString());
    return new PocEntityIsDeletedException(message);
  }

  public static PocException createPocException(String message) {
    return new PocException(message);
  }
}
