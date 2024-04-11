package fr.transport.modele.entite;

public enum Jour
{
	GENERAL		( 9, "Jour" ),
	LUNDI		( 1, "Lundi" ),
	MARDI		( 2, "Mardi" ),
	MERCREDI	( 3, "Mercredi" ),
	JEUDI		( 4, "Jeudi" ),
	VENDREDI	( 5, "Vendredi" ),
	SAMEDI		( 6, "Samedi" ),
	DIMANCHE	( 7, "Dimanche" ),
	FERIE		( 0, "férié" );

	private int indice;
	private String nom;

	/* FACTORY */

	public static Jour getJour( int indice )
	{
		for( Jour jour : Jour.values() )
		{
			if( jour.getIndice() == indice )
			{
				return jour;
			}
		}
		return null;
	}

	/* CONSTRUCTEUR */

	private Jour( int indice, String nom )
	{
		this.indice = indice;
		this.nom = nom;
	}

	/* ACCESSEURS */

	public int getIndice()
	{
		return this.indice;
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