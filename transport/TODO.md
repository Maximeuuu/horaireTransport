# HorairesTransport

Liste des choses à ajouter, modifier ou supprimer dans le projet.

## A faire

### Inverser sens des horaires
- si indice impair : indice +1 sinon indice-1
- bouton "inverser" sens
- bouton "inverser" -> image au lieu d'un texte ?

### Images
- changer noms recommendés pour "vehicule_identifiant_arretA_arretB.csv" afin de pouvoir :
- utiliser les images fournies par Anais (logo plus gros lorsque le transport correspond)
- par défaut (début ou erreur de format) : bandeau simple de base
- redimensionner l'iamge de panelImage

### Documentation du projet
- commenter
- javadoc
- relecture du code
- noms des variables
- allman

### Ajout d'un executable
- .exe ou lanceur

### Suppression des test dans le code
- System.out.println() sur les actions...

* * *

## Idées

### Clarification saisies IHM
- "*" devant les champs obligatoires (mettre en rouge etc)
- encadrer en rouge la case lors d'une mauvaise saisie
- ajouter sur l'affichage, le nom du dernier arrêt (terminus) pour chaque trajets

### Réparer la structure MVC (surtout sur la partie fichier et sauvegarde)
- passer par le controleur et une classe de métier
- supprimer ctrl.afficherMessage( String )
- corriger fautes orthographes dans le labelErreur

### Fonctionnalité trajet
- utiliser graphes
- ajouter plusieurs arrets à un arret
- détermine le meilleur chemin

### fonctionnalité express
- surligner dans JTable ou trier directement
- déterminer par un calcul ou une constante ou une saisie utilisateur ?
- case à cocher (et saisie utilisateur ?)
- utiliser la méthode "miseEnValeurLigne( int lig )"

### Reset pas profond
- verifier si le bouton reinitialiser affecter aussi la partie métier
- effacer correctement la table

### Intégrité des fichiers data
- verifier au début du programme si les fichiers de data sont ok
- verifier qu'ils sont de pair -> popup avertissement ?
- verifier bon nombre de lignes colonnes
- utiliser AnalyserFichierLigne( File file )

### Fonctionnalité de recherche avancée
- tester si le transport passe par une ville particulière
- "Ville : ___________"
- test si tab.contient( txtVille.getText() )
- ajouter un fichier xml pour la configuration des arrêts (liens entres les arrêts, aller-retour...)

### Partie affichage
- logo : bandeau en haut + icone -> Anais
- afficher le logo en haut de la fenetre
- changer les couleurs ?
- ajout d'un menubar ?
- réduire largeur colonne 1, pk bug affichage sur linux texte "coupé"
