package fr.transport.modele.entite;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class LigneTransport implements Serializable, Cloneable
{
	private String identifiant;
	private String nom;

	private List<Lieu> ensArretOrdonne;
	private List<Trajet> ensTrajet;

	private LigneTransport ligneInverse;

	/* CONSTRUCTEURS */

	public LigneTransport( String identifiant, String nom )
	{
		this.identifiant = identifiant;
		this.nom = nom;

		this.ensArretOrdonne = new ArrayList<Lieu>();
		this.ensTrajet = new ArrayList<Trajet>();

		this.ligneInverse = null;
	}

	/* ACCESSEURS */

	public String getIdentifiant()
	{
		return this.identifiant;
	}

	public String getNom()
	{
		return this.nom;
	}

	public List<Lieu> getEnsArretOrdonne()
	{
		return this.ensArretOrdonne;
	}

	public List<Trajet> getEnsTrajet()
	{
		return this.ensTrajet;
	}

	public LigneTransport getLigneInverse()
	{
		return this.ligneInverse;
	}

	/* MODIFIEURS */

	public void addArret( Lieu arret )
	{
		this.ensArretOrdonne.add( arret );
	}

	public void addTrajet( Trajet trajet )
	{
		this.ensTrajet.add( trajet );
	}

	public void setLigneInverse( LigneTransport ligneInverse )
	{
		this.ligneInverse = ligneInverse;
	}

	/* METHODES */

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append( "=== " + this.nom + " (" + this.identifiant + ") ===\n" );

		for( Lieu arret : ensArretOrdonne )
			sb.append( "\t-" + arret.toString() + "\n" );
		
		sb.append( "\n" );

		for( Trajet trajet : ensTrajet )
			sb.append( trajet.toString() + "\n\n" );
		
		return sb.toString();
	}

	@Override
	public LigneTransport clone()
	{
		LigneTransport clone = new LigneTransport( this.identifiant, this.nom );

		for( Lieu arret : ensArretOrdonne )
			clone.addArret( arret.clone() );

		for( Trajet trajet : ensTrajet )
			clone.addTrajet( trajet.clone() );

		clone.setLigneInverse( this.ligneInverse );

		return clone;
	}
}
