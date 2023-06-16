import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Fenetre extends JFrame
{
	private Controleur ctrl;

	private PanelSelection pnlSelection;
	private PanelSaisie pnlSaisie;
	private PanelAffichage pnlAffichage;
	private PanelActivation pnlActivation;
	/*
	private JPanel pnlErreur;
	private JPanel pnlHoraires;*/

	public Fenetre( Controleur ctrl )
	{
		this.ctrl = ctrl;

		this.setTitle( "Horaires de transport " );
		this.setSize(1000,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout( new BorderLayout() );

		//Création des composants
		this.pnlSelection  = new PanelSelection();
		this.pnlSaisie     = new PanelSaisie();
		this.pnlAffichage  = new PanelAffichage( this.ctrl );
		this.pnlActivation = new PanelActivation( this.ctrl );

		//Ajout des composants
		this.add( pnlSelection,  BorderLayout.NORTH );
		this.add( pnlSaisie,  BorderLayout.WEST );
		this.add( pnlAffichage,  BorderLayout.EAST );
		this.add( pnlActivation, BorderLayout.SOUTH );

		//Activation des composants
		this.setVisible( true );
	}

	//récupère dans un tableau l'ensemble des saisies utilisateurs
	public Object[] getEnsSaisie()
	{
		this.pnlSaisie.getEnsSaisie();
	}

	//affiche une erreur dans le Label en bas à coté de la Recherche
	public void afficherErreur( String msg )
	{
		this.pnlSaisie.afficherErreur( msg );
	}
}
