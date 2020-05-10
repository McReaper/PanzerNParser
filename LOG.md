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

# JOURNAL du Lundi 27 Avril 

## Planning du jour 

- Continuer le travail commencé le week-end et tenter un merge du tout.

## Répartition des tâches 

- `M>` implémentation de la classe inventory et mise à jour des droppable
- `E/V>`transition du drone au tank, modification de divers fonctionnalités liées au drone et au tank
- `B>` Modification des classes Avatar, correction du Viewport et implémentation de Cell de Entity.
- `JT>` Implémentation de dev.ATH, ajout de lisibilité et restructuration du code lié à l’ATH.
- `S>` Implémentation des markers pou le drone et ajout de la gestion de la souris.


## Remarques : 

- Le travail du jour a été efficace dans l’ensemble, chacun a pu avancer sur son travail tout en faisant avancé le projet. Des bugs sont apparus lors du merge sur dev ce qui a permis de revisité et corrigé le code afin d’apporter fluidité et lisibilité à celui-ci. L’intervention du tuteur a permis de débloquer le groupe sur l’ATH qui représentait jusque là une difficulté certaine.

## Pour après 

- `Continuité travail de B/JT>` avoir un bel ATH opérationnel.
- Optimisation du doTurn
- Interaction entre les entités
- Complétion des Pop/Wizz/Hit de chaque entité
- Créer plus de fichiers graphiques et sprites 
- Faire fonctionner l'affichage des entités dans un certain ordre.
- `S>` restructuré la classe marker et model ainsi que l’affichage des 	markers dans la view.
- (peut être pas demain) Penser une implémentation pour les upgrades

## Répartition des tâches 
- `Cyance>` A votre bon cœur. A voir demain

# JOURNAL du Mardi 28 Avril 

## Planning du jour 

- Interaction des entités entre elles
- créer plus de sprites
- terminé les features non achevées de la veille

## Répartition des tâches 

- `M>` implementation du nouveau viewPort et changement visuel lorsque on change de monde
- `E>`Implementation de Shots et interaction des entités entre elles et gestion des collisions
- `V>` création de sprites et modification de l'ATH
- `B>` implementation de l'ATH et creation d'un Widget Boussole indiquant les markers
- `JT>` Implementation ATH et creation de sprites. Implementation de son pour le jeu
- `S>` Completion du marker et des avatars liés au marker. 
- `V/S/M>` tentaive de modification du calcul de coordonnées pour l'affichage des markers

## Remarques : 

Pas de remarques particulières. Toujours quelques difficultés sur l'ATH mais celui-ci devient de plus en plus familié. Chaque feature a bien progressé, il va falloir faire un merge prochainement. Prendre en compte les retours du prof pour les animations (ex : le move)

## Pour après 

- Perfectioner l'ATH
- Implementation des ennemis
- Des sprites pour ennemis et drone
- Vision du drone

## Répartition des tâches 
- `Cyance>` A votre bon cœur. A voir demain

---

# JOURNAL du Mercredi 29 Avril

## Planning du jour

- Drop des ennemies implémentation et la récupération des drops
- Système des pooints de vie mis en place
- Interraction entre les entités partiellement implémenté
- Gestion de la vision du drone
- Implémentation d'un menu
- Implémentation d'un ATH dynamique

## Répartition des tâches

- M> Ajout de l'effet de tore pour les colisions et closest
- M/V> Debug général du code, gestion de la vision du drone.
- JT> Dessin de sprites enemy en mode vision thermique et en diagonal, implémentation d'un ATH dynamique
- E> Implémentation de features pour les ennemies : Drop, automate, colision (avec Sami). Ainsi le Tank : Ramassage des droppable et mise à jour de l'inventaire.
- B> Implémentation d'un menu, modification de Cell, getGridCoord, et de Marker(avec Sami). Ainsi que dessins de sprite
- V> Mise à jour de Closest, Merge des branchs
- S> Mise à jour du contrat, travail sur les Marker (avec Bertrand), travail sur les colisions (avec Emilie)

## Remarques :

Problème potentiel sur la gestion de la grille.

## Pour après :

- Réfléchir à une éventuelle refonte de la grille.
- Implémenter les amélioration
- Perfectionner ENCORE l'ATH

---

# JOURNAL du Jeudi 30 Avril

