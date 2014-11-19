import java.io.Serializable;


public class TrueFalse extends MultChoice implements Serializable {
	

	private static final long serialVersionUID = -1376710907664063506L;
	private String questionType = "TrueFalse";
	
	
	// default constructor
	public TrueFalse()
	{
		this.setQuestionType(questionType);
		this.addOption("True");
		this.addOption("False");
	}
	
	
	
	public String getQuestionType()
	{
		return this.questionType;
	}
	
	

}
