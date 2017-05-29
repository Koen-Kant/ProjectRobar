public class Actuator implements Act
{
	private String type;
	
	public Actuator(String type)
	{
		this.type = type;
	}
	
	@Override
	public int DoeIets() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int GetStatus() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String GetType()
	{
		return type;
	}

}
