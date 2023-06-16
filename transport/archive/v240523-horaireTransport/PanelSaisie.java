/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 15/04/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Arrays;

public class PanelSaisie extends JPanel
{
	private static final int NB_INPUT = 7;

	private Controleur ctrl;

	private JComboBox<String> ddlstJour;
	private JComboBox<String> ddlstPeriode;
	private JComboBox<String> ddlstDepart;
	private JComboBox<String> ddlstArrivee;
	private JTextField txtHeureD;
	private JTextField txtHeureA;
	private JCheckBox cbExpress;

	private static String[] ajouterDefautListe( String[] listeOption, String defaut )
	{
		listeOption = Arrays.copyOf(listeOption, listeOption.length + 1);
		System.arraycopy(listeOption, 0, listeOption, 1, listeOption.length - 1);
		listeOption[0] = defaut;
		return listeOption;
	}

	private void ajouterNouvelleSaisie( JPanel panel, String label, JComponent composant )
	{
		JPanel pnlTemp;
		JLabel lblTemp;

		pnlTemp = new JPanel ();
		pnlTemp.setLayout ( new FlowLayout ( FlowLayout.LEFT ) );
		//pnlTemp.setPreferredSize( new Dimension(700,0));

		lblTemp = new JLabel  ( label+" : " ,    SwingConstants.RIGHT );
		lblTemp.setPreferredSize ( new Dimension( 90, 12 ) );

		pnlTemp.add ( lblTemp );
		pnlTemp.add ( composant );

		panel.add ( pnlTemp );
	}

	public void majListeArrets()
	{
		String[] optionsArrets = PanelSaisie.ajouterDefautListe( this.ctrl.getArrets(), "Selectionner");
		this.ddlstDepart.removeAllItems();
		this.ddlstArrivee.removeAllItems();
		for( String arret : optionsArrets )
		{
			this.ddlstDepart.addItem( arret );
			this.ddlstArrivee.addItem( arret );
		}
	}

	public PanelSaisie( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setLayout( new GridLayout(PanelSaisie.NB_INPUT, 2, 0, 0) );
		//this.setMaximumSize( new Dimension( 600,600));

		//Création des composants
		String[] optionsJours = PanelSaisie.ajouterDefautListe( this.ctrl.getJours(), "Selectionner");
		String[] optionsPeriodes = PanelSaisie.ajouterDefautListe( this.ctrl.getPeriodes(), "Selectionner");
		String[] optionsArrets = PanelSaisie.ajouterDefautListe( this.ctrl.getArrets(), "Selectionner");

		this.ddlstJour = new JComboBox<>( optionsJours ); //5
		this.ddlstPeriode = new JComboBox<>( optionsPeriodes ); //3
		this.ddlstDepart = new JComboBox<>( optionsArrets ); //20
		this.ddlstArrivee = new JComboBox<>( optionsArrets ); //20
		this.txtHeureD = new JTextField(4);
		this.txtHeureA = new JTextField(4);
		this.cbExpress = new JCheckBox();

		JPanel pnlTemp = new JPanel( new GridLayout(1,2) );

		//Parametre des composants
		this.ddlstArrivee.setMaximumRowCount​(5);
		this.ddlstDepart.setMaximumRowCount​(5);

		//Ajouts des composants

		this.ajouterNouvelleSaisie( this, "Jour", this.ddlstJour );
		this.ajouterNouvelleSaisie( this, "Période", this.ddlstPeriode );
		this.ajouterNouvelleSaisie( this, "Départ", this.ddlstDepart );
		this.ajouterNouvelleSaisie( this, "Arrivée", this.ddlstArrivee );
		this.ajouterNouvelleSaisie( pnlTemp, "A partir de", this.txtHeureD );
		this.ajouterNouvelleSaisie( pnlTemp, "Arriver pour", this.txtHeureA );
		pnlTemp.add( new JPanel() );
		this.add( pnlTemp );
		this.ajouterNouvelleSaisie( this, "Express", this.cbExpress );

		//Activation des composants
		this.setVisible( true );
	}

	public Object[] getEnsSaisie()
	{
		int cpt=0;

		Object[] ensSaisie = new Object[PanelSaisie.NB_INPUT];
		ensSaisie[ cpt++ ] = Integer.valueOf( this.ddlstJour.getSelectedIndex() );
		ensSaisie[ cpt++ ] = Integer.valueOf( this.ddlstPeriode.getSelectedIndex() );
		ensSaisie[ cpt++ ] = Integer.valueOf( this.ddlstDepart.getSelectedIndex() );
		ensSaisie[ cpt++ ] = Integer.valueOf( this.ddlstArrivee.getSelectedIndex() );
		ensSaisie[ cpt++ ] = this.txtHeureD.getText();
		ensSaisie[ cpt++ ] = this.txtHeureA.getText();
		ensSaisie[ cpt++ ] = Boolean.valueOf(this.cbExpress.isSelected());

		return ensSaisie;
	}

	public void reinitialiser()
	{
		this.ddlstJour.setSelectedIndex(0);
	    this.ddlstPeriode.setSelectedIndex(0);
	    this.ddlstDepart.setSelectedIndex(0);
	    this.ddlstArrivee.setSelectedIndex(0);
	    this.txtHeureD.setText("");
	    this.txtHeureA.setText("");
	    this.cbExpress.setSelected(false);
	}
}
