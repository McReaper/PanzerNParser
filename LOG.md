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


