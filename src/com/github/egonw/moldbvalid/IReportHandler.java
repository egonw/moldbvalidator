package com.github.egonw.moldbvalid;

public interface IReportHandler {

  public void setFile(String filename);

  public void setSubject(String subject);

  public void handleError(String type, String error);

}

