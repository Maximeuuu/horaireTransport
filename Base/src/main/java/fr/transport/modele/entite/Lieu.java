package fr.transport.modele.entite;

import java.io.Serializable;

public class Lieu implements Serializable, Cloneable
{
	private String nom;

	/* CONSTRUCTEUR */

	public Lieu( String nom )
	{
		this.nom = nom;
	}

	/* ACCESSEUR */

	public String getNom()
	{
		return this.nom;
	}

	/* METHODES */

	@Override
	public String toString()
	{
		return this.nom;
	}

	@Override
	public boolean equals( Object obj )
	{
		if( obj instanceof Lieu )
		{
			Lieu lieu = (Lieu) obj;
			return this.nom.equals( lieu.nom );
		}

		return false;
	}

	@Override
	public int hashCode()
	{
		return this.nom.hashCode();
	}

	@Override
	public Lieu clone()
	{
		return new Lieu( this.nom );
	}
}
