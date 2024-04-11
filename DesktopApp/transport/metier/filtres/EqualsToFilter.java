/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 06/05/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

package transport.metier.filtres;
import transport.metier.IFiltre;

import java.util.List;
import java.util.ArrayList;

public class EqualsToFilter implements IFiltre {
    private final int column;
    private final Object value;

    public EqualsToFilter(int column, Object value) {
        this.column = column;
        this.value = value;
    }

    @Override
	public Object[][] filtrer(Object[][] tableau) {
        List<Object[]> result = new ArrayList<>();
        for (Object[] row : tableau) {
            if (row[column].equals(value)) {
                result.add(row);
            }
        }
        return result.toArray(new Object[0][0]);
    }

	@Override
    public boolean includeRow(Object[] row) {
        return row[column].equals(value);
    }
}
