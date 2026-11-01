# POO — Morpion, Puissance 4 et Nim

Projet collectif de **Programmation orientée objet**, réalisé en **L2 Informatique, semestre 3**, à l’Université d’Orléans.

L’application permet de jouer en console au Morpion, au Puissance 4 et à Nim. Les trois jeux partagent une même structure pour gérer les joueurs, les tours et les résultats.

## Fonctionnement

- Deux joueurs sur le même ordinateur, ou un joueur contre l’ordinateur.
- Deux niveaux de difficulté pour les adversaires automatiques.
- Vérification des coups, détection de fin de partie et bilan des victoires.
- Possibilité d’enchaîner plusieurs parties.

Pour Nim, la stratégie difficile utilise le XOR des tailles des tas. Les adversaires sont des stratégies programmées, sans apprentissage automatique.

## Lien avec le cours de POO

- **Classes et encapsulation** : joueurs, état de la partie et règles de chaque jeu.
- **Héritage et polymorphisme** : `Partie` est la classe abstraite commune à `Morpion`, `Puissance4` et `Nim`.
- **Abstraction des stratégies** : les adversaires spécialisés héritent de `IA` et redéfinissent `choisirCoup`.
- **Séparation des responsabilités** : modèle pour les règles, vue pour la console et contrôleur pour le déroulement.

## Organisation

```text
main/          Point d’entrée de l’application
modele/        Jeux, joueurs et stratégies des adversaires
vue/           Saisie et affichage en console
controleur/    Déroulement des parties
```

## Lancer le projet

Un JDK est nécessaire. Aucune bibliothèque externe n’est utilisée.

Depuis la racine du dépôt :

```sh
mkdir -p out
javac -encoding UTF-8 -d out main/*.java modele/*.java vue/*.java controleur/*.java
java -cp out main.Main
```

Dans IntelliJ IDEA, ouvrir le dossier, sélectionner un JDK puis lancer `main.Main`.

Le menu demande les noms, le mode de jeu et le jeu choisi. Les coups se saisissent sous la forme :

| Jeu | Saisie |
| --- | --- |
| Morpion | Ligne et colonne, par exemple `1 2` |
| Puissance 4 | Colonne de `1` à `7` |
| Nim | Numéro du tas et nombre d’allumettes à retirer, par exemple `2 3` |

## Autres projets universitaires

- [Système mono-tâche — compilateur Jack et Snake](https://github.com/hslimani45/monotache)
- [Framework Web 1 — Bonnes Lectures](https://github.com/hslimani45/bonnes-lectures)
