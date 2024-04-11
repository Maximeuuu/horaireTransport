/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 07/05/23
Dernière maj  : 10/06/23
Version       : 2
==============================================================================*/

package transport.metier;
import transport.metier.filtres.*;
import transport.Controleur;
import java.io.File;

public class HorairesTransport
{
//TODO : remplacer certaines constantes par des types énumérés
	/*
	ATTRIBUTS
	*/
	public final static String REPERTOIRE ="../data/horaires/";
	public final static String OUTPUT     ="../data/export/";
	public final static String EXTENSION  =".csv";
	private String fichier;

	public static final String[] ENS_JOURS = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
	public static final String[] ENS_PERIODES = {"TA","PS","VS","ETE","FERIE"};
	private String[]   ensArrets;

	private Object[][] tabDonneesOrig;
	private String[]   ensEntetes;
	private TableauObjetsFiltrable tableur;

	/*
	CONSTRUCTEUR
	*/
	public HorairesTransport( String fichier )
	{
		this.fichier = fichier;

		this.tabDonneesOrig = AnalyseFichiers.chargerDonnees( fichier );
		this.ensEntetes     = HorairesTransport.chargerEntetes();
		this.ensArrets      = HorairesTransport.chargerArrets ( this.tabDonneesOrig );

		this.tableur = new TableauObjetsFiltrable( this.tabDonneesOrig );
	}

	private static String[] chargerEntetes()
	{
		return new String[]{"n°", "depart", "arrivee", "temps"};
	}

	private static String[] chargerArrets( Object[][] tabDonnees )
	{
		String[] tabRet = new String[ tabDonnees.length-2 ];
		for( int cptLig=0; cptLig<tabRet.length; cptLig++ )
		{
			tabRet[cptLig] = (String)tabDonnees[cptLig+2][0];
		}
		return tabRet;
	}

	/*
	ACCESSEURS
	*/
	public String[] getJours()
	{
		return HorairesTransport.ENS_JOURS;
	}

	public String[] getPeriodes()
	{
		return HorairesTransport.ENS_PERIODES;
	}

	public String[] getArrets()
	{
		return this.ensArrets;
	}

	private Object[][] ajouterNumeroEtTemps()
	{
		//nécessaire pour avoir un tableau vide à l'affichage
		if( this.tableur.getRowCount() == 0 || this.tableur.getColumnCount() != 2 ){ return new Object[1][4]; } //correspond aux 2 colonnes "départ" et "arrivée" du trie

		Object[][] tabRet = new Object[ this.tableur.getRowCount() ][ 4 ];
		for( int cptLig=0; cptLig<tabRet.length; cptLig++ )
		{
			tabRet[cptLig][0] = cptLig+1;
			tabRet[cptLig][1] = this.tableur.getValueAt(cptLig, 0);
			tabRet[cptLig][2] = this.tableur.getValueAt(cptLig, 1);
			tabRet[cptLig][3] = new Temps( Temps.difference( new Temps((String)tabRet[cptLig][2]), new Temps((String)tabRet[cptLig][1]) ) );
		}
		return tabRet;
	}

	public GrilleValeurs getGrilleValeur()
	{
		return new GrilleValeurs( this.ensEntetes, this.ajouterNumeroEtTemps() );
	}

	public TableauObjetsFiltrable getTableurAffichage()
	{
		return this.tableur;
	}

	/*
	METHODES - UTILITAIRE
	*/
	public void reinitialiser()
	{
		this.tableur.reset();
		this.tableur.resetIFiltres();
	}

	private void inverserLigCol()
	{
		this.tableur.applyIFiltres();
		this.tableur.inverseLignesColonnes();
		this.tableur.resetIFiltres();
	}

	/*
	METHODES - FILTRES
	*/
	private void filtrerArrets( String arret1, String arret2 )
	{
		IFiltre filtreOu;
		filtreOu = new OrFilter
		(
			new FirstNRowsFilter(2, this.tableur.toArray() ), //on garde les 2 premiers car ce sont des compléments d'informations aux arrets
			new EqualsToFilter(0, arret1 ),
			new EqualsToFilter(0, arret2 )
		);
		this.tableur.addIFiltre( filtreOu );
	}

	private void filtrerPeriodes( String periode )
	{
		IFiltre filtreOu;
		filtreOu = new OrFilter
		(
			new StringContainsFilter( 1, HorairesTransport.ENS_PERIODES[0].toLowerCase() ),
			new StringContainsFilter( 1, periode )
		);
		this.tableur.addIFiltre( filtreOu );
	}

	private void supprimerNomArrets()
	{
		this.tableur.addIFiltre( new NotFilter( new SingleRowFilter(0, this.tableur.toArray()) ) );
	}

	private void filtrerJours( String jour )
	{
		this.tableur.addIFiltre( new StringContainsFilter( 0, jour ) );
	}

