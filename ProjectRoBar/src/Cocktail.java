public class Cocktail implements CTP 
{
	private String type;
	private ReceptActie[] Recept;
	private Kleur Kleur;
	
	public Cocktail(String nnaam, ReceptActie[] nRecept, Kleur nKleur)
	{
		Kleur = nKleur;
		type = nnaam;
		Recept = nRecept;
	}
	@Override
	public Kleur GetKleur() {
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
