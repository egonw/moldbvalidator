package com.github.egonw.moldbvalid;

import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.io.*;
import org.openscience.cdk.io.iterator.*;
import org.openscience.cdk.*;
import org.openscience.cdk.atomtype.*;
import org.openscience.cdk.exception.*;
import org.openscience.cdk.nonotify.*;
import org.openscience.cdk.io.IChemObjectReader.Mode;
import org.openscience.cdk.validate.*;
import java.io.*;

public class Validate {

  IReportHandler handler;
  ValidatorEngine validator;

  public Validate(IReportHandler handler) {
    this.handler = handler;
    this.validator = new ValidatorEngine();
    this.validator.addValidator(new CDKValidator());
    this.validator.addValidator(new BasicValidator());
    this.validator.addValidator(new AbstractValidator() {
      CDKAtomTypeMatcher matcher = CDKAtomTypeMatcher.getInstance(
        DefaultChemObjectBuilder.getInstance()
      );
      public ValidationReport validateMolecule(IMolecule molecule) {
        ValidationReport report = new ValidationReport();
        try {
          IAtomType[] types = matcher.findMatchingAtomType(molecule);
          for (int i=0; i<types.length; i++) {
            ValidationTest atError = new ValidationTest(
              molecule.getAtom(i),
              "The atom's type is not recognized."
            );
            if (types[i] == null) {
              report.addError(atError);
            } else {
              report.addError(atError);
            }
          }
        } catch (Exception exception) {
          ValidationTest test = new ValidationTest(
            molecule,
            "Error while atom typing of molecule."
          );
          test.setDetails(exception.getMessage());
          report.addCDKError(test);
        }
        return report;
      }
    });
  }

  public void validateFile(String fileName) throws Exception {
    ErrorHandler errorHandler = new ErrorHandler(this.handler);
    handler.setFile(fileName);

    IteratingMDLReader iterator = new IteratingMDLReader(
      new FileReader(new File(fileName)),
      NoNotificationChemObjectBuilder.getInstance()
    );
    iterator.setReaderMode(Mode.RELAXED);
    iterator.setErrorHandler(errorHandler);

    while (iterator.hasNext()) {
      IMolecule mol = (IMolecule)iterator.next();
      handler.setSubject("" + mol.getProperty(CDKConstants.TITLE));
      try {
        ValidationReport report = validator.validateMolecule(mol);
        process(handler, report);
      } catch (Exception exception) {}
    }
  }

  private void process(IReportHandler handler, ValidationReport report) {
    for (ValidationTest test : report.getErrors())
      handler.handleError(":error", test);
    for (ValidationTest test : report.getWarnings())
      handler.handleError(":warning", test);
    for (ValidationTest test : report.getCDKErrors())
      handler.handleError(":cdkError", test);
  }
}
