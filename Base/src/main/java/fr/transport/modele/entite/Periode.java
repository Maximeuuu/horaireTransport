package fr.transport.modele.entite;

public enum Periode
{
	TA	( "ta", "Toute l'année" ),
	PS	( "ps", "Période scolaire" ),
	ETE	( "ete", "Période estivale" ),
	VS	( "vs", "Vacances scolaires" );

	private String code;
	private String nom;

	/* FACTORY */

	public static Periode getPeriode( String code )
	{
		for( Periode periode : Periode.values() )
		{
			if( periode.getCode().equals( code ) )
			{
				return periode;
			}
		}
		return null;
	}

	public static Periode getPeriodeParNom( String nom )
	{
		for( Periode periode : Periode.values() )
		{
			if( periode.getNom().equals( nom ) )
			{
				return periode;
			}
		}
		return null;
	}

	/* CONSTRUCTEUR */

	private Periode( String code, String nom )
	{
		this.code = code;
		this.nom = nom;
	}

	/* ACCESSEURS */

	public String getCode()
	{
		return this.code;
	}

	public String getNom()
	{
		return this.nom;
	}

	/* METHODE */

	@Override
	public String toString()
	{
		return this.nom;
	}
}
