/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 06/05/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class OrFilter implements IFiltre {

	private List<IFiltre> filters;

	public OrFilter(IFiltre... filters) {
		this.filters = Arrays.asList(filters);
	}

	public Object[][] filtrer(Object[][] tableau) {
		ArrayList<Object[]> lignesFiltrees = new ArrayList<Object[]>();

		// On parcourt les lignes du tableau
		for (Object[] row : tableau) {
			// Si au moins un filtre de la liste renvoie true pour cette ligne
			if (filters.stream().anyMatch(f -> f.includeRow(row))) {
				// On ajoute la ligne à la liste des lignes filtrées
				lignesFiltrees.add(row);
			}
		}

		// On retourne un tableau contenant les lignes filtrées
		return lignesFiltrees.toArray(new Object[lignesFiltrees.size()][]);
	}

	public boolean includeRow(Object[] row) {
		for (IFiltre filter : filters) {
			if (filter.includeRow(row)) {
				// Si au moins un filtre est vrai, alors on inclut la ligne
				return true;
			}
		}
		return false;
	}
}
