package fr.transport.modele.filtrage;

import fr.transport.modele.entite.LigneTransport;

public interface IFiltre extends java.io.Serializable
{
	public void filtrer( LigneTransport ligneTransport );
}
