package category.analyzer.io;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConsoleWriterTest {
	
	private ByteArrayOutputStream out = new ByteArrayOutputStream();
	private ByteArrayOutputStream err = new ByteArrayOutputStream();

	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(out));
	    System.setErr(new PrintStream(err));
	}

	@Test
	public void test() throws IOException {
		ConsoleWriter consoleWriter = new ConsoleWriter();
		String testString = "Test console writer by checking if input string is same wa what is displayed.";
		consoleWriter.write(testString);
		assertEquals("Console output", testString, out.toString());
	}

	@After
	public void tearDown() throws Exception {
	    System.setOut(null);
	    System.setErr(null);
	}
}
