# Présentation du Groupe

_Membre de l'équipe :_
- Victor MALOD
- Bertrand BAUDEUR
- Sami ELHADJI TCHIAMBOU
- Mathis MUTEL
- Jean-Théophane DE MULATIER
- Emilie TONDEUX
- (Absent du projet) *Andréa VOISIN-GRALL*

*Nous sommes tous étudiants en troisième année de la filière INFO de l'école d'ingénieur Polytech GRENOBLE.*

__Tuteur :__
- Alexandre MARECHAL

__Proffesseurs responsables du projet :__
- Michaël PÉRIN
- Olivier GRUBER

Le but du projet est de créer un jeu vidéo en 4 semaines.
Le sujet de travail est libre.
Nous avons donc créé un jeu pouvant rentrer dans les catégories stratégies, guerre et survie.

# Comment lancer le jeu

Pour lancer le jeu, il suffit d'exécuter et compiler le fichier "workspace/panzernparser/info3/game/GameMain.java" depuis l'IDE eclipse avec une version de Java 1.6 minimum (le jdk de POO a été utilisé pour faire tourner ce programme).

# Presentation du jeu

Ici, le joueur est représenté par un tank, son but est de survivre le plus longtemps possible.
Pour résumer ce qu'il va suivre, vous pouvez aussi visualiser la **vidéo de démonstration** _*[ICI](https://www.youtube.com/watch?v=icWR9-wuG6Q&t=9s)*_

## --Le Tank--

Il est composé de 2 entités différentes ayant chacune un automate.
- Le Chassis : Permet de se déplacer dans 8 directions grâce aux touches ZQSD. Il peut également creuser dans le sol afin de trouver des ressources avec la touche E.
- Le canon : il peut se tourner (dans les 8 mêmes directions) via les flèches gauche et droite. Il peut également tirer avec la touche ESPACE.
Il est important que les deux entités soient autonomes pour que le joueur puisse avancer dans une direction et tirer dans une autre en même temps.
Ils sont simplement liés dans l'espace car le canon ne peut être dissocié du chassis cela va de soit.

## --Le Drone --

Le tank à une vision de jeu assez limitée, donc pour l'aider à voir l'environnement autour de lui, le joueur peut appyuer sur la touche A et ainsi diriger un drone qui part de l'emplacement du tank pour monter dans le ciel et explorer les environs.
Toujours en vue du dessus, le drone est en perpétuel mouvement. Le joueur peut le diriger de la même manière que le Tank, en appuyant sur les touches ZQSD. 
Il possède deux modes de visions : 

- Vision "Thermique" : Ne voit que les ennemis.
- Vision "Rayons X"  : Ne voit que les ressources. C'est le seul moyen qu'a le joueur de voir certaines ressources présentent sous le sol.

Pour changer d'une vision à une autre, il suffit d'appuyer sur la touche E.

Une fois le repérage des ressources ou des ennemis fait, le joueur peut déposer des drapeaux sur la carte grace au clique de la souris sur l'endroit souhaité.

Mais attention, le drone possède un nombre limité de drapeaux donc si on souhaite en placer un autre, il suffit de cliquer sur la carte pour que le drapeau se pose à l'endroit marqué, mais un des drapeaux précédement placés sera enlevé. De retour au tank, les drapeaux sont toujours visibles et indiquent l'emplacement précédement marqué.
Le drone a également la capacité de retirer un drapeau en vol en cliquant une deuxième fois dessus.

Le drone se situe dans les airs, il ne peut donc pas être repéré ou touché par les ennemis. Contrairement au tank, il n'est pas non plus bloqué par les obstacles sur le sol.
En revanche ce n'est pas parce que le drone est intouchable que le tank l'est aussi. 
Même si le joueur utilise le Drone, le tank peut toujours se faire attaquer et perdre de la vie. Donc pour revenir sur le tank, il suffit d'appuyer sur la touche A.
Le drone est tout de même limité dans le temps, lorsqu'il n'a plus de puissance, il disparait et le joueur reprend le control du tank. Son energie remonte progressivement tant que vous contrôlez le tank.

## --Les ennemis--
Différents types d'ennemis sont présents sur la carte. Leur objectif est de tuer le tank par tous les moyens.
 
