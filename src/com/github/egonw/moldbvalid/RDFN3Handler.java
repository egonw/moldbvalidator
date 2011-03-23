package com.github.egonw.moldbvalid;

import java.io.*;

public class RDFN3Handler implements IReportHandler {

  private PrintStream output;

  private String currentSubject = ":1";

  public RDFN3Handler(OutputStream output) {
    if (output instanceof PrintStream) {
      this.output = (PrintStream)output;
    } else {
      this.output = new PrintStream(output);
    }
  }

  public void setSubject(String subject) {
    this.currentSubject = subject;
  };

  public void handleError(String type, String error) {
    output.print(currentSubject);
    output.print(" ");
    output.print(type);
    output.print(" ");
    output.println("\"" + error + "\" .");
  }

}

