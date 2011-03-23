package com.github.egonw.moldbvalid;

public interface IReportHandler {

  public void setSubject(String subject);

  public void handleError(String type, String error);

}

