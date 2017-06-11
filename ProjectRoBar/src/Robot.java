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
	
	@SuppressWarnings("unused")
	private Thread P1T;
	@SuppressWarnings("unused")
	private Thread P2T;
	@SuppressWarnings("unused")
	private Thread P3T;
	@SuppressWarnings("unused")
	private Thread P4T;
	@SuppressWarnings("unused")
	private Thread P5T;
	@SuppressWarnings("unused")
	private Thread MWT;
	@SuppressWarnings("unused")
	private Thread LAT;
	@SuppressWarnings("unused")
	private Thread MKT;
	@SuppressWarnings("unused")
	private Thread LST;
	@SuppressWarnings("unused")
	private Thread KST;
	
	final GpioController gpio;

	private GpioPinDigitalOutput n04;
	private GpioPinDigitalOutput n17;
	private GpioPinDigitalOutput n18;
	private GpioPinDigitalOutput n27;
	private GpioPinDigitalOutput n22;
	private GpioPinDigitalInput n23;
	private GpioPinAnalogInput n24;
	private GpioPinDigitalOutput n05; 
	private GpioPinDigitalOutput n25; 
	private GpioPinDigitalOutput n06; 
	private GpioPinDigitalOutput n12; 
	private GpioPinDigitalOutput n13; 

	//constructor
	public Robot(String nnaam)
	{
		gpio = GpioFactory.getInstance();
		n04 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Pomp1", PinState.LOW);
		n17 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_17, "Pomp2", PinState.LOW);
		n18 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18, "Pomp3", PinState.LOW);
		n27 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, "Pomp4", PinState.LOW);
		n22 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22, "Pomp5", PinState.LOW);
		n05 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "LiAc", PinState.LOW);
		n25 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "WielA", PinState.LOW);
		n06 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "WielB", PinState.LOW);
		n12 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12, "KiepA", PinState.LOW);
		n13 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, "KiepB", PinState.LOW);
		n23 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_23, "LS");
		n24 = gpio.provisionAnalogInputPin(RaspiPin.GPIO_24, "CS");
		
		Cocktails = new ArrayList<Cocktail>();
		
		P1 = new Pomp(n04);
		P2 = new Pomp(n17);
		P3 = new Pomp(n18);
		P4 = new Pomp(n27);
		P5 = new Pomp(n22);

		LS = new LichtSluis(n23);
		KS = new KleurSen(n24);
		
		MW = new MotorWiel(n05, n06);
		LA = new LinAct(n25);
		MK = new MotorKiep(n12, n13);
		
		(P1T = new Thread(P1)).start();
		(P2T = new Thread(P2)).start();
		(P3T = new Thread(P3)).start();
		(P4T = new Thread(P4)).start();
		(P5T = new Thread(P5)).start();
		(MWT = new Thread(MW)).start();
		(LAT = new Thread(LA)).start();
		(MKT = new Thread(MK)).start();
		(LST = new Thread(LS)).start();
		(KST = new Thread(KS)).start();
		
		this.VoegCocktailToe(null, null, null);
	}
	
	//methodes
	public void WachtTotGlas()
	{
		while(!KS.ValData());
	}
	
	public void VoegCocktailToe(String naam, ReceptActie[] Recept, Kleur Kleur)
	{
		Cocktails.add(new Cocktail(naam, Recept, Kleur));
	}
	
	public Cocktail LeesGlas()
	{
		Kleur Kleur = null;
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
			while(LA.complete());
			break;
		case CONDIMENT2:
			ToPossision(Location.START);
			LA.Druk();
			while(LA.complete());
			break;
		case CONDIMENT3:
			ToPossision(Location.START);
			LA.Druk();
			while(LA.complete());
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
			while(LA.complete());
			break;
		case BOTTLE2:
			ToPossision(Location.FLES2);
			LA.Druk();
			while(LA.complete());
			break;
		case BOTTLE3:
			ToPossision(Location.FLES3);
			LA.Druk();
			while(LA.complete());
			break;
		case BOTTLE4:
			ToPossision(Location.FLES4);
			LA.Druk();
			while(LA.complete());
			break;
		case BOTTLE5:
			ToPossision(Location.FLES5);
			LA.Druk();
			while(LA.complete());
			break;
		case BOTTLE6:
			ToPossision(Location.FLES6);
			LA.Druk();
			while(LA.complete());
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
			while(P1.Completed());
			break;
		case PUMP2:
			P2.PompVloeistof();
			while(P2.Completed());
			break;
		case PUMP3:
			P3.PompVloeistof();
			while(P3.Completed());
			break;
		case PUMP4:
			P4.PompVloeistof();
			while(P4.Completed());
			break;
		default:
			break;
		}
	}
	
	private void Clean()
	{
		ToPossision(Location.FRIS);
		P5.PompVloeistof();
		while(P5.Completed());
		Stirr();
		ToPossision(Location.FRIS);
		MK.Kiep();
		MK.Completed();
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
		}
		else 
		{
			if(HuLo.ordinal() == 7 && (Place.ordinal()>= 0&&Place.ordinal() <4)||HuLo.ordinal()==6 &&(Place.ordinal()>=0 && Place.ordinal()<3)||HuLo.ordinal()==5&&(Place.ordinal()==0&&Place.ordinal()==1)||HuLo.ordinal()==4&&Place.ordinal()==0)
			{
				MW.ZetRichting("CCW");
				MW.GaToggle();
				LS.Verder(HuLo.ordinal()-Place.ordinal());
			}
			
			else
			{
				MW.ZetRichting("CCW");
				MW.GaToggle();
				LS.Verder(HuLo.ordinal()-Place.ordinal());
			}
		}
		HuLo = Place;
		while(LS.hasArrived());
		MW.GaToggle();
	}

	public class CocktailIterator implements Iterator<Object>
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
		
		public Cocktail CocktialType(Kleur kleur)
		{
			while(CocktailIterator.this.hasNext())
			{
				Hold = CocktailIterator.this.next();
				if(Hold.GetKleur()==kleur)
				{return Hold;}
			}
			return null;
		}
	}
}


