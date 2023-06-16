/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 15/04/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class PanelSelection extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JComboBox<String> ddlstLigne;
	private JButton btnInverser;
	private JButton btnReinitialiser;

	public PanelSelection( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setLayout( new BorderLayout() );

		//Création des composants
		JPanel pnlLigne = new JPanel();

		String[] optionsLigne = this.ctrl.getLignesTransport();
		this.ddlstLigne = new JComboBox<>( optionsLigne );

		this.btnInverser = new JButton("inverser"); // à remplacer par une image
		this.btnReinitialiser = new JButton("Réinitialiser");

		//Ajouts des composants
		this.add( new JLabel("Horaires transports"), BorderLayout.NORTH );

		pnlLigne.add( this.ddlstLigne );
		pnlLigne.add( this.btnInverser );
		this.add( pnlLigne, BorderLayout.WEST );

		this.add( this.btnReinitialiser, BorderLayout.EAST );

		//Activation des composants
		this.btnReinitialiser.addActionListener( this );
		this.ddlstLigne.addActionListener( this );
		this.setVisible( true );
	}

	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == this.btnReinitialiser )
		{
			System.out.println("Réinitialiser");
			this.ctrl.reinitialiserAffichage();
		}
		if( e.getSource() == this.ddlstLigne )
		{
			System.out.println("Ligne");
			this.ctrl.changerLigneTransport( (String)this.ddlstLigne.getSelectedItem() );
		}
	}
}
