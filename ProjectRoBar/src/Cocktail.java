public class Cocktail implements CTP 
{
	private String naam;
	private ReceptActie[] Recept;
	
	public Cocktail(String nnaam, ReceptActie[] nRecept)
	{
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
