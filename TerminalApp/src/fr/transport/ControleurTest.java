package fr.transport;

import java.io.File;
import java.util.List;
import java.util.Map;

import fr.transport.modele.entite.*;
import fr.transport.modele.importation.LectureCSV;
import fr.transport.modele.outils.UtilitaireRechercheTrajet;

public class ControleurTest
{
	public static void main( String[] args )
	{
		new ControleurTest();
	}

	public ControleurTest()
	{
		String fileName = "data/ligne20_caudebec-en-caux_le-havre.csv";

		File fichier = new File( fileName );

		LectureCSV lecture = new LectureCSV( fichier );
		
		LigneTransport ligne = lecture.getLigneTransport();

		Lieu arretDepart = ligne.getEnsArretOrdonne().get(15);
		Lieu arretDestination = ligne.getEnsArretOrdonne().get(38);

		RechercheTrajet recherche = new RechercheTrajet( arretDepart, arretDestination );
		recherche.setJour( Jour.LUNDI );
		recherche.setPeriode( Periode.TA );
		//recherche.addArretPassage( ligne.getEnsArretOrdonne().get( 19 ) );
		recherche.filtrer(ligne);

		Map<Lieu, List<Heure>> mapHeuresArrets = UtilitaireRechercheTrajet.getEnsembleHeuresParArrets( ligne, recherche );
		
		System.out.println( UtilitaireRechercheTrajet.periodeEtJourToString( recherche ) );
		System.out.println( UtilitaireRechercheTrajet.heuresParArretsToString( mapHeuresArrets ) );
	}
}
