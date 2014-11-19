import java.util.ArrayList;


public class Menu {
	
	
	// Need a List for menu options
	ArrayList<String> menuOptions;
	
	// Need a string for a title for the menu
	private String title;
	
	// prompt text for user
	private String prompt;
	
	Display console = new Display();
	
	
	
	// default constructor
	public Menu() 
	{
		this.menuOptions = new ArrayList<String>();
		
		this.setMenuTitle("");
	}
	
	// constructor for menu with given title
	public Menu(String title)
	{
		this.menuOptions = new ArrayList<String>();
		
		this.setMenuTitle(title);
		
	}
	
	// add new option to menu
	public void addOption(String menuOption)
	{
		this.menuOptions.add(menuOption);
	}
	
	// remove option from menu
	public void removeOption(int optIndex)
	{
		this.menuOptions.remove(optIndex);
	}

	
	// function to display the menu
	public void displayMenu()
	{
		// need to make sure there are options in to display
		if(menuOptions.size() > 0)
		{
			
			console.drawDisplay();
			console.drawDisplay(this.getMenuTitle());
			console.drawDisplay();
			
			
			int optionCount = 1;
			for(int i = 0; i < menuOptions.size(); i++)
			{
				if (!menuOptions.get(i).isEmpty())
				{
					console.drawDisplay(optionCount + ") ");
					console.drawDisplay(menuOptions.get(i));
					console.drawDisplay();
					optionCount++;
				}

			}
		}
		else
		{
			console.drawDisplay("Menu is empty");
		}
	}
	
	
	// prompt user to pick options
	public String promptUser()
	{
		Input userInput = new Input();
		
		console.drawDisplay(this.prompt);
		console.drawDisplay();
		
		String response = userInput.getResponse();
		
		return response;
		
	}
	
	
	// start running the menu
	public int runMenu()
	{
		String response;
		
		int doneRunning = 0;
		int optionNumber = 0;
		
		while(doneRunning == 0)
		{
			this.displayMenu();
			
			this.setPrompt("Select an option");
			response = this.promptUser();
			
			optionNumber = Integer.parseInt(response)-1;
			console.drawDisplay("You have selected the option: " + menuOptions.get(optionNumber));
			console.drawDisplay();
			doneRunning = 1;
		}
		
		return (optionNumber + 1);
		
	}
	
	
	// set the title of the menu
	public void setMenuTitle(String title)
	{
		this.title = title;
	}
	
	// get title
	public String getMenuTitle()
	{
		return this.title;
	}
	
	// set prompt
	public void setPrompt(String prompt)
	{
		this.prompt = prompt;		
	}
	
	
	// get prompt
	public String getPrompt()
	{
		return this.prompt;
	}
	
	
	

	

}
