# Trabalho 3AOJR SOFTWARE ENGINEERING DEVELOPMENT

Integrantes
- Fernando Finardi - 347564
- Gabriela Teruel - 347561
- Renan Santos - 347563

## Microsserviço
O microsserviço criado tem como objetivo automatizar uma chamada ao ChatGPT da OpenAI, incluindo a chamada de API, funções de pré-processamento e pós-processamento.

### Pré processamento


### Chamada ao ChatGPT
Para conseguir realizar a chamada ao ChatGPT é preciso inserir a chave de API no arquivo "application.properties".

### Pós processamento

### Estrutura do microsserviço

O microsserviço foi desenvolvido em Java com o Framework Spring. Os testes foram desonvolvidos com o Framework JUnit.

A estrutura do projeto é:
.
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── mbagpt
│   │   │               ├── Controller
│   │   │               │   └── GptController.java
│   │   │               ├── MbaGptApplication.java
│   │   │               ├── Model
│   │   │               │   ├── ChatRequestDTO.java
│   │   │               │   ├── ChatResponseDTO.java
│   │   │               │   ├── ChoicesDTO.java
│   │   │               │   └── MessageDTO.java
│   │   │               └── Service
│   │   │                   ├── GptCallService.java
│   │   │                   ├── PostProcessorService.java
│   │   │                   └── PreProcessorService.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── mbagpt
│                       └── MbaGptApplicationTests.java