/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 07/05/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

package transport.metier;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class AnalyserTabObjet
{
	public static final int ASC = 1;
	public static final int DESC = 0;

	public static Object[][] filterByLowerThan( Object[][] tab, int colonne, Object valeur, boolean inclusif )
	{
		if (colonne >= tab[0].length || tab.length < 1)
		{
			return new Object[0][0]; // retourne un tableau vide si le tableau est vide ou si la colonne est hors limites
		}

		ArrayList<Object[]> tabRet = new ArrayList<Object[]>();
		int valComp;

		for( int cpt = 0; cpt < tab.length; cpt++ )
		{
			try
			{
				valComp = ( (Comparable) tab[cpt][colonne] ).compareTo( (Comparable)valeur );
				if( inclusif && valComp == 0 || valComp < 0 ){ tabRet.add( tab[cpt] ); }
			}
			catch (ClassCastException e) {/*ignore les éléments qui ne sont pas comparables*/}
		}

		return tabRet.toArray( new Object[tabRet.size()][tab[0].length] );
	}

	public static Object[][] filterByUpperThan( Object[][] tab, int colonne, Object valeur, boolean inclusif )
	{
		if (colonne >= tab[0].length || tab.length < 1)
		{
			return new Object[0][0]; // retourne un tableau vide si le tableau est vide ou si la colonne est hors limites
		}

		ArrayList<Object[]> tabRet = new ArrayList<Object[]>();
		int valComp;

		for( int cpt = 0; cpt < tab.length; cpt++ )
		{
			try
			{
				valComp = ( (Comparable) tab[cpt][colonne] ).compareTo( (Comparable)valeur );
				if( inclusif && valComp == 0 || valComp > 0 ){ tabRet.add( tab[cpt] ); }
			}
			catch( ClassCastException e ){/*ignore les éléments qui ne sont pas comparables*/}
		}

		return tabRet.toArray( new Object[tabRet.size()][tab[0].length] );
	}


	public static Object[][] filterByEqualsTo( Object[][] tab, int colonne, Object valeur )
	{
		if (colonne >= tab[0].length || tab.length < 1)
		{
			return new Object[0][0]; // retourne un tableau vide si le tableau est vide ou si la colonne est hors limites
		}

		ArrayList<Object[]> tabRet = new ArrayList<Object[]>();
		for( int cpt = 0; cpt < tab.length; cpt++ )
		{
			try
			{
				if( ((Comparable)tab[cpt][colonne]).equals( valeur ) ){ tabRet.add(tab[cpt]); }
			}
			catch (ClassCastException e) {/*ignore les éléments qui ne sont pas comparables*/}
		}

		return tabRet.toArray( new Object[tabRet.size()][tab[0].length] );
	}


	public static Object[][] orderBy( Object[][] tab, int colonne, int ordre ) //0 ou 1 et utiliser compareTo
	{
		return null;
	}

	public static Object[][] filterByNotNull( Object[][] tab, int colonne )
	{
		return AnalyserTabObjet.filterByNotNull( tab, colonne );
	}

	public static Object[][] filterByNotNull( Object[][] tab, int colDeb, int colFin )
	{
		return null;
	}
}
