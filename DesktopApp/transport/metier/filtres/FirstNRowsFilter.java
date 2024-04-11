/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 06/05/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

package transport.metier.filtres;
import transport.metier.IFiltre;

import java.util.Arrays;

public class FirstNRowsFilter implements IFiltre {
    private final int n;
    private final Object[][] tableau;

    public FirstNRowsFilter(int n, Object[][] tableau) {
        this.n = n;
        this.tableau = tableau;
    }

    @Override
    public Object[][] filtrer(Object[][] tableau) {
        return Arrays.copyOfRange(tableau, 0, n);
    }

    @Override
    public boolean includeRow(Object[] row) {
        Object[][] filteredTable = filtrer(tableau);
        for (Object[] filteredRow : filteredTable) {
            if (Arrays.equals(row, filteredRow)) {
                return true;
            }
        }
        return false;
    }
}
