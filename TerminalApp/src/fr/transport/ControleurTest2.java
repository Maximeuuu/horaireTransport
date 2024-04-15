package fr.transport;

import java.io.File;
import java.util.List;
import java.util.Map;

import fr.transport.modele.entite.*;
import fr.transport.modele.importation.LectureCSV;
import fr.transport.modele.outils.UtilitaireRechercheTrajet;
import fr.transport.modele.RechercheTrajetsLigne;

public class ControleurTest2
{
	public static void main( String[] args )
	{
		new ControleurTest2();
	}

	public ControleurTest2()
	{
		String fileName = "data/ligne20_caudebec-en-caux_le-havre.csv";

		File fichier = new File( fileName );

		LectureCSV lecture = new LectureCSV( fichier );
		
		LigneTransport ligne = lecture.getLigneTransport();

		Lieu arretDepart = ligne.getEnsArretOrdonne().get(15);
		Lieu arretDestination = ligne.getEnsArretOrdonne().get(38);

		RechercheTrajetsLigne recherche = new RechercheTrajetsLigne( ligne, arretDepart, arretDestination );
		recherche.setJour( Jour.LUNDI );
		recherche.setPeriode( Periode.PS );
		//recherche.addArretPassage( ligne.getEnsArretOrdonne().get( 19 ) );

		Map<Lieu, List<Heure>> mapHeuresArrets = recherche.rechercher();
		
		System.out.println( UtilitaireRechercheTrajet.periodeEtJourToString( recherche ) );
		System.out.println( UtilitaireRechercheTrajet.heuresParArretsToString( mapHeuresArrets ) );
	}
}
