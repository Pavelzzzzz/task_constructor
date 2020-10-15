package com.pavelzzzzz.task_control.handler;

import com.pavelzzzzz.task_control.exception.PocEntityIsDeletedException;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * handles PocNotFoundException
   * @param exception exception to handle
   * @return HfsError ResponseEntity
   */
  @ExceptionHandler(PocNotFoundException.class)
  public ResponseEntity handlePocNotFoundException(
      PocNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  /**
   * handles PocEntityIsDeletedException
   * @param exception exception to handle
   * @return HfsError ResponseEntity
   */
  @ExceptionHandler(PocEntityIsDeletedException.class)
  public ResponseEntity handlePocEntityIsDeletedException(
      PocEntityIsDeletedException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.I_AM_A_TEAPOT);
  }

  /**
   * handles IllegalArgumentException
   * @param exception exception to handle
   * @return HfsError ResponseEntity
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity handleIllegalArgumentException(
      IllegalArgumentException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
