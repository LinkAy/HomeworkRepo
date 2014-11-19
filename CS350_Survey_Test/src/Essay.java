import java.io.Serializable;


public class Essay extends Question implements Serializable {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4433407239702261736L;

	private String questionType = "Essay";
	
	private int maxChars = -1;
	
	// default constructor
	public Essay() 
	{
		this.setMaxChars(-1);
		this.setQuestionType(questionType);
	}
	
	// used to set number of maxed allowed inputted (not implemented into solution)
	public Essay(int maxChars)
	{
		this.setMaxChars(maxChars);
		this.setQuestionType(questionType);
	}
	
	// getters and setters
	public int getMaxChars()
	{
		return this.maxChars;
	}
	
	public void setMaxChars(int maxChars)
	{
		this.maxChars = maxChars;
	}
	
	public String getQuestionType()
	{
		return this.questionType;
	}
	
	
	// displays essay question
	public void displayQuestion()
	{
		super.displayQuestion();
		if(this.getMaxChars() > 0)
		{
			System.out.print("The maxmimum allowed characters is: ");
			System.out.print(this.getMaxChars());
			System.out.println(", character exceeding limit will not be recorded");
		}
		else
		{
			System.out.println("No maxmimum character limit is in place.");
		}
		
	}
	

	

}
