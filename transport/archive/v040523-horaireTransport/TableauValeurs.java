import javax.swing.table.AbstractTableModel;

public class TableauValeurs extends AbstractTableModel //TabDonnees
{
	protected String[] tabEntetes;
	protected Object[][] tabDonnees;

	/*Constructeurs*/
	public TableauValeurs( int nbLig, int nbCol)
	{
		this.tabEntetes = new String[nbCol];
		this.tabDonnees = new Object[nbLig][nbCol];
	}

	public TableauValeurs( String[] entetes, Object[][] tab )
	{
		this.tabEntetes = entetes;
		this.tabDonnees = tab;
	}

	/*public TableauValeurs(TableauValeurs tab) //via chatGPT
	{
		this( tab.getRowCount(), tab.getColumnCount() );
	    int nbLig = tab.getRowCount();
	    int nbCol = tab.getColumnCount();

		for( int cptCol=0; cptCol<nbCol; cptCol++ )
		{
			this.tabEntetes[cptCol] = tab.getEntetes()[cptCol];
		}

	    for (int i = 0; i < nbLig; i++)
		{
	        for (int j = 0; j < nbCol; j++)
			{
	            Object valeur = tab.getValeur(i, j);
	            if (valeur instanceof int[] || valeur instanceof String[] || valeur instanceof Object[]) {
	                // Si la valeur est un tableau, il faut faire une copie de chaque élément du tableau
	                Object[] copieValeur = new Object[((Object[]) valeur).length];
	                for (int k = 0; k < copieValeur.length; k++)
					{
	                    copieValeur[k] = ((Object[]) valeur)[k];
	                }
	                this.tabDonnees[i][j] = copieValeur;
	            }
				else
				{
	                this.tabDonnees[i][j] = valeur;
	            }
	        }
	    }
	}*/

	/*accesseurs*/
	public Object[] getLigne( int lig )
	{
		return this.tabDonnees[lig];
	}

	public Object[][] getLignes( int ligDeb, int ligFin )
	{
		Object[][] tabRet = new Object[ ligFin-ligDeb+1 ][ this.getColumnCount() ];
		for( int cpt=ligDeb; cpt<ligFin; cpt++ )
		{
			tabRet[cpt] = this.getLigne( cpt );
		}
		return tabRet;
	}

	public Object[][] getColonnes( int colDeb, int colFin ) //via chatGPT
	{
		Object[][] tabRet = new Object[ this.getRowCount() ][ colFin-colDeb+1 ];
    	for (int cptLig = 0; cptLig < tabRet.length; cptLig++)
		{
        	for (int cptCol = colDeb; cptCol <= colFin; cptCol++)
			{
            	tabRet[cptLig][cptCol - colDeb] = this.tabDonnees[cptLig][cptCol];
        	}
    	}
    	return tabRet;
	}

	public Object[][] getColonne( int col )
	{
		Object[][] tabRet = new Object[ this.getRowCount() ][ 1 ];
		for( int cpt=0; cpt<tabRet.length; cpt++ )
		{
			tabRet[cpt][1] = this.tabDonnees[ cpt ][ col ];
		}
		return tabRet;
	}

	public Object getValueAt( int lig, int col )
	{
		return this.tabDonnees[lig][col];
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
	}

	/*Conversions*/
	public String toString() //via chatGPT
	{
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < this.tabDonnees.length; i++) {
	        sb.append("[");
	        for (int j = 0; j < this.tabDonnees[i].length; j++) {
	            sb.append(this.tabDonnees[i][j]);
	            if (j < this.tabDonnees[i].length - 1) {
	                sb.append(", ");
	            }
	        }
	        sb.append("]");
	        if (i < this.tabDonnees.length - 1) {
	            sb.append("\n");
	        }
	    }
	    return sb.toString();
	}

	public Object[][] toArray()
	{
		return this.getTable();
	}


	/*méthodes*/
	public void reverse() //via chatGPT
	{
	    int rows = this.tabDonnees.length;
	    int cols = this.tabDonnees[0].length;
	    Object[][] temp = new Object[rows][cols];
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            temp[i][j] = this.tabDonnees[rows - i - 1][cols - j - 1];
	        }
	    }
	    this.tabDonnees = temp;
	}

	public TableauValeurs clone() //chat gpt conseille de mettre une maj "Clone"
	{
		return null;
	}

	public boolean isEmpty()
	{
		for( int cptLig=0; cptLig<this.getRowCount(); cptLig++ )
		{
			for( int cptCol=0; cptCol<this.getColumnCount(); cptCol++ )
			{
				if( this.tabDonnees[cptLig][cptCol] != null ){ return false; }
			}
		}
		return true;
	}

	public String getTypeCase( int lig, int col) //retourne un String ou jsp
	{
		return "";
	}

}
