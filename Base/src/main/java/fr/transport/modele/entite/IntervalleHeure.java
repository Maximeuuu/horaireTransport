package fr.transport.modele.entite;

import java.io.Serializable;

@Deprecated
public class IntervalleHeure implements IIntervalle, Serializable
{
	private Heure heureDebut;
	private Heure heureFin;

	public IntervalleHeure( Heure heureDebut, Heure heureFin )
	{
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}

	public Heure getHeureDebut()
	{
		return this.heureDebut;
	}

	public Heure getHeureFin()
	{
		return this.heureFin;
	}

	public boolean contient( Heure heure )
	{
		return this.heureDebut.compareTo( heure ) <= 0 && this.heureFin.compareTo( heure ) >= 0;
	}
}
