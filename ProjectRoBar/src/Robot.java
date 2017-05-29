import java.util.ArrayList;
import java.util.Iterator;

public class Robot 
{
	//variabele
	private String naam;
	//links
	private ArrayList<Cocktail> Cocktails;  
	private ArrayList<Sensor> Sensors;
	private ArrayList<Actuator> Actuators;
	//constructor
	public Robot(String nnaam)
	{
		naam = nnaam;
		Cocktails = new ArrayList<Cocktail>();
		Sensors = new ArrayList<Sensor>();
		Sensors.add(new Sensor("LichtSluisje"));
		Sensors.add(new Sensor("KleurenSensor"));
		Actuators = new ArrayList<Actuator>();
		Actuators.add(new Actuator("Pomp1"));
		Actuators.add(new Actuator("Pomp2"));
		Actuators.add(new Actuator("Pomp3"));
		Actuators.add(new Actuator("Pomp4"));
		Actuators.add(new Actuator("Pomp5"));
		Actuators.add(new Actuator("Draaiwiel"));
		Actuators.add(new Actuator("LinAct"));
		Actuators.add(new Actuator("Kieper"));
	}
	//methodes
	public void WachtTotGlas()
	{
		//While(noglass)
	}
	
	public void VoegCocktailToe(String naam, ReceptActie[] Recept, String Kleur)
	{
		Cocktails.add(new Cocktail(naam, Recept, Kleur));
	}
	
	public Cocktail LeesGlas()
	{
		//Leeskleur
		//geeft out de waarde van 1 van de cocktails die aan de kleur gelinkt is
		ReceptActie[] RA= new ReceptActie[4]; //placehloder
		Cocktail out = new Cocktail("WIP", RA, "WIT");
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
		ReturnCocktail();
		Clean();
	}
	
	private void DoeReceptActie(ReceptActie Stap)
	{
		switch(Stap)
		{
		case BOTTLE1:
		case BOTTLE2:
		case BOTTLE3:
		case BOTTLE4:
		case BOTTLE5:
		case BOTTLE6:
			Fles(Stap);
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
	private void Fles(ReceptActie Fles)
	{
		switch(Fles)
		{
		case BOTTLE1:
			//Goto(Fless1)
			//GetDrank
			break;
		case BOTTLE2:
			//Goto(Fless2)
			//GetDrank
			break;
		case BOTTLE3:
			//Goto(Fless3)
			//GetDrank
			break;
		case BOTTLE4:
			//Goto(Fless4)
			//GetDrank
			break;
		case BOTTLE5:
			//Goto(Fless5)
			//GetDrank
			break;
		case BOTTLE6:
			//Goto(Fless6)
			//GetDrank
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
	
	public class CocktailIterator implements Iterator
	{
		@Override
		public boolean hasNext() {
			//if next? T/F
			return false;
		}
		@Override
		public Cocktail next() 
		{
			//next coctail
			return null;
		}	
		
		public Cocktail CocktialType(String type)
		{
			//find coctail with type
			//return cocktil/null
			return null;
		}
	}
	
	public class ActuatorIterator implements Iterator
	{
		@Override
		public boolean hasNext() {
			//if next? T/F
			return false;
		}

		@Override
		public Actuator next() {
			//next Actuator
			return null;
		}
		
		public Actuator ActuatorType(String type)
		{
			//find Actuator with type
			//return Actuator/null
			return null;
		}
	}
	
	public class SensorIterator implements Iterator
	{
		@Override
		public boolean hasNext() {
			//if next? T/F
			return false;
		}

		@Override
		public Object next() {
			//next Actuator
			return null;
		}
		
		public Sensor SensorType(String type)
		{
			//find Sensor with type
			//return Sensor/null
			return null;
		}
	}
}


