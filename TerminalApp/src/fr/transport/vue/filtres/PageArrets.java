package fr.transport.vue.filtres;

import fr.transport.Controleur;
import fr.transport.vue.OutilsSaisie;

public class PageArrets
{
	private Controleur ctrl;

	public PageArrets( Controleur ctrl )
	{
		this.ctrl = ctrl;

		System.out.println( this.afficherChoix() );
		int choix = OutilsSaisie.saisirEntier( "Choix : " );
		this.traiter( choix );
	}
	
	private String afficherChoix()
	{
		StringBuilder sb = new StringBuilder();
		sb.append( "1 - Depart\n" );
		sb.append( "2 - Passage\n" );
		sb.append( "3 - Arrivée\n" );
		sb.append( "4 - Retour\n" );
		return sb.toString();
	}

	private void traiter( int choix )
	{
		int indiceArret = -1;
		if( choix == 1 || choix == 2 || choix == 3 )
			indiceArret = OutilsSaisie.saisirEntier( "Numéro de l'arret : " ) - 1;

		switch( choix )
		{
			case 1:
				this.ctrl.setArretDepart( indiceArret );
				break;
			case 2:
				this.ctrl.addArretPassage( indiceArret );
				break;
			case 3:
				this.ctrl.setArretDestination( indiceArret );
				break;
			case 4:
				break;
		}
	}
}
