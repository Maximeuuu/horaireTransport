/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 06/05/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

package transport.metier.filtres;
import transport.metier.IFiltre;

import java.util.Arrays;

public class SingleRowFilter implements IFiltre {
    private final int rowIndex;
    private final Object[][] tableau;

    public SingleRowFilter(int rowIndex, Object[][] tableau) {
        this.rowIndex = rowIndex;
        this.tableau = tableau;
    }

    @Override
    public Object[][] filtrer(Object[][] tableau) {
        Object[][] result = new Object[1][];
        if (tableau != null) {
            result[0] = tableau[rowIndex];
        }
        return result;
    }

    @Override
    public boolean includeRow(Object[] row) {
        return Arrays.equals(row, filtrer(tableau)[0]);
    }
}
