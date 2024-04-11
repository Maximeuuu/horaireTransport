/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 28/04/23
Dernière maj  : 09/06/23
Version       : 1
==============================================================================*/

package transport.ihm;

import javax.swing.filechooser.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.File;

public class FileChooserSauvegarde extends JFileChooser
{
	public FileChooserSauvegarde( String rep )
	{
		super( FileSystemView.getFileSystemView().getDefaultDirectory() );

		/*Propriétés*/
		this.setDialogTitle("Exporter données");
		this.setCurrentDirectory( new File(rep) );

		/*filtrer les fichiers*/
		this.setFileSelectionMode( JFileChooser.FILES_ONLY );

		/*filtrer types de fichiers*/
		this.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Texte CSV (*.csv)", "csv");
		this.addChoosableFileFilter(filter);
	}

	public static boolean confirmerEcraserFichier( File fichier )
	{
		int valeur = ((Integer)JOptionPane.showConfirmDialog( null, "Le fichier "+fichier.getName()+" existe déjà.\nVoulez-vous l'écraser ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE));
		return valeur == JOptionPane.YES_OPTION;
	}
}