## Planning du jour

- Drop des ennemies implémentation et la récupération des drops
- Système des pooints de vie mis en place
- Interraction entre les entités partiellement implémenté
- Gestion de la vision du drone
- Implémentation d'un menu
- Implémentation d'un ATH dynamique


## Pour après :

- Réfléchir à une éventuelle refonte de la grille.
- Implémenter les amélioration
- Perfectionner ENCORE l'ATH
- creer une grille contenant toutes les entités pour avoir un accès plus simple et plus optimisé pour certaines fonctions
- réglé le probleme des fleches dans la boussole

## Répartition des tâches

- Cyance> Revue de code de tout le dev
- E> amélioration du code des entités suite aux revues et créations de plusieurs classes shots qui ont des particularités diff + changement d'arme via le canon
- V/S/M> debug des Avatar et implem d'une amélioration de la classe Avatar
- B> suite de l'implem de l'ATH et implem de la grille pour classer les entité suivant leurs position sur la grille
- S> Creation d'un sol particulier : le wall + déplacement de quelques fonction pour rendre le code plus clair + merge de toutes les branches sur dev et debug qui va avec
- JT> suite de l'implem pour avoir des ATH dynamiques + creation d'un son pour le canon
- M> Travail sur les animations et les avatars Enemy + aide B> pour la creation de la Grille

## Remarques :

Pas de remarques particulières

## Réflexion en cours

- Réécrire certaines fonctions avec la nouvelle Grille qui nous permet de lister les elements en fonction de leur emplacement. Plus d'optimisations.
- Discussion sur les prochains objectifs et la manière de les atteindre

---

# Journal du Vendredi 1 Mai

## Planning du jour
- Matinée de revue de code croisée pour un néttoyage du code.
- S'occuper un minimum de l'aspect graphique du jeu.
- Gérer le creusage du tank sur les veins.
- Réfléchir et commencer a implémenter les upgrades
- Travail sur l'ATH
- Implémenté un prélude de gestionnaire de collisions
- Voir le reste de ce qui peut etre fait.

## Répartition des tâches
- `B>` Création des sprites du drone + fix de cell + création d'avatars pour les droppable, les veins et le drone.
- `E>` Création de la classe Hole.java et de son automate. Le trou est créé a partir du pop du tank. 
- `V>` Managment des merges de la matinée et gestion des conflits engendrés. Début d'implémentations des améliorations en jeu pour le tank ou le drone. Aide sur plusieurs branches de l'équipe.
- `JT>` Mise en place d'un ATH lié au model. Travail sur l'ergonomie de l'ATH en général.
- `S>` Travail sur le gestionnaire des collisions.
- `M>` Gestion du ramassage des marqueurs avec le tank et de suppression de ces derniers via un clic depuis le drone.


---

# Journal du Samedi 2 Mai

## Planning du jour
- Pas de planning en particulier, on s'adapte en fonction des besoins actuels du jeu.

## Répartition des tâches
- `B>` Implem d'un fluide zoom dans le viewport + Implem collision manager + Bouttons fonctionnel pour les upgrades.
- `E>` Implem d'un nouveau type d'ennemi. Implem d'une classe Weapon pour gérer différents types de tirs.
- `V>` Travail sur les upgrades en coop avec l'ATH + implem de qq nouveaux sprites
- `JT>` Début du développement d'un fond de monde de 3200px de large/haut et avancement de l'ATH
- `S>` Modif et finalisation des collisions + interdiction du move devant un obstacle + Travail sur les weapons
- `M>` Gestion de l'inventaire avec ajout des ressources récupérées dans ce dernier. + Travail sur la vue du drone + Gestion de la durée de vol du drone.

---

# Journal du Dimanche 3 Mai

## Journée de pause

---

# Journal du Lundi 4 Mai

## Répartition des tâches
- `B>` Finalisation de l'HUD, réorganisation de la classe pour rendre les fonctions constructeur et refresh plus modulaire + merge des branches de beaucoup de gens.
- `E>` Régénaration de la Map par pattern (premiers tests) + Implem d'un GameOver (premiere implem) + Création de boue/glace pour ralentir/augmenté la vitesse du tank. + Modif des constantes pour rendre le jeu plus agréable.
- `V>` Début d'implem pour l'affichage d'une épave (+sprites) + mise a jour des upgrades et lien entre controller et modèle + tentative d'optimisation de la fonction checkmove de entity.
- `JT>` Dev de la Map
- `M/S>` Implémentation du Score + Un peu du collision manager
- `S>` Deux nouvelles entités : Walls + WreckTank avec `V`.

