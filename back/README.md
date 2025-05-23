#Alten E-commerce API – Test KATA (Back-end)

Ce projet est une API REST réalisée avec **Spring Boot**, développée dans le cadre du test technique Alten.  
Elle couvre la gestion des produits, des utilisateurs, de l’authentification JWT, d’un panier d’achat et d’une wishlist(liste d'envie).

---

## Fonctionnalités principales

### Authentification JWT
- POST /sign-up : inscription
- POST /sign-in : connexion et retour du token
- Toutes les routes sont sécurisées par JWT (sauf '/sign-up' et '/sign-in')

### Produits
- GET /products : liste des produits
- GET /products/{id} : détail d’un produit
- POST /products : création (réservée à admin@admin.com)
- PATCH /products/{id} : mise à jour (réservée à admin@admin.com)
- DELETE /products/{id} : suppression (réservée à admin@admin.com)

### Panier
- GET /cart : consulter son panier
- POST /cart/{productId}?quantity=n` : ajouter un produit
- PATCH /cart/{productId}?quantity=n` : modifier la quantité
- DELETE /cart/{productId}` : retirer un produit

### Wishlist
- GET /wishlist` : consulter sa liste d’envie
- POST /wishlist/{productId}` : ajouter un produit
- DELETE /wishlist/{productId}` : retirer un produit

---

## Stratégie de test

Une **collection Postman** est fournie pour tester l’ensemble de l’API :  

"kata-test-api.postman_collection.json"
- Token JWT automatiquement stocké après login
- Variables {{token}} et {{baseUrl}} intégrées
- Requêtes séparées pour chaque fonctionnalité

---

##  Stack technique

Ce projet a été réalisé avec la stack suivante :

-  **Java 17**
-  **Spring Boot 3.0.9**
-  **Spring Security** + JWT (via **JJWT**)
-  **Spring Data JPA** avec base **PostgresSQL**
-  **Lombok** pour simplifier le code surtout avec les getters et setters
-  **Postman** pour tester l'API REST

.
---

## Admin par défaut

Par règle métier, seul l’utilisateur suivant a le droit d’ajouter, modifier ou supprimer des produits :

- **Email :** admin@admin.com
- L’attribution du rôle "admin" est **gérée dynamiquement dans le backend**, sans système de rôles complexes.

---

## Auteur

Ce projet a été réalisé par **BAMBA AIDARA** dans le cadre du **test KATA Alten**.

---

## Lancement local

### Prérequis :
- Java 17
- Maven
- PostgresSQL ou base compatible

### Étapes :

```bash
# 1. Installer les dépendances
mvn clean install

# 2. Lancer l'application
mvn spring-boot:run