import com.pi4j.io.gpio.GpioPinDigitalInput;

public class LichtSluis
{
	private int i;
	private GpioPinDigitalInput Pout;
	
	public LichtSluis(GpioPinDigitalInput nPout) 
	{
		Pout = nPout;
		i =0;
	}
	
	public boolean Verder(int LocatiesVerder)
	{
		for(i = 0; i<LocatiesVerder; i++)
		{
			while(Pout.isLow());
			try {Thread.sleep(500);} 
			catch (InterruptedException e) {e.printStackTrace();}
		}
		return true;
	}

}
