/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 05/05/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

//calculer le temps avec "soustraire"
//dans JTable -> après traitement, ajouter Temps + n°

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
		this.metier = new HorairesTransport( "ligne20_le-havre_caudebec-en-caux" );
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
		return HorairesTransport.getListeFichier();
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
		this.vue.afficherErreur( messageErreur );
	}

	//change le sens de destination A -> B en B -> A
	public void inverserLignesTransport()
	{
//TODO : A FAIRE
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
}
