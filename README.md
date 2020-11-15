# Teste Elotech 

### Ferramentas Utilizadas

  - SPRING BOOT
  - SPRING DATA
  - SPRING VALIDATOR
  - HIBERNATE
  - DB POSTGRES 
  - JUNIT

### API RECURSOS
## Pessoa
   -  GET pessoa/{idPessoa}
   -  GET pessoa/buscaTodasPessoas
   -  GET pessoa/buscaTodasPessoas?page=0&size=3&sort=nome //PAGINAVEL
   - POST pessoa/adicionaPessoa
   - PUT pessoa/atualizar
   - DELETE pessoa/deletaPessoa/{idPessoa}
  ``` 
  @Body exemplo -> adicionaPessoa 
{
    "nome": "Lucas Henrique",
    "cpf": "282.472.760-89",
    "dataNascimento": "30/03/2000",
    "listaContatos": [
        {
            "nome": "lucas henrique",
            "telefone": "16994157664",
            "email": "joao@gmail.com"
       
        }
    ]
}
   ```
 
   ``` 
  @Body exemplo -> atualizar 
{
    "id": 1
    "nome": "Lucas Henrique",
    "cpf": "282.472.760-89",
    "dataNascimento": "30/03/2000",
    "listaContatos": [
        {
            "id": 1
            "nome": "lucas henrique",
            "telefone": "16994157664",
            "email": "joao@gmail.com"
        }
    ]
}
``` 
## Contatos

   -  GET contato/{idcontato}
   -  GET contato/buscaTodosContatos 
   -  GET contato/buscaTodosContatos?page=0&size=3&sort=nome    //PAGINAVEL 
   - POST contato/adicionaContato
   - DELETE contato/deletaContato/{idContato}"
   - PUT contato/atualizar
   
     ``` 
  @Body exemplo -> contato/adicionaContato 
{
     "nome": "lucas henrique",
    "telefone": "16994157664",
    "email": "joao@gmail.com"
}
  ``` 
   
   
  ``` 
  @Body exemplo -> contato/atualizar 
{
	"id": 1,
	"nome": "lucas henrique",
    "telefone": "16994157664",
    "email": "joao@gmail.com"
}

  ``` 
  
  ## EXEMPLO DE JSON DE ERRO
  
  ``` 
  [
    {
        "campo": "nome",
        "erro": "não pode estar em branco"
    }
]
  ``` 
  
