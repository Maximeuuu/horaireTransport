package fr.transport.modele.entite;

import java.io.Serializable;

public class IntervalleTemps implements IIntervalle, Serializable
{
	public Heure debut;
	public Heure duree;

	public IntervalleTemps( Heure debut, Heure duree )
	{
		this.debut = debut;
		this.duree = duree;
	}

	public Heure getDebut()
	{
		return this.debut;
	}

	public Heure getDuree()
	{
		return this.duree;
	}

	public boolean contient( Heure heure )
	{
		//FIXME: todo
		return false;
	}
}
