package fr.transport.vue.filtres;

import fr.transport.Controleur;

public class PageArrets
{
	private Controleur ctrl;

	public PageArrets( Controleur ctrl )
	{
		this.ctrl = ctrl;

		System.out.println( this.afficherChoix() );
		int choix = this.ctrl.demanderChoix();
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
		switch( choix )
		{
			case 1:
				this.ctrl.filtrer( "arret", "depart" );
				break;
			case 2:
				this.ctrl.filtrer( "arret", "passage" );
				break;
			case 3:
				this.ctrl.filtrer( "arret", "arrivee" );
				break;
			case 4:
				break;
		}
	}

	private void gererArret()
	{
		System.out.print( "Numéro de l'arret : " );
		int choix = this.ctrl.demanderChoix();
		this.ctrl.filtrer( "arret", choix );
	}
}
