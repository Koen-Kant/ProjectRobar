import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class LinAct implements Runnable
{
	private GpioPinDigitalOutput Pout;
	private boolean go;
	
	public LinAct(GpioPinDigitalOutput nPout) {
		Pout = nPout;
		go = false;
	}
	
	public void Druk()
	{
		go = true;
	}
	
	public boolean complete()
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
				Pout.setState(PinState.HIGH);
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				Pout.setState(PinState.LOW);
				go = false;
			}
		}
		
	}

}
