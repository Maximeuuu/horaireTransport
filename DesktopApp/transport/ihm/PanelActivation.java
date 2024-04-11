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

public class PanelActivation extends JPanel implements ActionListener
{
	/*    ATTRIBUTS    */
	private JButton btnRechercher;
	private JButton btnExporter;
	private JLabel  lblErreur;
	private Controleur ctrl;


	/*    CONSTRUCTEUR    */
	PanelActivation( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setLayout( new BorderLayout() );
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setOpaque(false);

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


	/*    METHODES - CONTROLEUR    */
	//affiche un message pour l'utilisateur
	public void afficherErreur( String msgErreur )
	{
		this.lblErreur.setText( msgErreur );
	}

	//retirer le message pour l'utilisateur
	public void reinitialiser()
	{
		this.lblErreur.setText("");
	}


	/*    METHODE - boutons activation    */
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
			this.ctrl.exporter();
		}
	}
}
