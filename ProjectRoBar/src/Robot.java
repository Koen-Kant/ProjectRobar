import java.util.ArrayList;

public class Robot 
{
	//variabele
	private String naam;
	//links
	private ArrayList<Cocktail> Cocktails = new ArrayList<Cocktail>();  
	private Sensor[] Sensors;
	private Actuator[] Actuators;
	//constructor
	public Robot(String nnaam)
	{
		naam = nnaam;
		Sensors = new Sensor[6];
		Sensors[0] = new Sensor();
		Sensors[1] = new Sensor();
		Sensors[2] = new Sensor();
		Sensors[3] = new Sensor();
		Sensors[4] = new Sensor();
		Sensors[5] = new Sensor();
		Actuators = new Actuator[6];
		Actuators[0] = new Actuator();
		Actuators[1] = new Actuator();
		Actuators[2] = new Actuator();
		Actuators[3] = new Actuator();
		Actuators[4] = new Actuator();
		Actuators[5] = new Actuator();				
	}
	//methodes
	public void WachtTotGlas()
	{
		//While(noglass)
	}
	
	public Cocktail LeesGlas()
	{
		//Leesglas
		//get receptacties[] from saved cocktail
		ReceptActie[] RA = new ReceptActie[3]; 
		Cocktail out = new Cocktail("Bla", RA);
		return out;
	}
	
	public void MaakCoctail(Cocktail Recept)
	{
		int i=0;
		ReceptActie[] Hold = Recept.GetRecept();
		while(i<Hold.length)
		{
			DoeReceptActie(Hold[i]);
			i++;
		}
	}
	
	private void DoeReceptActie(ReceptActie Stap)
	{
		switch(Stap)
		{
		case BOTTLE1:
			break;
		case BOTTLE2:
			break;
		case BOTTLE3:
			break;
		case BOTTLE4:
			break;
		case BOTTLE5:
			break;
		case BOTTLE6:
			break;
		case PUMP1:
		case PUMP2:
		case PUMP3:
		case PUMP4:
			Pomp(Stap);
			break;
		case CONDIMENT1:
		case CONDIMENT2:
		case CONDIMENT3:
			Condiment(Stap);
			break;
		case STIRR:
			Stirr();
			break;
		}
		
	}
	
	private void Condiment(ReceptActie Condiment) 
	{
		switch(Condiment)
		{
		case CONDIMENT1:
			//pomp 1
			break;
		case CONDIMENT2:
			//pomp 2
			break;
		case CONDIMENT3:
			//pomp 3 
			break;
		default:
			break;
		}

	}
	private void Stirr()
	{
		//DoStirr
	}
	
	private void Pomp(ReceptActie Drank)
	{
		switch(Drank)
		{
		case PUMP1:
			//pomp 1
			break;
		case PUMP2:
			//pomp 2
			break;
		case PUMP3:
			//pomp 3 
			break;
		case PUMP4:
			//pomp 4
			break;
		default:
			break;
		}
	}
	
	private void Clean()
	{
		//DoClean
	}
	
	private void ReturnCocktail()
	{
		//DoReturnCoctail
	}
}


