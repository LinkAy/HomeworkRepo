import java.io.Serializable;
import java.util.ArrayList;


public class Matching extends Question implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5153176111017344021L;

	private String questionType = "Matching";
	
	ArrayList<String> leftColumn;
	ArrayList<String> rightColumn;
	
	
	// default constructor
	public Matching()
	{
		this.setPrompt("Please Match each column");
		this.leftColumn = new ArrayList<String>();
		this.rightColumn = new ArrayList<String>();
		this.setQuestionType(questionType);
		
	}
	
	
	// constructor used to start the create of matching
	public Matching(int i)
	{
		this.setPrompt("Please Match each column");
		this.leftColumn = new ArrayList<String>();
		this.rightColumn = new ArrayList<String>();
		this.setQuestionType(questionType);
		this.createMatching();
	}
	
	
	// wrapper for the 3 start functions
	public void createMatching()
	{
		startLeftColumn();
		startRightColumn();
		checkBalance();
		
	}
	
	
	// following two add to respective columns
	public void addToLeftColumn(String item)
	{
		leftColumn.add(item);
	}
	
	public void addToRightColumn(String item)
	{
		rightColumn.add(item);
	}
	
	
	// getters and setters for each column
	public ArrayList<String> getLeftColumn()
	{
		return leftColumn;
	}
	
	public ArrayList<String> getRightColumn()
	{
		return rightColumn;
	}
	
	public void setLeftColumn(ArrayList<String> leftColumn)
	{
		this.leftColumn = leftColumn;
	}
	
	public void setRightColumn(ArrayList<String> rightColumn)
	{
		this.rightColumn = rightColumn;
	}
	
	
	// starts the process to add choices to left column
	public void startLeftColumn()
	{
		System.out.println("Editing Left Column Item List.");
		int addAnother = 0;
		
		while(addAnother == 0)
		{
			System.out.println("Enter choice (type '**finished' when done adding choices): ");
			String optionText = reader.getResponse();
			
			if(optionText.equals("**finished"))
			{
				// done getting options
				addAnother = 1;
				
			}
			else
			{
				this.addToLeftColumn(optionText);
			}			
			
		}
		
		
	}
	// starts the process to add choices to right column
	public void startRightColumn()
	{
		System.out.println("Editing Right Column Item List.");
		int addAnother = 0;
		
		while(addAnother == 0)
		{
			System.out.println("Enter choice (type '**finished' when done adding choices): ");
			String optionText = reader.getResponse();
			
			if(optionText.equals("**finished"))
			{
				// done getting options
				addAnother = 1;
				
			}
			else
			{
				this.addToRightColumn(optionText);
			}			
			
		}		
		
	}
	
	// function to make sure sides are balanced
	public void checkBalance()
	{
		int leftCount = this.leftColumn.size();
		int rightCount = this.rightColumn.size();
		int difference;
		if(leftCount != rightCount)
		{
			if(leftCount > rightCount)
			{
				difference = leftCount - rightCount;
				for(int i=0; i <= difference; i++)
				{
					this.rightColumn.add("");
				}
			}
			else
			{
				difference = rightCount - leftCount;
				for( int i =0; i <= difference; i++)
				{
					this.leftColumn.add("");
				}
			}
		}
	}
	
	
	// displays columns, if right column is empty it doesn't display that column (used for ranking display
	public void displayQuestion()
	{
		super.displayQuestion();
		
		System.out.println("Printing left column");
		this.printColumn(this.getLeftColumn());
		
		if(!this.rightColumn.isEmpty())
		{
			System.out.println("Printing Right column");
			this.printColumn(this.getRightColumn());
		}
		
	}
	
	public String getQuestionType()
	{
		return this.questionType;
	}
	
	public void printColumn(ArrayList<String> column)
	{
		int itemCount = 1;
		
		for(String item : column)
		{
			System.out.print(itemCount + ") ");
			System.out.println(item);
			itemCount++;
		}
	}

}
