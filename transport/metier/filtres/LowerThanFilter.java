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

@SuppressWarnings("unchecked")
public class LowerThanFilter implements IFiltre {
    private final int column;
    private final Object value;
    private final boolean inclusive;

    public LowerThanFilter(int column, Object value, boolean inclusive) {
        this.column = column;
        this.value = value;
        this.inclusive = inclusive;
    }

    @Override
    public Object[][] filtrer(Object[][] tableau) {
        List<Object[]> result = new ArrayList<>();
        for (Object[] row : tableau) {
            int cmp = ((Comparable) row[column]).compareTo( ((Comparable)value) );
            if (inclusive) {
                if (cmp <= 0) {
                    result.add(row);
                }
            } else {
                if (cmp < 0) {
                    result.add(row);
                }
            }
        }
        return result.toArray(new Object[0][0]);
    }

	@Override
    public boolean includeRow(Object[] row)
    {
        int cmp = ((Comparable) row[column]).compareTo(value);
        return (inclusive) ? cmp <= 0 : cmp < 0;
    }

	/*
	@Override
    public boolean includeRow(Object[] row) {
		Object value = row[column];
		if (value == null) {
			return false; // or true, depending on what you want to do with null values
		}

        int cmp = ((Comparable) row[column]).compareTo(value);
        if (inclusive) {
            return cmp <= 0;
        } else {
            return cmp < 0;
        }
    }*/
}
