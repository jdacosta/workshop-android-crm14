# CipherWire

**L'application (enfin, si on peut appeler ça une application...) a été publié sur le Play Store**

Nous avons réalisé plusieurs `services` pour faciliter le développement de notre application :

* `AuthService` pour la gestion du compte utilisateur
* `AESCryptoService` et `RSACryptoService` pour la gestion du chiffrement
* `DiscussionService` pour la gestion des discussions
* `UserService` pour la gestion des utilisateurs *(par exemple la partie contact)*
* `Preferences` pour la gestion des SharedPreferences *(ce qui nous permet de réaliser un stockage local des données liées à notre application)*
* `DatabaseService` pour la gestion de Firebase *(récupération de notre instance FB)*

### Splashscreen

* Pour réaliser le splashscreeen, nous avons étendu `Thread` pour pouvoir lancer automatiquement notre `DashboardActivity` après 3 secondes d'attente.

### Ajout de contact

* Pour ajouter un contact, il faut se rendre dans l'onglet `Contacts` et ajouter un contact à l'aide du `FloatingActionButton`. Lors de l'ajout d'un contact nous interrogeons Firebase et nous vérifions si le nom du contact est bien existant.

### Envoi de message

* Pour envoyer un message depuis l'application, il faut dans un premier temps ajouter le contact avec lequel l'on souhaite communiquer. Ensuite, il suffit de cliquer sur ce contact pour pouvoir accéder à la discussion.

### Chiffrement RSA

* Le chiffrement `RSA`, nous permet de pouvoir vérifier l'identité de notre destinataire à l'aide de la signature. Ce chiffrement est essentiellement utilisé dans notre projet, pour pouvoir échanger la passphrase de manière asynchrone et sécurisée.

### Chiffrement AES

* Le chiffrement `AES`, nous permet de pouvoir chiffrer chaque message de notre fil de discussion. Chaque message possède un `salt` différent. Pour déchiffrer un message, le `salt` lié au message et la passphrase sont utilisés.

### Difficultés rencontrées

* Pour le stockage de la clé privée sur le téléphone, nous n'avons pas trouvé de solution sécurisée pour pouvoir changer de compte utilisateur sur le même téléphone.

* Le changement de mail est géré dans le profil de l'utilisateur. Cependant, Firebase nous renvoie une erreur alors que nous avons bien suivi la [documentation](https://www.firebase.com/docs/web/guide/login/password.html#section-changing-passwords)

* La gestion des événements  été laborieuse, nous avons dans un premier temps utilisé les interfaces, avant de mettre en place le système de `BUS`. Ce système est bien plus pratique, mais reste assez lourd à cause de la mise en place des `events`, `handler`...

* La réalisation des vues est plutôt laborieuse.

* Pour l'activité `Dashboard`, nous utilisons les `TabLayout`, qui nous permet d'ajouter des onglets de fragments dans notre vue. Pour la mise en place de cette vue, plusieurs difficultés ont été rencontrées (mauvaise façon de faire, gestion des fragments...)

* Nous avons rencontré plusieurs problèmes avec les retours d'événements de Firebase (avoir le résultat d'une requête unique), mais le problème a été résolu grâce à l'utilisation d'une méthode.

* La classe RSA n'était pas fonctionnelle sur certaines méthodes (cela a été résolu avec Louis).

* L'éditeur Android Studio a planté plusieurs fois sur nos PC. Qu'il a été parfois difficile de retrouver notre projet fonctionnel (même après l'utilisation de la pocket Ball).

* La gestion du `Bundle` pour la restauration des données d'une activité *(utilisation du bouton retour Android)* n'est pas fonctionnel.

### Ressources et liens

* Github: https://github.com/jdacosta/workshop-android-crm14
* Archive: https://github.com/jdacosta/workshop-android-crm14/releases/tag/0.0.1
* Screenshot: https://github.com/jdacosta/workshop-android-crm14/tree/rendu_final/Screenshots
* APK: https://github.com/jdacosta/workshop-android-crm14/tree/rendu_final
