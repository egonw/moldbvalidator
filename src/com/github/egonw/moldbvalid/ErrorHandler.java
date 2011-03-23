package com.github.egonw.moldbvalid;

import org.openscience.cdk.io.*;

public class ErrorHandler
implements IChemObjectReaderErrorHandler {
  public void handleError(String message) {
    System.out.println(message);
  };
  public void handleError(String message,
    Exception exception)
  {
    System.out.println(message + "\n  -> " +
            exception.getMessage());
  };
  public void handleError(String message,
    int row, int colStart, int colEnd)
  {
    System.out.println("location: " + row + ", " + 
          colStart + "-" + colEnd + ": ");
    System.out.println(message);
  };
  public void handleError(String message,
    int row, int colStart, int colEnd,
    Exception exception)
  {
    System.out.println("location: " + row + ", " +
          colStart + "-" + colEnd + ": ");
    System.out.println(message + "\n  -> " +
            exception.getMessage());
  };
}

