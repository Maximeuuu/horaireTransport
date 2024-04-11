package fr.transport.modele.filtrage;

import fr.transport.modele.entite.Periode;
import fr.transport.modele.entite.LigneTransport;

public class FiltreTrajetPendantPeriode implements IFiltre
{
	private Periode periode;

	public FiltreTrajetPendantPeriode( Periode periode )
	{
		this.periode = periode;
	}

	@Override
	public void filtrer( LigneTransport ligneTransport )
	{
		ligneTransport.getEnsTrajet().removeIf( trajet -> !trajet.pendantPeriode( this.periode ) );
	}
}
