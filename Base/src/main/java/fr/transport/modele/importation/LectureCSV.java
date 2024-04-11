package fr.transport.modele.importation;

import fr.transport.modele.entite.LigneTransport;
import fr.transport.modele.outils.UtilitaireTableau;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LectureCSV extends AbstractLectureLigne
{
	private LigneTransport ligneTransport;
	private static final String SEPARATEUR = ",";

	public LectureCSV( File fichierLigne )
	{
		super();

		String nomFichier = fichierLigne.getName();

		String extension = nomFichier.substring( nomFichier.lastIndexOf( '.' ) + 1 );
		String nomFichierSansExtension = nomFichier.substring( 0, nomFichier.lastIndexOf( '.' ) );

		if( ! extension.equals( "csv" ) )
		{
			throw new IllegalArgumentException( "Le fichier n'est pas un fichier CSV" );
		}

		this.ligneTransport = initialiserLigneTransport( nomFichierSansExtension );

		try
		{
			String[][] donnees = lireDonnees( fichierLigne );
			AnalyseLigneTransport analyseLigneTransport = new AnalyseLigneTransport( donnees );
			analyseLigneTransport.completerLigne( this.ligneTransport );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

	private LigneTransport initialiserLigneTransport( String nomFichier )
	{
		String[] informations = nomFichier.split( "_" );

		if( informations.length != 3 )
		{
			throw new IllegalArgumentException( "Le nom du fichier n'est pas correct" );
		}

		String identifiant = informations[0];
		String nom = informations[1] + " " + informations[2];

		return new LigneTransport( identifiant, nom );
	}

	private String[][] lireDonnees( File fichierLigne ) throws IOException
	{
		List<String[]> lstDonnees = new ArrayList<>();

		InputStream inputStream = new FileInputStream( fichierLigne );
		BufferedReader br = new BufferedReader( new InputStreamReader( inputStream ) );

		String ligne;
		while( ( ligne = br.readLine() ) != null )
		{
			String[] cellules = ligne.split( SEPARATEUR );
			lstDonnees.add( cellules );
		}

		br.close();
		inputStream.close();

		return UtilitaireTableau.convertirListeEnTableau( lstDonnees );
	}

	@Override
	public LigneTransport getLigneTransport()
	{
		return this.ligneTransport;
	}
}
