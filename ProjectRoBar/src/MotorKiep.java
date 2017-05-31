public class MotorKiep extends Actuator 
{
	//private pin Pout;
	
	public MotorKiep(String type) {
		super(type);
		// TODO Auto-generated constructor stub
		//Pout = pin[Kiep]
	}
	
	public void Kiep()
	{
		//pout = true;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//pout = false;
	}

}
