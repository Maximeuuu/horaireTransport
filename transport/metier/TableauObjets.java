/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 05/04/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

package transport.metier;

public class TableauObjets
{
	protected Object[][] tabObjets;

	/*Constructeurs*/
	public TableauObjets( int nbLig, int nbCol)
	{
		this.tabObjets = new Object[nbLig][nbCol];
	}

	public TableauObjets( Object[][] tab )
	{
		this.tabObjets = tab;
	}

	/*accesseurs*/
	public Object[] getRow( int lig )
	{
		return this.tabObjets[lig];
	}

	public Object[][] getRows( int ligDeb, int ligFin )
	{
		Object[][] tabRet = new Object[ ligFin-ligDeb+1 ][ this.getColumnCount() ];
		for( int cpt=ligDeb; cpt<ligFin; cpt++ )
		{
			tabRet[cpt] = this.getRow( cpt );
		}
		return tabRet;
	}

	public Object[][] getColumns( int colDeb, int colFin )
	{
		Object[][] tabRet = new Object[ this.getRowCount() ][ colFin-colDeb+1 ];
    	for (int cptLig = 0; cptLig < tabRet.length; cptLig++)
		{
        	for (int cptCol = colDeb; cptCol <= colFin; cptCol++)
			{
            	tabRet[cptLig][cptCol - colDeb] = this.tabObjets[cptLig][cptCol];
        	}
    	}
    	return tabRet;
	}

	public Object[][] getColumn( int col )
	{
		Object[][] tabRet = new Object[ this.getRowCount() ][ 1 ];
		for( int cpt=0; cpt<tabRet.length; cpt++ )
		{
			tabRet[cpt][1] = this.tabObjets[ cpt ][ col ];
		}
		return tabRet;
	}

	public Object getValueAt( int lig, int col )
	{
		return this.tabObjets[lig][col];
	}

	public Object[][] getTab()
	{
		return this.tabObjets;
	}

	public int getRowCount()
	{
		return this.tabObjets.length;
	}

	public int getColumnCount()
	{
		if( this.tabObjets.length!=0 )
		{
			return this.tabObjets[0].length;
		}
		return 0;
	}

	/*setteurs*/
	//mettre tous les setteurs
	public void setTab( Object[][] tab )
	{
		this.tabObjets = tab;
	}

	/*Conversions*/
	public String toString() //via chatGPT
	{
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < this.tabObjets.length; i++) {
	        sb.append("[");
	        for (int j = 0; j < this.tabObjets[i].length; j++) {
	            sb.append(this.tabObjets[i][j]);
	            if (j < this.tabObjets[i].length - 1) {
	                sb.append(", ");
	            }
	        }
	        sb.append("]");
	        if (i < this.tabObjets.length - 1) {
	            sb.append("\n");
	        }
	    }
	    return sb.toString();
	}

	public Object[][] toArray()
	{
		return this.getTab();
	}


	/*méthodes*/
	// Méthode pour effectuer une symétrie axiale horizontale //via chatGPT
	public void symetrieHorizontale() {
	    int nbLignes = this.tabObjets.length;
	    int nbColonnes = this.tabObjets[0].length;

	    for (int i = 0; i < nbLignes / 2; i++) {
	        Object[] temp = this.tabObjets[i];
	        this.tabObjets[i] = this.tabObjets[nbLignes - 1 - i];
	        this.tabObjets[nbLignes - 1 - i] = temp;
	    }
	}

    // Méthode pour effectuer une symétrie axiale verticale //via chatGPT
	public void symetrieVerticale() {
	    int nbLignes = this.tabObjets.length;
	    int nbColonnes = this.tabObjets[0].length;

	    for (int i = 0; i < nbLignes; i++) {
	        for (int j = 0; j < nbColonnes / 2; j++) {
	            Object temp = this.tabObjets[i][j];
	            this.tabObjets[i][j] = this.tabObjets[i][nbColonnes - 1 - j];
	            this.tabObjets[i][nbColonnes - 1 - j] = temp;
	        }
	    }
	}

	// Méthode pour inverser les lignes et les colonnes //via chatGPT
	public void inverseLignesColonnes() {
	    int nbLignes = this.tabObjets.length;
		if( nbLignes == 0 ){ return; }
	    int nbColonnes = this.tabObjets[0].length;

	    Object[][] newTab = new Object[nbColonnes][nbLignes];

	    for (int i = 0; i < nbLignes; i++) {
	        for (int j = 0; j < nbColonnes; j++) {
	            newTab[j][i] = this.tabObjets[i][j];
	        }
	    }

	    this.tabObjets = newTab;
	}

	public boolean isEmpty()
	{
		for( int cptLig=0; cptLig<this.getRowCount(); cptLig++ )
		{
			for( int cptCol=0; cptCol<this.getColumnCount(); cptCol++ )
			{
				if( this.tabObjets[cptLig][cptCol] != null ){ return false; }
			}
		}
		return true;
	}

	public Class getTypeAt( int lig, int col) //retourne un String ou jsp
	{
		return this.tabObjets[lig][col].getClass();
	}

}
