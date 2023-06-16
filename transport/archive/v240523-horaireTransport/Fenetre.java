/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 15/04/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Fenetre extends JFrame
{
	private Controleur ctrl;

	private PanelSelection pnlSelection;
	private PanelSaisie pnlSaisie;
	private PanelAffichage pnlAffichage;
	private PanelActivation pnlActivation;
	/*
	private JPanel pnlErreur;
	private JPanel pnlHoraires;*/

	public Fenetre( Controleur ctrl )
	{
		this.ctrl = ctrl;

		this.setTitle( "Horaires de transport" );
		//this.setIconImage( new ImageIcon( "images/icone.png" ).getImage() );
		this.setSize(1100,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(1100, 500));
		this.setLayout( new BorderLayout() );

		//Création des composants
		this.pnlSelection  = new PanelSelection( this.ctrl );
		this.pnlSaisie     = new PanelSaisie( this.ctrl );
		this.pnlAffichage  = new PanelAffichage( this.ctrl );
		this.pnlActivation = new PanelActivation( this.ctrl );

		//Ajout des composants
		this.add( pnlSelection,  BorderLayout.NORTH );
		this.add( pnlSaisie,  BorderLayout.WEST );
		this.add( pnlAffichage,  BorderLayout.EAST );
		this.add( pnlActivation, BorderLayout.SOUTH );

		//Activation des composants
		this.setVisible( true );
	}

	public Object[] getEnsSaisie()
	{
		return this.pnlSaisie.getEnsSaisie();
	}

	public void afficherErreur( String msgErreur )
	{
		this.pnlActivation.afficherErreur( msgErreur );
	}

	public void majTableau()
	{
		this.pnlAffichage.majTableau();
	}
	
	public void majArrets()
	{
		this.pnlSaisie.majListeArrets();
	}

	public void reinitialiser()
	{
		this.pnlSaisie.reinitialiser();
		this.pnlAffichage.reinitialiser();
		this.pnlActivation.reinitialiser();
	}
}
