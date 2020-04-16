# PROPOSITION DE JEU - Groupe 2 - La Cyance

# Panzer n' Parser

## Deux univers
le tank sur terre et le drone dans un univers aérien

### `Jeu d'action`, *sans fin*.

#### Objectifs
- Ramasser des `ressources` pour fabriquer des `améliorations` (upgrapde).
- Survivre le plus longtemps possible face aux ennemis.

#### Gameplay
- Possibilité de jouer avec deux points de vues (**toujours dans un axe perpendiculaire au sol**) : 
    - Celui du Tank : Action centrée sur la récolte des ressources (**qu'il ne peut pas voir tout seul**), la survie du véhicule et la lutte contre les ennemies.
    - Celui du Drone : Action centrée sur le repérage des ressources récoltables, le repérage des groupes d'ennemis.
- L'ensemble du gameplay tourne sur la nécessité d'utiliser l'exploration du drone pour évoluer avec le tank. 
- L'objectif final est de survivre le plus longtemps possibles afin d'avoir le plus haut score : Plus le tank survie longtemps et détruit le plus d'ennemis, plus le score est élevé.
- Le drone a un **viewport augmenté** et **une durée limitée**, il peut poser des marqueurs dans le monde qui servent au tank, *ces marques disparaissent seulement quand on en place un nouveau* (car on est limité avec ce nombre de marque).
- Les ressources (celles récoltées sur les ennemis (les parties électroniques)  ou au sol (les minéraux)) servent aux améliorations pour aider le tank

---

### Les actions pop et wizz du tank et du drone

#### Pour le tank
- <strike>Pop : Changer d'arme.</strike>
- <strike>Wizz: Passer au contrôle du drone.</strike>
- <strike>Egg : `à discuter` </strike>
    - <strike>ne future upgrade pour les tirs </strike>
    - <strike>Lancement de tank semblable au notre qui agit seul pour récolter des ressources. Cela coûte des ressources de le créer.</strike>
- <strike>Hit : Attaquer avec l'arme active.</strike>
- <strike>Get : Creuser pour trouver des ressources.</strike>

---

#### Autre proposition de tank

###### Canon + Chassis
- Move: Déplacements (Chassis+Canon) (avec ZQSD, dans `éclaircir` *8 directions*)

###### Chassis (corps du tank)
- Pop : Miner (pour faire apparaitre une ressource si sur filon)
- Wizz: Passer au contrôle du drône. (`a discuter pour l'animation`)
- Turn: Tourne le chassis (appelé par le move)

###### Canon (tourelle du tank)
- Pop : Changer d'arme (*enclenche un rechargement obligatoire pour l'arme choisit*)
- Wizz: Recharger le canon
- Hit : Attaquer avec l'arme active. (les upgrades ont une limitation d'utilisation)
- Turn: Tourne le canon (avec les **flèches directionnelles**)

---

#### Pour le drone
- Pop : Switcher entre vue ressource et vue ennemi. (`a discuter pour l'animation`)
- Wizz: Retour au tank.
- Hit : Marquer une ressources ou des enemies pour le tank. Via la souris.
- Move: Se déplacer dans les airs<br/>
`pour une prochaine feature :`
- ????: Descendre
- ????: Monter

---

### Originalités du jeu

- Les ennemis sont porteur de ressources obligeant le joueur a prendre des risques pour évoluer.
- L'environnement n'est que partiellement connu du point de vue du tank, mais plus amplement du drone.
- Un système d'améliorations passant par **une interface sous forme de bandeau cliquable** offrant une expérience changeante de jeu.
- Ensuite le tank se déplace pour tuer les ennemis et survivre. Le drone se déplace également et voit les ennemis et les ressources. Il indique au tank dans quelle direction aller pour trouver les ressources (pendant ce temps **le tank est vulnérable**). 
- Les ennemis se déplacent dans une zone de manière aléatoire. Lorsque le joueur se trouve à une certaine distance d'un ennemi, en fonction de sa "distance de détection", l'ennemi ira vers le joueur pour l'attaquer.
- Plusieurs catégories d'ennemis avec des comportements/déplacements/zones de détection/attaques différentes.

