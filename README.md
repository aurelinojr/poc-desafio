

# Nome do Projeto

fluxocaixa

# Container Docker 

  Abra a pasta docker e execute na linha de comando: docker-compose up  
  
# Estrutura dos dados

  Formato da data yyyy-mm-dd
  
	{
	  "cxa_desc": "Prato",
	  "cxa_valor": 39.50,
	  "cxa_oper" : "D",
	  "cxa_data": "2023-02-02"
	}  

# URI para chamada da api

  Inserir lançamentos no caixa (post)
  
  http://localhost:8080/api/v1/fluxocaixa/insert_caixa
  
  
  Alterar lançamentos no caixa (put)
  
  http://localhost:8080/api/v1/fluxocaixa/update_caixa/{id}
  
  
  Deletar lançamentos no caixa (delete)
  
  http://localhost:8080/api/v1/fluxocaixa/delete_caixa/{id}
  
  Obter os lançamentos consolidados diário (get)
  
  Formato da {data} dd-mm-yyyy
  
  http://localhost:8080/api/v1/fluxocaixa/consolidado_diario/{data}

  Obter os lançamentos consolidados por perído (get)
  
  http://localhost:8080/api/v1/fluxocaixa/caixa/{dataInicial}/{dataFinal}
  

## Requisitos do Sistema

Certifique-se de ter os seguintes requisitos instalados em seu sistema:

- [Docker]
- [Java Development Kit (JDK)]
- [Maven](https://maven.apache.org/)  
 

