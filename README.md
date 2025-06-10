Pour le projet il vous faut démarrer une base de données postgres  
Si vous avez Docker sur votre poste lancez cette commande :  
```docker run --name pg1 -p 5432:5432 -e POSTGRES_USER=mkyong -e POSTGRES_PASSWORD=password -e POSTGRES_DB=mydb -d postgres:15-alpine```
Ensuite lancez le projet depuis l'IDE de votre choix