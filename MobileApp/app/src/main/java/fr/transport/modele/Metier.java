package fr.transport.modele;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.transport.modele.entite.Heure;
import fr.transport.modele.entite.Jour;
import fr.transport.modele.entite.Lieu;
import fr.transport.modele.entite.LigneTransport;
import fr.transport.modele.entite.Periode;
import fr.transport.modele.entite.RechercheTrajet;
import fr.transport.modele.outils.UtilitaireRechercheTrajet;

public class Metier implements Serializable //FIXME: utiliser RechercheTrajetsLigne et son extend
{
	private LigneTransport ligneTransport;
	private RechercheTrajet rechercheTrajet;

	public Metier()
	{

	}

	/* SETTEURS */

	public void setLigneTransport(Context context, String fichier )
	{
		this.ligneTransport = FileToLigneTransport.LireFichierAndroid(context, fichier );
	}

	public void setArrets( int indiceArretDepart, int indiceArretDestination )
	{
		Lieu arretDepart = this.ligneTransport.getEnsArretOrdonne().get( indiceArretDepart );
		Lieu arretDestination = this.ligneTransport.getEnsArretOrdonne().get( indiceArretDestination );
		this.rechercheTrajet = new RechercheTrajet( arretDepart, arretDestination );
	}

	public void setPeriode( String nomPeriode )
	{
		Periode periode = Periode.getPeriodeParNom( nomPeriode );
		this.rechercheTrajet.setPeriode( periode);
	}

	public void setJour( String nomJour )
	{
		Jour jour = Jour.getJour( nomJour );
		this.rechercheTrajet.setJour( jour );
	}

	public void addArretPassage( int choixArret )
	{
		Lieu arret = this.ligneTransport.getEnsArretOrdonne().get( choixArret );
		this.rechercheTrajet.addArretPassage( arret );
	}

	public void setArretDepart( int choixArretDepart )
	{
		Lieu arretDepart = this.ligneTransport.getEnsArretOrdonne().get( choixArretDepart );
		this.rechercheTrajet.setArretDepart( arretDepart );
	}

	public void setArretDestination( int choixArretDestination )
	{
		Lieu arretDestination = this.ligneTransport.getEnsArretOrdonne().get( choixArretDestination );
		this.rechercheTrajet.setArretDestination( arretDestination );
	}

	/* GETTEURS */

	public LigneTransport getLigneTransport()
	{
		return this.ligneTransport;
	}
	public RechercheTrajet getRechercheTrajet() { return this.rechercheTrajet; }

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

	public int getNbArretsPassage()
	{
		return this.rechercheTrajet.getNbArretsPassage();
	}

	/* METHODES */

	public void reinitialiserFiltres()
	{
		this.rechercheTrajet = new RechercheTrajet( this.rechercheTrajet.getArretDepart(), this.rechercheTrajet.getArretDestination() );
	}


	/*public LigneTransport filtrerEnLigneTransport( ) //FIXME: simplifier avec la classe RechercheTrajetsLigne
	{
		LigneTransport ligneModifiee = this.rechercheTrajet.filtrer( this.ligneTransport );
		return ligneModifiee;
	}*/

	public List<Heure[]> filtrerEnListe( ) //FIXME: simplifier avec la classe RechercheTrajetsLigne
	{
		LigneTransport ligneModifiee = this.rechercheTrajet.filtrer( this.ligneTransport );

		List<Heure[]> ensHoraireTrajet = UtilitaireRechercheTrajet.getHorairesTrajets( ligneModifiee, this.rechercheTrajet );

		return ensHoraireTrajet;
	}
}
