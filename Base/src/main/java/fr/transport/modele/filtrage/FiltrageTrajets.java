package fr.transport.modele.filtrage;

import fr.transport.modele.entite.LigneTransport;

import java.util.ArrayList;
import java.util.List;

public class FiltrageTrajets implements IFiltre
{
	private List<IFiltre> filtres;

	public FiltrageTrajets()
	{
		this.filtres = new ArrayList<IFiltre>();
	}

	public void addFiltre( IFiltre filtre )
	{
		this.filtres.add( filtre );
	}

	public void resetFiltres()
	{
		this.filtres.clear();
	}

	public void filtrer( LigneTransport ligneTransport ) //TODO: créer une copie avant ?
	{
		for( IFiltre filtre : this.filtres )
		{
			filtre.filtrer( ligneTransport );
		}
	}
}
