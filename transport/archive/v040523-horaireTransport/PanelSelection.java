import javax.swing.*;
import java.awt.BorderLayout;

public class PanelSelection extends JPanel
{
	private JComboBox<String> ddlstLigne;
	private JButton btnInverser;
	private JButton btnReinitialiser;

	public PanelSelection()
	{
		this.setLayout( new BorderLayout() );

		//Création des composants
		JPanel pnlLigne = new JPanel();

		String[] optionsLigne = {"506_le-Havre_caudebec-en-caux","506_caudebec-en-caux_le-Havre", "..."};
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
		this.setVisible( true );
	}
}
