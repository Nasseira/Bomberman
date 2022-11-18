# Projet ANC3 - G01 - Bomberman App

## Notes de version itération 1

* Nous avons implémenté l'ensemble des fonctionnalités demandées.

### Choix des conceptions :

* Nous sommes partis du principe que chaque cellule contient un ensemble de game elements, chaque élément étant cassable (wall, hero, key, bomb) ou pas cassable (pillar). 
* L'affichage du bouton play a été directement implémenté dans la vue principale (BombermanView).
* Notre modèle Game se charge d'implémenter les règles du jeu et de changer les différents statuts, ce dernier est bindé avec le statut de BombermanViewModel.
* Dès que le jeu est lancé, les coordonnées de la clé sont affichées dans la console afin de facilité la tenue du jeu.

### Liste des bugs connus

* (pas de bug connu)


## Notes de version itération 2

* Nous avons implémenté l'ensemble des fonctionnalités demandées.
* Nous affichons dans le menu du jeu, un compteur indiquant le nombre de clés restantes. 


### Choix des conceptions :

* Voir itération 1.
* Toutes les coordonnées des élements du jeu sont affichées dans la console dès que le jeu est lancé.

### Liste des bugs connus

* (pas de bug connu)