package fr.transport.modele.outils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.transport.modele.entite.*;

public class UtilitaireRechercheTrajet
{
	private UtilitaireRechercheTrajet() {}

	public static Map<Lieu, List<Heure>> getEnsembleHeuresParArrets( LigneTransport ligneTransport, RechercheTrajet rechercheTrajet )
	{
		Map<Lieu, List<Heure>> mapHeuresArrets = new LinkedHashMap<Lieu, List<Heure>>();

		List<Lieu> ensArretAAfficher = new ArrayList<Lieu>();
		ensArretAAfficher.add( rechercheTrajet.getArretDepart() );
		ensArretAAfficher.addAll( rechercheTrajet.getEnsArretPassage() );
		ensArretAAfficher.add( rechercheTrajet.getArretDestination() );

		for( Lieu arret : ensArretAAfficher )
		{
			mapHeuresArrets.put( arret, new ArrayList<Heure>() );

			for( Trajet trajet : ligneTransport.getEnsTrajet() )
			{
				Heure heure = trajet.getHeureArret( arret );
				mapHeuresArrets.get( arret ).add( heure );
			}
		}

		return mapHeuresArrets;
	}

	public static List<Heure[]> getHorairesTrajets( LigneTransport ligneTransport, RechercheTrajet rechercheTrajet )
	{
		List<Heure[]> ensHoraireTrajet = new ArrayList<Heure[]>();

		List<Lieu> ensArretAAfficher = new ArrayList<Lieu>();
		ensArretAAfficher.add( rechercheTrajet.getArretDepart() );
		ensArretAAfficher.addAll( rechercheTrajet.getEnsArretPassage() );
		ensArretAAfficher.add( rechercheTrajet.getArretDestination() );

		for( Trajet trajet : ligneTransport.getEnsTrajet() )
		{
			Heure[] tabHeure = new Heure[ensArretAAfficher.size()];
			for( int cptHeure = 0; cptHeure < ensArretAAfficher.size(); cptHeure++ )
			{
				Lieu arret = ensArretAAfficher.get( cptHeure );
				Heure heure = trajet.getHeureArret( arret );
				tabHeure[cptHeure] = heure;
			}
			ensHoraireTrajet.add( tabHeure );
		}

		return ensHoraireTrajet;
	}

	public static String heuresParArretsToString( Map<Lieu, List<Heure>> mapHeuresArrets )
	{
		StringBuilder sb = new StringBuilder();

		for( Lieu arret : mapHeuresArrets.keySet() )
		{
			sb.append( String.format("%50s", arret) );
			sb.append( " | " );

			for( Heure heure : mapHeuresArrets.get( arret ) )
			{
				sb.append( String.format("%5s", heure) );
				sb.append( " | " );
			}

			sb.append( "\n" );
		}

		return sb.toString();
	}

	public static String periodeEtJourToString( RechercheTrajet rechercheTrajet )
	{
		StringBuilder sb = new StringBuilder();

		Periode periode = rechercheTrajet.getPeriode();
		if( periode != null )
		{
			sb.append( periode );
			sb.append( " - " );
		}

		Jour jour = rechercheTrajet.getJour();
		if( jour != null )
		{
			sb.append( jour );
		}

		return sb.toString();
	}
}
