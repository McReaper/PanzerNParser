# JOURNAL du Mardi 14 avril

# Disparition d'Andréa

`Andréa est non-officiellement absent du projet (malgré nos tentatives de prise de contact avec lui)`

## Répartition des tâches

- `Victor`, `Mathis`, `Sami` travaille sur le modèle (diagramme de classe) et sur les méthodes de l'automate
- `Victor` et `Sami` travaille sur le PROPOSAL.md
- `Emilie` et `Jean-Théophane` travaillent sur les automates avec l'aide de `Mathis`
- `Bertrand` travaille sur le Toolkit de Java pour essayer de l'intégrer au SpaceDuel

### Emilie, Jean-Théophane
- création d'une entité et de l'action Move (sans le parser).

---

# JOURNAL du Mercredi 15 avril

## Planning des choses à réaliser

- Mise a jour du [PROPOSAL.md](PROPOSAL.md) avec les retours du tuteur
- Listing des taches a réaliser dans le projet pour aboutir a un diagramme de gantt
- rédiger un contrat et le soumettre au tuteur pour une relecture

## Répartition des tâches

- `B>` travaille sur des début de Sprites du jeu / sur les maquettes de l'interface utilisateur
- `V/M/S/JT/E` travaille sur le contrat et les diagrammes intégrés

---

# JOURNAL du Jeudi 16 avril

## Planning du jour

- Envoit du Contrat à M.Périn
- Etude du podcast et débat avec le groupe
- Ajout des liens de plusieurs fichiers `.gal` au [PROPOSAL.md](PROPOSAL.md)
- Mise en place du squelette du projet Java & Gestion des branches git

## Répartition des tâches

- `V>` Mise à jour du LOG.md, Mise en place du projet Java sous eclipse avec le parser et push sur la branche master
- `JT>` Écriture des classes Java de bases (vides) pour le projet
- `E/M/S>` Écriture de plusieurs fichiers `.gal` complexes pour le jeu (notemment pour le chassis du tank et le drone)
- `M>` Mise a jour de la branche `automate` qui contient un exemple d'exécution d'automate sans parser
- `B>` Premières approches avec GameCanvas.java pour comprendre son utilisation

## Réflexion en cours

- `V/B>` Le view se fait repaint grâce au controller ou par lui-même comme dans spaceduel ? Les deux points de vue se valent (`à discuter`).

---

# JOURNAL du Vendredi 17 avril

## Planning du jour

- Réajustement des fichiers `.gal` + complétion des fichiers manquants pour certaines entités (comme le tir du canon)
- Etude rapide du parser pour comprendre comment analyser un Objet Java AST.
- Début d'implémentation des classes essentielles à AutBuilder.java qu'on devra implémenter plus tard
- Début d'implémentation du MVC sur la branche `mvc` à partir de la branche `dev`

## Répartition des tâches

- `B/M` expliquent au reste de l'équipe le rôle et le fonctionnement du parser.
- `V/JT/B>` Partie MVC, création des premières classes essentielles pour fournir une base de développement au reste de l'équipe (**devra incorporer la partie Automate plus tard**)
- `E/M/S>` Partie Automate, création d'un premier diagramme de classe que **AutBuilder.java** devra respecter

## Remarques :

- `E>` a developpé un fichier `.gal` pour le tank et mis à jour celui du chassis suite aux conseils du tuteur.
- Par rapport à la dernière refléxion de `V & B` (*lol, VnB*) : Le view se fait repaint grâce à lui-même (FPS) -> voir comment gérer ça dans le code

## Réflexion en cours

- `V/B>` Comment gérer le timer de **Model.java**- Retravailler sur le modèle/vue/contrôleur
- Retravailler sur AutBuilder et la classe Automaton
- Essayer une fusion des deux travaux (au moins avec les classes vides) ?
- L'utilisation de l'interface **IVisitor.java** du parser Java reste un peu flou de notre point de vue. Sachant que AutBuilder devra en hériter, c'est embétant.

---

# JOURNAL du Samedi 18 Avril

## Planning du jour

- Retravailler sur le modèle/vue/contrôleur et mettre au claire les zones de flou (les timers, la view, les avatars).
- Retravailler sur AutBuilder et la classe Automaton, créer toutes les classes du automaton vides, puis en implémenter certaines pour faire fonctionner un petit exemple d'automate sans le parser.
- Essayer une fusion des deux travaux (au moins avec les classes vides).

## Répartition des tâches

- `B/JT/V>` Regler toutes les zones d'ombres du MVC, sur les timer, les ticks, les repaints, les avatars.
Continuer de travailler sur la partie MVC pour avoir une version très simple d'une fenêtre qui s'affiche.
- `E/M/S>` Implémenter les classes vides des automates, implémentations de certaines classes pour faire fonctionner un petit automate sans parser.
- `M>` Début et avancement de l'implémentation du botBuilder qui instancie les automates à partir de l'AST.
- `B/M/S/JT/E/V>` Revue du code des autres membres, commentaires et questions pour comprendre. Correction une fois les commentaires lus et les questions soulevées par ceux ci résolues avec le groupe.

