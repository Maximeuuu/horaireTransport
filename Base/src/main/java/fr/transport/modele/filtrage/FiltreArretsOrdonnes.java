package fr.transport.modele.filtrage;

import fr.transport.modele.entite.LigneTransport;
import fr.transport.modele.entite.Trajet;
import fr.transport.modele.entite.Lieu;

import java.util.List;

public class FiltreArretsOrdonnes implements IFiltre
{
	private Lieu arretDepart;
	private Lieu arretDestination;

	public FiltreArretsOrdonnes( Lieu arretDepart, Lieu arretDestination )
	{
		this.arretDepart = arretDepart;
		this.arretDestination = arretDestination;
	}

	@Override
	public void filtrer( LigneTransport ligneTransport )
	{
		List<Lieu> ensArret = ligneTransport.getEnsArretOrdonne();
		int nbArretsTrouves = 0;

		for( Lieu arret : ensArret )
		{
			boolean arretDepartTrouve = (arret.equals( this.arretDepart ));
			boolean aucunArretTrouve = (nbArretsTrouves == 0);
			if( arretDepartTrouve && aucunArretTrouve )
			{
				nbArretsTrouves++;
			}

			boolean arretDestinationTrouve = (arret.equals( this.arretDestination ));
			boolean premierArretTrouve = (nbArretsTrouves == 1);
			if( arretDestinationTrouve && premierArretTrouve )
			{
				nbArretsTrouves++;
			}
		}

		if( nbArretsTrouves != 2 )
		{
			List<Trajet> ensTrajet = ligneTransport.getEnsTrajet();
			ensTrajet.clear();
		}
	}
}
