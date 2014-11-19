import java.io.Serializable;


public class Question implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4913108046063116733L;
	private String prompt;	
	// Not sure if I want this yet. If needed, will go back and get rid of
	private String questionType = null;
	private Response response;
	
	// don't serialize the reader
	public transient Input reader = new Input();
	
	// default constructor
	public Question()
	{
		System.out.println("Enter the question(If matching or Ranking enter nothing)");
		this.setPrompt(reader.getResponse());
	}
	
	// default display question function
	public void displayQuestion()
	{
		System.out.println("Question Type: " + this.getQuestionType());
		System.out.println("Question: " + this.getPrompt());
		
	}
	
	
	// getters and setters for private vars
	public String getPrompt()
	{
		return this.prompt;
	}
	
	public void setPrompt(String prompt)
	{
		this.prompt = prompt;
	}
	
	public String getQuestionType()
	{
		return this.questionType;
	}
	
	public void setQuestionType(String questionType)
	{
		this.questionType = questionType;
	}
	
	// the following will be needed for the next part
	public Response getResponse()
	{
		return this.response;
	}
	
	public void setResponse(String response)
	{
		this.response.setResponse(response);
	}
	
	public void setResponse(Response response)
	{
		this.response = response;
	}
	
	public void editQuestion()
	{
		System.out.println("Please enter the option number");
		System.out.println("0) Close");
		System.out.println("1) Edit prompt");
		String response = reader.getResponse();
		if(response.equals("1"))
		{
			System.out.println("Enter the new prompt");
			String newPrompt = reader.getResponse();
			this.setPrompt(newPrompt);
		}
	}
	
}
