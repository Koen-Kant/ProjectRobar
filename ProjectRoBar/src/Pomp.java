import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class Pomp implements Runnable
{
	private GpioPinDigitalOutput Pout;
	private boolean go;
	
	public Pomp(GpioPinDigitalOutput nPout) 
	{
		Pout = nPout;
		go = false;
	}
	
	public void PompVloeistof()
	{
		go = true;
	}

	public boolean Completed()
	{
		return !go;
	}
	@Override
	public void run() 
	{
		while(true)
		{
			if(go)
			{
				Pout.pulse(1000);
				go = false;
			}
		}
		
	}

}
