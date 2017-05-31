public class Actuator implements Act
{
	private String type;
	
	public Actuator(String type)
	{
		this.type = type;
	}
	
	@Override
	public void DoeIets() {
		// TODO Auto-generated method stub
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
