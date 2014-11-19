import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Survey {
	
	private String surveyName;
	private String surveyPath;
	private Input reader;
	
	protected ArrayList<Question> questions;
	
	
	// default survey class constructor
	public Survey()
	{
		questions = new ArrayList<Question>();
		
		this.surveyName = "";
		this.surveyPath = "";
		
	}
	
	// survey constructor that uses name and path
	public Survey(String Name, String Path)
	{
		questions = new ArrayList<Question>();
		
		this.surveyName = Name;
		this.surveyPath = Path;
	}
	
	// getters and setters
	public int getNumOfQuestions()
	{
		return this.questions.size();
	}
	
	public String getSurveyName()
	{
		return this.surveyName;
	}
	
	public void setSurveyName(String name)
	{
		this.surveyName = name;
	}
	
	
	public void setQuestions(ArrayList<Question> questions)
	{
		this.questions = questions;		
	}
	
	public void edit(int questionNumber)
	{
	
		Question editQuest = this.questions.get(questionNumber);
		editQuest.editQuestion();
	}
	
	public Question getQuestion(int questionNumber)
	{
		return this.questions.get(questionNumber);
	}
	
	public ArrayList<Question> getQuestions()
	{
		return this.questions;
	}
	
	// adds 1 question to array of questions
	public void addQuestion(Question question)
	{
		this.questions.add(question);
		System.out.println("Question added");
	}
	
	// getter and setter for survey path
	public void setSurveyPath(String path)
	{
		this.surveyPath = path;
	}
	
	public String getSurveyPath()
	{
		return this.surveyPath;
	}
	
	
	// displays all questions with Q number
	public void displayQuestions()
	{
		int questionNumber = 1;
		for(Question question : this.questions)
		{
			System.out.println("Q #" + questionNumber + " ");
			question.displayQuestion();
			System.out.println(" ");
			
		}
		
	}
	
	
	
	// function to save questions
	public void save() throws FileNotFoundException
	{
		this.createSurveyDirectory(this.getSurveyPath());
		String fullPath = this.getSurveyPath() + "/" + this.getSurveyName() + "/questions";
		try {
			FileOutputStream fOut = new FileOutputStream(fullPath);
			ObjectOutputStream out = new ObjectOutputStream(fOut);
			out.writeObject(this.getQuestions());
			out.close();
			fOut.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
	
	// function to load survey and its questions
	@SuppressWarnings("unchecked")
	public boolean load(String filePath, String fileName) throws IOException
	{
		System.out.println("Loading " + fileName);
		try {
			FileInputStream fIn = new FileInputStream(filePath + "/" + fileName + "/questions");
			ObjectInputStream in = new ObjectInputStream(fIn);
			
			try {
				this.setQuestions((ArrayList<Question>) in.readObject());
				this.setSurveyName(fileName);
				this.setSurveyPath(filePath);
				in.close();
				System.out.println("File loaded");
				return true;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				in.close();
				System.out.println("File failed loading");
				return false;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Could not find file");
			return false;
			
		}
	
	}
	
	
	// function to create survey directory incase it doesn't exist
	public void createSurveyDirectory(String path)
	{
		String filePath;
		if(path.length() > 0)
		{
			filePath = path + "/" + this.surveyName;
		}
		else
		{
			filePath = this.surveyPath + "/" + this.surveyName;
		}
		
		File fDirectory = new File(filePath);
		
		if (!fDirectory.exists())
		{
			boolean result = fDirectory.mkdirs();
			if(result)
			{
				System.out.println("Directory created");
				
			}
			else
			{
				System.out.println("Directory creation failed");
			}
		}
		else
		{
			System.out.println("Directory exists");
			System.out.println("File will be overwritten");
		}
		
	}
	
	// will be used in next assignment
	
	public void take(UserAnswerSheet uAS)
	{
		int questionNumber = 1;
		for(Question question : this.questions)
		{
			System.out.println("Q #" + questionNumber + " ");
			question.displayQuestion();
			System.out.println(" ");
			
			System.out.println("Please enter your answer(if more than one use '//' to seperate each answer");
			System.out.println("For matching use the format 'leftnum:rightnum//leftnum:rightnum', and ranking the format 'firstchoicerank//second");
			
			String answer = reader.getResponse();
			
			uAS.addAnswer(answer);
			questionNumber++;
		} 
		
		// save after done taking
		this.createSurveyDirectory(this.getSurveyPath());
		String fullPath = this.getSurveyPath() + "/" + this.getSurveyName() + "/answers/" + uAS.getName() +"answer";
		try {
			FileOutputStream fOut = new FileOutputStream(fullPath);
			ObjectOutputStream out = new ObjectOutputStream(fOut);
			out.writeObject(uAS.getAnswersList());
			out.close();
			fOut.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		
	}
	
	public boolean tabulate() throws IOException
	{
		ArrayList<String> fileNames = this.getAnswerSheetNames();
		
		ArrayList<ArrayList<String>> tabulating = new ArrayList<ArrayList<String>>();
		
		for (String name : fileNames)
		{
			String filePath = this.getSurveyPath() + "/" + this.getSurveyName() + "/answers/" + name;
			try {
				FileInputStream fIn = new FileInputStream(filePath);
				ObjectInputStream in = new ObjectInputStream(fIn);
						
						try {
							UserAnswerSheet uAS = new UserAnswerSheet();
							uAS.setAnswerList((ArrayList<Response>) in.readObject());
							in.close();
							System.out.println("File loaded");
							return true;
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							in.close();
							System.out.println("File failed loading");
							return false;
						}
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Could not find file");
						return false;
						
					}
					
			// start to tabulate here
					// use double array
			
			
		}
		
		return true;
	
		
		
		
	}
	
	public ArrayList<String> getAnswerSheetNames()
	{
		String filePath = this.getSurveyPath() + "/" + this.getSurveyName() + "/answers/";
		File file = new File(filePath);
		
		ArrayList<String> folderNames = new ArrayList<String>();
		
		String[] fileNames = file.list();
		
		for(String name : fileNames)
		{
			if(new File(filePath + name).isFile())
			{
				folderNames.add(name);
			}
		}
		
		return folderNames;
	}
	

}
