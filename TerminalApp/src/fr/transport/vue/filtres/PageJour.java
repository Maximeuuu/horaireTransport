package fr.transport.vue.filtres;

import fr.transport.Controleur;
import fr.transport.modele.entite.Jour;

public class PageJour
{
	private Controleur ctrl;

	public PageJour( Controleur ctrl )
	{
		this.ctrl = ctrl;

		System.out.println( this.afficherChoix() );
		int choix = this.ctrl.demanderChoix();
		this.traiter( choix );
	}

	private String afficherChoix()
	{
		StringBuilder sb = new StringBuilder();
		for( Jour jour : Jour.values() )
		{
			sb.append( jour.getIndice() + " - " + jour.getNom() + "\n" );
		}
		return sb.toString();
	}

	private void traiter( int choix )
	{
		Jour jour = Jour.getJour( choix );
		this.ctrl.filtrer( "jour", jour );
	}
}
