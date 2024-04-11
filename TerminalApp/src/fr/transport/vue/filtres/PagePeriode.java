package fr.transport.vue.filtres;

import fr.transport.Controleur;
import fr.transport.modele.entite.Periode;

public class PagePeriode
{
	private Controleur ctrl;

	public PagePeriode( Controleur ctrl )
	{
		this.ctrl = ctrl;

		System.out.println( this.afficherChoix() );
		String choix = this.ctrl.demanderChoixChaine();
		this.traiter( choix );
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

	private void traiter( String choix )
	{
		Periode periode = Periode.getPeriode( choix );
		this.ctrl.filtrer( "periode", periode );
	}
}
