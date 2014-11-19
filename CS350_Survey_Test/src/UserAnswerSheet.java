import java.io.Serializable;
import java.util.ArrayList;


public class UserAnswerSheet implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -383879999225715978L;
	private String name;
	ArrayList<Response> responses;
	
	public UserAnswerSheet()
	{
		name = "";
		responses = new ArrayList<Response>();
		
	}
	
	
	public ArrayList<Response> getAnswersList()
	{
		ArrayList<Response> l = new ArrayList<Response>();
		return l;
	}
	
	public void setAnswerList(ArrayList<Response> answers)
	{
		this.responses = answers;
		
	}
	
	public Response getAnswer(int index)
	{
		Response a = new Response();
		
		return a;
		
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void displayAnswers(ArrayList<Response> responses)
	{
		int count = 1;
		for (Response r : responses)
		{
			System.out.println("Answer " + count + ": ");
			r.displayResponse();
			System.out.println("");
		}
	}
	
	public void addAnswer(String response)
	{
		responses.add(new Response(response));
	}

}
