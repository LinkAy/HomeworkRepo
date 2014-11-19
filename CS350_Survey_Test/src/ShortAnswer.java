import java.io.Serializable;


public class ShortAnswer extends Essay implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -193211510499313241L;
	private String questionType = "ShortAnswer";
	
	
	// default constructor (max input for answer = 10)
	public ShortAnswer()
	{
		this.setMaxChars(10);
		this.setQuestionType(questionType);
	}
	
	// constructor to set max chars (not implemented in solution)
	public ShortAnswer(int maxChars)
	{
		this.setMaxChars(maxChars);
		this.setQuestionType(questionType);
	}
	
	

	public String getQuestionType()
	{
		return this.questionType;
	}
	
	
	// no chance ti display question between essay and SA
	public void displayQuestion() 
	{
		super.displayQuestion();
	}

}
