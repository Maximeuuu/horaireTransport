import javax.swing.*;
import java.awt.BorderLayout;

public class PanelAffichage extends JPanel
{
	private JLabel lblNbHoraire;
	private JTable tblHoraires;
	private Controleur ctrl;

	public PanelAffichage( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setLayout( new BorderLayout() );

		//Création des composants
		this.lblNbHoraire = new JLabel( "0 horaire disponible" ); //exemple à modifier par la suite
		this.tblHoraires = new JTable( this.ctrl.getTableur() );

		//Ajouts des composants
		this.add( this.lblNbHoraire, BorderLayout.NORTH );
		this.add( this.tblHoraires, BorderLayout.CENTER );

		//Activation des composants
		this.setVisible( true );
	}
}
