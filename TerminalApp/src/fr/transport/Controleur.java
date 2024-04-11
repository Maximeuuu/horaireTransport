package fr.transport;

import fr.transport.modele.entite.*;
import fr.transport.modele.filtrage.*;
import fr.transport.modele.importation.LectureCSV;
import fr.transport.vue.Terminal;

import java.io.File;

public class Controleur
{
	private static final String REPERTOIRE = "data";
	public static void main( String[] args )
	{
		new Controleur();
	}

	public Controleur()
	{
		new Terminal( this );
	}

	public File[] getFichiersData()
	{
		File dir = new File( REPERTOIRE );
		return dir.listFiles();
	}

	public int demanderChoix()
	{
		return Integer.parseInt( System.console().readLine() );
	}

	public String demanderChoixChaine()
	{
		return System.console().readLine();
	}

	public void appliquerFiltres()
	{
		// TODO
	}

	public LigneTransport getLigneTransport()
	{
		// TODO
		return null;
	}

	public void filtrer( String nomFiltre, Object... valeurs )
	{
		// TODO
	}

	/*
	 * String fileName = "data/ligne20_le-havre_caudebec-en-caux.csv";

		File fichier = new File( fileName );

		LectureCSV lecture = new LectureCSV( fichier );
		
		LigneTransport ligne = lecture.getLigneTransport();

		Lieu arretDepart = ligne.getEnsArretOrdonne().get(15);
		Lieu arretDestination = ligne.getEnsArretOrdonne().get(38);

		RechercheTrajet recherche = new RechercheTrajet( arretDepart, arretDestination );
		recherche.setJour( Jour.LUNDI );
		recherche.setPeriode( Periode.TA );
		recherche.addArretPassage( ligne.getEnsArretOrdonne().get( 19 ) );
		recherche.filtrer(ligne);

		Map<Lieu, List<Heure>> mapHeuresArrets = UtilitaireRechercheTrajet.getEnsembleHeuresParArrets( ligne, recherche );
		
		System.out.println( UtilitaireRechercheTrajet.periodeEtJourToString( recherche ) );
		System.out.println( UtilitaireRechercheTrajet.heuresParArretsToString( mapHeuresArrets ) );
	 */
}
