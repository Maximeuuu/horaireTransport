/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 15/04/23
Dernière maj  : 10/06/23
Version       : 2
==============================================================================*/

/*    PACKAGE    */
package transport.ihm;
import transport.Controleur;

/*    IMPORT    */
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class PanelSelection extends JPanel implements ActionListener
{
	/*    ATTRIBUTS    */
	private Controleur ctrl;
	private JComboBox<String> ddlstLigne;
	private JButton btnInverser;
	private JButton btnReinitialiser;


	/*    CONSTRUCTEUR    */
	public PanelSelection( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setLayout( new BorderLayout() );
		this.setBorder(new EmptyBorder(10, 10, 10, 10));

		//Création des composants
		JPanel pnlLigne = new JPanel();

		String[] optionsLigne = this.ctrl.getLignesTransport();
		this.ddlstLigne = new JComboBox<>( optionsLigne );

		this.btnInverser = new JButton("inverser");
		this.btnReinitialiser = new JButton("Réinitialiser");

		//Ajouts des composants
		//this.add( new JLabel("Horaires transports"), BorderLayout.NORTH );

		pnlLigne.add( this.ddlstLigne );
		pnlLigne.add( this.btnInverser );
		this.add( pnlLigne, BorderLayout.WEST );

		this.add( this.btnReinitialiser, BorderLayout.EAST );

		//Activation des composants
		this.btnReinitialiser.addActionListener( this );
		this.btnInverser.addActionListener( this );
		this.ddlstLigne.addActionListener( this );
		this.setVisible( true );
	}

	public void setLigneTransport( int indice )
	{
		this.ddlstLigne.setSelectedIndex( indice );
	}

	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == this.btnReinitialiser )
		{
			System.out.println("Réinitialiser");
			this.ctrl.reinitialiserAffichage();
		}
		if( e.getSource() == this.btnInverser )
		{
			System.out.println("Inverser");
			this.ctrl.inverserLignesTransport( this.ddlstLigne.getSelectedIndex() );
		}
		if( e.getSource() == this.ddlstLigne )
		{
			System.out.println("Ligne");
			this.ctrl.changerLigneTransport( (String)this.ddlstLigne.getSelectedItem() );
		}
	}
}
