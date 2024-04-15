package fr.transport.vue.filtres;

import fr.transport.Controleur;
import fr.transport.modele.entite.Periode;
import fr.transport.vue.OutilsSaisie;

public class PagePeriode
{
	private Controleur ctrl;

	public PagePeriode( Controleur ctrl )
	{
		this.ctrl = ctrl;

		System.out.println( this.afficherChoix() );
		String choix = OutilsSaisie.saisirChaine( "Choix : " );
		this.ctrl.setPeriode( choix );
	}

	private String afficherChoix()
	{
		StringBuilder sb = new StringBuilder();
		for( Periode periode : Periode.values() )
		{
			sb.append( periode.getCode() + " - " + periode.getNom() + "\n" );
		}
		return sb.toString();
	}
}
