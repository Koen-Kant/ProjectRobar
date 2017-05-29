public class Sensor implements Sen
{
	private String type;
	
	public Sensor(String type)
	{
		this.type = type;
	}
	
	@Override
	public int MeetIets() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int GetStatus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String GetType() {
		return type;
	}

}
