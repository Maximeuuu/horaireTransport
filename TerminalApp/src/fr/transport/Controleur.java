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
		LigneTransport ligneTransport = lecture.getLigneTransport();


		Lieu arretDepart = new Lieu( "LE HAVRE/Gare Routière" );
		Lieu arretPassage = new Lieu( "TANCARVILLE BAS/Écluse/École Pican/Petit Pont" );
		Lieu arretDestination = new Lieu( "LILLEBONNE/Théatre Romain - Place F.Faure" );

		FiltrageTrajets filtrage = new FiltrageTrajets();

		filtrage.addFiltre( new FiltreArretsOrdonnes( arretDepart, arretDestination ) );
		filtrage.addFiltre( new FiltreTrajetPasseParArret( arretDepart ) );
		//filtrage.addFiltre( new FiltreTrajetPasseParArret( arretPassage ) );
		filtrage.addFiltre( new FiltreTrajetPasseParArret( arretDestination ) );
		filtrage.addFiltre( new FiltreTrajetPendantPeriode( Periode.PS ) );
		filtrage.addFiltre( new FiltreTrajetJour( Jour.SAMEDI) );
		filtrage.addFiltre( new FiltreArretPendantIntervalle(arretDestination, new IntervalleHeure( new Heure("15:00"), new Heure("18:00") ) ) );
		filtrage.addFiltre( new FiltreArretPendantIntervalle(arretDepart, new IntervalleHeure( new Heure("15:00"), new Heure("18:00") ) ) );

		filtrage.filtrer( ligneTransport );

		System.out.println( ligneTransport );
	 */
}
