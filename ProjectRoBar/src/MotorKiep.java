import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class MotorKiep 
{
	private GpioPinDigitalOutput Pout;
	
	public MotorKiep(GpioPinDigitalOutput nPout) {
		Pout = nPout;
	}
	
	public void Kiep()
	{
		//TODO
		
		Pout.toggle();
		
		//wait
		
		Pout.toggle();
	}

}
