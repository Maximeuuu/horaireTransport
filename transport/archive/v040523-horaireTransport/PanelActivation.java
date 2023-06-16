import javax.swing.*;
import java.awt.BorderLayout;

public class PanelActivation extends JPanel implements ActionListener
{
	private JButton btnRechercher;
	private JLabel  lblErreur;
	private Controleur ctrl;

	PanelActivation( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setLayout( new BorderLayout() );

		//Création des composants
		this.lblErreur = new JLabel("...");
		this.btnRechercher = new JButton("Rechercher");

		//Ajouts des composants
		this.add( this.lblErreur, BorderLayout.CENTER );
		this.add( this.btnRechercher, BorderLayout.EAST );

		//Activation des composants
		this.btnRechercher.addActionListener(this);
		this.setVisible( true );
	}
	
	public void actionPerformed( ActionEvent e)
	{
		if( e.getSource() == this.btnRechercher )
		{
			System.out.println("Rechercher");
			this.ctrl.rechercher();
		}
	}
}
