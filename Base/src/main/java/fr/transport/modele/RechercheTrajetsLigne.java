package fr.transport.modele;

import java.util.List;
import java.util.Map;

import fr.transport.modele.entite.*;
import fr.transport.modele.outils.UtilitaireRechercheTrajet;

public class RechercheTrajetsLigne extends RechercheTrajet
{
	private LigneTransport ligneTransport;

	/* CONSTRUCTEUR */

	public RechercheTrajetsLigne( LigneTransport ligneTransport, Lieu arretDepart, Lieu arretDestination )
	{
		super( arretDepart, arretDestination );
		this.ligneTransport = ligneTransport;
	}

	/* ACCESSEUR */

	public Map<Lieu, List<Heure>> rechercher()
	{
		LigneTransport ligneModifiee = this.filtrer( this.ligneTransport );

		Map<Lieu, List<Heure>> mapHeuresArrets = UtilitaireRechercheTrajet.getEnsembleHeuresParArrets( ligneModifiee, this );

		return mapHeuresArrets;
	}

	public List<Heure[]> rechercherHorairesTrajet()
	{
		LigneTransport ligneModifiee = this.filtrer( this.ligneTransport );

		List<Heure[]> ensHoraireTrajet = UtilitaireRechercheTrajet.getHorairesTrajets( ligneModifiee, this );

		return ensHoraireTrajet;
	}
}
