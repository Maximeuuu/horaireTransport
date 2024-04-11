/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 15/04/23
Dernière maj  : 10/06/23
Version       : 2
==============================================================================*/

package transport.ihm;
import transport.Controleur;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.io.File;

//import io.util.File;

public class Fenetre extends JFrame
{
	public static final String REP_IMAGES = "../transport/ihm/images/";

	private Controleur ctrl;

	private PanelSelection pnlSelection;
	private PanelImage pnlImage;
	private PanelSaisie pnlSaisie;
	private PanelAffichage pnlAffichage;
	private PanelActivation pnlActivation;

	private FileChooserSauvegarde fcSauvegarde;
	private File fichierExport;

	public Fenetre( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setSize(1100,750);

		this.setTitle( "Horaires de transport" );
		this.setIconImage( new ImageIcon( Fenetre.REP_IMAGES + "icone.png" ).getImage() );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(1100, 750));
		this.setMaximumSize(new Dimension(1100, 750));
		this.setLayout( new BorderLayout() );
		this.setResizable(false);

		//Création des composants
		this.pnlSelection  = new PanelSelection( this.ctrl );
		this.pnlImage      = new PanelImage();
		this.pnlSaisie     = new PanelSaisie( this.ctrl );
		this.pnlAffichage  = new PanelAffichage( this.ctrl );
		this.pnlActivation = new PanelActivation( this.ctrl );

			this.fcSauvegarde = new FileChooserSauvegarde( this.ctrl.getOutputFolder() );

		//Ajout des composants
		JPanel pnlTemp = new JPanel( new BorderLayout() );
		pnlTemp.add( this.pnlSelection, BorderLayout.NORTH );
		pnlTemp.add( this.pnlImage, BorderLayout.SOUTH );

		this.add( pnlTemp, BorderLayout.NORTH );

		pnlTemp = new JPanel( new FlowLayout() );
		pnlTemp.add( pnlSaisie );

		this.add( pnlTemp,       BorderLayout.WEST );
		this.add( pnlAffichage,  BorderLayout.EAST );
		this.add( pnlActivation, BorderLayout.SOUTH );

		//Activation des composants
		this.setVisible( true );
	}

	public Object[] getEnsSaisie()
	{
		return this.pnlSaisie.getEnsSaisie();
	}

	public void afficherMessage( String msg )
	{
		this.pnlActivation.afficherErreur( msg );
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

	public void inverserArrets( int newIndice )
	{
		this.pnlSelection.setLigneTransport( newIndice );
		this.pnlSaisie.inverser();
	}

	public String afficherFichiers()
	{
		int valeur = this.fcSauvegarde.showSaveDialog(this);

		if( valeur == JFileChooser.APPROVE_OPTION )
		{
			this.fichierExport = this.fcSauvegarde.getSelectedFile();
		}
		else
		{
			this.fichierExport = null;
			return "Exportation annulée.";
		}
		return "";
	}

	public File getFichierExport()
	{
		return this.fichierExport;
	}
}
