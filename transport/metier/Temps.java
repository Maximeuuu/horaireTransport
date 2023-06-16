/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 15/04/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

package transport.metier;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Temps
{
	private int heure;
	private int minute;

	/*Méthodes utilitaire*/
	public static int heureToMinute( double heure ){ return (int)(heure * 60.0); }
	public static double minuteToHeure( int minute ){ return minute / 60.0; }

	public static boolean estTemps( String s )
	{
		String separateur = Temps.findSeparateur( s );
		return separateur != null;
	}

	public static String findSeparateur(String s) { //chatGPT
	    Pattern pattern = Pattern.compile("[:h,]");
	    Matcher matcher = pattern.matcher(s);
	    if (matcher.find()) {
	        return matcher.group();
	    } else {
	        return null;
	    }
	}

	public static int difference( Temps temps1, Temps temps2 )
	{
		return temps1.compareTo( temps2 );
	}


	/*Constructeurs*/
	public Temps( int heure, int minute )
	{
		this.heure  = 0;
		this.minute = 0;
		this.setHeure ( heure );
		this.setMinute( minute );
	}

	public Temps( int minute )
	{
		this( 0, minute );
	}

	public Temps( double heure )
	{
		this( (int)(Temps.heureToMinute( heure )) );
	}

	/*public Temps( String sTemps, String separateur )
	{
		String[] decomp = sTemps.split( separateur );
		this.setHeure ( Integer.parseInt(decomp[0]) );
		if( decomp[1].equals("") ){ decomp[1]="0"; }
		this.setMinute( Integer.parseInt(decomp[1]) );
	}*/

	public Temps(String sTemps, String separateur ) { //chatGPT
	    Pattern pattern = Pattern.compile("(\\d{1,2})"+separateur+"?(\\d{2})?");
	    Matcher matcher = pattern.matcher(sTemps);
	    if (matcher.matches()) {
	        int heure = Integer.parseInt(matcher.group(1));
	        int minute = matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 0;
	        this.setHeure(heure);
	        this.setMinute(minute);
	    } else {
	        throw new IllegalArgumentException("format d'heure invalide : " + sTemps);
	    }
	}

	public Temps( String sTemps )
	{
		this( sTemps, Temps.findSeparateur( sTemps ) );
	}

	public Temps( Temps temps )
	{
		this.heure  = temps.heure;
		this.minute = temps.minute;
	}

	/*Accesseurs*/
	public int getHeure(){ return this.heure; }
	public int getMinute(){ return this.minute; }

	/*Setteurs*/
	public boolean setHeure( int heure )
	{
		if( heure < 0 ){ return false; }
		this.heure = heure;
		return true;
	}

	public boolean setMinute( int minute )
	{
		if( minute < 0 ){ return false; }
		if( minute >= 60 )
		{
			this.heure += minute/60;
			minute = minute%60;
		}
		this.minute = minute;
		return true;
	}

	/*Sorties*/
	public int toMinute()
	{
		return Temps.heureToMinute( this.heure ) + this.minute;
	}

	public double toHeure()
	{
		return this.heure + Temps.minuteToHeure( this.minute );
	}

	public String toString()
	{
		return this.heure + ":" + String.format( "%02d", this.minute );
	}

	public String toString4Digits()
	{
		return String.format( "%02d", this.heure ) + ":" + String.format( "%02d", this.minute );
	}

	/*Comparaisons*/
	public boolean equals( Temps temps )
	{
		return temps.heure == this.heure && temps.minute == this.minute;
	}

	public int compareTo( Temps temps )
	{
		return this.toMinute() - temps.toMinute();
	}

	/*Méthodes*/
	public void arrondir( int arrondi ) //exemple : int = 15 -> arrondis les minutes en 0,15, 30,45
	{
		int minute = (int) Math.round((double) this.minute / arrondi) * arrondi;
    	if (minute == 60)
		{
        	this.minute = 0;
        	this.heure++;
    	}
		else
		{
        	this.minute = minute;
    	}
	}
}
