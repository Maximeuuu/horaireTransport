package fr.transport;

import fr.transport.modele.entite.*;
import fr.transport.vue.Terminal;

import java.io.File;

public class Controleur
{
	private static final String REPERTOIRE = "data";

	private Terminal vue;
	private Metier metier;

	public static void main( String[] args )
	{
		new Controleur();
	}

	public Controleur()
	{
		this.metier = new Metier();
		this.vue = new Terminal( this );
	}

	public File[] getFichiersData() //FIXME: remplacer toutes les utilisations de File par InputStream (aussi dans projet : Base)
	{
		File dir = new File( REPERTOIRE );
		return dir.listFiles();
	}

	public String arretsToString()
	{
		StringBuilder sb = new StringBuilder();
		int cpt = 1;
		for( Lieu arret : this.metier.getLigneTransport().getEnsArretOrdonne() )
		{
			sb.append( cpt + " - " + arret + "\n" );
			cpt++;
		}
		return sb.toString();
	}

	public void ouvrirFichier( File fichier )
	{
		this.metier.ouvrirFichier( fichier );
	}

	public void setArrets( int choixArretDepart, int choixArretDestination )
	{
		this.metier.setArrets( choixArretDepart, choixArretDestination );
	}

	public String rechercheToString()
	{
		return this.metier.rechercheToString();
	}

	public void setPeriode( String codePeriode )
	{
		this.metier.setPeriode( codePeriode );
	}

	public void setJour( int codeJour )
	{
		this.metier.setJour( codeJour );
	}

	public void addArretPassage( int choixArret )
	{
		this.metier.addArretPassage( choixArret );
	}

	public void setArretDepart( int indiceArret )
	{
		this.metier.setArretDepart( indiceArret );
	}

	public void setArretDestination( int indiceArret )
	{
		this.metier.setArretDestination( indiceArret );
	}

	public void reinitialiserFiltres()
	{
		this.metier.reinitialiserFiltres();
	}
}
