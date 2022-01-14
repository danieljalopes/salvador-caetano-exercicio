## Documentação da API
Feito com OpenApi
Pode ser acedido em http://localhost:8080/swagger-ui.html

##Executar Spring Cli
````
.\gradlew bootrun 
````

##Testes
### BDD
É usado BDD através da framework Cucumber
#### Como Executar
1. Executar o backend no perfil de test
````
.\gradlew bootrun --args='--spring.profiles.active=test'
````
2. Executar o Cucumber
```
.\gradlew cucumberCli
```
### Testes unitários
Testes unitários em Junit
````
.\gradlew test
````

## Como Usar
1. Iniciar o serviço
````
.\gradlew bootrun
````
2. Aceder ao frontend
no browser pôr o url http://localhost:8080/prestacao-mensal/calcular