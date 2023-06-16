import javax.swing.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class PanelSaisie extends JPanel
{
	//private JPanel[] ensJPanel;
	private final int NB_INPUT = 7;
	private JComboBox<String> ddlstJour;
	private JComboBox<String> ddlstPeriode;
	private JComboBox<String> ddlstDepart;
	private JComboBox<String> ddlstArrivee;
	private JTextField txtHeureD;
	private JTextField txtHeureA;
	private JCheckBox cbExpress;

	public PanelSaisie()
	{
		//int cptPanel;
		//this.ensJPanel = new JPanel[8];

		//this.setLayout( new GridLayout(8, 2, 10, 10) );
		this.setLayout( new BorderLayout() );

		//Création des composants
		/*
		for( int cpt=0; cpt<this.ensJPanel.length; cpt++ )
		{
			this.ensJPanel[cpt] = new JPanel();
			this.ensJPanel[cpt].setLayout( new FlowLayout(FlowLayout.LEFT) );
		}*/
		JPanel pnlLabel = new JPanel( new GridLayout(8,1) );
		JPanel pnlSaisie = new JPanel( new GridLayout(8,1) );

		String[] optionsJour = {"Selectionner jour", "Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"}; //il faudra faire un get dans le controleur/metier par la suite
		String[] optionsPeriode = {"Selectionner peridode", "PS","VS","ETE","FERIE"};
		String[] optionsDepart = {"ST-ARNOULT/Croix Blanche/Houquetot/Centre/Mairie","2","3","4"};
		String[] optionsArrivee = {"ST-ARNOULT/Croix Blanche/Houquetot/Centre/Mairie","2","3","4"};

		this.ddlstJour = new JComboBox<>( optionsJour ); //5
		//this.pnlSaisie.setMaximumSize(new Dimension(500, 1));
		this.ddlstPeriode = new JComboBox<>( optionsPeriode ); //3
		this.ddlstDepart = new JComboBox<>( optionsDepart ); //20
		this.ddlstArrivee = new JComboBox<>( optionsArrivee ); //20
		this.txtHeureD = new JTextField(4);
		this.txtHeureA = new JTextField(4);

		this.cbExpress = new JCheckBox();

		//Ajouts des composants
		/*for( int cpt=0; cpt<this.ensJPanel.length; cpt++ )
		{
			this.add( ensJPanel[cpt] );
		}*/
		this.add( pnlLabel, BorderLayout.WEST  );
		this.add( pnlSaisie, BorderLayout.CENTER );

		/*cptPanel = 0;

		this.ensJPanel[cptPanel].add( new JLabel("Jour : ") );
		this.ensJPanel[cptPanel].add( this.ddlstJour );
		cptPanel++;
		//this.add( new JLabel("Jour : "));
		//this.add( this.ddlstJour ); //a regarder pour bien aligner ?

		this.ensJPanel[cptPanel].add( new JLabel("Période : ") );
		this.ensJPanel[cptPanel].add( this.ddlstPeriode );
		cptPanel++;

		this.ensJPanel[cptPanel].add( new JLabel("Départ : ") );
		this.ensJPanel[cptPanel].add( this.ddlstDepart );
		cptPanel++;

		this.ensJPanel[cptPanel].add( new JLabel("Arrivée : ") );
		this.ensJPanel[cptPanel].add( this.ddlstArrivee );
		cptPanel++;

		this.ensJPanel[cptPanel].add( new JLabel("A partir de : ") );
		this.ensJPanel[cptPanel].add( this.txtHeureD );
		cptPanel++;

		this.ensJPanel[cptPanel].add( new JLabel("Arriver pour : ") );
		this.ensJPanel[cptPanel].add( this.txtHeureA );
		cptPanel++;

		this.ensJPanel[cptPanel].add( new JLabel("Express : ") );
		this.ensJPanel[cptPanel].add( this.cbExpress );
		cptPanel++;*/

		pnlLabel.add( new JLabel("Jour : ", JLabel.RIGHT) );
		pnlSaisie.add( this.ddlstJour );

		pnlLabel.add( new JLabel("Période : ", JLabel.RIGHT) );
		pnlSaisie.add( this.ddlstPeriode );

		pnlLabel.add( new JLabel("Départ : ", JLabel.RIGHT) );
		pnlSaisie.add( this.ddlstDepart );

		pnlLabel.add( new JLabel("Arrivée : ", JLabel.RIGHT) );
		pnlSaisie.add( this.ddlstArrivee );

		pnlLabel.add( new JLabel("A partir de : ", JLabel.RIGHT) );
		pnlSaisie.add( this.txtHeureD );

		pnlLabel.add( new JLabel("Arriver pour : ", JLabel.RIGHT) );
		pnlSaisie.add( this.txtHeureA );

		pnlLabel.add( new JLabel("Express : ", JLabel.RIGHT) );
		pnlSaisie.add( this.cbExpress );

		//Activation des composants
		this.setVisible( true );
	}
	
	public Object[] getEnsSaisie()
	{
		int cpt=0;
		
		Object[] ensSaisie = new Object[ PanelSaisie.NB_INPUT ];
		ensSaisie[ cpt++ ] = (String)this.ddlstJour.getSelectedItem();
		ensSaisie[ cpt++ ] = (String)this.ddlstPeriode.getSelectedItem();
		ensSaisie[ cpt++ ] = (String)this.ddlstDepart.getSelectedItem();
		ensSaisie[ cpt++ ] = (String)this.ddlstArrivee.getSelectedItem();
		ensSaisie[ cpt++ ] = new Temps( "00:00" );
		ensSaisie[ cpt++ ] = new Temps( "23:59" );
		ensSaisie[ cpt++ ] = (boolean) true;
		
		return ensSaisie;
	}
}
