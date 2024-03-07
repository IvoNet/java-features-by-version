import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.nio.file.Path;

public class JEP454 {
    public static void main(String[] args) {
        try (var arena = Arena.ofConfined()) {
            var lib = SymbolLookup.libraryLookup(Path.of("calc.so"), arena);
            var linker = Linker.nativeLinker();
            var fd = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT);
            var addFunc = lib.find("add").get();
            var minusFunc = lib.find("minus").get();
            var multiplyFunc = lib.find("product").get();
            System.out.println("sum(20, 22)   = " + linker.downcallHandle(addFunc, fd).invoke(20, 22));
            System.out.println("minus(45, 3)  = " + linker.downcallHandle(minusFunc, fd).invoke(45, 3));
            System.out.println("product(7,6)  = " + linker.downcallHandle(multiplyFunc, fd).invoke(7, 6));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
