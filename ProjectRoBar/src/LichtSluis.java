import com.pi4j.io.gpio.GpioPinDigitalInput;

public class LichtSluis implements Runnable
{
	private GpioPinDigitalInput Pout;
	private int LocGo;
	
	public LichtSluis(GpioPinDigitalInput nPout) 
	{
		Pout = nPout;
		LocGo = 0;
	}
	
	public void Verder(int LocatiesVerder)
	{
		LocGo = LocatiesVerder;
	}
	
	public boolean hasArrived()
	{
		return LocGo == 0;
	}

	@Override
	public void run() 
	{
		while(true)
		{
			if(LocGo > 0)
			{
				while(Pout.isLow());
				try {Thread.sleep(500);} 
				catch (InterruptedException e) {e.printStackTrace();}
				LocGo--;
			}
		}
		
	}

}
