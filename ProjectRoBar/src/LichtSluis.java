public class LichtSluis extends Sensor 
{

	private int i;
	public LichtSluis(String type) {
		super(type);
		i =0;
	}
	
	public boolean Verder(int LocatiesVerder)
	{
		i=0;
		while(i<LocatiesVerder)
		{
			//meet pulses
		}
		return true;
	}

}
