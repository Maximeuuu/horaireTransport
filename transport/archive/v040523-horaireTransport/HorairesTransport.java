public class HorairesTransport
{
	private String file;
	private Tableur tableur;

	public HorairesTransport( String file )
	{
		this.file = file;
		this.tableur = new Tableur( HorairesTransport.chargerEntetes(), HorairesTransport.chargerDonnees() );
	}

	public static Object[][] chargerDonnees()
	{ // à remplacer par une lecture du "file"
		return new Object[][]
				{
					{1, new Temps(8,30), new Temps(9,00), new Temps(0,30)},
					{2, new Temps(12,00), new Temps(13,15), new Temps(1,15)},
					{3, new Temps(18,15), new Temps(18,45), new Temps(0,30)}
				};
	}

	public static String[] chargerEntetes()
	{ //à remplacer par une lecture du "file"
		return new String[]{"n°", "depart", "arrivee", "temps"};
	}

	public Tableur getTableur()
	{
		return this.tableur;
	}
}
