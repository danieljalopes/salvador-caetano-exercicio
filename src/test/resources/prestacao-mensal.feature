Feature: Calcular prestaçao Mensal

  Scenario Outline: Calcular Prestacao Mensal
    Given Servico define o <URL>
    And Realiza um pedido á API para calcular um Financiamento <FINANCIAMENTO_TIPO>, viatura no valor <VALOR_VIATURA> e numero de prestacoes <NUMERO_PRESTACOES>
    When Servico valida o pedido com o <VALOR_PRESTACAO> e estado <STATUS_CODE>

    Examples:
    |URL                  |FINANCIAMENTO_TIPO|VALOR_VIATURA   |NUMERO_PRESTACOES|VALOR_PRESTACAO|STATUS_CODE |
    |http://localhost:8080|INTERNO           |10000.00        |2                |5200.00        |200         |
    |http://localhost:8080|INTERNO           |10000.00        |48               |216.667        |200         |
    |http://localhost:8080|EXTERNO           |10000.00        |12               |1375.00        |200         |
    |http://localhost:8080|EXTERNO           |10000.00        |60               |275.00         |200         |


  Scenario Outline: Prestacoes com valores Invalidos
    Given Servico define o <URL>
    And Realiza um pedido á API para calcular um Financiamento <FINANCIAMENTO_TIPO>, viatura no valor <VALOR_VIATURA> e numero de prestacoes <NUMERO_PRESTACOES>
    When Servico valida o pedido com a menssagem <MENSSAGEM> na variavel <VARIAVEL> e estado <STATUS_CODE>

    Examples:
      |URL                  |FINANCIAMENTO_TIPO|VALOR_VIATURA   |NUMERO_PRESTACOES|MENSSAGEM                           |STATUS_CODE |VARIAVEL         |
      |http://localhost:8080|INTERNO           |[null]          |12               |Valor da Viatura e obrigatorio      |400         |valorViatura     |
      |http://localhost:8080|INTERNO           |10000.00        |[null]           |Numero de Prestacoes e obrigatorio  |400         |numPrestacoes    |
      |http://localhost:8080|[null]            |10000.00        |12               |Tipo de Financiamento e obrigatorio |400         |tipoFinanciamento|

  Scenario Outline: Prestacoes valores fora dos limites
    Given Servico define o <URL>
    And Realiza um pedido á API para calcular um Financiamento <FINANCIAMENTO_TIPO>, viatura no valor <VALOR_VIATURA> e numero de prestacoes <NUMERO_PRESTACOES>
    When Servico valida o pedido com a menssagem <MENSSAGEM> na variavel <VARIAVEL> e estado <STATUS_CODE>

    Examples:
    |URL                  |FINANCIAMENTO_TIPO|VALOR_VIATURA   |NUMERO_PRESTACOES|MENSSAGEM                                                                     |STATUS_CODE |VARIAVEL         |
    |http://localhost:8080|INTERNO           |0               |12               |Valor da Viatura tem que ser maior que zero                                   |400         |valorViatura     |
    |http://localhost:8080|INTERNO           |10000.00        |0                |Numero de Prestacoes tem que ser maior que zero                               |400         |numPrestacoes     |
    |http://localhost:8080|INTERNO           |10000.00        |49               |Financiamento Interno: Numero de mensalidades não pode ser superior a 48      |400         |message     |
    |http://localhost:8080|EXTERNO           |0               |12               |Valor da Viatura tem que ser maior que zero                                   |400         |valorViatura     |
    |http://localhost:8080|EXTERNO           |10000.00        |0                |Numero de Prestacoes tem que ser maior que zero                               |400         |numPrestacoes     |
    |http://localhost:8080|EXTERNO           |10000.00        |11               |Financiamento Externo: Numero de mensalidades tem que estar entre 12 e 60 meses      |400         |message     |
    |http://localhost:8080|EXTERNO           |10000.00        |61               |Financiamento Externo: Numero de mensalidades tem que estar entre 12 e 60 meses      |400         |message     |