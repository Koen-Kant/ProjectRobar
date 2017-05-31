public class KleurSen extends Sensor 
{
	//private pin Pout;
	private int out;
	
	public KleurSen(String type) {
		super(type);
		out = 0;
	}
	
	public int MeetKleur()
	{
		//krijg waarde van pin
		return out;
	}
	
	public boolean ValData()
	{
		//if(Pout == BOggus)
		return true;
	}

}
