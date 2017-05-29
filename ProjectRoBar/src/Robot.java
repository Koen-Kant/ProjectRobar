import java.util.ArrayList;
import java.util.Iterator;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Robot 
{
	//variabele
	private String naam;
	private Location HuLo;
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
		while(!((new SensorIterator()).SensorType("KleurenSensor").MeetIets()==0));
	}
	
	public void VoegCocktailToe(String naam, ReceptActie[] Recept, String Kleur)
	{
		Cocktails.add(new Cocktail(naam, Recept, Kleur));
	}
	
	public Cocktail LeesGlas()
	{
		//krijg een waarde van de kleurensensor
		String Kleur = null;
		//geeft out de waarde van 1 van de cocktails die aan de kleur gelinkt is
		Cocktail out = (new CocktailIterator()).CocktialType(Kleur);
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
			ToPossision(Location.START);
			//pomp 1 WIP
			break;
		case CONDIMENT2:
			ToPossision(Location.START);
			//pomp 2 WIP
			break;
		case CONDIMENT3:
			ToPossision(Location.START);
			//pomp 3 WIP
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
			ToPossision(Location.FLES1);
			(new ActuatorIterator()).ActuatorType("LinAct").DoeIets();
			break;
		case BOTTLE2:
			ToPossision(Location.FLES2);
			(new ActuatorIterator()).ActuatorType("LinAct").DoeIets();
			break;
		case BOTTLE3:
			ToPossision(Location.FLES3);
			(new ActuatorIterator()).ActuatorType("LinAct").DoeIets();
			break;
		case BOTTLE4:
			ToPossision(Location.FLES4);
			(new ActuatorIterator()).ActuatorType("LinAct").DoeIets();
			break;
		case BOTTLE5:
			ToPossision(Location.FLES5);
			(new ActuatorIterator()).ActuatorType("LinAct").DoeIets();
			break;
		case BOTTLE6:
			ToPossision(Location.FLES6);
			(new ActuatorIterator()).ActuatorType("LinAct").DoeIets();
			break;
		default:
			break;
		}
	}
	
	private void Stirr()
	{
		for(int i=0;i<5;i++)
		{
		ToPossision(Location.START);
		ToPossision(Location.FLES5);
		}
	}
	
	private void Pomp(ReceptActie Drank)
	{
		switch(Drank)
		{
		case PUMP1:
			(new ActuatorIterator()).ActuatorType("Pomp1").DoeIets();
			break;
		case PUMP2:
			(new ActuatorIterator()).ActuatorType("Pomp2").DoeIets();
			break;
		case PUMP3:
			(new ActuatorIterator()).ActuatorType("Pomp3").DoeIets();
			break;
		case PUMP4:
			(new ActuatorIterator()).ActuatorType("Pomp4").DoeIets();
			break;
		default:
			break;
		}
	}
	
	private void Clean()
	{
		ToPossision(Location.FRIS);
		(new ActuatorIterator()).ActuatorType("Pomp4").DoeIets();
		Stirr();
	}
	
	private void ReturnCocktail()
	{
		ToPossision(Location.START);
		(new ActuatorIterator()).ActuatorType("Kieper").DoeIets();
	}
	
	private void ToPossision(Location Place) 
	{
		if(Place.ordinal() > HuLo.ordinal() && Place.ordinal() < HuLo.ordinal()+5)
		{
			(new ActuatorIterator()).ActuatorType("Draaiwiel").DoeIets(); //CCW
		}
		else 
		{
			if(HuLo.ordinal() == 8 && (Place.ordinal()>= 0&&Place.ordinal() <5)||HuLo.ordinal() == 7 && (Place.ordinal()>= 0&&Place.ordinal() <4)||HuLo.ordinal()==6 &&(Place.ordinal()>=0 && Place.ordinal()<3)||HuLo.ordinal()==5&&(Place.ordinal()==0&&Place.ordinal()==1)||HuLo.ordinal()==4&&Place.ordinal()==0)
					{
				(new ActuatorIterator()).ActuatorType("Draaiwiel").DoeIets(); //CCW
					}
			else
			{
				(new ActuatorIterator()).ActuatorType("Draaiwiel").DoeIets(); //CCW
			}
		}
			
	}

	public class CocktailIterator implements Iterator
	{
		private int i;
		private Cocktail Hold;
		
		public CocktailIterator()
		{
			i = 0;
			Hold = null;
		}
		
		@Override
		public boolean hasNext() {
			if(Cocktails.size() < i)
			{return true;}
			else
			{return false;}
		}
		@Override
		public Cocktail next() 
		{
			return Cocktails.get(i++);
		}	
		
		public Cocktail CocktialType(String Kleur)
		{
			while(CocktailIterator.this.hasNext())
			{
				Hold = CocktailIterator.this.next();
				if(Hold.GetKleur()==Kleur)
				{return Hold;}
			}
			return null;
		}
	}
	
	public class ActuatorIterator implements Iterator
	{
		private int i;
		private Actuator Hold;
		
		public ActuatorIterator()
		{
			i = 0;
			Hold = null;
		}
		
		@Override
		public boolean hasNext() {
			if(Actuators.size() < i)
			{return true;}
			else
			{return false;}
		}

		@Override
		public Actuator next() {
			return Actuators.get(i++);
		}
		
		public Actuator ActuatorType(String type)
		{
			while(ActuatorIterator.this.hasNext())
			{
				Hold = ActuatorIterator.this.next();
				if(Hold.GetType()==type)
				{return Hold;}
			}
			return null;
		}
	}
	
	public class SensorIterator implements Iterator
	{
		private int i;
		private Sensor Hold;
		
		public SensorIterator()
		{
			i = 0;
			Hold = null;
		}
		
		@Override
		public boolean hasNext() {
			if(Sensors.size() < i)
			{return true;}
			else
			{return false;}
		}

		@Override
		public Sensor next() 
		{
			return Sensors.get(i++);
		}
		
		public Sensor SensorType(String type)
		{
			while(SensorIterator.this.hasNext())
			{
				Hold = SensorIterator.this.next();
				if(Hold.GetType()==type)
				{return Hold;}
			}
			return null;
		}
	}
}


