package java16;

import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.LibraryLookup;

import java.io.File;
import java.lang.invoke.MethodType;
import java.nio.file.Path;

// navigate to the src folder
// make sure you are using jdk 16
// javac --add-modules jdk.incubator.foreign java16/JEP389.java
// java --add-modules jdk.incubator.foreign -Dforeign.restricted=permit java16.JEP389

public class JEP389 {
  public static void main(final String[] args) throws Throwable {
    final String fileResource = getFileResource("java16/helloworld.so");
    var lib = LibraryLookup
          .ofPath(Path.of(fileResource)); //fully qualified path

    var sym = lib.lookup("helloworld").get();
    var fd = FunctionDescriptor.ofVoid();
    var mt = MethodType.methodType(Void.TYPE);
    var mh = CLinker.getInstance()
                    .downcallHandle(sym.address(), mt, fd);
    mh.invokeExact();
  }

  /**
   * Get a filname from the recourse folder.
   *
   * @param fileName the filename to get in src/test/resources
   * @return the absolute path to the filename
   */
  public static String getFileResource(final String fileName) {
    String abspath = new File(".").getAbsolutePath();
    abspath = abspath.substring(0, abspath.length() - 1);
    return new File(abspath + fileName).getAbsolutePath();
  }

}
