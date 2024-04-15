package fr.transport.vue;

import fr.transport.Controleur;
import fr.transport.modele.entite.*;
import fr.transport.modele.filtrage.*;
import fr.transport.modele.importation.LectureCSV;

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
		int choix = OutilsSaisie.saisirEntier( "Choix : " );
		this.ctrl.ouvrirFichier( fichiers[choix-1] );


		System.out.println( this.ctrl.arretsToString() );
		int choixArretDepart = OutilsSaisie.saisirEntier( "Choisir un arret de depart : " );
		int choixArretDestination = OutilsSaisie.saisirEntier( "Choisir un arret de destination : " );
		this.ctrl.setArrets( choixArretDepart-1, choixArretDestination-1 );

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
