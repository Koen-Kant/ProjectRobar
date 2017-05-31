public class MotorWiel extends Actuator 
{
	private int ID;
	//private pin Pout;
	private String Richting;
	
	public MotorWiel(String type) {
		super(type);
		// TODO Auto-generated constructor stub
		//Pout = pin[Wiel]
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
		//if(Pout==false)
		//{
		//	Pout = true;
		//}
		
		//else if(Pout==true)
		//{
		//	Pout = false;
		//}
	}
}
