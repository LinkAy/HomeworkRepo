import java.util.ArrayList;
import java.io.Serializable;


public class MultChoice extends Question  implements Serializable {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3347296952073971356L;

	private String questionType = "MultipleChoice";
	
	ArrayList<String> choiceOptions;
	
	
	// default constructor
	public MultChoice()
	{
		this.choiceOptions = new ArrayList<String>();
		this.setQuestionType(questionType);
	}
	
	
	
	// used to for actual mult choice
	public MultChoice(int numOfOptions)
	{
		this.choiceOptions = new ArrayList<String>();
		this.setQuestionType(questionType);
		
		if(numOfOptions > 0)
		{
			createOptions();
		}		
	}
	

	// adds the actual mult choice optons
	public void createOptions() 
	{
		int addAnother = 0;
		
		while(addAnother == 0)
		{
			System.out.println("Enter options (type '**finished' when done adding choices): ");
			String optionText = reader.getResponse();
			
			if(optionText.equals("**finished"))
			{
				// done getting options
				addAnother = 1;
				
			}
			else
			{
				this.addOption(optionText);
			}			
			
		}
		
		
	}
	
	public void addOption(String optionText)
	{
		choiceOptions.add(optionText);
		
	}
	
	
	public void editQuestion()
	{
		super.editQuestion();
		System.out.println("2) Edit options");
		String response = reader.getResponse();
		if(response.equals("2"))
		{
			this.displayOptions();
			System.out.println("Please pick an option");
			int optionNum = Integer.parseInt(reader.getResponse());
			
			System.out.println("Please enter new option");
			String newOption = reader.getResponse();
			
			this.choiceOptions.set(optionNum, newOption);
		}
	}
	public int getNumOfOptions()
	{
		return this.choiceOptions.size();
	}
	// displays options
	public void displayOptions()
	{
		int optionCount = 1;
		
		for(String option : choiceOptions )
		{
			System.out.print(optionCount + ")");
			System.out.println(option);
			optionCount++;
		}
		
	}
	
	// overrides display question
	@Override
	public void displayQuestion()
	{
		super.displayQuestion();
		displayOptions();
		
	}
	
	public String getQuestionType()
	{
		return this.questionType;
	}

}
