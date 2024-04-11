package fr.transport.modele.importation;

import fr.transport.modele.entite.LigneTransport;
import fr.transport.modele.entite.Lieu;
import fr.transport.modele.entite.Trajet;

public class AnalyseLigneTransport
{
	private String[][] donnees;

	static final int COL_ARRET = 0;

	static final int DEBUT_COL_INFORMATIONS = 1;
	static final int DEBUT_LIG_INFORMATIONS = 2;

	public AnalyseLigneTransport( String[][] donnees )
	{
		this.donnees = donnees;
	}

	public void completerLigne( LigneTransport ligneTransport )
	{
		this.ajouterArrets( ligneTransport );
		this.ajouterTrajets( ligneTransport );
	}

	private void ajouterArrets( LigneTransport ligneTransport )
	{
		int nbLigne = this.donnees.length;

		for( int cptLig = DEBUT_LIG_INFORMATIONS; cptLig < nbLigne; cptLig++ )
		{
			ligneTransport.addArret( AnalyseLigneTransport.getArret( this.donnees, cptLig ) );
		}
	}

	private void ajouterTrajets( LigneTransport ligneTransport )
	{
		int nbColonne = this.donnees[0].length;

		for( int cptCol = DEBUT_COL_INFORMATIONS; cptCol < nbColonne; cptCol++ )
		{
			Trajet trajet = new Trajet( );
		
			AnalyseTrajet analyseTrajet = new AnalyseTrajet( this.donnees );
			analyseTrajet.completerTrajet( trajet, cptCol );

			ligneTransport.addTrajet( trajet );
		}
	}

	public static Lieu getArret( String[][] donnees, int lig )
	{
		String nomArret = donnees[lig][COL_ARRET];
		return new Lieu( nomArret );
	}
}
