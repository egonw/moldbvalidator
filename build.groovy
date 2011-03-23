def ant = new AntBuilder()

bindir = "bin"
srcdir = "src"
libdir = "jar"
pkgname = "moldbvalid"

ant.taskdef(
  name:"groovyc",
  classname:"org.codehaus.groovy.ant.Groovyc"
)

ant.mkdir(dir:"bin")
ant.javac(
  srcdir:"$srcdir", destdir:"$bindir",
  includes:"**/*.java",
  fork:"true") {
  classpath {
    fileset dir:"$libdir", {
      include name:"*.jar"
    }
  }
}
ant.groovyc(
  srcdir:"$srcdir", destdir:"$bindir",
  includes:"*.groovy",
  fork:"true") {
  classpath {
    fileset dir:"$libdir", {
      include name:"*.jar"
    }
  }
}

ant.jar(destfile:pkgname + ".jar", compress:"true") {
  fileset( dir:"$bindir", includes:"**/*.class" )
  zipgroupfileset( dir:"$libdir", includes:'*.jar' )
  manifest {
    attribute( name: 'Main-Class', value: 'validate' )
  }
}
