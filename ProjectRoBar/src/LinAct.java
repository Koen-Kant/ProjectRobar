public class LinAct extends Actuator 
{
	//private pin Pout;
	
	public LinAct(String type) {
		super(type);
		// TODO Auto-generated constructor stub
		//Pout = Pin[LinAct]
	}
	
	public void Druk()
	{
		//Pout = true;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Pout = false;
	}

}