## Remarques

- Il faudrait revoir la position de certaines méthodes dans le model (mieux dans Grid ?).

## Pour après 

- Se focaliser sur les objectifs principaux avant d'attaquer la démo 

---

# Journal du Mardi 5 Mai

##Planning du jour
- Faire une version définitive du Game Over + rejouer
- Régénération des patterns
- Finaliser les upgrades
- Implem healing du Tank
- Explosions
- Finir les sprites
- Mettre l'audio
- Implem de la map de fond.

## Répartition des tâches
- `S>` Creation des différents niveaux du jeu
- `B>` Création de l'animation de fin de niveau + creation de sprites pour la boue et la tourelle auto + finalisation de l'HUD
- `E>` Création et implem de la tourrelle automatique pour les ugrades + implem heal Tank + creation des patterns de niveau 1 et 2.
- `JT>` Creation de la map de fond + création du son d'explosion
- `M>` implem de l'explosion des entitées + bug fix
- `S/V>` génération aléatoire de la map au début de la partie à régénération après à la fin d'un niveau
- `V>` bug fix fonction isInMe et boue qui ralentie les entitées dessus

## Remarques

## Pour après 

- Continuer les bug fix pour rendre un jeu avec 0 bugs.
- Finir les objectifs du jour non terminé.

---

# Journal du Mercredi 6 Mai

##Planning du jour
- Finir la version définitive du Game Over + rejouer
- Finir les patterns
- Finaliser les upgrades
- Finir les sprites
- Mettre l'audio

## Répartition des tâches
- `S>` Recherche de sons libre de droit pour les effets sonores du jeu
- `B>` Mise a jour du menu + petites corrections dans l'HUD + début creation du menu de config des automates + creation de la fonction isQuiet pour les sons.
- `E>` Creation des patterns niveau 3 + tests sur les patterns + creation automate pour ennemis capable de contourner les obstacles.
- `JT>` Creation d'un remix de la musique Katyusha pour la musique de fin + implem de cette musique danes le jeu
- `V>` Implem de l'animation de la tourelle auto + bug fix sur tout le jeu
- `M>` implem restart à la fin du jeu de manière opti (Pas besoin de tout recharger) + suite explosions + divers bug fix

## Remarques 

## Pour après 

---

# Journal du Jeudi 7 Mai

##Planning du jour

- Finir les sons
- Finir les sprites
- Commencer Reflexion Demo
- Chercher et réparer les bugs

## Répartition des tâches
- `S>` Implem des sons
- `B>` Fin du menu de config pour les automates + optimisation de la foncion closest + sprites
- `E/V >` Creation des shots ennemis et du boss + automate du boss + creation d'un shot intelligent qui suit les joueurs + début reflection demo
- `JT>` Equilibrage des sons
- `M>` animation enemis niveau 1 et 2 recherche et correction de bug dans le jeu.
 
## Remarques 

## Pour après 

---

# Journal du Vendredi 8 Mai

##Planning du jour

- Ecriture complete du script de la demo
- Chercher et réparer les bugs

## Répartition des tâches
- `S>` Ajout de son + ecriture scenario demo
- `E>` Ecriture script + bug fix
- `B>` Creation automate Boss + reparations de bugs en tout genres + ajout de pop et wizz aux entitées crées récement
- `V >` Bug fix + ajustements, equilibrage du jeu.
- `JT>` Finission de la map
- `M>` bug fix
- `E/JT/S/V/B/M>` Finission de l'écriture du scénarion de la demo
 
## Remarques 

## Pour après 

---

# Journal du Samedi 9 Mai

##Planning du jour

- Enregistrement des voix de la demo
- Commencer le montage
- Filmer les scenes de la démo
- recherche et réparation de bugs

## Répartition des tâches
- `S>` enregistrement voix général + correction automate missile
- `E>` Ecriture readme
- `B>` Enregistrement voix didact
- `V >` Montage video + film scene pour la demo
- `M>` Creation de scene pour la demo
 
## Remarques 

## Pour après 





