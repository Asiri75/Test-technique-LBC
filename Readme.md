
# LBC test technique

Ce projet est un test technique pour Le Bon Coin. C'est une application Android développée en Kotlin.
Elle a pour objectif de charger et afficher une liste d'albums provenant d'un api public.  

## Description de l'application
## Analyse fonctionnelle

### Comportement de l'application
L'application charge et affiche la liste des albums.
Pour chaque élément, elle affiche la vignette de son 1er titre et indique l'id de l'album ainsi que le nombre de titres qu'elle contient.
Lorsque l'on clique sur l'un d'entre eux, elle affiche la liste des titres qu'elle contient.
Pur chaque élément, elle affiche sa vignette et indique le titre.

### Comportement du code
Pour faire cela, l'application charge les données et les insère directement dans la base de données interne de l'application. C'est à partir de cette base que l'application affiche ensuite les données sous forme de liste  d'éléments à l'utilisateur.

L'API liste tous les titres avec une référence à un album. Nous récupérons donc tous ces titres et les stockons dans la base. Pour afficher la liste des albums, nous parcourons tous les titres, en ressortons une liste d'albums avec le nombre de titres et la vignette du premier titre de cet album.

## Analyse technique
### Architecture
La structure de l'application est en MVVM.
Elle dispose donc d'un modèle détaché de la vue.
Le "ViewModel" assure l'échange entre les deux.
La vue est dépendante du "ViewModel" mais pas inversement.
Cette indépendance du "ViewModel" permet de développer plus facilement des tests unitaires sur cette couche

Pour assurer cette structure, nous utilisons les Android Architecture Components (AAC), comme nous allons le voir plus bas.

### Choix des librairies

#### Persistance des données
Pour garder les données en interne, nous utilisons la librairie **Room**, développée par Google. Elle permet de simplifier les requêtes vers la base de données, alléger la quantité de code et s'intègre bien dans le concept d'AAC.
En effet, pour récupérer les données tout en gérant la problématique de thread, elle permet de récupérer un **LiveData** (un des composants de l'AAC) que l'on peut écouter en direct comme un observable

#### Récupération des données de l'API
 
Pour faire une requête HTTP vers le l'API public, nous utilisons la librairie **Retrofit** développée par Square.
Cette librairie a également l'avantage de simplifier l'appel vers le serveur. Accompagnée de la librairie **GSON converter**, elle permet également de simplifier la conversion de l'objet JSON reçu en liste d'objets prête à être intégrée dans la base de données.

#### Autres librairies utilisées

**RecyclerView**: Pour l'affichage des albums et titre sous forme de liste
**Picasso**: Pour le chargement simplifié des images des titres
**Timber**: Pour simplifier les logs (pas besoin de TAG, la librairie prend directement le nom de la classe contenant le log)