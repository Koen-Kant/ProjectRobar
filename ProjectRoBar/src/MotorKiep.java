import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class MotorKiep implements Runnable
{

	private GpioPinDigitalOutput Pout1, Pout2;
	private boolean go;
	
	public MotorKiep(GpioPinDigitalOutput nPout1, GpioPinDigitalOutput nPout2) 
	{
		Pout1 = nPout1;
		Pout2= nPout2;
		Pout1.setState(PinState.LOW);
		Pout2.setState(PinState.LOW);
	}
	
	public void Kiep()
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
				Pout2.setState(PinState.HIGH);
				
				try {Thread.sleep(500);}catch (InterruptedException e){e.printStackTrace();}
				
				Pout1.toggle();
				Pout2.toggle();
				
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				
				Pout1.setState(PinState.LOW);
				Pout2.setState(PinState.LOW);
				go = false;
			}
		}
		
	}

}
