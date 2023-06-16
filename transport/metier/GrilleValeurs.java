/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 06/05/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

package transport.metier;

import javax.swing.table.AbstractTableModel;

public class GrilleValeurs extends AbstractTableModel
{
	private String[] tabEntetes;
	private Object[][] tabDonnees;

	/*Constructeurs*/
	public GrilleValeurs( int nbLig, int nbCol )
	{
		this.tabEntetes = new String[nbCol];
		this.tabDonnees = new Object[nbLig][nbCol];
		fireTableStructureChanged();
	}

	public GrilleValeurs( String[] entetes, Object[][] tab )
	{
		this.tabEntetes = entetes;
		this.tabDonnees = tab;
		fireTableStructureChanged();
	}

	/*accesseurs*/
	public Object getValueAt( int lig, int col )
	{
		return this.tabDonnees[lig][col];
	}

	public String getColumnName( int col )
	{
        return this.tabEntetes[ col ];
    }


	public Object[][] getTable()
	{
		return this.tabDonnees;
	}

	public String[] getEntetes()
	{
		return this.tabEntetes;
	}

	public int getRowCount()
	{
		return this.tabDonnees.length;
	}

	public int getColumnCount()
	{
		return this.tabDonnees[0].length;
	}

	/*setteurs*/
	//mettre tous les setteurs
	public void setTable( Object[][] tab )
	{
		this.tabDonnees = tab;
		fireTableStructureChanged();
	}

}
