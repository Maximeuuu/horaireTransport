package fr.transport.vue;

import fr.transport.Controleur;
import fr.transport.modele.entite.*;
import fr.transport.modele.filtrage.*;
import fr.transport.modele.importation.LectureCSV;

import java.io.Console;
import java.io.File;

public class Terminal
{
	private Controleur ctrl;

	public Terminal( Controleur ctrl )
	{
		this.ctrl = ctrl;

		System.out.println("=== Bienvenue dans le terminal de l'application de transport ===\n");

		System.out.println( "Choisir un fichier :" );
		File[] fichiers = ctrl.getFichiersData();
		System.out.println( afficherFichiers( fichiers ) );
		int choix = this.ctrl.demanderChoix();

		LectureCSV lecture = new LectureCSV( fichiers[choix-1] );
		LigneTransport ligneTransport = lecture.getLigneTransport();

		int cpt = 1;
		for( Lieu arret : ligneTransport.getEnsArretOrdonne() )
		{
			System.out.println( cpt + " - " + arret );
			cpt++;
		}

		System.out.print( "Choisir un arret de depart : " );
		int choixArretDepart = this.ctrl.demanderChoix();
		Lieu arretDepart = ligneTransport.getEnsArretOrdonne().get( choixArretDepart-1 );

		System.out.print( "Choisir un arret de destination : " );
		int choixArretDestination = this.ctrl.demanderChoix();
		Lieu arretDestination = ligneTransport.getEnsArretOrdonne().get( choixArretDestination-1 );

		FiltrageTrajets filtrage = new FiltrageTrajets();
		filtrage.addFiltre( new FiltreArretsOrdonnes( arretDepart, arretDestination ) );
		filtrage.addFiltre( new FiltreTrajetPasseParArret( arretDepart ) );
		filtrage.addFiltre( new FiltreTrajetPasseParArret( arretDestination ) );

		new Menu( this.ctrl );
	}

	private String afficherFichiers( File[] fichiers )
	{
		StringBuilder sb = new StringBuilder();
		int cpt = 1;
		for( File fichier : fichiers )
		{
			sb.append( cpt + " - " + fichier.getName() + "\n" );
			cpt++;
		}
		return sb.toString();
	}
}
