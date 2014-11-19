public class Response {
	
	private String response;
	
	public Response()
	{
		this.response = "";
	}
	
	public Response(String response)
	{
		this.response = response;
	}
	
	public void setResponse(String response)
	{
		this.response = response;
	}
	
	public String getResponse()
	{
		return this.response;
	}
	
	public void displayResponse()
	{
		System.out.print(this.response);
	}
}
