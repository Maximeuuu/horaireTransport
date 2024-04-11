/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 15/04/23
Dernière maj  : 10/06/23
Version       : 2
==============================================================================*/

/*    PACKAGE    */
package transport.ihm;
import transport.Controleur;
import transport.metier.GrilleValeurs;

/*    IMPORT    */
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.EmptyBorder;

public class PanelAffichage extends JPanel
{
	/*    ATTRIBUTS    */
	private JLabel lblNbHoraire;
	private JTableHoraires tblHoraires;
	private JScrollPane scpHoraires;
	private Controleur ctrl;


	/*    CONSTRUCTEUR    */
	public PanelAffichage( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setLayout( new BorderLayout() );
		this.setBorder(new EmptyBorder(10, 10, 10, 10));

		/*Création des composants*/
		this.tblHoraires = new JTableHoraires( this.ctrl.getGrilleValeur() );
		this.lblNbHoraire = new JLabel("");
		this.scpHoraires = new JScrollPane( this.tblHoraires ); //rendre le tableau scrollable

		/*Ajouts des composants*/
		this.add( this.lblNbHoraire, BorderLayout.NORTH );
		this.add( this.scpHoraires, BorderLayout.CENTER );

		/*Activation des composants*/
		this.setVisible( true );
	}


	/*    METHODES - CONTROLEUR    */
	//mise à jour du contenu du tableau + compter nb heures trouvées
	public void majTableau()
	{
		GrilleValeurs grdVal = this.ctrl.getGrilleValeur();
		this.tblHoraires.setModel( grdVal );

		int nbHoraires = grdVal.getRowCount();
		if( grdVal.getValueAt(0,0) == null ){ nbHoraires = 0; } //si le tableau est sans données mais qu'il y a des lignes, on compte à 0
		String horaireText = nbHoraires <= 1 ? "horaire trouvé" : "horaires trouvés";
		this.lblNbHoraire.setText(nbHoraires + " " + horaireText);
	}

	//remplace les valeurs du tableau par un nouveau tableau vide
	public void reinitialiser()
	{
		GrilleValeurs grdVide = new GrilleValeurs( this.ctrl.getGrilleValeur().getEntetes(), new Object[1][4] );
		this.tblHoraires.setModel( grdVide );
		this.lblNbHoraire.setText("Rechercher un horaire");
	}
}
