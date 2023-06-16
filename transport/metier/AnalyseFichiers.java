/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 27/05/23
Dernière maj  : 10/06/23
Version       : 1
==============================================================================*/

package transport.metier;
import transport.Controleur;

import java.io.FileReader;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class AnalyseFichiers
{
	//Exporte les fichiers au format CSV
	public static String grilleValeursToCSV( Controleur ctrl, GrilleValeurs tableau, File fichier)
	{
		//tester si l'extension est la bonne sinon l'ajouter
		if( !fichier.toString().toLowerCase().endsWith( HorairesTransport.EXTENSION ) )
		{
			fichier = new File( fichier.getAbsolutePath().toString() + HorairesTransport.EXTENSION );
		}

		//demander confirmation si le fichier existe déjà
		if( fichier.exists() )
		{
			if( !ctrl.confirmerEcraserFichier( fichier ) ){ return "Exportation annulée."; }
		}

		try
		{
			PrintWriter csv = new PrintWriter( new FileOutputStream( fichier ) );

			for (int i = 0; i < tableau.getColumnCount(); i++)
			{
				csv.print(tableau.getColumnName(i) + ",");
			}

			csv.write("\n");

			for (int i = 0; i < tableau.getRowCount(); i++)
			{
				for (int j = 0; j < tableau.getColumnCount(); j++)
				{
					csv.print(tableau.getValueAt(i, j).toString() + ",");
				}
				csv.print("\n");
			}

			csv.close();
		}
		////catch( NullPointerException npe ){ return "Aucune données."; }
		catch( Exception e ){ return "Erreur : "+e; }
		return "Fichier exporté.";
	}

	// Récupère la liste des fichier dans repertoire
	public static String[] getListeFichier( String repertoire )
	{
		File dir = new File( repertoire );
		File[] liste = dir.listFiles();
		String[] listeFichier = new String[ liste.length ];
		String fichier;

		for( int cpt=0; cpt<listeFichier.length; cpt++ )
		{
			if( liste[cpt].isFile() )
			{
				fichier = liste[cpt].getName();
				listeFichier[cpt] = fichier.substring(0, fichier.lastIndexOf('.'));
			}
		}
		return listeFichier;
	}

	//charge les données depuis un fichier csv
	public static Object[][] chargerDonnees( String fichier )
	{
		ArrayList<Object[]> tabData = new ArrayList<>();
		FileReader fr;

		try
		{
			fr = new FileReader( HorairesTransport.REPERTOIRE+fichier+HorairesTransport.EXTENSION );
			Scanner sc = new Scanner( fr );

			while( sc.hasNextLine() )
			{
				Object[] ligData = sc.nextLine().split(",");
				tabData.add( ligData );
			}

			fr.close();
		}
		catch (Exception e){ e.printStackTrace(); }

		//convertir dans un tableau d'objet à deux dimensions
		Object[][] tabRet = new Object[ tabData.size() ][];
		for (int lig = 0; lig < tabData.size(); lig++)
		{
			tabRet[lig] = tabData.get( lig );
		}
		return tabRet;
	}
}
