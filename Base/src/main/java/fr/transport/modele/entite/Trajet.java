package fr.transport.modele.entite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trajet implements Serializable, Cloneable
{
	private List<Jour> ensJour;
	private List<Periode> ensPeriode;
	private Map<Lieu, Heure> mapArrets;

	/* CONSTRUCTEUR */

	public Trajet( )
	{
		this.ensJour = new ArrayList<Jour>();
		this.ensPeriode = new ArrayList<Periode>();
		this.mapArrets = new HashMap<Lieu, Heure>();
	}

	/* ACCESSEUR */

	public Heure getHeureArret( Lieu lieu )
	{
		return mapArrets.get( lieu );
	}

	/* MODIFIEURS */

	public void addJour( Jour jour )
	{
		ensJour.add( jour );
	}

	public void addPeriode( Periode periode )
	{
		ensPeriode.add( periode );
	}

	public void addArret( Lieu arret, Heure heure )
	{
		mapArrets.put( arret, heure );
	}

	/* TESTEURS */

	public boolean pendantJour( Jour jour )
	{
		return ensJour.contains( jour );
	}

	public boolean pendantPeriode( Periode periode )
	{
		return ensPeriode.contains( periode ) || ensPeriode.contains( Periode.TA );
	}

	public boolean passeParArret( Lieu arret )
	{
		return mapArrets.containsKey( arret );
	}

	public boolean arretPendantIntervalle( Lieu lieu, IntervalleHeure intervalleHeure )
	{
		if( !mapArrets.containsKey( lieu ) )
		{
			return false;
		}

		Heure heure = mapArrets.get( lieu );
		return intervalleHeure.contient( heure );
	}

	/* METHODES */

	@Override
	public String toString( )
	{
		StringBuilder sb = new StringBuilder( );
		
		for( Jour jour : ensJour )
		{
			sb.append( jour + " " );
		}

		sb.append( "\n" );
		for( Periode periode : ensPeriode )
		{
			sb.append( periode + " " );
		}
		
		sb.append( "\n" );
		for( Lieu arret : mapArrets.keySet( ) )
		{
			sb.append( arret + " " );
			sb.append( mapArrets.get( arret ) + " " );
		}
		
		return sb.toString( );
	}

	@Override
	public boolean equals( Object obj )
	{
		if( obj instanceof Trajet )
		{
			Trajet trajet = (Trajet) obj;
			return this.ensJour.equals( trajet.ensJour ) && this.ensPeriode.equals( trajet.ensPeriode ) && this.mapArrets.equals( trajet.mapArrets );
		}

		return false;
	}

	@Override
	public Trajet clone( )
	{
		Trajet clone = new Trajet( );

		for( Jour jour : ensJour )
		{
			clone.addJour( jour );
		}

		for( Periode periode : ensPeriode )
		{
			clone.addPeriode( periode );
		}

		for( Lieu arret : mapArrets.keySet( ) )
		{
			clone.addArret( arret, mapArrets.get( arret ) );
		}

		return clone;
	}
}
