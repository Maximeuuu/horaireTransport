/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 06/05/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

import java.util.List;
import java.util.ArrayList;

public class UpperThanFilter implements IFiltre
{
    private final int column;
    private final Object value;
    private final boolean inclusive;

    public UpperThanFilter(int column, Object value, boolean inclusive)
    {
        this.column = column;
        this.value = value;
        this.inclusive = inclusive;
    }

    @Override
    public Object[][] filtrer(Object[][] tableau)
    {
        List<Object[]> result = new ArrayList<>();
        for (Object[] row : tableau) {
            int cmp = ((Comparable) row[column]).compareTo(value);
            if (inclusive) {
                if (cmp >= 0) {
                    result.add(row);
                }
            } else {
                if (cmp > 0) {
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
        return (inclusive) ? cmp >= 0 : cmp > 0;
    }
}
