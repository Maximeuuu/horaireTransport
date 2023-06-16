/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 07/05/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

package transport.metier.filtres;
import transport.metier.IFiltre;

import java.util.List;
import java.util.ArrayList;

public class StringContainsFilter implements IFiltre {
    private final int column;
    private final Object value;

    public StringContainsFilter(int column, Object value) {
        this.column = column;
        this.value = value;
    }

	@Override
	public Object[][] filtrer(Object[][] tableau) {
	    List<Object[]> rows = new ArrayList<>();

	    for (Object[] row : tableau) {
	        if (includeRow(row)) {
	            rows.add(row);
	        }
	    }

	    return rows.toArray(new Object[rows.size()][]);
	}

	@Override
	public boolean includeRow(Object[] row) {
	    String cellValue = row[column].toString();
	    return cellValue.contains(value.toString());
	}

}
