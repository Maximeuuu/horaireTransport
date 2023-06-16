/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 06/05/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

import java.util.ArrayList;
import java.util.List;

public class TableauObjetsFiltrable extends TableauObjets {

    public static final int ASC = 1;
    public static final int DESC = 0;

    private List<IFiltre> IFiltres;
	private Object[][] tabOrig;

    public TableauObjetsFiltrable(Object[][] tab) {
        super(tab);
		this.tabOrig = tab.clone();
		this.IFiltres = new ArrayList<>();
    }

	public void reset()
	{
		this.tabObjets = tabOrig.clone();
		this.resetIFiltres();
	}

	public void resetIFiltres()
	{
		IFiltres = new ArrayList<>();
	}

    public TableauObjets toTableauObjets() {
        return (TableauObjets) (this);
    }

    public void addIFiltre(IFiltre filter) {
        this.IFiltres.add(filter);
    }

	public void removeIFiltre(IFiltre filter) {
        this.IFiltres.remove(filter);
    }

    public void applyIFiltres() {
        List<Object[]> filteredRows = new ArrayList<>();
        for (Object[] row : tabObjets) {
            boolean rowShouldBeIncluded = true;
            for (IFiltre filter : IFiltres) {
				if (!filter.includeRow(row)) {
                    rowShouldBeIncluded = false;
                    break;
                }
            }
            if (rowShouldBeIncluded) {
                filteredRows.add(row);
            }
        }
        tabObjets = filteredRows.toArray(new Object[0][]);
    }

    public void orderBy(int colonne, int ordre) {
        // utiliser la méthode sort de List avec un Comparator
        // pour trier la liste de tableaux d'objets
        List<Object[]> rows = new ArrayList<>(List.of(tabObjets));
        rows.sort((row1, row2) -> {
            Comparable<Object> value1 = (Comparable<Object>) row1[colonne];
            Comparable<Object> value2 = (Comparable<Object>) row2[colonne];
            return (ordre == ASC) ? value1.compareTo(value2) : value2.compareTo(value1);
        });
        tabObjets = rows.toArray(new Object[0][]);
    }

}
