package skillup.java.scripting;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptEngineManagerTest {
	public static void main(String[] args) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");

		try {
			String script = "print('Hello Scripting')";
			engine.eval(script);
		} catch (ScriptException exception) {
			exception.printStackTrace();
		}

	}
}
