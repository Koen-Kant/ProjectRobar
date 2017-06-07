import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class LinAct 
{
	private GpioPinDigitalOutput Pout;
	
	public LinAct(GpioPinDigitalOutput nPout) {
		Pout = nPout;
	}
	
	public void Druk()
	{
		Pout.toggle();

		//TODO
		//wait
		
		Pout.toggle();
	}

}
