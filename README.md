# Bananas export REST

## 1. How to start
```
$ git clone https://github.com/jenny19919215/BananesExportREST.git
$ cd BananesExportREST
$ mvn spring-boot:run

To list all destinations
$ curl http://localhost:8080/destinations
 To save a new destination 
$ curl -v -X POST localhost:8080/destinations -H "Content-type:application/json" -d "{\"name\":\"dest2\",\"address\":\"tototot\",\"postCode\":\"712000\",\"city\":\"xx\",\"country\":\"china\"}"

To save a new command avec with a destination
$ curl -v -X POST localhost:8080/commands -H "Content-type:application/json" -d "{\"deliverDate\":\"2019-06-09\",\"quantity\":25,\"price\":62.5,\"dest\":{\"name\":\"PARIS\",\"address\":\"tttt\",\"postCode\":71222,\"city\":\"paris\",\"country\":\"france\"}}"

To list all the commands
$ curl http://localhost:8080/commands

To list all the commands of destination whose id = 4
$ curl http://localhost:8080/commands/dest/4
