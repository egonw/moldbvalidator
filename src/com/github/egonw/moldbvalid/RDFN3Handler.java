package com.github.egonw.moldbvalid;

import java.io.*;

public class RDFN3Handler implements IReportHandler {

  private PrintStream output;

  private int counter = 0;
  private String currentSubject = "";

  public RDFN3Handler(OutputStream output) {
    if (output instanceof PrintStream) {
      this.output = (PrintStream)output;
    } else {
      this.output = new PrintStream(output);
    }
  }

  public void setFile(String filename) {
    output.println(":file rdfs:label \"" + filename + "\" .");
  }

  public void setSubject(String subject) {
    this.counter++;
    this.currentSubject = subject;
    output.println(":file :describes :mol" + counter + " .");
    output.println(":mol" + counter + "  rdfs:label \"" + subject + "\" .");
  };

  public void handleError(String type, String error) {
    output.print(currentSubject);
    output.print(" ");
    output.print(type);
    output.print(" ");
    output.println("\"" + error + "\" .");
  }

}

