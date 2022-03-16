package com.creolophus.lucky.common.exception;

public abstract class ApiException extends CreolophusException {

  private String uri;

  protected ApiException(String message) {
    super(message);
  }

  protected ApiException(String message, Throwable cause) {
    super(message, cause);
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }
}
