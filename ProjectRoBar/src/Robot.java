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
	//private ArrayList<Sensor> Sensors;
	//private ArrayList<Actuator> Actuators;
	private LichtSluis LS;
	private KleurSen KS;
	
	private Pomp P1;
	private Pomp P2;
	private Pomp P3;
	private Pomp P4;
	private Pomp P5;
	private MotorWiel MW;
	private LinAct LA;
	private MotorKiep MK;
	//constructor
	public Robot(String nnaam)
	{
		naam = nnaam;
		Cocktails = new ArrayList<Cocktail>();
		//Sensors = new ArrayList<Sensor>();
		//Sensors.add(new LichtSluis("LichtSluisje"));
		//Sensors.add(new KleurSen("KleurenSensor"));
		LS = new LichtSluis("LichtSluis");
		KS = new KleurSen("KleurSen");
		//Actuators = new ArrayList<Actuator>();
		//Actuators.add(new Pomp("Pomp1",1));
		//Actuators.add(new Pomp("Pomp2",2));
		//Actuators.add(new Pomp("Pomp3",3));
		//Actuators.add(new Pomp("Pomp4",4));
		//Actuators.add(new Pomp("Pomp5",5));
		//Actuators.add(new MotorWiel("Draaiwiel"));
		//Actuators.add(new LinAct("LinAct"));
		//Actuators.add(new MotorKiep("Kieper"));
		P1 = new Pomp("Pomp1", 1);
		P2 = new Pomp("Pomp2", 2);
		P3 = new Pomp("Pomp3", 3);
		P4 = new Pomp("Pomp4", 4);
		P5 = new Pomp("Pomp5", 5);
		MW = new MotorWiel("Draaiwiel");
		LA = new LinAct("LinAct");
		MK = new MotorKiep("Kieper");
	}
	//methodes
	public void WachtTotGlas()
	{
		while(!KS.ValData());
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
			LA.Druk();
			break;
		case CONDIMENT2:
			ToPossision(Location.START);
			LA.Druk();
			break;
		case CONDIMENT3:
			ToPossision(Location.START);
			LA.Druk();
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
			LA.Druk();
			break;
		case BOTTLE2:
			ToPossision(Location.FLES2);
			LA.Druk();
			break;
		case BOTTLE3:
			ToPossision(Location.FLES3);
			LA.Druk();
			break;
		case BOTTLE4:
			ToPossision(Location.FLES4);
			LA.Druk();
			break;
		case BOTTLE5:
			ToPossision(Location.FLES5);
			LA.Druk();
			break;
		case BOTTLE6:
			ToPossision(Location.FLES6);
			LA.Druk();
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
			P1.Pomp();
			break;
		case PUMP2:
			P2.Pomp();
			break;
		case PUMP3:
			P3.Pomp();
			break;
		case PUMP4:
			P4.Pomp();
			break;
		default:
			break;
		}
	}
	
	private void Clean()
	{
		ToPossision(Location.FRIS);
		P5.Pomp();
		Stirr();
		ToPossision(Location.FRIS);
		MK.Kiep();
	}
	
	private void ReturnCocktail()
	{
		ToPossision(Location.START);
		MK.Kiep();
	}
	
	private void ToPossision(Location Place) 
	{
		if(Place.ordinal() > HuLo.ordinal() && Place.ordinal() < HuLo.ordinal()+5)
		{
			MW.ZetRichting("CW");
			MW.GaToggle();
			LS.Verder(HuLo.ordinal()-Place.ordinal());
			MW.GaToggle();
		}
		else 
		{
			if(HuLo.ordinal() == 7 && (Place.ordinal()>= 0&&Place.ordinal() <4)||HuLo.ordinal()==6 &&(Place.ordinal()>=0 && Place.ordinal()<3)||HuLo.ordinal()==5&&(Place.ordinal()==0&&Place.ordinal()==1)||HuLo.ordinal()==4&&Place.ordinal()==0)
			{
				MW.ZetRichting("CCW");
				MW.GaToggle();
				LS.Verder(HuLo.ordinal()-Place.ordinal());
				MW.GaToggle();
			}
			
			else
			{
				MW.ZetRichting("CCW");
				MW.GaToggle();
				LS.Verder(HuLo.ordinal()-Place.ordinal());
				MW.GaToggle();
			}
		}
		HuLo = Place;	
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
	
/*	public class ActuatorIterator implements Iterator
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
	}*/
}


