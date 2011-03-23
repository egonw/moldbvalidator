package com.github.egonw.moldbvalid;

import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.io.*;
import org.openscience.cdk.*;
import org.openscience.cdk.nonotify.*;
import org.openscience.cdk.io.IChemObjectReader.Mode;
import java.io.*;

public class Validate {

  public static void validateFile(String fileName) throws Exception {
    ErrorHandler errorHandler = new ErrorHandler();

    MDLV2000Reader reader = new MDLV2000Reader(
      new FileReader(new File(fileName)),
      Mode.RELAXED
    );
    reader.setErrorHandler(errorHandler);
    IMolecule water = reader.read(new NNMolecule());
  }
}
