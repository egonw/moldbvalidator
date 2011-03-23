import com.github.egonw.moldbvalid.*

def validator = new Validate(
  new RDFN3Handler(System.out)
)
validator.validateFile(args[0])
