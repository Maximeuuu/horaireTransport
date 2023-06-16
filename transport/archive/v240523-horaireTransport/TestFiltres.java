/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 06/05/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

public class TestFiltres {
    public static void main(String[] args) {
        // Création d'un tableau d'objets
        Object[][] tab = {
            { "Pierre", "Dupont", Integer.valueOf(25), "Paris" },
            { "Jeanne", "Martin", Integer.valueOf(32), "Lyon" },
            { "Marc", "Dupont", Integer.valueOf(19), null },
            { "Sophie", "Leclerc", Integer.valueOf(45), "Marseille" },
            { "Lucas", "Fournier", Integer.valueOf(29), "Lille" },
            { "Lucie", "Dupont", Integer.valueOf(37), "Toulouse" },
            { "Julien", "Durand", Integer.valueOf(18), "Nice" },
            { "Marie", "Lefebvre", Integer.valueOf(18), "Bordeaux" }
        };

        // Création d'un objet Tableur
		TableauObjetsFiltrable tableur;

        // Filtrage par valeur inférieure à
		tableur = new TableauObjetsFiltrable(tab);
        tableur.addIFiltre(new LowerThanFilter(2, Integer.valueOf(30), false));
        System.out.println("Filtrage par valeur inférieure à 30 :");
		tableur.applyIFiltres();
        System.out.println( tableur.toString() );
        System.out.println();

        // Filtrage par valeur supérieure à
		tableur = new TableauObjetsFiltrable(tab);
        tableur.addIFiltre(new UpperThanFilter(2, Integer.valueOf(19), false));
        System.out.println("Filtrage par valeur supérieure à 19 :");
		tableur.applyIFiltres();
        System.out.println( tableur.toString() );
        System.out.println();

        // Filtrage par valeur égale à
		tableur = new TableauObjetsFiltrable(tab);
        tableur.addIFiltre(new EqualsToFilter(1, "Dupont"));
        System.out.println("Filtrage par valeur égale à Dupont :");
		tableur.applyIFiltres();
        System.out.println( tableur.toString() );
        System.out.println();

        // Filtrage par valeur nulle
		tableur = new TableauObjetsFiltrable(tab);
        tableur.addIFiltre(new NotNullFilter(3));
        System.out.println("Filtrage par valeur nulle :");
		tableur.applyIFiltres();
        System.out.println( tableur.toString() );
        System.out.println();

        // Filtrage par une sorte d'opérateur correspondant à un "non" ou "!"
		tableur = new TableauObjetsFiltrable(tab);
        tableur.addIFiltre(new NotFilter(new EqualsToFilter(1, "Dupont")));
        System.out.println("Filtrage par valeur différente de Dupont :");
		tableur.applyIFiltres();
        System.out.println( tableur.toString() );
        System.out.println();

		//Filtrage avec plusieurs conditions de validation "OU"
		tableur = new TableauObjetsFiltrable(tab);
		System.out.println("Filtrage par valeur égales à DUPONT ou DURAND :");
		IFiltre tmp = new OrFilter(
			new EqualsToFilter(1, "Dupont"),
			new EqualsToFilter(1, "Durand")
		);
		tableur.addIFiltre( tmp );
		tableur.applyIFiltres();
		System.out.println( tableur.toString() );
        System.out.println();

		// Filtrage appartenant aux X premières lignes - NE MARCHE PAS
		tableur = new TableauObjetsFiltrable(tab);
        tableur.addIFiltre(new FirstNRowsFilter(3, tableur.toArray()));
        System.out.println("Filtrage des 3 premières lignes :");
		tableur.applyIFiltres();
        System.out.println( tableur.toString() );
        System.out.println();

        // Filtrage appartenant à la ligne N
		tableur = new TableauObjetsFiltrable(tab);
        tableur.addIFiltre(new SingleRowFilter(0, tableur.toArray() ));
        System.out.println("Filtrage de la 1ere ligne :");
		tableur.applyIFiltres();
        System.out.println( tableur.toString() );
        System.out.println();

		// Filtrage avec plusieurs filtres
		tableur = new TableauObjetsFiltrable(tab);
		System.out.println("Filtrage particulier :");
		IFiltre tmpFiltre = new OrFilter(
			new SingleRowFilter(0, tableur.toArray() ),
			new SingleRowFilter(1, tableur.toArray() ),
			new EqualsToFilter(1, "Dupont")
			);
		tableur.addIFiltre( tmp );
		tableur.addIFiltre( new NotFilter( new LowerThanFilter(2, Integer.valueOf(30), false) ) );
		tableur.applyIFiltres();
		System.out.println( tableur.toString() );
        System.out.println();

		// Filtrage contenant valeur "on"
		tableur = new TableauObjetsFiltrable(tab);
        tableur.addIFiltre(new StringContainsFilter(1, "on" ));
        System.out.println("Filtrage contenant on :");
		tableur.applyIFiltres();
        System.out.println( tableur.toString() );
        System.out.println();
    }
}
