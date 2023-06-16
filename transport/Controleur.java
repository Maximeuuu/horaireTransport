/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 05/05/23
Dernière maj  : 09/06/23
Version       : 1
==============================================================================*/

package transport;
import transport.ihm.*;
import transport.metier.*;
import java.util.*;
import java.io.File;

public class Controleur
{
	/*
	ATTRIBUTS
	*/
	private Fenetre vue;
	private HorairesTransport metier;

	/*
	CONSTRUCTEUR
	*/
	public Controleur()
	{
		this.metier = new HorairesTransport( this.getLignesTransport()[0] );
		this.vue =  new Fenetre( this );
		this.reinitialiserAffichage();
	}

	/*
	MAIN
	*/
	public static void main( String[] args )
	{
		new Controleur();
	}

	/*
	ACCESSEURS
	*/
	public GrilleValeurs getGrilleValeur()
	{
		return this.metier.getGrilleValeur();
	}

	public String[] getJours()
	{
		return this.metier.getJours();
	}

	public String[] getPeriodes()
	{
		return this.metier.getPeriodes();
	}

	public String[] getArrets()
	{
		return this.metier.getArrets();
	}

	public String[] getLignesTransport()
	{
		return AnalyseFichiers.getListeFichier( HorairesTransport.REPERTOIRE );
	}

	public String getOutputFolder()
	{
		return HorairesTransport.OUTPUT;
	}

	/*
	METHODES
	*/
	//récupère les infos, analyse et affiche
	public void rechercher()
	{
		String messageErreur="";
		Object[] ensSaisie = this.vue.getEnsSaisie();

		this.metier.reinitialiser();
		messageErreur = this.metier.rechercher( ensSaisie );

		this.vue.majTableau();
		this.vue.afficherMessage( messageErreur );
	}

	//change le sens de destination A -> B en B -> A
	public void inverserLignesTransport( int indice )
	{
		String[] ensLignes; //List<String> ensLignes;
		int newIndice;
		HorairesTransport newMetier;

		ensLignes = this.getLignesTransport(); //ensLignes = Arrays.asList( this.getLignesTransport() );
		newIndice = indice;

		if( indice % 2 == 0 ){ newIndice++; }
		else                 { newIndice--; }

		if( newIndice > ensLignes.length ) //on test s'il manque des fichiers (nombre impair)
		{
			this.vue.afficherMessage("Impossible d'inverser les lignes.");
			return;
		}

		newMetier = new HorairesTransport( ensLignes[ newIndice ] );

		/* NE FONCTIONNE PAS PUISQUE LES DONNEES PEUVENT ETRE LEGEREMENT DIFFRENTES
		List<String> list1 = Arrays.asList(this.metier.getArrets());
		List<String> list2 = Arrays.asList(newMetier.getArrets());

		Set<String> set1 = new HashSet<>(list1);
		Set<String> set2 = new HashSet<>(list2);

		boolean areEqual = set1.equals(set2);

		if( areEqual ) //on test que les fichiers sont bien identiques*/

		if( this.metier.getArrets().length == newMetier.getArrets().length ) //on vérifie que les données sont cohérentes
		{
			int indiceArret1;
			int indiceArret2;
//TODO : IL FAUT REFAIRE TOUTES CETTE PARTIE DE LA CONDITION - MAJ IHM ET METIER PAS BONNES
			this.metier = newMetier;
			this.vue.majArrets();
			this.reinitialiserAffichage();

			/*
			this.vue.inverserArrets( newIndice );
			this.vue.majTableau();
			this.vue.majArrets();*/

			this.vue.afficherMessage("Inversion du sens.");
			this.rechercher();
		}
		else //dans le cas où les données sont incompatibles
		{
			this.vue.afficherMessage("Arrets incompatibles pour l'inversion.");
		}
	}

	//reinitialiser tout l'affichage
	public void reinitialiserAffichage()
	{
		this.vue.reinitialiser();
	}

	//mets en valeur une ligne dans l'affichage
	public void miseEnValeurLigne( int lig )
	{
//TODO : A FAIRE
	}

	//change la ligne de transport
	public void changerLigneTransport( String fichier )
	{
		this.metier = new HorairesTransport( fichier );
		this.vue.majArrets();
		this.reinitialiserAffichage();
	}

	//exporter en CSV
	/*public void exporterToCSV( File fichier )
	{
		//this.ctrl.afficherMessage( "Les données ont été exportés dans le fichier "+this.fcSauvegarde.getFichier()+"." );


		String messageErreur = this.metier.exporterToCSV( fichier );
		this.vue.afficherMessage( messageErreur );
	}*/
	public void exporter()
	{
		String message="";
		if( !this.metier.estExportable() ){ message = "Aucune données à exporter."; }
		else
		{
		 	message = this.vue.afficherFichiers();

			File fichier = this.vue.getFichierExport();
			if( fichier == null ){ message = "Exportation annulée."; }
			else
			{
				message = this.metier.exporterToCSV( this, fichier );
			}
		}

		this.vue.afficherMessage( message );
	}

	public boolean confirmerEcraserFichier( File fichier )
	{
		return FileChooserSauvegarde.confirmerEcraserFichier( fichier );
	}

	//afficher un message à l'utilisateur
	public void afficherMessage( String msg )
	{
		this.vue.afficherMessage( msg );
	}
}
