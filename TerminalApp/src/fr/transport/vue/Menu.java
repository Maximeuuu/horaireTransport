package fr.transport.vue;

import fr.transport.Controleur;

public class Menu
{
	private Controleur ctrl;

	public Menu( Controleur ctrl )
	{
		this.ctrl = ctrl;

		System.out.println( this.afficherMenu() );
		int choix = this.ctrl.demanderChoix();
		this.rediriger( choix );
	}

	private String afficherMenu()
	{
		StringBuilder sb = new StringBuilder();
		sb.append( "1 - Filtrer les trajets\n" );
		sb.append( "2 - Afficher les trajets\n" );
		sb.append( "3 - Reinitialiser les filtres\n" );
		sb.append( "4 - Quitter\n" );
		return sb.toString();
	}

	private void rediriger( int choix )
	{
		switch( choix )
		{
			case 1:
				new MenuFiltres( this.ctrl );
				new Menu( this.ctrl );
				break;
			case 2:
				new AfficherTrajets( this.ctrl );
				new Menu( this.ctrl );
				break;
			case 3:
			//TODO: reinitialiser les filtres
				break;
			case 4:
				break;
		}
	}
}
