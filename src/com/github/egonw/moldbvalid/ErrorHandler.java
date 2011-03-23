package com.github.egonw.moldbvalid;

import org.openscience.cdk.io.*;

public class ErrorHandler
implements IChemObjectReaderErrorHandler {

  private IReportHandler report;

  public ErrorHandler(IReportHandler report) {
    this.report = report;
  }

  public void handleError(String message) {
    report.handleError(":error", message);
  };

  public void handleError(String message,
    Exception exception)
  {
    report.handleError(":error", message + "\n  -> " +
            exception.getMessage());
  };

  public void handleError(String message,
    int row, int colStart, int colEnd)
  {
    report.handleError(":error", "location: " + row + ", " + 
      colStart + "-" + colEnd + ": " + message);
  };

  public void handleError(String message,
    int row, int colStart, int colEnd,
    Exception exception)
  {
    report.handleError(":error", "location: " + row + ", " + 
      colStart + "-" + colEnd + ": " + message + " -> " +
      exception.getMessage());
  };
}

