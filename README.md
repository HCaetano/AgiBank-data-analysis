# AgiBank-data-analysis

## Código para processo seletivo do AgiBank.

### A tarefa: 

Dado um arquivo com conteúdo formatado em linhas que seguem o padrão ```001çCPFçNameçSalary```, ```002çCNPJçNameçBusiness Area``` e ```003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name```, processar os dados a fim de obter:

1) Quantidade de clientes no arquivo de entrada; <img height="20px" src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.dXIQ83xpMofIu7LXkuoEBwHaHQ%26pid%3DApi&f=1" alt="tick">
2) Quantidade de vendedores no arquivo de entrada; <img height="20px" src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.dXIQ83xpMofIu7LXkuoEBwHaHQ%26pid%3DApi&f=1" alt="tick">
3) ID da venda mais cara; <img height="20px" src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.dXIQ83xpMofIu7LXkuoEBwHaHQ%26pid%3DApi&f=1" alt="tick">
4) O pior vendedor. <img height="20px" src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.dXIQ83xpMofIu7LXkuoEBwHaHQ%26pid%3DApi&f=1" alt="tick">

O sistema deve funcionar continuamente e processar arquivos novos quando estes forem adicionados ao diretório de entrada <img height="20px" src="https://img.icons8.com/ios-filled/50/000000/x.png" />.  
Os arquivos de entrada podem ser somente da extensão ```.dat``` <img height="20px" src="https://img.icons8.com/ios-filled/50/000000/x.png" />.   
Estes ficarão no diretório ```% HOMEPATH% / data / in```<img height="20px" src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.dXIQ83xpMofIu7LXkuoEBwHaHQ%26pid%3DApi&f=1" alt="tick">.   
Os arquivos de saída seguirão a seguinte nomenclatura: ```{flat_file_name} .done.dat```<img height="20px" src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.dXIQ83xpMofIu7LXkuoEBwHaHQ%26pid%3DApi&f=1" alt="tick">.   
E ficarão em ```% HOMEPATH% / data / out``` <img height="20px" src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.dXIQ83xpMofIu7LXkuoEBwHaHQ%26pid%3DApi&f=1" alt="tick">.  

### Comentários: 

O código possui testes nos métodos em que há decisões ou modificação de dados <img height="20px" src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.dXIQ83xpMofIu7LXkuoEBwHaHQ%26pid%3DApi&f=1" alt="tick">.  
Exceto pelas seções de IO, carece de lançamento de exceções,  validação e logging <img height="20px" src="https://img.icons8.com/ios-filled/50/000000/x.png" />. 

### Road map planejado para o desenvolvimento e Semantic Versioning:

0.1: operações de escrita a leitura de arquivos;  
0.2: processamento da informação para obter os passos de 1 a 4;  
0.3: migrar a lógica existente para POO;  
0.4: adicionar testes unitários;  
0.5: adicionar logging;  
0.6: tratamento de exceções;  
0.7: refatorar código para utilizar funcionalidades como Stream API;  
0.8: adicionar documentação ao projeto;  
0.9: melhorar performance;  
1.0: implementar JMS API;  

Executados passos 0.1 a 0.4 e 0.8.