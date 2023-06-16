/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 05/04/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

public class Tableur extends TableauObjets
{
	public static final int ASC = 1;
	public static final int DESC = 0;

	public Tableur( Object[][] tab )
	{
		super( tab );
	}

	public TableauObjets toTableauObjets()
	{
		return (TableauObjets)(this);
	}

	public void withLowerThan( int colonne, Object valeur, boolean inclusif )
	{
		this.tabObjets = AnalyserTabObjet.filterByLowerThan( this.tabObjets, colonne, valeur, inclusif );
	}

	public void withUpperThan( int colonne, Object valeur, boolean inclusif )
	{
		this.tabObjets = AnalyserTabObjet.filterByUpperThan( this.tabObjets, colonne, valeur, inclusif );
	}

	public void withEqualsTo( int colonne, Object valeur )
	{
		this.tabObjets = AnalyserTabObjet.filterByEqualsTo( this.tabObjets, colonne, valeur );
	}

	public void withNotNull( int colonne )
	{
		this.tabObjets = AnalyserTabObjet.filterByNotNull( this.tabObjets, colonne );
	}

	public void withNotNull( int colDeb, int colFin, Object valeur )
	{
		this.tabObjets = AnalyserTabObjet.filterByNotNull( this.tabObjets, colDeb, colFin );
	}

	public void orderBy( int colonne, int ordre ) //0 ou 1 et utiliser compareTo
	{
		this.tabObjets = AnalyserTabObjet.orderBy( this.tabObjets, colonne, ordre );
	}
}
