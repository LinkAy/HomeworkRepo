import java.io.Serializable;


public class Ranking extends Matching implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 78417043033716141L;
	private String questionType = "Ranking";
	
	
	// default constructor for ranking
	public Ranking()
	{
		this.setPrompt("Please rank the following using their numbers");
		// only need 1 column fill the other with blanks
		super.startLeftColumn();
		this.setQuestionType(questionType);		
	}
	
	// no change in display functions
	public void displayQuestion()
	{
		super.displayQuestion();
	}
	public String getQuestionType()
	{
		return this.questionType;
	}

}
