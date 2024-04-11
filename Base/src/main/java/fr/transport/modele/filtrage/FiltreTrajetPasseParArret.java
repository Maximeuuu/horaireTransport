package fr.transport.modele.filtrage;

import fr.transport.modele.entite.Lieu;
import fr.transport.modele.entite.LigneTransport;
import fr.transport.modele.entite.Trajet;

import java.util.List;

public class FiltreTrajetPasseParArret implements IFiltre
{
	private Lieu lieu;

	public FiltreTrajetPasseParArret( Lieu lieu )
	{
		this.lieu = lieu;
	}

	@Override
	public void filtrer( LigneTransport ligneTransport )
	{
		List<Trajet> ensTrajet = ligneTransport.getEnsTrajet();
		
		ensTrajet.removeIf( trajet -> !trajet.passeParArret( this.lieu ) );
	}
}
