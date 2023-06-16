/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 30/05/23
Dernière maj  : 10/06/23
Version       : 2
==============================================================================*/

package transport.ihm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.File;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;


public class PanelImage extends JPanel
{
	public PanelImage()
	{
		//this.setBorder(new EmptyBorder(0, 0, 30, 0));

		this.add( new JLabel( new ImageIcon( Fenetre.REP_IMAGES + "banniere.png" ), SwingConstants.LEFT ) );

		/*try
		{
			BufferedImage bufferedImage = ImageIO.read(new File("transport/ihm/images/banniere.png"));
			Image image = bufferedImage.getScaledInstance(1000, 400, Image.SCALE_DEFAULT);
			this.add( new JLabel( new ImageIcon( image ), SwingConstants.LEFT ) );
		}
		catch( IOException ioe )
		{
		}*/

	}
}
