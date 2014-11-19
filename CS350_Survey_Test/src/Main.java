import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
	
	private Menu mainMenu;
	private Menu testMenu;
	private Menu surveyMenu;
	private Menu editSurveyMenu;
	private Menu editTestSurvey;
	private Input reader = new Input();
	private Display output = new Display();
	private Survey survey;
	private Test test;
	
	private String surveyLocation = "savedFiles/surveys";
	private String testLocation = "savedFiles/tests";
	
	
	// main function start main menu
	public static void main(String[] args)
	{
		Main main = new Main();
		main.mainMenuStart();
	}
	
	
	// add options to main menu interface
	public void createMainMenu(){
		this.mainMenu = new Menu("Main Menu");
		mainMenu.addOption("Create a new Survey");
		mainMenu.addOption("Create a new Test");
		mainMenu.addOption("Display a Survey");
		mainMenu.addOption("Display a Test");
		mainMenu.addOption("Load a Survey");
		mainMenu.addOption("Load a Test");
		mainMenu.addOption("Save a Survey");
		mainMenu.addOption("Save a Test");
		mainMenu.addOption("Modify an Existing Survey");
		mainMenu.addOption("Modify an Existing Test");
		mainMenu.addOption("Take a Survey");
		mainMenu.addOption("Take a test");
		mainMenu.addOption("Grade a Test");
		mainMenu.addOption("Tabulate a Survey");
		mainMenu.addOption("Tabulate a Test");
		mainMenu.addOption("Quit");
	}
	
	// add options to create survey interface
	public void createSurveyMenu(String name)
	{
		this.surveyMenu = new Menu("New Survey:  " + name);
		surveyMenu.addOption("Add a new T/F question");
		surveyMenu.addOption("Add a new multiple choice question");
		surveyMenu.addOption("Add a new short answer question");
		surveyMenu.addOption("Add a new essay question");
		surveyMenu.addOption("Add a new ranking question");
		surveyMenu.addOption("Add a new matching question");
		surveyMenu.addOption("Close");
	}
	
	
	// add options to create test interface
	public void createTestMenu(String name)
	{
		this.testMenu = new Menu("New Test:  " + name);
		testMenu.addOption("Add a new T/F question");
		testMenu.addOption("Add a new multiple choice question");
		testMenu.addOption("Add a new short answer question");
		testMenu.addOption("Add a new essay question");
		testMenu.addOption("Add a new ranking question");
		testMenu.addOption("Add a new matching question");
		testMenu.addOption("Close");
	}
	

	
	
	// switch statement to start respective functions in main menu
	public void mainMenuStart()
	{
		this.createMainMenu();
		
		int option = mainMenu.runMenu();
		
		switch(option) {
		case 1:
			this.createSurvey();
			break;
		case 2:
			this.createTest();
			break;
		case 3:
			this.displaySurvey();
			break;
		case 4:
			this.displayTest();
			break;
		case 5:
			this.loadSurvey();
			break;
		case 6:
			this.loadTest();
			break;
		case 7:
			this.saveSurvey();
			break;
		case 8:
			this.saveTest();
			break;
		case 9:
			output.drawDisplay("exiting");
			break;
		default:
			output.drawDisplay("MainMenu");
			break;
		}
			
		
	}
	// create survey run edit survey menu
	public void createSurvey()
	{
		output.drawDisplay("EnterName");
		output.drawDisplay();
		
		String surveyName = reader.getResponse();
		
		output.drawDisplay();
		
				
		survey = new Survey(surveyName, this.surveyLocation);
		
		addQuestionsSurvey(survey);
		
		
	}
	
	
	// switch statement to respective edit survey functions
	public void addQuestionsSurvey(Survey survey)
	{
		this.createSurveyMenu(survey.getSurveyName());
		
		int optionNum = 0;
		
		Boolean doneEdit = false;
		
		while(!doneEdit) 
		{
			optionNum = surveyMenu.runMenu();
			switch(optionNum){
			case 1:
				survey.addQuestion(new TrueFalse());
				break;
			case 2:
				survey.addQuestion(new MultChoice(1));
				break;
			case 3:
				survey.addQuestion(new ShortAnswer());
				break;
			case 4:
				survey.addQuestion(new Essay());
				break;
			case 5:
				survey.addQuestion(new Ranking());
				break;
			case 6:
				survey.addQuestion(new Matching(1));
				break;
			case 7:
				doneEdit = true;
				output.drawDisplay("Survey being autoSaved");
				output.drawDisplay();
				this.saveSurvey();
				this.mainMenuStart();
				
				break;
			default:
				System.out.println("Something happened");
			}
		}
			
	}
	
	// Edit Survey Questions
	public void editSurveyQuestions()
	{
		this.loadSurvey();
		output.drawDisplay("Please enter the question number you'd like to edit");
		output.drawDisplay();
		int qNum = Integer.parseInt(reader.getResponse());
		survey.edit(qNum);
		
		
	}
	
	
	// create test start edit test menu
	public void createTest()
	{
		output.drawDisplay("EnterName");
		output.drawDisplay();
		String testName = reader.getResponse();
		output.drawDisplay();
		test = new Test(testName, this.testLocation);
		
		addQuestionsTest(test);
		
	}
	
	
	
	
	// switch statement to respective edit test functions
	public void addQuestionsTest(Test test)
	{
		this.createTestMenu(test.getSurveyName());
		
		int optionNumber = 0;
		Boolean doneEdit = false;
		
		while(!doneEdit)
		{
			optionNumber = testMenu.runMenu();
			switch(optionNumber){
			case 1:
				test.addQuestion(new TrueFalse());
				test.addAnswer();
				break;
			case 2:
				test.addQuestion(new MultChoice(1));
				test.addAnswer();
				break;
			case 3:
				test.addQuestion(new ShortAnswer());
				test.addAnswer();
				break;
			case 4:
				test.addQuestion(new Essay());
				test.addAnswer("Essay");
				break;
			case 5:
				test.addQuestion(new Ranking());
				test.addAnswer();
				break;
			case 6:
				test.addQuestion(new Matching(1));
				test.addAnswer();
				break;
			case 7:
				doneEdit = true;
				output.drawDisplay("Test being autoSaved");
				output.drawDisplay();
				this.saveTest();
				this.mainMenuStart();
				break;
			default:
				System.out.println("Something happened");
			}
		}
		
	}

	
	// function to display survey questions
	public void displaySurvey()
	{
		ArrayList<Question> allQuestions = survey.getQuestions();
		if(allQuestions == null)
		{
			output.drawDisplay("No survey loaded");
		}
		else
		{
			for (int i = 0; i < allQuestions.size(); i++)
			{
				Question quest = allQuestions.get(i);
				quest.displayQuestion();
			}
			
		}
		this.mainMenuStart();
		
	}
	
	
	// function to display test questions and answers
	public void displayTest()
	{
		ArrayList<Question> allQuestions = test.getQuestions();
		ArrayList<String> allAnswers = test.getAnswers();
		if(allQuestions == null)
		{
			output.drawDisplay("No survey loaded");
		}
		
		for (int i = 0; i < allQuestions.size(); i++)
		{
			allQuestions.get(i).displayQuestion();
			output.drawDisplay("The correct answer is: ");
			output.drawDisplay(allAnswers.get(i));
			output.drawDisplay();
		}
		
		this.mainMenuStart();
		
	}
	
	
	// function to save survey
	public void saveSurvey()
	{
		if(survey == null)
		{
			output.drawDisplay("No survey loaded");
		}
		else
		{
			try {
				survey.save();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				output.drawDisplay("Could not properly save survey");
				output.drawDisplay();
				
			}
			
			output.drawDisplay("Survey saved");
			output.drawDisplay();
			
		}
	
		this.mainMenuStart();
		
	}
	
	
	// function to save test
	public void saveTest()
	{
		if(test == null)
		{
			output.drawDisplay("No test loaded");
		}
		else
		{
			try {
				test.save();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				output.drawDisplay("Could not properly save test");
				output.drawDisplay();
			}
			
			output.drawDisplay("Test saved");
			output.drawDisplay();
		}

		this.mainMenuStart();
	}
	
	
	// get all surveys save
	// let user pick survey
	// load survey using load function
	public void loadSurvey()
	{
		output.drawDisplay("Load survey start");
		output.drawDisplay();
		
		output.drawDisplay("Saved survey names are: ");
		output.drawDisplay();
		listSavedSurveys();
		
		boolean doneLoading = false;
		while(!doneLoading)
		{
			output.drawDisplay("Enter the name of the survey you'd like to load(**back to go back to main menu): ");
			String loadSurveyName = reader.getResponse();
			output.drawDisplay();
			
			if(loadSurveyName.length() > 0)
			{
				if(loadSurveyName.equals("**back"))
				{
					this.mainMenuStart();
				}
				else
				{
					ArrayList<String> surveyNames = this.getFolderNames(surveyLocation);
					if (surveyNames.contains(loadSurveyName))
					{
						Survey loadSurvey = new Survey();
						try {
							if (loadSurvey.load(this.surveyLocation, loadSurveyName));
							{
								this.survey = loadSurvey;
								doneLoading = true;
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							output.drawDisplay("File could not bed loaded");
							output.drawDisplay();
							doneLoading = true;
						}
						
					}
					else
					{
						output.drawDisplay("File name not in listed surveys try again");
						output.drawDisplay();
						
					}
				}
			}
			else
			{
				output.drawDisplay("Sorry could not recognize input, try again");
				output.drawDisplay();
			}
			
		}
		
		this.mainMenuStart();
	}
	
	// get all tests save
	// let user pick test
	// load test using load function
	public void loadTest()
	{
		output.drawDisplay("Load test start");
		output.drawDisplay();
		
		output.drawDisplay("Saved test names are: ");
		output.drawDisplay();
		listSavedTests();
		
		boolean doneLoading = false;
		while(!doneLoading)
		{
			output.drawDisplay("Enter the name of the test you'd like to load(**back to go back to main menu): ");
			String loadTestName = reader.getResponse();
			output.drawDisplay();
			
			if(loadTestName.length() > 0)
			{
				if(loadTestName.equals("**back"))
				{
					this.mainMenuStart();
				}
				else
				{
					ArrayList<String> testNames = this.getFolderNames(testLocation);
					if (testNames.contains(loadTestName))
					{
						Test loadTest = new Test();
						try {
							if (loadTest.load(this.testLocation, loadTestName));
							{
								this.test = loadTest;
								doneLoading = true;
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							output.drawDisplay("File could not bed loaded");
							output.drawDisplay();
							doneLoading = true;
						}
						
					}
					else
					{
						output.drawDisplay("File name not in listed tests try again");
						output.drawDisplay();
						
					}
				}
			}
			else
			{
				output.drawDisplay("Sorry could not recognize input, try again");
				output.drawDisplay();
			}
			
		}
		this.mainMenuStart();
		
		
	}
	
	
	// function to list all saved surveys
	public void listSavedSurveys()
	{
		ArrayList<String> surveyNames = this.getFolderNames(surveyLocation);
		
		for(String name : surveyNames)
		{
			output.drawDisplay(name);
			output.drawDisplay();
		}
	}
	
	
	// function to list all saved tests
	public void listSavedTests()
	{
		ArrayList<String> testNames = this.getFolderNames(testLocation);
		
		for(String name : testNames)
		{
			output.drawDisplay(name);
			output.drawDisplay();
		}
	}
	
	
	// function to get "folder" or test/survey names
	public ArrayList<String> getFolderNames(String pathOfFolders)
	{
		File file = new File(pathOfFolders);
		
		ArrayList<String> folderNames = new ArrayList<String>();
		
		String[] fileNames = file.list();
		
		for(String name : fileNames)
		{
			if(new File(pathOfFolders + "/" + name).isDirectory())
			{
				folderNames.add(name);
			}
		}
		
		return folderNames;
		
		
	}
	

}

