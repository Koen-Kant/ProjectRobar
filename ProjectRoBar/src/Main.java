public class Main 
{
	public static void main(String[] args) 
	{
        Robot CR = new Robot("HALL-9000");
        
        ReceptActie[] PH = new ReceptActie[5];
        PH[0] = ReceptActie.BOTTLE1;
        PH[1] = ReceptActie.BOTTLE2;
        PH[2] = ReceptActie.PUMP1;
        PH[3] = ReceptActie.PUMP1;
        PH[4] = ReceptActie.PUMP1;
        CR.VoegCocktailToe("Black Pearl", PH, Kleur.ZWART);
        
        PH = new ReceptActie[2];
        PH[0] = ReceptActie.BOTTLE3;
        PH[1] = ReceptActie.BOTTLE4;
        CR.VoegCocktailToe("Black Russian", PH, Kleur.ROOD);
        
        PH = new ReceptActie[4];
        PH[0] = ReceptActie.BOTTLE6;
        PH[1] = ReceptActie.PUMP1;
        PH[2] = ReceptActie.PUMP1;
        PH[3] = ReceptActie.PUMP1;
        CR.VoegCocktailToe("Cuba Libre", PH, Kleur.GROEN);
        
        PH = new ReceptActie[3];
        PH[0] = ReceptActie.BOTTLE1;
        PH[1] = ReceptActie.BOTTLE4;
        PH[2] = ReceptActie.BOTTLE5;
        CR.VoegCocktailToe("Black Russian", PH, Kleur.ORANJE);
        
        PH = new ReceptActie[4];
        PH[0] = ReceptActie.BOTTLE2;
        PH[1] = ReceptActie.BOTTLE2;
        PH[2] = ReceptActie.BOTTLE1;
        PH[3] = ReceptActie.BOTTLE1;
        CR.VoegCocktailToe("Red Caribian", PH, Kleur.BLAUW);  
        
        PH = new ReceptActie[4];
        PH[0] = ReceptActie.BOTTLE2;
        PH[1] = ReceptActie.BOTTLE2;
        PH[2] = ReceptActie.PUMP3;
        PH[3] = ReceptActie.PUMP3;
        CR.VoegCocktailToe("Cupido", PH, Kleur.WIT);  
        
        PH = new ReceptActie[4];
        PH[0] = ReceptActie.BOTTLE3;
        PH[1] = ReceptActie.BOTTLE3;
        PH[2] = ReceptActie.PUMP2;
        PH[3] = ReceptActie.PUMP2;
        CR.VoegCocktailToe("Screwdriver", PH, Kleur.ROZE); 
        
        PH = null;
        
        while(true)
        {
        	CR.WachtTotGlas();
        	CR.MaakCoctail(CR.LeesGlas());        	
        }
        
        
		//-Fles1: Malibu
		//-Fles2: Flugel
		//-Fles3: Wodka
		//-Fles4: Kahlua
		//-Fles5: Tequilla
		//-Fles6: Rum
        
		//-Pomp1: Cola
		//-Pomp2: Sinasappelsap
		//-Pomp3: Cassis
		//-Pomp4: 
		//-Pomp5: 
		//-Pomp6: Water
    }
}
