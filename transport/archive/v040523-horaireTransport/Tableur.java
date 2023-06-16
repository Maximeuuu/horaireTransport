public class Tableur extends TableauValeurs
{
	public static final int ASC = 1;
	public static final int DESC = 0;

	public Tableur( String[] entetes, Object[][] tab )
	{
		super( entetes, tab );
	}

	public TableauValeurs toTabValeur()
	{
		return (TableauValeurs)(this);
	}

	public void withLowerThan( int colonne, Object valeur, boolean inclusif )
	{
		super.tabDonnees = AnalyserTabObjet.filterByLowerThan( super.tabDonnees, colonne, valeur, inclusif );
	}

	public void withUpperThan( int colonne, Object valeur, boolean inclusif )
	{
		super.tabDonnees = AnalyserTabObjet.filterByUpperThan( super.tabDonnees, colonne, valeur, inclusif );
	}

	public void withEqualsTo( int colonne, Object valeur )
	{
		super.tabDonnees = AnalyserTabObjet.filterByEqualsTo( super.tabDonnees, colonne, valeur );
	}

	public void withNotNull( int colonne )
	{
		super.tabDonnees = AnalyserTabObjet.filterByNotNull( super.tabDonnees, colonne );
	}

	public void withNotNull( int colDeb, int colFin, Object valeur )
	{
		super.tabDonnees = AnalyserTabObjet.filterByNotNull( super.tabDonnees, colDeb, colFin );
	}

	public void orderBy( int colonne, int ordre ) //0 ou 1 et utiliser compareTo
	{
		super.tabDonnees = AnalyserTabObjet.orderBy( super.tabDonnees, colonne, ordre );
	}
}
