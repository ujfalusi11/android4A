# Projet Mobile 4A - Liste de Rapper + Biographie

Ce projet est une application android qui permet à un utilisateur de créer un compte, de s'identifier et d'accéder à une liste de rappers 
afin de lire leur biographie à l'aide d'appels webservice à une API rest crée.

## Description

L'objectif du projet est d'afficher une liste à l'aide d'un Recyclerview et de voir le détail de chaque élément de la liste avec le language Kotlin.

### L'application:

* Langage Kotlin
* Architecture:
  * MVVM
  * Singletons
  * Clean architecture
* Gitflow
* CI/CD
* Affichage d'une liste dans un recyclerView
* Utilisation d'une BDD room
* Design simple

## Fonctionnalités


### Page de login
* Page de login avec l'option de création de compte
* renvoi d'erreurs en cas de non mauvaise saisie ou de champ laissé libre
* Option de show/hide du mot de passe

![Alt text](/images/log1.png "Optional Title")
![Alt text](/images/log2.png "Optional Title")

### Page d'inscription
* Inscription pour sauvegarder less informations de l'utilisateur dans la BDD
* renvoi d'erreurs en cas de non mauvaise saisie ou de champ laissé libre

![Alt text](/images/reg.png "Optional Title")

### Page d'affichage de la liste
* Liste des rappers

![Alt text](/images/list.png "Optional Title")

### Page d'information sur le rapper
* Affichage de la biographie du rapper
* Scrollbar pour lire la biographie

![Alt text](/images/bio.png "Optional Title")
