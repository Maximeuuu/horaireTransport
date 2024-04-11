/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 28/04/23
Dernière maj  : 28/05/23
Version       : 1
==============================================================================*/

package transport.ihm;

import javax.swing.*;
import java.awt.Color;
import javax.swing.table.*;
import java.awt.Component;

public class JTableHoraires extends JTable
{
	public JTableHoraires( AbstractTableModel tbl )
	{
		super( tbl );

		// Désactiver le déplacement des colonnes
		JTableHeader header = this.getTableHeader();
		header.setReorderingAllowed(false);

		// Empêcher le redimensionnement des colonnes
		this.getTableHeader().setResizingAllowed(false);

		// Couleurs
		//this.tblHoraires.setGridColor​( new Color(0,0,0) );
		this.setSelectionBackground( new Color(97,175,227) );
	}

	public Component prepareRenderer( TableCellRenderer renderer, int row, int column )
	{
		Component c = super.prepareRenderer( renderer, row, column );
		Color color1 = Color.WHITE;
		Color color2 = new Color(220,220,220);
		if( !c.getBackground().equals(getSelectionBackground()) )
		{
			Color coleur = (row % 2 == 0 ? color1 : color2);
			c.setBackground(coleur);
			coleur = null;
		}
		return c;
	}
}
