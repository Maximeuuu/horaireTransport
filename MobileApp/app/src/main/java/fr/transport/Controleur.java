package fr.transport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.transport.modele.entite.Jour;
import fr.transport.modele.entite.Lieu;
import fr.transport.modele.entite.LigneTransport;
import fr.transport.modele.entite.Periode;
import fr.transport.modele.entite.Trajet;
import fr.transport.modele.filtrage.*;

public class Controleur implements Serializable
{
	private LigneTransport ligneTransport;
	public Lieu lieuDepart;
	public Lieu lieuArrivee;

	public Controleur()
	{
	}

	public void setLigneTransport( LigneTransport ligneTransport )
	{
		this.ligneTransport = ligneTransport;
	}

	public LigneTransport getLigneTransport()
	{
		return this.ligneTransport;
	}

	public List<String> getEnsArret()
	{
		List<String> ensArret = new ArrayList<>();
		for(Lieu arret : this.ligneTransport.getEnsArretOrdonne() )
		{
			ensArret.add( arret.getNom() );
		}
		return ensArret;
	}

	public List<String> getEnsJour()
	{
		List<String> ensJour = new ArrayList<>();
		for( Jour jour : Jour.values() )
		{
			ensJour.add( jour.getNom() );
		}
		return ensJour;
	}

	public List<String> getEnsPeriode() {
		List<String> ensPeriode = new ArrayList<>();
		for (Periode periode : Periode.values()) {
			ensPeriode.add(periode.getNom());
		}
		return ensPeriode;
	}

	public void filtrer( Lieu depart, Lieu arrivee, Periode periode, Jour jour )
	{
		FiltrageTrajets filtrage = new FiltrageTrajets();

		filtrage.addFiltre( new FiltreArretsOrdonnes( depart, arrivee ) );
		filtrage.addFiltre( new FiltreTrajetPasseParArret( depart ) );
		filtrage.addFiltre( new FiltreTrajetPasseParArret( arrivee ) );

		filtrage.addFiltre( new FiltreTrajetPendantPeriode( periode ) );
		filtrage.addFiltre( new FiltreTrajetJour( jour ) );

		filtrage.filtrer( this.ligneTransport );
	}
}
