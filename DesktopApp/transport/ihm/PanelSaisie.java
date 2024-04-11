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
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Arrays;
import javax.swing.border.EmptyBorder;

public class PanelSaisie extends JPanel
{
	/*    CONSTANTES    */
	private static final int NB_INPUT = 7;


	/*    ATTRIBUTS    */
	private Controleur ctrl;

	private JComboBox<String> ddlstJour;
	private JComboBox<String> ddlstPeriode;
	private JComboBox<String> ddlstDepart;
	private JComboBox<String> ddlstArrivee;
	private JTextField txtHeureD;
	private JTextField txtHeureA;
	private JCheckBox cbExpress;


	/*    CONSTRUCTEUR    */
	public PanelSaisie( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setLayout( new GridLayout(PanelSaisie.NB_INPUT, 2, 0, 0) );
		this.setBorder(new EmptyBorder(10, 0, 0, 0));
		this.setOpaque(false);

		//Création des composants
		String[] optionsJours = PanelSaisie.ajouterDefautListe( this.ctrl.getJours(), "Selectionner");
		String[] optionsPeriodes = PanelSaisie.ajouterDefautListe( this.ctrl.getPeriodes(), "Selectionner");
		String[] optionsArrets = PanelSaisie.ajouterDefautListe( this.ctrl.getArrets(), "Selectionner");

		this.ddlstJour = new JComboBox<>( optionsJours );
		this.ddlstPeriode = new JComboBox<>( optionsPeriodes );
		this.ddlstDepart = new JComboBox<>( optionsArrets );
		this.ddlstArrivee = new JComboBox<>( optionsArrets );
		this.txtHeureD = new JTextField(4);
		this.txtHeureA = new JTextField(4);
		this.cbExpress = new JCheckBox();
		//TODO : implémenter paramètres sur cbExpress

		JPanel pnlTemp = new JPanel( new GridLayout(1,2) );

		//Parametre des composants
		this.ddlstArrivee.setMaximumRowCount​(5);
		this.ddlstDepart.setMaximumRowCount​(5);

		//Ajouts des composants
		this.ajouterNouvelleSaisie( this, "Jour", this.ddlstJour );
		this.ajouterNouvelleSaisie( this, "Période", this.ddlstPeriode );
		this.ajouterNouvelleSaisie( this, "*Départ", this.ddlstDepart );
		this.ajouterNouvelleSaisie( this, "*Arrivée", this.ddlstArrivee );
		this.ajouterNouvelleSaisie( pnlTemp, "A partir de", this.txtHeureD );
		this.ajouterNouvelleSaisie( pnlTemp, "Arriver pour", this.txtHeureA );
		pnlTemp.add( new JPanel() );
		this.add( pnlTemp );
		this.ajouterNouvelleSaisie( this, "Express", this.cbExpress );

		//Activation des composants
		this.setVisible( true );
	}


	/*    METHODES - INIT    */
	//premet d'ajouter un texte par défaut
	private static String[] ajouterDefautListe( String[] listeOption, String defaut )
	{
		listeOption = Arrays.copyOf(listeOption, listeOption.length + 1);
		System.arraycopy(listeOption, 0, listeOption, 1, listeOption.length - 1);
		listeOption[0] = defaut;
		return listeOption;
	}

	//créé et positionne un composant (aligné)
	private void ajouterNouvelleSaisie( JPanel panel, String label, JComponent composant )
	{
		JPanel pnlTemp;
		JLabel lblTemp;

		pnlTemp = new JPanel ();
		pnlTemp.setLayout ( new FlowLayout ( FlowLayout.LEFT ) );
		pnlTemp.setBorder(new EmptyBorder(10, 0, 10, 0));

		lblTemp = new JLabel  ( label+" : " ,    SwingConstants.RIGHT );
		lblTemp.setPreferredSize ( new Dimension( 90, 12 ) );

		pnlTemp.add ( lblTemp );
		pnlTemp.add ( composant );

		panel.add ( pnlTemp );
	}


	/*    METHODES - CONTROLEUR    */
	//met à jour le contenu des listes déroulantes pour les arrets
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

	//permet de récupérer toutes les saisies dans un tableau
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

	//remet tous les inputs à l'origine
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

	//inverser l'arret A avec le B
	public void inverser()
	{
		int indiceD = this.ddlstDepart.getSelectedIndex();
		int indiceA = this.ddlstArrivee.getSelectedIndex();

		this.ddlstDepart.setSelectedIndex( indiceA );
	    this.ddlstArrivee.setSelectedIndex( indiceD );
	}
}
