package fr.transport.modele.importation;

import fr.transport.modele.entite.Trajet;
import fr.transport.modele.entite.Jour;
import fr.transport.modele.entite.Periode;
import fr.transport.modele.entite.Heure;
import fr.transport.modele.entite.Lieu;

class AnalyseTrajet
{
	private String[][] donnees;

	private static final int LIG_DATES = 0;
	private static final int LIG_PERIODES = 1;

	private static final String SEPARATEUR_VALEURS = ";";
	private static final String SANS_HEURE = "|";

	public AnalyseTrajet( String[][] donnees )
	{
		this.donnees = donnees;
	}

	public void completerTrajet( Trajet trajet, int colonne )
	{
		this.ajouterJours( trajet, colonne );
		this.ajouterPeriodes( trajet, colonne );
		this.ajouterHeures( trajet, colonne );
	}

	private void ajouterJours( Trajet trajet, int colonne )
	{
		String celluleDate = this.donnees[LIG_DATES][colonne];
		String[] ensIndiceJour = celluleDate.split( SEPARATEUR_VALEURS );

		for( String indiceJour : ensIndiceJour )
		{
			if( indiceJour.equals( "ferie" ) ){ indiceJour = "0"; }
			Jour jour = Jour.getJour( Integer.parseInt( indiceJour ) );
			trajet.addJour( jour );
		}
	}

	private void ajouterPeriodes( Trajet trajet, int colonne )
	{
		String cellulePeriode = this.donnees[LIG_PERIODES][colonne];
		String[] ensCodePeriode = cellulePeriode.split( SEPARATEUR_VALEURS );

		for( String codePeriode : ensCodePeriode )
		{
			Periode periode = Periode.getPeriode( codePeriode );
			trajet.addPeriode( periode );
		}
	}

	private void ajouterHeures( Trajet trajet, int colonne )
	{
		int nbLigne = this.donnees.length;

		for( int cptLig = AnalyseLigneTransport.DEBUT_LIG_INFORMATIONS; cptLig < nbLigne; cptLig++ )
		{
			String celluleHeure = this.donnees[cptLig][colonne];

			boolean aHeure = ! celluleHeure.equals( SANS_HEURE );
			if( aHeure )
			{
				Heure heure = new Heure( celluleHeure );
				Lieu arret = AnalyseLigneTransport.getArret( this.donnees, cptLig );
				trajet.addArret( arret, heure );
			}
		}
	}
}