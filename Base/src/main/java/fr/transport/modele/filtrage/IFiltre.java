package fr.transport.modele.filtrage;

import fr.transport.modele.entite.LigneTransport;

public interface IFiltre
{
	public void filtrer( LigneTransport ligneTransport );
}
