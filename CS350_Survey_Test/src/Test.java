import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Test extends Survey{
	
	// need array for correct answers
	ArrayList<String> correctAnswers;
	
	private Input reader = new Input();

	// default constructor
	public Test()
	{
		questions = new ArrayList<Question>();
		correctAnswers = new ArrayList<String>();
		
		this.setSurveyName("");
		this.setSurveyPath("");
		
	}
	
	
	// constructor with name and path
	public Test(String testName, String testPath)
	{
		questions = new ArrayList<Question>();
		correctAnswers = new ArrayList<String>();
		
		this.setSurveyName(testName);
		this.setSurveyPath(testPath);
		
	}
	
	
	// function to prompt to add answer
	public void addAnswer()
	{
		System.out.println("Add answer for question (if more than one use '//' to seperate each answer");
		System.out.println("For matching use the format 'leftnum:rightnum//leftnum:rightnum', and ranking the format 'firstchoicerank//second");
		correctAnswers.add(reader.getResponse());
	}
	
	// function to add preselected answer (will be used for essays
	public void addAnswer(String answer)
	{
		this.correctAnswers.add(answer);
	}
	
	// function to add edited question
	public void editCorrectAnswer(String answer, int questionNumber)
	{
		this.correctAnswers.set(questionNumber, answer);
	}
	
	public void edit(int questionNumber)
	{
		super.edit(questionNumber);
		System.out.println("Would you like to edit an answer yes/no");
		String response = reader.getResponse();
		if(response.equals("yes"))
		{
			System.out.println("Enter the new Answer");
			String newAns = reader.getResponse();
			
			this.editCorrectAnswer(newAns, questionNumber);
		}
	}
	
	
	// gets specific question's answer
	public String getCorrectAnswer(int questionNumber)
	{
		return this.correctAnswers.get(questionNumber);		
	}
	
	// sets answers to inputted array
	public void setCorrectAnswers(ArrayList<String> answers)
	{
		this.correctAnswers = answers;
	}
	
	// gets array of answers
	public ArrayList<String> getAnswers()
	{
		return this.correctAnswers;
	}
	
	// prompts user for to keep adding answers for each question (not used at all)
	public void setCorrectAnswers()
	{
		for(int i = 0; i < this.questions.size(); i++)
		{
			System.out.println("Enter Answer for questions " + i);
			correctAnswers.add(reader.getResponse());
			
		}
	}
	
	// function to save questions and save answers
	public void save() throws FileNotFoundException
	{
		this.createSurveyDirectory(this.getSurveyPath());
		String fullPath = this.getSurveyPath() + "/" + this.getSurveyName() + "/questions";
		
		FileOutputStream fOut;
		try {
			fOut = new FileOutputStream(fullPath);
			ObjectOutputStream out = new ObjectOutputStream(fOut);
			out.writeObject(this.getQuestions());
			out.writeObject(this.getAnswers());
			out.close();
			fOut.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fullPath = this.getSurveyPath() + "/" + this.getSurveyName() + "/answers";
		try {
			fOut = new FileOutputStream(fullPath);
			ObjectOutputStream out = new ObjectOutputStream(fOut);
			out.writeObject(this.getAnswers());
			out.close();
			fOut.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	// function to load test questions and their answers
	@SuppressWarnings({ "unchecked", "resource" })
	public boolean load(String filePath, String fileName) throws IOException
	{
		System.out.println("Loading " + fileName);
		FileInputStream fIn = new FileInputStream(filePath + "/" + fileName + "/questions");
		ObjectInputStream in = new ObjectInputStream(fIn);
		FileInputStream fIn2 = new FileInputStream(filePath + "/" + fileName + "/answers");
		ObjectInputStream in2 = new ObjectInputStream(fIn2);
		
		try {
			this.setQuestions((ArrayList<Question>) in.readObject());
			this.setSurveyName(fileName);
			this.setSurveyPath(filePath);
			in.close();
			this.setCorrectAnswers((ArrayList<String>) in2.readObject());
			in2.close();
			System.out.println("File loaded");
			return true;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				in.close();
				System.out.println("File failed loading");
				return false;
			}
			
	
	}
	
	// used in next assignment
	public int grade()
	{
		return 0;
	}
	
	public void take()
	{
		
	}
	
	

}
