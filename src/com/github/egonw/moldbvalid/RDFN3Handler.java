package com.github.egonw.moldbvalid;

import java.io.*;

import org.openscience.cdk.validate.*;

public class RDFN3Handler implements IReportHandler {

  private PrintStream output;

  private int fileCounter = 0;
  private int molCounter = 0;
  private int errorCounter = 0;
  private String currentSubject = "";

  public RDFN3Handler(OutputStream output) {
    if (output instanceof PrintStream) {
      this.output = (PrintStream)output;
    } else {
      this.output = new PrintStream(output);
    }
  }

  public void setFile(String filename) {
    fileCounter++;
    output.println(":file" + fileCounter + " rdfs:label \"" + filename + "\" .");
  }

  public void setSubject(String subject) {
    this.molCounter++;
    this.currentSubject = subject;
    output.println(":file" + fileCounter + " :describes :mol" + molCounter + " .");
    output.println(":mol" + molCounter + "  rdfs:label \"" + subject + "\" .");
  };

  public void handleError(String type, ValidationTest test) {
    errorCounter++;
    String error = ":error" + errorCounter;
    output.println(currentSubject + " " + type + " " + error + " .");
    output.println(error + " a :ValidationTest ;");
    output.println(" :message \"" + test.getError() + "\" ;");
    output.println(" :details \"" + test.getDetails() + "\" ;");
  }

}

