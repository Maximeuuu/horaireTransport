package fr.transport.modele.entite;

import java.io.Serializable;

public class Heure implements Comparable<Heure>, Serializable
{
	private int heure;
	private int minute;
	public static final Heure MINUIT = new Heure( 0, 0 );
	public static final Heure MAX = new Heure( 23, 59 );
	public static final Heure MIN = new Heure( 0, 0 );

	/* CONSTRUCTEURS */

	public Heure( int heure, int minute )
	{
		this.heure = heure;
		this.minute = minute;
	}

	public Heure( String heure )
	{
		String[] tab = heure.split( ":" );
		this.heure = Integer.parseInt( tab[0] );
		this.minute = Integer.parseInt( tab[1] );
	}

	/* ACCESSEURS */

	public int getHeure()
	{
		return heure;
	}

	public int getMinute()
	{
		return minute;
	}

	/* METHODES */

	@Override
	public String toString()
	{
		return String.format( "%02d:%02d", this.heure, this.minute );
	}

	@Override
	public boolean equals( Object obj )
	{
		if( obj instanceof Heure )
		{
			Heure heure = (Heure) obj;
			return this.heure == heure.heure && this.minute == heure.minute;
		}

		return false;
	}

	@Override
	public int compareTo( Heure heure )
	{
		if( this.heure < heure.heure )
		{
			return -1;
		}
		else if( this.heure > heure.heure )
		{
			return 1;
		}
		else
		{
			if( this.minute < heure.minute )
			{
				return -1;
			}
			else if( this.minute > heure.minute )
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
	}
}