## Remarques :

- `JT>` Avancement dans la composition de musique pour le jeu. <\3
- Toutes l'équipe n'était pas présente toute la journée. Nottamment `Jean-Théophane` Et `Bertrand` ont dû s'absenter dans l'après midi.

## Rélfexion en cours

- Les Avatar doivent ils gérer eux même les séquences d'images ou se référer à une classe animation ?

---

# JOURNAL du Lundi 20 Avril

## Planning du jour

- Corrections des bugs dans les classes du package automaton suite aux revus de code + amélioration de la classe `AutomatonMain.java` pour pousser les tests sans utiliser le parser
- Correction et Amélioration de `BotBuilder.java` (paix aux groupes qui n'ont pas implémenté IVisitor), qui implémente beaucoup mieux IVisitor.

## Répartition des tâches

- `Cyance>` Revue de code croisée.
- `E>` Amélioration et implémentation de tests plus détaillés pour `AutomatonMain.java`, qui effectue des tests avec des objets que pourrait génèrer `BotBuilder.java` depuis l'AST.
- `M>` Améliorations et corrections de quelque lignes de code dans `BotBuilder.java` suite aux revues de codes du groupe
- `V/B>` Mise à plat des tâches pouvant être réalisées si tôt dans le projet.
- `V/E>` Affichage d'un premier sprite animé dans la vue grâce a la classe Avatar.
- `B>` Implémentation du côté aléatoire de la méthode `execute()` de `Action.java`
- `B/S>` Génération de la grille dans `Grid.java` depuis des fichiers pattern
- `JT + Aide M>` Implémentation de Move et Turn dans `Entity.java` et de la gestion des mouvement sans les automates depuis le controleur sur une entité à l'écran.
- `M>` Merge de plusieurs branche pour mettre a jour l'automate. Correction de plusieurs fichier `.gal`, surtout au niveau de la syntaxe.
- `M>` Aujout de la liste des automates dans le model.

## Remarques :

- Le travail du jour a été très productif, les tâches sont bien réparties, nous alternerons les rôles attribués sur certaine tâche le lendemain pour que chacun touche à quelque chose. (*L'idée serait de garder une personne déjà présente sur une tâche pour aider a la transition sans perdre trop de temps*)

## Rélfexion en cours

- Les Avatar doivent ils gérer eux même les séquences d'images ou se référer à une classe Animation pour une certaine action en cours (Pop/Wizz/...) ? (dans les deux cas on se base sur la description des fichiers .ani pour une entité)

---

# JOURNAL du Mardi 21 Avril

## Planning du jour

- Retour sur les revues de codes croisées (discussion comme d'habitude)
- Finir l'implém de la grille et la connecter au model.
- Travailler sur les automates pour commencer à implémenter une intéraction avec les entités.
- Travailler sur la vue pour implémenter les premières animations à l'écran en fonction de la progression d'une action d'une entité.

## Répartition des tâches

- `Cyance>` Discussion sur la dernière revue de code croisée.
- `E/V>` Travail sur la vue et début d'implem des classes Avatar sans classes d'animation -> l'animation s'adapte en fonction de la progression de l'action de l'ntité dans son action courante.
- `V>` Développement de la classe Animation qui se base sur le contenu des fichiers .ani listés dans le fichier de configuration.
- `V/B/M>` Finissions sur les classes d'Avatar pour prendre compte des ficheirs de config, début d'un aspect concret pour les animations.
- `B>` Liaison de la grille au modèle (liste des entités)
- `B/S>` Finissions de qq détails sur la grille
- `JT/S/M/E>` Implémentations des premières intéraction entre automate et modèle.
- `JT>` Complétion de la gestions des touches (ajout/suppression dans une liste) depuis le contrôleur vers le modèle et dans le modèle
- `M>` Modification des fichiers .gal ayant une syntaxe erronée.
- `Cyance>` Revue de code croisée.

## Remarques :

- Le travail du jour a été très productif, les tâches sont bien réparties, nous alternerons les rôles attribués sur certaine tâche le lendemain pour que chacun touche à quelque chose. (*L'idée serait de garder une personne déjà présente sur une tâche pour aider a la transition sans perdre trop de temps*)

## Pour après

- Généraliser grâce à une classe les temps des actions pour aléger le code par entité.
- Commencer à implémenter un gestionnaire de physique (pour les collisions par exemple) dans le modèle ou dans certaines entités.
- Afficher les déplacements/rotations de manière progressifs dans la vue et non pas instentanément.
- Continuer de travailler sur l'interaction entre les automates et les entités

---

# JOURNAL du Mercredi 22 Avril

## Planning du jour

- Compréhension du code des Avatars et de GameConfiguration (une classe qui parse un fichier de config pour associer aux entités un comportement (.gal) et une animation (.ani))
- Implémentation de déplacements fluide entre deux cases en fonction de la progression de l'entité dans son move
- Début de certains sprite pour l'aspect graphique du jeu.
- Allégement des classes filles d'Entity quant aux corps des fonctions `step()` et `getActionProgress()`

## Répartition des tâches

- `Cyance>` *Est-ce que quelqu'un lit ce journal ?*

---

# JOURNAL du Jeudi 23 Avril

**Jour de pause après une mise a plat du code et d'un push sur master**

## Remarques :

- Il va falloir commencer à penser à l'interface utilisateur (pour un accueil ou une fois dans le jeu)

## Pour après

- Avancer les Sprites/Automates/...
- Commencer à implémenter le comportement des entités dans le modèle
- Implémenter les fonctions `Move`, `Cell` & `Closest` dans Entity et effectuer des tests pour que les comportements soit ceux d'un Tore (à la pacman).
- Voir la suite dans le planning de Vendredi.

---

# JOURNAL du Vendredi 24 Avril

## Planning du jour

- Pas de planning prévu à l'avance ce jour là. Les décisions ont été prises le matin même et ont donné la répartition des tâches ci-dessous.

## Répartition des tâches

- `M>` Implémentation du ViewPort.
- `E>` Implémentation de la classe tank, d'un moyen de générer le tank sur la map via la grid,
mise en place de la coordination tank-tourelle.
- `E/V>` Implémentation des mécanismes pour l'implémentation de cell et closest.
- `B>` Implémentation de la classe chassisAvatar et création des sprites nécéssaires.
- `JT>` Utilisation de swing pour commencer à implémenter du HUD.
- `S>` Optimisation de la liste des entités, via la conversion de celle-ci en HashMap avec le type d'entité pour clé.
- `Cyance>` Revues de code croisées de ce qui a été fait aujourd'hui.

## Remarques :

- Le travail du jour a été pour la plupart des tâches laborieux, beaucoup de formule mathématiques, exploration de nouvelles fonctionnalités de java, création de nombreuses sprites, etc... Il n'en reste pas moins que l'équipe a avancé sur beaucoup de champs en même temps et a aquis des outils qui permettront d'avancer plus vite dans les prochains jours.

## Réflexions en cours :
- Comment faire fonctionner efficacement l'affichage en bord de map ?
- Comment optimiser la gestion de la liste des avatars ?
- Comment imprimer les avatar dans un ordre selon le type d'entité ?
- Choisir le design pour l'HUD (propositions existantes mais à délibérer).

## Pour après

- `Continuité travail de B/E>` Finaliser le lien entre tourelle et tank, notamment l'aspect graphique. (transition entre deux cases)
- `Continuité travail de M>` Incorporer le ViewPort dans la View, la faire fonctionner, se questionner sur l'affichage des bords de map.
- `Continuité travail de E>` Implémentation du drone et de la transition entre les deux mondes.
- `Continuité travail de S>` Faire fonctionner l'affichage des entités dans un certain ordre.
Mieux géré la génération des avatars.
- `Continuité travail de V>` Faire fonctionner le Cell et Closest pour faire fonctionner des automates plus compliqués.
- `Continuité travail de JT>` Continuer d'avancer dans le HUD.

---

# JOURNAL du Samedi 25 Avril

## Planning du jour

- Avancer l'implémentation du Drone et du Tank
- Avancer l'ATH
- Implémenter un viewport fonctionnel
- Implémenter la fonction Closest & Cell

## Répartition des tâches

- `JT>` Etude de l'ATH avec la bibliothèque Swing.
- `S>` Finissions des modifications des listes d'entités dans le modèle.
- `E>` Mise a jour de la vue qui fait suivre le corps du tank et son canon.
- `E/S>` Ajustement du Move des entités pour prendre compte le Tore grâce au modèle. Première version du drone et du tank et de leur transition en appuyant sur une touche.
- `B>` Création de TurretAvatar + Modularisation des move + Aide sur les mouvemnt du tank à Emilie.
- `B/M>` Travail sur l'implémentation de la classe Viewport et d'une première version fonctionnelle qui prend en compte le Tore.
- `V>` Implem de Closest (> *c'était vraiment pas simple, j'essairai d'expliquer le fonctionnement du code à la team*).
- `Cyance>` Lecture des revues de code croisées de la veille.

## Remarques :

- Le travail du jour a été productif malgré les difficultés mathématiques et logiques sur certaines tâches. Il faudra fusionner le travail dès demain en regardant ce que l'on garde ou non.

## Réflexions en cours :
- **Vaut-il mieux utilisé Java AWT ou Swing ?**
- Choisir le design pour l'HUD (propositions existantes mais à délibérer).
- Comment gérer le passage du drone au tank et inversement ? (saisis des touches, ...) Sans garder le contrôle sur les deux entités.
- 

## Pour après

- `Continuité travail de JT>` Revoir si swing est un bon choix, peut etre checké AWT. 
- `Continuité travail de S>` Merge avec dev
- `Continuité travail de E>` Merge avec dev
- `Continuité travail de E/S>` Debug + merge.
- `Continuité travail de B>` Merge
- `Continuité travail de V>` Merge, puis s'attaquer a la fonction cell.
- `Continuité travail de B/M>` Debug + merge

---

