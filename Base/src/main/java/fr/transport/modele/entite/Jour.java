package fr.transport.modele.entite;

import java.util.ArrayList;
import java.util.List;

public enum Jour
{
	SEMAINE		( 9, "Semaine" ),
	WEEKEND		( 10,"Week-end" ),
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

	public static Jour getJour( String nom )
	{
		for( Jour jour : Jour.values() )
		{
			if( jour.getNom().equals( nom ) )
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

	public static List<Jour> getJoursSemaine()
	{
		List<Jour> jours = new ArrayList<Jour>();
		jours.add( LUNDI );
		jours.add( MARDI );
		jours.add( MERCREDI );
		jours.add( JEUDI );
		jours.add( VENDREDI );
		return jours;
	}

	public static List<Jour> getJoursWeekend()
	{
		List<Jour> jours = new ArrayList<Jour>();
		jours.add( SAMEDI );
		jours.add( DIMANCHE );
		return jours;
	}

	@Override
	public String toString()
	{
		return this.nom;
	}
}