---

### Les upgrades

##### Statistiques

 - Vie
 - Dégats
 - Vitesse (tank+drone)
 - Portée (tir+viewport)
 - Temps de rechargement
 - Temps d'utilisation du drone

##### Uniques

 - Bouclier rechargeable
 - Tourelle automatique
 - Tir multiples (*dégats dispersés dans un cône ?*) (arme)
 - Canon augmenté (*plus de dégats, moins de cadence*) (arme)
 - Marquage multiples (pour le drone)
 - <strike>Mine a placer</strike>

---

### L'inventaire du tank

 - affichage simple avec symbole de la ressource + son compteur (on cap' l'affichage a 999 par exemple pour eviter le surpoids sur l'écran).

---

### La grille de jeu

On a pensé à générer la grille entière depuis des patterns qu'on dispose aléatoirement pour former la grille entière.

##### Basée sur un Tore

Les entités seront disposés sur une grille a maillage fin, c'est à dire que certaines entités peuvent occuper plusieurs cases, comme le tank par exemple. Elle possède une taille fixe.
- Le tank occupe 3x3 cases.
- Le drone occupe une case car il n'entre en collision avec aucunes entités, il n'a donc besoin que d'une position.
- On verra plus tard pour les ennemis et les obstacles.

##### Son viewport

Définit la porté de ce que peut voir le joueur à l'écran. <br/>
Donc en fonction de sa taille, les entités apparaissent plus ou moins grosses à l'écran.
- Le viewport du point de vue du tank :
    - s'adapte en fonction de ses upgrades, mais reste basse comparée au drone.
- Le viewport du point de vue du drone :
    - fixe mais élevée / `feature ? >` s'adapte en fonction de sa hauteur.

---

### Idées supplémentaires à l'étude

##### Idée d'upgrades
- <strike>Radar de proximité pour les ressources</strike>
- Le drone peut marquer plus d'emplacement
- Améliorations d'arme
- Tourelle automatique sur le tank (un deuxième canon ?)
- <strike>Lance grenade</strike>
- Explosifs
- Amélioration de la puissance des tirs
- Tir triple
- Bouclier temporaire rechargeable
- Améliorer la hauteur du drone
- Améliorer l'autonomie du drone
- Améliorer les points de vie du tank
- <strike>Plus de résistance au vent du drone</strike>
- <strike>La taille du tank augmente</strike>
- Vitesse du tank et/ou du drone augmentent

##### Idée de features
- ajout d'armes sur le chassis (lance-flamme ?)
- Gestion de la difficulté
- Pour le drone : viewport adaptable en fonction de la hauteur, mais en contre partie son temps d'utilisation sera propotionel.
- Hit : Fait apparaitre des piques autour du tank ou faire un dash (bond en avant qui fait des dégâts)
- Du vent, des conditions météo qui limite l'usage du drone
- L'écran se secoue à chaque impact et/ou explosion.
- Le score évolue selon une méthode de "combo" : Plus on enchaîne les ennemis, plus le score augmente rapidement.
- Utilisation de munitions limitées ou mettre un temps de rechargement.
- Création de plusieurs type d'élément pour les attaques avec plus ou moins d'efficacité en fonction des ennemis ( notion de vulnérabilité et résistance des ennemis).
- Ajout d'un boss final.
- Des cheats codes.

---

### Des automates

Nous avons réaliser plusieurs automates pour nos entités de notre jeu, en voici une liste :
- (vein.gal)[automate.gal/vein.gal] Automate décrivant le comportement d'un filon.
- (kamikaze.gal)[automate.gal/kamikaze.gal] Un automate pour un kamikaze
- (ranged_enemy.gal)[automate.gal/ranged_enemy.gal] Automate qui décrit un ennemi avec de la porté pour tirer sur le joueur
- (enemyFollowing.gal)[automate.gal/enemyFollowing.gal] Automate qui définit un comportement ennemi qui cherche le joueur
- (tankCanon.gal)[automate.gal/tankCanon.gal] Automate qui définit le comportement du cannon du tank.
- (tankChassis.gal)[automate.gal/tankChassis.gal] Automate qui définit le comportement du corps du tank.



