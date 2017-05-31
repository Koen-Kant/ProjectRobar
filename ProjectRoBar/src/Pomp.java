import com.pi4j.io.gpio.RaspiPin;

public class Pomp extends Actuator 
{
	private int ID;
	//private pin Pout; 
	public Pomp(String type, int ID) 
	{
		super(type);
		this.ID = ID;
		//Pout = pin[ID]
	}
	
	public void Pomp()
	{
		//Pout = true;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Pout = false;
	}

}
