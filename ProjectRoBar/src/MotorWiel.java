import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class MotorWiel implements Runnable
{
	private GpioPinDigitalOutput Pout1, Pout2;
	private boolean CCW;
	private boolean go;
	public MotorWiel(GpioPinDigitalOutput nPout1, GpioPinDigitalOutput nPout2) 
	{
		Pout1 = nPout1;
		Pout2 = nPout2;
		CCW= true;
		Pout1.setState(PinState.LOW);
		Pout2.setState(PinState.LOW);
	}
	
	public void ZetRichting(String richting)
	{
		if(richting == "CCW")
		{
			CCW = true;
		}
		else if(richting == "CW")
		{
			CCW = false;
		}
	}
	
	public void GaToggle()
	{
		if(go)
		{
			go = false;
		}
		else if(!go)
		{
			go = true;
		}
	}

	@Override
	public void run() 
	{
		while(true)
		{
			if(go)
			{
				if(CCW)
				{
					Pout1.setState(PinState.HIGH);
				}
				else if(!CCW)
				{
					Pout2.setState(PinState.HIGH);
				}
					
			}
			else
			{
				Pout1.setState(PinState.LOW);
				Pout1.setState(PinState.LOW);
			}
		}
		
	}
}
