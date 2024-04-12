package fr.transport.modele.entite;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import fr.transport.modele.filtrage.FiltrageTrajets;
import fr.transport.modele.filtrage.FiltreArretsOrdonnes;
import fr.transport.modele.filtrage.FiltreTrajetJour;
import fr.transport.modele.filtrage.FiltreTrajetPasseParArret;
import fr.transport.modele.filtrage.FiltreTrajetPendantPeriode;

public class RechercheTrajet implements Serializable
{
	private FiltrageTrajets filtre;

	//TODO: ajouter aussi les heures
	private List<Lieu> ensArretPassage;
	private Lieu arretDepart;
	private Lieu arretDestination;

	private Periode periode;
	private Jour jour;

	/* CONSTRUCTEUR */

	public RechercheTrajet( Lieu arretDepart, Lieu arretDestination )
	{
		this.filtre = new FiltrageTrajets();

		this.ensArretPassage  = new ArrayList<Lieu>();
		this.arretDepart      = arretDepart;
		this.arretDestination = arretDestination;
		
		this.periode = null;
		this.jour 	 = null;
	}

	/* ACCESSEURS */

	public List<Lieu> getEnsArretPassage()
	{
		return this.ensArretPassage;
	}

	public Lieu getArretDepart()
	{
		return this.arretDepart;
	}

	public Lieu getArretDestination()
	{
		return this.arretDestination;
	}

	public Periode getPeriode()
	{
		return this.periode;
	}

	public Jour getJour()
	{
		return this.jour;
	}

	/* MODIFIEURS */

	public void setArretDepart( Lieu nouvelArretDepart )
	{
		this.arretDepart = nouvelArretDepart;
	}

	public void setArretDestination( Lieu nouvelArretDestination )
	{
		this.arretDestination = nouvelArretDestination;
	}

	public void addArretPassage( Lieu arretPassage )
	{
		this.ensArretPassage.add( arretPassage );
	}

	public void setPeriode( Periode periode )
	{
		this.periode = periode;
	}

	public void setJour( Jour jour )
	{
		this.jour = jour;
	}

	/* METHODES */

	public void reinitialiser()
	{
		this.ensArretPassage.clear();
		this.periode = null;
		this.jour = null;
	}

	public LigneTransport filtrer( LigneTransport ligneTransport )
	{
		LigneTransport copieLigneTransport = ligneTransport.clone();
		
		this.initialiserFiltre();
		this.filtre.filtrer( copieLigneTransport );

		return copieLigneTransport;
	}

	private void initialiserFiltre()
	{
		this.filtre.resetFiltres();

		this.ajouterFiltresArretPassage();
		this.ajouterFiltresArretOrdonnes();
		this.ajouterFiltresPeriode();
		this.ajouterFiltresJour();
	}

	private void ajouterFiltresArretPassage( )
	{
		this.filtre.addFiltre( new FiltreTrajetPasseParArret( this.arretDepart ) );
		this.filtre.addFiltre( new FiltreTrajetPasseParArret( this.arretDestination ) );

		for( Lieu arretPassage : this.ensArretPassage )
		{
			this.filtre.addFiltre( new FiltreTrajetPasseParArret( arretPassage ) );
		}
	}

	private void ajouterFiltresArretOrdonnes()
	{
		this.filtre.addFiltre( new FiltreArretsOrdonnes( this.arretDepart, this.arretDestination ) );
	}

	private void ajouterFiltresPeriode()
	{
		if( this.periode != null )
		{
			this.filtre.addFiltre( new FiltreTrajetPendantPeriode( this.periode ) );
		}
	}

	private void ajouterFiltresJour()
	{
		if( this.jour != null )
		{
			this.filtre.addFiltre( new FiltreTrajetJour( this.jour ) );
		}
	}
}
