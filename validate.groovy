import com.github.egonw.moldbvalid.*

def validator = new Validate(
  new RDFN3Handler(System.out)
)
for (file in args) validator.validateFile(file)
