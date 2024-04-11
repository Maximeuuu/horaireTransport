package fr.transport.modele.filtrage;

import fr.transport.modele.entite.Jour;
import fr.transport.modele.entite.LigneTransport;

public class FiltreTrajetJour implements IFiltre
{
	private Jour jour;

	public FiltreTrajetJour( Jour jour )
	{
		this.jour = jour;
	}

	@Override
	public void filtrer( LigneTransport ligneTransport )
	{
		ligneTransport.getEnsTrajet().removeIf( trajet -> !trajet.pendantJour( this.jour ) );
	}
}
