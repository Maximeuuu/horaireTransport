package fr.transport.vue;

import fr.transport.Controleur;
import fr.transport.vue.filtres.*;

public class MenuFiltres
{
	private Controleur ctrl;

	public MenuFiltres( Controleur ctrl )
	{
		this.ctrl = ctrl;

		System.out.println( this.afficherMenu() );
		int choix = OutilsSaisie.saisirEntier( "Choix : " );
		this.rediriger( choix );
	}

	private String afficherMenu()
	{
		StringBuilder sb = new StringBuilder();
		sb.append( "1 - Période\n" );
		sb.append( "2 - Jour\n" );
		sb.append( "3 - Arrets\n" );
		sb.append( "4 - Intervalle pour un arret\n" );
		sb.append( "5 - Retour\n" );
		return sb.toString();
	}

	private void rediriger( int choix )
	{
		switch( choix )
		{
			case 1:
				new PagePeriode( this.ctrl );
				new MenuFiltres( this.ctrl );
				break;
			case 2:
				new PageJour( this.ctrl );
				new MenuFiltres( this.ctrl );
				break;
			case 3:
				new PageArrets( this.ctrl );
				new MenuFiltres( this.ctrl );
				break;
			/*case 4:
				new PageIntervalle( this.ctrl );
				new MenuFiltres( this.ctrl );
				break;*/
			case 5:
				break;
		}
	}
}
