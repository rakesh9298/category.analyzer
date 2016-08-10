package category.analyzer.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import category.analyzer.interfaces.ResponseWriter;

/**
 * 
 * @author rakesh
 * This class writes incoming response to console.
 */
public class ConsoleWriter implements ResponseWriter {
	
	private static final Charset UTF_8 = StandardCharsets.UTF_8;
	private static BufferedWriter consoleWriter = null;
	
	static{
		initialize();	//Restricted to Single instance of BufferedWriter. Synchronization needs to be done in case of multi threading.
	}
	
	private static void initialize(){
		consoleWriter = new BufferedWriter(new OutputStreamWriter(System.out, UTF_8));	
	}

	public void write(String responseString) throws IOException {
		if(consoleWriter==null){ // In case JVM unloads class for some weird reason.
			initialize();
		}
        consoleWriter.write(responseString);
        consoleWriter.flush();        
	}

}
