public class Cocktail implements CTP 
{
	private String type;
	private ReceptActie[] Recept;
	private String Kleur;
	
	public Cocktail(String nnaam, ReceptActie[] nRecept, String nKleur)
	{
		Kleur = nKleur;
		type = nnaam;
		Recept = nRecept;
	}
	@Override
	public String GetKleur() {
		return Kleur;
	}
	@Override
	public ReceptActie[] GetRecept() {
		return Recept;
	}
	@Override
	public String Type() {
		return type;
	}
	

}
