import java.util.ArrayList;
import java.util.Iterator;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinAnalogInput;
import com.pi4j.io.gpio.GpioPinDigitalInput;
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
	
	final GpioController gpio;
	//private GpioPinDigitalOutput SDA = null;
	//private GpioPinDigitalOutput SCL = null;
	//private GpioPinDigitalOutput TXD = null;
	//private GpioPinDigitalOutput RX0 = null;
	private GpioPinDigitalOutput n04;
	private GpioPinDigitalOutput n17;
	private GpioPinDigitalOutput n18;
	private GpioPinDigitalOutput n27;
	private GpioPinDigitalOutput n22;
	private GpioPinDigitalInput n23;
	private GpioPinAnalogInput n24;
	private GpioPinDigitalOutput n25;
	//private GpioPinDigitalOutput MOSI = null;
	//private GpioPinDigitalOutput CLK = null;
	//private GpioPinDigitalOutput CE0 = null;
	//private GpioPinDigitalOutput CE1 = null;
	private GpioPinDigitalOutput n05; 
	private GpioPinDigitalOutput n06;
	//private GpioPinDigitalOutput n12 = null;
	//private GpioPinDigitalOutput n13 = null;
	//private GpioPinDigitalOutput n16 = null;
	//private GpioPinDigitalOutput n19 = null;
	//private GpioPinDigitalOutput n20 = null;
	
	//constructor
	public Robot(String nnaam)
	{
		naam = nnaam;
		gpio = GpioFactory.getInstance();
		n04 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Pomp1", PinState.LOW);
		n17 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_17, "Pomp2", PinState.LOW);
		n18 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18, "Pomp3", PinState.LOW);
		n27 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, "Pomp4", PinState.LOW);
		n22 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22, "Pomp5", PinState.LOW);
		n25 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "Kiep", PinState.LOW);
		n05 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "LiAc", PinState.LOW);
		n06 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "Wiel", PinState.LOW);
		n23 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_23, "LS");
		n24 = gpio.provisionAnalogInputPin(RaspiPin.GPIO_24, "CS");
		
		Cocktails = new ArrayList<Cocktail>();
		
		LS = new LichtSluis(n23);
		KS = new KleurSen(n24);
		P1 = new Pomp(n04);
		P2 = new Pomp(n17);
		P3 = new Pomp(n18);
		P4 = new Pomp(n27);
		P5 = new Pomp(n22);
		MW = new MotorWiel(n06);
		LA = new LinAct(n05);
		MK = new MotorKiep(n25);
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
		String Kleur = null;
		Kleur = KS.MeetKleur();
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
			P1.PompVloeistof();
			break;
		case PUMP2:
			P2.PompVloeistof();
			break;
		case PUMP3:
			P3.PompVloeistof();
			break;
		case PUMP4:
			P4.PompVloeistof();
			break;
		default:
			break;
		}
	}
	
	private void Clean()
	{
		ToPossision(Location.FRIS);
		P5.PompVloeistof();
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
}


