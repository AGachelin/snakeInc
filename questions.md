# Question 1
## Class, object, primitive types
Un objet est une instance d'une classe  
Exemple : classe SnakeApp, objet gamePanel dans SnakeApp, primitive type Integer (GameParams)
## Encapsulation, properties, getter and setter, final
L'encapsulation est le fait de regrouper des données et/ou des fonctions dans une classe. Exemple : Cell  
Une propriété est une donnée associée à une classe, exemple : x dans Cell  
Un getter est une fonction permettant de récupérer une propriété, exemple : @Getter avec x dans Cell
Un setter est une fonction permettant de modifier une propriété, exemple : setX dans Cell  
final empêche les modifications à l'élément affecté ; dans le cas d'une variable, il permet la définition d'une constante : body dans Snake
## Instantiation of objects, Constructors
L'instantiation d'un objet est sa création  
Un constructeur est une fonction dans une classe servant à l'instantiation d'un objet  
## Static fields, static methods. What are the particularity of "static" ?
Une variable ou une propriété statique est partagée entre toutes les instances de la classe 
Une méthode statique est une méthode affectant la classe et non une instance, exemple : createAppleInCell
## Composition
C'est le fait d'inclure des classes dans d'autres classes, exemple : Basket qui contient des Apple
## Inheritance, interface, polymorphism
L'héritage c'est le fait de créer une classe fille à partir d'une classe mère, la classe fille héritant des propriétés de la classe mère, exemple :  GamePanel hérite de JPanel  
Une interface est une sorte de classe spécifiant des méthodes et/ou des propriétés qui seront définies dans toutes les classes qui l'implémentent, exemple : GamePanel implémente ActionListener, KeyListener  
Le polymorphisme c'est quand plusieurs méthodes ont le même nom mais des signatures différentes, par exemple une méthode définie dans une classe mère et redéfinie dans la classe fille. Exemple : les @Override dans GamePanel
## Static VS dynamic types
Une variable en java doit être associée à un type (qui est spécifié à sa création) et ne peut pas en changer, c'est le 'static typing'. Lorsqu'une variable peut changer de type, comme en javascript, c'est du 'dynamic typing'.
## Separation of concerns (design principle)
C'est le fait de séparer les différentes fonctionnalités d'une application dans différentes parties (des fonctions, des classes...).
## Collections
C'est une classe contenant différents objets, exemple : ArrayList dans Basket
## Exceptions
C'est ce qui est "renvoyé" lorsqu'il y a une erreur durant l'exécution
## Functional interfaces / Lambda
Les lambdas sont des fonctions anonymes, qui ne sont utilisées qu'une seule fois lors de leur instantiation, exemple : dans la méthode Game() de Game
Les interfaces fonctionnelles sont des interfaces ne contenant qu'une méthode et pouvant donc être implémentées dans une lambda
## Lombok : https://projectlombok.org/
C'est une librairie java permettant de générer automatiquement les getters, setters, toString et autres.
