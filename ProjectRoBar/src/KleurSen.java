import com.pi4j.io.gpio.GpioPinAnalogInput;

public class KleurSen implements Runnable
{
	private double Meet;
	private double Out;
	private GpioPinAnalogInput Pout;
	private boolean ValDate;
	
	public KleurSen(GpioPinAnalogInput nPout) 
	{
		Pout = nPout;
		Meet = 0;
		Out = 0;
	}
	
	public Kleur MeetKleur()
	{ //this assumes  the sensor in this configuration provides a value between 0 and 255 where  0 is black and 255 is white 
		if(Out <30)
		{return Kleur.ZWART;}
		else if(Out < 60)
		{return Kleur.ORANJE;}
		else if(Out < 90)
		{return Kleur.ROOD;}
		else if(Out < 120)
		{return Kleur.ROZE;}
		else if(Out < 150)
		{return Kleur.GROEN;}
		else if(Out < 180)
		{return Kleur.BLAUW;}
		else if(Out < 210)
		{return Kleur.WIT;}
		else
		{return null;}
		
	}
	
	public boolean ValData()
	{
		return ValDate;
	}

	@Override
	public void run() 
	{
		while(true)
		{
			Meet = Pout.getValue();
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			if(Pout.getValue() == Meet)
			{
				Out = Meet;
			}
			else
			{
				ValDate = false;
			}
		}
	}

}
