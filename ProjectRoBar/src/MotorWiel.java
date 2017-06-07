import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class MotorWiel 
{
	private GpioPinDigitalOutput Pout;
	private String Richting;
	
	public MotorWiel(GpioPinDigitalOutput nPout) {
		Pout = nPout;
		Richting = "CW";
	}
	
	public void ZetRichting(String richting)
	{
		if(richting == "CW")
		{
			Richting = "CW";
		}
		else if(richting == " CCW" )
		{
			Richting = "CCW";
		}
	}
	
	public void GaToggle()
	{
		if(Pout.isHigh())
		{
			Pout.toggle();
		}
		
		else if(Pout.isLow())
		{
			Pout.toggle();
		}
	}
}
