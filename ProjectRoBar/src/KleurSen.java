import com.pi4j.io.gpio.GpioPinAnalogInput;

public class KleurSen 
{
	private double Meet;
	private double Out;
	private GpioPinAnalogInput Pout;
	
	public KleurSen(GpioPinAnalogInput nPout) 
	{
		Pout = nPout;
		Meet = 100;
		Out = 100;
	}
	
	public String MeetKleur()
	{
		Out = Pout.getValue();
		if(Out == 0)
		{return "Zwart";}
		else if(Out == 50)
		{return "Grijs";}
		else
		{return "Wit";}
		
	}
	
	public boolean ValData()
	{
		Meet = Pout.getValue();
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		if(Meet == Pout.getValue())
		{return true;}
		else
		{return false;}
	}

}