- Les ennemis basiques : leurs automate recherche en permanence le tank. Lorsqu'ils sont à une certaine distance du joueur, ils se mettent en mouvement et avance en direction de celui-ci puis tirent.
Leurs balles sont assez lentes et font peu de dégats. Mais ils sont nombreux sur la carte. Ils sont représentés par de simples soldats à pied.
- Les ennemis Niveau 2 : Ils ont les même aptitudes que les ennemis basiques, mais en plus, ils peuvent contourner des obstacles. Ils sont sous la forme de tanks plus petit que celui du joueur.
Contrairement aux ennemis basiques, ils peuvent tirer 2 à la fois balles au lieu d'une. Ils repèrent le tank du joueur de plus loin et leurs balles sont plus rapides.
- Les Boss : Ce sont des tanks aussi gros que celui du joueur. Son automate lui permet comme aux autres de trouver le joueur et de le suivre. Mais en plus il a le choix entre plusieurs actions une fois sa cible en vue :
  + Lancer un missile tête chercheuse.
  + tirer quatres balles d'ennemis niveau 2.
  + recupérer de la vie.
Les balles de cet ennemi sont plus grandes, font plus de dégats, ont une grande portée et peuvent suivre le joueur.

## --Les armes--
Le Tank possède 3 types d'armes mais seule une est disponible au début du jeu.
Il possède également un chargeur de dix balles qui se recharge automatiquement lorsque tout a été tiré. Mais cela prend un certain temps c'est pourquoi il est nécessaire de bien gérer ses ressources. On peut lancer le rechargement à tout moment avec la flèche du haut.
Les ennemis, quand à eux, n'ont pas de temps de rechargement et peuvent tirer à l'infini. Mais le temps nécessaire pour tirer diminue en fonction de leur niveau.

## --Les ressources--
Dans ce jeu il existe deux types de ressources :
- Les ressources Electroniques : Disponible à divers endroits sur la carte, posés sur le sol. Lorsqu'un ennemi meurt, il en dépose également à l'endroit de sa mort.
- Les ressources Minerales  : Situées sous la terre. Elle ne sont visibles que par la vision Rayons X du drone et ne sont accessibles qu'en creusant avec le tank au dessus de leur emplacement. Si le joueur creuse au bon endroit, la ressource apparait sur le sol.
Le joueur peut récupérer toutes les ressources et les drapeaux au sol simplement en roulant dessus.

## --Les améliorations--
Une fois un nombre suffisant de ressources recoltées, des améliorations sont disponibles.
Il en existe deux types :

- Des améliorations statistiques : Ce sont des aides disponibles plusieurs fois dans une seule partie. Mais attentions le prix augmente après chaque utilisation.
  + Régénération de la vie du tank --> le tank récupère 50% de sa vie.
  + Nouvelle arme disponible --> pour changer d'arme il faut appuyer sur la Flèche du bas.
  + Plus grande barre de vie --> la vie max du tank augmente.
  + Temps d'utilisation du drone augmenté.
  + Temps de minage diminué --> pour creuser les ressources plus rapidement.
  + Plus de balles dans un chargeur.
  + Plus de dégats avec ses tirs.
  + Plus de vitesse --> le tank se déplace plus vite.
  + Plus de drapeaux disponibles pour le drone.
- Des améliorations uniques : Comme son nom l'indique, on ne peut les appeler qu'une seule fois dans la partie
  + Possbilité de monter ou descendre avec le drone --> avec les flêches haut et bas le drone peut monter ou descendre dans le ciel, cela donne une plus large vision de l'environnement.
  + Creation d'une tourrelle automatique sur le toit du tank qui s'ajoute au canon et tire lorsqu'elle repère des ennemis à proximité.


## --Les obstacles--
Sur le sol il n'y a pas que des ennemis et des ressources.
D'autres entités sont présentes et ne servent qu'à géner la progression du tank et des ennemis.
Ce sont des obstacles. Ils ont différentes formes :
- Les murs et les pierres empèchent le tank et les ennemis de traverser. On ne peut pas tirer au travers d'eux.
- Les tas de boues : le Tank et les ennemis peuvent le traverser mais sont ralentis lorsqu'ils passent au dessus. On peut tirer au travers sans que les ballent explosent.
- Les carcasses de tank : elle sont destructibles et déposent des ressources.

## --ATH--
Sur le coté gauche se trouve toutes les informations importantes sur la vie ou les ressources du tank
on peut voir la barre de vie du tank, la barre d'energie du drone, l'arme utilisé, le nombre de ressources de chaque type.
Sur le coté droit se situe une boussole qui indique dans quelle direction doit se déplacer le joueur pour trouver les drapeaux, le nombre de balles restantes dans le chargeur et les améliorations disponibles ou non. Avec le prix de chacune (en ressources). Un code couleur simple indique les ressources manquantes pour les obtenir. On peut également trouver sur le côté droit le score du joueur, le niveau actuel et le temps écoulé depuis le début de la partie.

Sur le jeu, la barre de  vie des ennemis est représentée pour permettre au joueur de savoir où il en est dans son combat.









