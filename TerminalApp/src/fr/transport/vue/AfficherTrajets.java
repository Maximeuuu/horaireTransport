package fr.transport.vue;

import fr.transport.Controleur;

public class AfficherTrajets
{
	private Controleur ctrl;

	public AfficherTrajets( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.ctrl.appliquerFiltres();
		System.out.println( this.ctrl.getLigneTransport() );
	}
}