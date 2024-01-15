# Trabalho 3AOJR SOFTWARE ENGINEERING DEVELOPMENT

Integrantes
- Fernando Finardi - 347564
- Gabriela Teruel - 347561
- Renan Santos - 347563

## Microsserviço
O microsserviço criado tem como objetivo automatizar uma chamada ao ChatGPT da OpenAI, incluindo a chamada de API, funções de pré-processamento e pós-processamento.

### Pré processamento
Os seguintes pré-processamentos são realizados:
- Remoção de caracteres especiais
- Transformação da string para Lower Case
- Tokenização (Divisão da string em tokens)
- Remoção de Stop words


### Chamada ao ChatGPT
Para conseguir realizar a chamada ao ChatGPT é preciso inserir a chave de API no arquivo "application.properties".

### Pós processamento
Os seguintes pós processamentos foram realizados:
- Limpeza de caracteres de espaçamento
- Remoção de caracteres especiais

### Estrutura do microsserviço

O microsserviço foi desenvolvido em Java com o Framework Spring. Os testes foram desonvolvidos com o Framework JUnit.

A estrutura do projeto é:
.
├── application.properties
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
│                       ├── MbaGptApplicationTests.java
│                       └── Service
│                           ├── GptCallServiceTest.java
│                           ├── PostProcessorServiceTest.java
│                           └── PreProcessorServiceTest.java