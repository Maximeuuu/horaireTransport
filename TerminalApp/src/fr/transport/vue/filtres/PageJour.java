package fr.transport.vue.filtres;

import fr.transport.Controleur;
import fr.transport.modele.entite.Jour;
import fr.transport.vue.OutilsSaisie;

public class PageJour
{
	private Controleur ctrl;

	public PageJour( Controleur ctrl )
	{
		this.ctrl = ctrl;

		System.out.println( this.afficherChoix() );
		int choix = OutilsSaisie.saisirEntier( "Choix : " );
		this.ctrl.setJour( choix );
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
}
