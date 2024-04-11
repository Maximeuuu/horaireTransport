package fr.transport.modele.outils;

import java.util.List;

public class UtilitaireTableau
{
	private UtilitaireTableau( )
	{
	}

	public static String[][] convertirListeEnTableau( List<String[]> lstDonnees )
	{
		String[][] donnees = new String[lstDonnees.size()][];

		for( int i = 0; i < lstDonnees.size(); i++ )
		{
			donnees[i] = lstDonnees.get( i );
		}

		return donnees;
	}
}
