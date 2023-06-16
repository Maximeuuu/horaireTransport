/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 15/04/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;

public class PanelAffichage extends JPanel
{
	private JLabel lblNbHoraire;
	private JTableHoraires tblHoraires;
	private JScrollPane scpHoraires;
	private Controleur ctrl;

	public PanelAffichage( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setLayout( new BorderLayout() );

		/*
		Création des composants
		*/
		this.tblHoraires = new JTableHoraires( this.ctrl.getGrilleValeur() );
		this.lblNbHoraire = new JLabel("");
		this.scpHoraires = new JScrollPane( this.tblHoraires ); //rendre le tableau scrollable
		
		/*
		Ajouts des composants
		*/
		this.add( this.lblNbHoraire, BorderLayout.NORTH );
		this.add( this.scpHoraires, BorderLayout.CENTER );

		/*
		Activation des composants
		*/
		this.setVisible( true );
	}

	public void majTableau()
	{
		GrilleValeurs grdVal = this.ctrl.getGrilleValeur();
		this.tblHoraires.setModel( grdVal );

		int nbHoraires = grdVal.getRowCount();
		if( grdVal.getValueAt(0,0) == null ){ nbHoraires = 0; } //si le tableau est sans données mais qu'il y a des lignes, on compte à 0
		String horaireText = nbHoraires <= 1 ? "horaire trouvé" : "horaires trouvés";
		this.lblNbHoraire.setText(nbHoraires + " " + horaireText);
	}

	public void reinitialiser()
	{
		GrilleValeurs grdVide = new GrilleValeurs( this.ctrl.getGrilleValeur().getEntetes(), new Object[1][4] );
		this.tblHoraires.setModel( grdVide );
		this.lblNbHoraire.setText("Rechercher un horaire");
	}

}
