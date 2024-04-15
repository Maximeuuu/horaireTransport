package fr.transport;

import java.io.File;
import java.util.List;
import java.util.Map;

import fr.transport.modele.entite.*;
import fr.transport.modele.importation.LectureCSV;
import fr.transport.modele.outils.UtilitaireRechercheTrajet;

public class Metier
{
	private LigneTransport ligneTransport;
	private RechercheTrajet rechercheTrajet;

	public Metier()
	{
	}

	public void ouvrirFichier( File fichier )
	{
		LectureCSV lecture = new LectureCSV( fichier );
		this.ligneTransport = lecture.getLigneTransport();
	}

	public LigneTransport getLigneTransport()
	{
		return this.ligneTransport;
	}

	public void setArrets( int choixArretDepart, int choixArretDestination )
	{
		Lieu arretDepart = this.ligneTransport.getEnsArretOrdonne().get( choixArretDepart );
		Lieu arretDestination = this.ligneTransport.getEnsArretOrdonne().get( choixArretDestination );
		this.rechercheTrajet = new RechercheTrajet( arretDepart, arretDestination );
	}


	public void setPeriode( String codePeriode )
	{
		Periode periode = Periode.getPeriode( codePeriode );
		this.rechercheTrajet.setPeriode( periode);
	}

	public void setJour( int codeJour )
	{
		Jour jour = Jour.getJour( codeJour );
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

	public void reinitialiserFiltres()
	{
		this.rechercheTrajet = new RechercheTrajet( this.rechercheTrajet.getArretDepart(), this.rechercheTrajet.getArretDestination() );
	}

	public String rechercheToString()
	{
		LigneTransport ligneModifiee = this.rechercheTrajet.filtrer( this.ligneTransport );

		Map<Lieu, List<Heure>> mapHeuresArrets = UtilitaireRechercheTrajet.getEnsembleHeuresParArrets( ligneModifiee, this.rechercheTrajet );
	
		StringBuilder sb = new StringBuilder();
		sb.append( UtilitaireRechercheTrajet.periodeEtJourToString( this.rechercheTrajet ) );
		sb.append( "\n" );
		sb.append( UtilitaireRechercheTrajet.heuresParArretsToString( mapHeuresArrets ) );
		return sb.toString();
	}
}
