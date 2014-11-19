import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Input {
	
	public String getResponse() 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// if no input gotten return blank line		
		return "";
		
		
	}

	
}