	private void supprimerHeuresInvalides() //supprime toutes les horaires contenant "|" ou vide
	{
		IFiltre filtreOu = new OrFilter
		(
			new EqualsToFilter(2, ""),
			new EqualsToFilter(3, ""),
			new EqualsToFilter(2, "|"),
			new EqualsToFilter(3, "|")
		);
		this.tableur.addIFiltre( new NotFilter( filtreOu ) );
	}

	private void filtrerHeuresDeparts( String heure )
	{
		heure = ( new Temps(heure) ).toString4Digits(); //convertir dans un bon format
		this.tableur.addIFiltre( new UpperThanFilter(2, heure, true) );
	}

	private void filtrerHeuresArrivees( String heure )
	{
		heure = ( new Temps(heure) ).toString4Digits(); //convertir dans un bon format
		this.tableur.addIFiltre( new LowerThanFilter(3, heure, true) );
	}

	private void supprimerPourAffichage() //trouver un nom plus explicite
	{
		this.tableur.addIFiltre( new NotFilter( new FirstNRowsFilter(2, this.tableur.toArray()) ) );
	}

	/*
	METHODES - ACTIONS
	*/
	public String exporterToCSV( Controleur ctrl, File fichier )
	{
		return AnalyseFichiers.grilleValeursToCSV( ctrl, this.getGrilleValeur(), fichier);
	}
	public boolean estExportable()
	{
		//ne rien faire si le tableau est vide ou si la premiere valeur est nulle
		GrilleValeurs tableau = this.getGrilleValeur();
		return !(tableau.getRowCount() == 0 || tableau.getValueAt(0,0) == null );
	}

	public String rechercher( Object[] ensSaisie ) // l'ordre a son importance
	{
		String periode;
		String jour;
		String msgErreur="";

		/*définition de chaque saisie :*/
		int indiceJour    = (int)ensSaisie[0]; //par défaut : 0
		int indicePeriode = (int)ensSaisie[1]; //par défaut : 0
		int indiceArret1  = (int)ensSaisie[2]; //par défaut : 0
		int indiceArret2  = (int)ensSaisie[3]; //par défaut : 0
		String heure1     = (String)ensSaisie[4]; //par défaut : ""
		String heure2     = (String)ensSaisie[5]; //par défaut : ""
		boolean express   = (boolean)ensSaisie[6]; //par défaut : "false"

		/*Réinitilisation*/
		this.reinitialiser();

		/*Analyse des arrets*/ //en cas d'erreur il est impératif d'arrêter tout tris ici
		if( indiceArret1 == 0 || indiceArret2 == 0 ){ return "Selectionner 2 arrêts."; }
		if( indiceArret1 >= indiceArret2 )          { return "Arrets invalide."; }
		this.filtrerArrets( this.ensArrets[ indiceArret1-1 ], this.ensArrets[ indiceArret2-1 ] ); //-1 car le premier élément de la liste est un texte par défaut

		/*Etape intermédiaire*/
		this.inverserLigCol();

		/*Supression des noms des arrets*/
		this.supprimerNomArrets();

		/*Analyse des periodes*/
		if( indicePeriode != 0 ) // valeur par défaut
		{
			periode = HorairesTransport.ENS_PERIODES[ indicePeriode-1 ].toLowerCase(); //-1 car le premier élément de la liste est un texte par défaut
			if( periode.equals("ferie") ){ indiceJour = -1; }
			else{ this.filtrerPeriodes( periode ); }
		}

		/*Analyse des jours*/
		if( indiceJour !=0 ) //valeur par défaut
		{
			if( indiceJour == -1 ){ jour = "ferie"; }
			else{ jour = indiceJour+""; }
			this.filtrerJours( jour );
		}

		/*Supression des horaires invalides*/ //arret non deservi
		this.supprimerHeuresInvalides();

		/*Analyse des heures*/
		try
		{
			if( !heure1.equals("") && !heure2.equals("") )
			{
				if( (new Temps(heure1)).compareTo( (new Temps(heure2)) ) > 0 ){ msgErreur += "Ordre d'heure invalide"; }
			}

			if( !heure1.equals("") )
			{
				if( Temps.estTemps( heure1 ) ){ this.filtrerHeuresDeparts( heure1 ); }
				else{ msgErreur += "Heure départ invalide. "; }
			}

			if( !heure2.equals("") )
			{
				if( Temps.estTemps( heure2 ) ){ this.filtrerHeuresArrivees( heure2 ); }
				else{ msgErreur += "Heure arrivée invalide. "; }
			}
		}
		catch( IllegalArgumentException e ){ msgErreur += e.getMessage() +". "; }

		/*Etape intermédiaire*/
		this.inverserLigCol();

		/*Suppression des dernieres infos inutiles*/
		this.supprimerPourAffichage();

		/*Préparation pour l'affichage*/
		this.inverserLigCol();
		this.tableur.orderBy(0, TableauObjetsFiltrable.ASC );

		return msgErreur;
	}

}
