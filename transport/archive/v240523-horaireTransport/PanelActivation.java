/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 15/04/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class PanelActivation extends JPanel implements ActionListener
{
	private JButton btnRechercher;
	private JButton btnExporter;
	private JLabel  lblErreur;
	private Controleur ctrl;

	PanelActivation( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setLayout( new BorderLayout() );

		//Création des composants
		this.lblErreur = new JLabel("");
		this.btnRechercher = new JButton("Rechercher");
		this.btnExporter = new JButton("Exporter");
		JPanel pnlTmp = new JPanel();

		//Ajouts des composants
		this.add( this.lblErreur, BorderLayout.CENTER );
		pnlTmp.add( this.btnRechercher );
		pnlTmp.add( this.btnExporter );
		this.add( pnlTmp, BorderLayout.EAST );

		//Activation des composants
		this.btnRechercher.addActionListener( this );
		this.btnExporter.addActionListener( this );
		this.setVisible( true );
	}

	public void afficherErreur( String msgErreur )
	{
		this.lblErreur.setText( msgErreur );
	}

	public void reinitialiser()
	{
		this.lblErreur.setText("");
	}

	public void actionPerformed( ActionEvent e)
	{
		if( e.getSource() == this.btnRechercher )
		{
			System.out.println("Rechercher");
			this.ctrl.rechercher();
		}
		if( e.getSource() == this.btnExporter )
		{
			System.out.println("Exporter");
//TODO : ctrl.Exporter
		}
	}
}
