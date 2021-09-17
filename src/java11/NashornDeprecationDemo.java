//package java11;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Should be run with java 11
 */
public class NashornDeprecationDemo {
    public static void main(final String[] args) throws ScriptException {
        final ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
        scriptEngine.eval("print('there should be a warning above me');");

        /*
        Warning: Nashorn engine is planned to be removed from a future JDK release
        there should be a warning above me
         */
    }
}
