package fr.transport.modele.entite;

public enum Periode
{
	TA	( "ta", "Toute l'année" ),
	PS	( "ps", "Période scolaire" ),
	ETE	( "ete", "Période estivale" ),
	VS	( "vs", "Vacances scolaires" );

	private String code;
	private String nom;

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

	private Periode( String code, String nom )
	{
		this.code = code;
		this.nom = nom;
	}

	public String getCode()
	{
		return this.code;
	}

	public String getNom()
	{
		return this.nom;
	}

	@Override
	public String toString()
	{
		return this.nom;
	}
}
