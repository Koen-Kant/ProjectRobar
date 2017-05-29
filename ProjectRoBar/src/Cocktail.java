public class Cocktail implements CTP 
{
	private String naam;
	private ReceptActie[] Recept;
	private String Kleur;
	
	public Cocktail(String nnaam, ReceptActie[] nRecept, String nKleur)
	{
		Kleur = nKleur;
		naam = nnaam;
		Recept = nRecept;
	}
	@Override
	public String GetName() {
		return naam;
	}
	@Override
	public ReceptActie[] GetRecept() {
		return Recept;
	}
	

}
