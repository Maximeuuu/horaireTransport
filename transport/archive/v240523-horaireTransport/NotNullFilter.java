/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 06/05/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

import java.util.List;
import java.util.ArrayList;

public class NotNullFilter implements IFiltre {
    private final int column;

    public NotNullFilter(int column) {
        this.column = column;
    }

    @Override
    public Object[][] filtrer(Object[][] tableau) {
        List<Object[]> result = new ArrayList<>();
        for (Object[] row : tableau) {
            if (row[column] != null) {
                result.add(row);
            }
        }
        return result.toArray(new Object[0][0]);
    }

	@Override
	public boolean includeRow(Object[] row) {
		return row[column] != null;
	}

}
