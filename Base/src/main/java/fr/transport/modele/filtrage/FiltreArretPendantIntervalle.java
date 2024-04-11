package fr.transport.modele.filtrage;

import fr.transport.modele.entite.LigneTransport;
import fr.transport.modele.entite.IntervalleHeure;
import fr.transport.modele.entite.Lieu;

public class FiltreArretPendantIntervalle implements IFiltre
{
	private Lieu lieu;
	private IntervalleHeure intervalleHeure;

	public FiltreArretPendantIntervalle( Lieu lieu, IntervalleHeure intervalleHeure )
	{
		this.lieu = lieu;
		this.intervalleHeure = intervalleHeure;
	}

	public void filtrer( LigneTransport ligneTransport )
	{
		ligneTransport.getEnsTrajet().removeIf( trajet -> !trajet.arretPendantIntervalle( this.lieu, this.intervalleHeure ) );
	}
}
