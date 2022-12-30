# Voting
Voting System with Java SpringBoot

Documentação no link:
https://documenter.getpostman.com/view/17743925/2s8Z6yYZD7


Para rodar a aplicação sigam os passos:

- Entre no seu terminal de preferência
- Navegue até o diretório principal do projeto onde estão o arquivo docker-compose.yaml e Dockerfile
- Execute o comando " docker-compose up "
- Aguardo o projeto iniciar
- Utilize o projeto realizando as requisições via localhost:8080
- Para conexão com o banco de dados conecte-se em localhost:5432 e utilize as credenciais do banco, as mesmas do applications. properties

PS: (Para rodar apenas o banco de dados dockerizado)

Caso deseje subir apenas o banco de dados no docker e rodar a aplicação localmente siga os seguintes passos:
- Entre no seu terminal de preferência
- Navegue até a pasta " only_database "
- Execute o comando " docker-compose up "
- Para conexão com o banco de dados conecte-se em localhost:5432 e utilize as credenciais do banco, as mesmas do applications. properties
- Importe o projeto no Spring Boot Tools e o execute localmente
- Após isso ele estará disponivel para acesso localhost:8080

Funcionalidades do projeto:

- Cadastro de associados
- Buscar lista de associados e buscar por dados de um associado em específico
- Edição de dados dos associados
- Deletar associado
- Cadastrar pauta como tópico com titulo descrição e tempo em minutos que a votação deve ficar aberta
  - Tempo o qual a votação estará aberta deve ser registrado em minutos
  - Caso o tempo de votação não seja definido ele está marcado para 1 minuto por padrão
- Buscar lista de pautas cadastradas
- Cadastrar voto (Realizar voto)
- Cria relação entre um associado e um tópico com seu respectivo voto
- Voto só pode ser realizado se este for realizado antes do fim do tempo estipulado para a votação "0", "1", "Yes", "No" não serão aceitos



