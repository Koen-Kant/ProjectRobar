import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class Pomp 
{
	private GpioPinDigitalOutput Pout;
	
	public Pomp(GpioPinDigitalOutput nPout) 
	{
		Pout = nPout;
	}
	
	public void PompVloeistof()
	{
		Pout.pulse(1000);
	}

}
