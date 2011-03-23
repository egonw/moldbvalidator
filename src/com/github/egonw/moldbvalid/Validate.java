package com.github.egonw.moldbvalid;

import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.io.*;
import org.openscience.cdk.io.iterator.*;
import org.openscience.cdk.*;
import org.openscience.cdk.nonotify.*;
import org.openscience.cdk.io.IChemObjectReader.Mode;
import java.io.*;

public class Validate {

  IReportHandler handler;

  public Validate(IReportHandler handler) {
    this.handler = handler;
  }

  public void validateFile(String fileName) throws Exception {
    ErrorHandler errorHandler = new ErrorHandler(this.handler);

    IteratingMDLReader iterator = new IteratingMDLReader(
      new FileReader(new File(fileName)),
      NoNotificationChemObjectBuilder.getInstance()
    );
    iterator.setReaderMode(Mode.RELAXED);
    iterator.setErrorHandler(errorHandler);

    while (iterator.hasNext()) {
      IMolecule mol = (IMolecule)iterator.next();
      System.out.println("" + mol.getProperty(CDKConstants.TITLE));
    }
  }
}
