/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 06/05/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class NotFilter implements IFiltre {
    private final IFiltre filter;

    public NotFilter(IFiltre filter) {
        this.filter = filter;
    }

    @Override
    public Object[][] filtrer(Object[][] tableau) {
        Object[][] result = tableau;
        result = filter.filtrer(result);
        List<Object[]> resultList = new ArrayList<>(Arrays.asList(result));
        List<Object[]> notList = new ArrayList<>();
        for (Object[] row : tableau) {
            if (!resultList.contains(row)) {
                notList.add(row);
            }
        }
        return notList.toArray(new Object[0][0]);
    }

	@Override
    public boolean includeRow(Object[] row) {
        return !filter.includeRow(row);
    }
}
