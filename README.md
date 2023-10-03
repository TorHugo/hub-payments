# HUB-PAYMENT 

## Descrição: 
O projeto Hub-Payments é uma plataforma que permite realizar transações financeiras para diversos outros projetos. Ele atua como um hub central para processar pagamentos e transferências de fundos entre diferentes sistemas e aplicativos. Com uma tecnologia sólida e confiável, o Hub-Payments facilita a integração de serviços financeiros em qualquer projeto que necessite de funcionalidades de pagamento. 

## Status do Projeto: 
🚧 Projeto em desenvolvimento. 🚧 

## Tecnologias 
- Java 17 
- PostgreSQL 
- SpringBoot v3.1.4 (validation/ gson/ devtools/ jdbc/ lombok) 

 

## ENDPOINTS: 
### STORE. 
```http
  POST /api/v1/store
```
| Parâmetro     | Tipo     | Descrição                                           | 
|:--------------|:---------|:----------------------------------------------------| 
| `email`       | `string` | **Obrigatório**. Email para cadastro da loja.       | 
| `password`    | `string` | **Obrigatório**. Senha para cadastro da loja.       | 
| `cpf_or_cnpj` | `string` | **Obrigatório**. Cpf ou Cnpj para cadastro da loja. | 

```http
  GET /api/v1/store/retrieve/{store_id}
```
| Parâmetro    | Tipo   | Descrição                    | 
|:-------------|:-------|:-----------------------------| 
| ` store_id ` | `long` | **Obrigatório**. ID da LOJA. |

### USER. 
```http 
  POST /api/v1/user/register 
``` 
| Parâmetro     | Tipo     | Descrição                                              | 
|:--------------|:---------|:-------------------------------------------------------| 
| `email`       | `string` | **Obrigatório**. Email para cadastro de cliente.       | 
| `store_id`    | `long`   | **Obrigatório**. ID da loja associada a este cliente.  | 
| `cpf_or_cnpj` | `string` | **Obrigatório**. CPF ou CNPJ para cadastro do cliente. | 
| `first_name`  | `string` | **Obrigatório**. Primeiro nome do cliente.             | 
| `last_name`   | `string` | **Obrigatório**. Último nome do cliente.               | 
| `phone`       | `string` | **Obrigatório**. Telefone para contato do cliente.     | 

```http 
  GET/api/v1/user/retrieve/{store_id} ?{email} 
``` 
| Parâmetro    | Tipo     | Descrição                                             | 
|:-------------|:---------|:------------------------------------------------------| 
| ` store_id ` | `long`   | **Obrigatório**. ID da loja associada a este cliente. | 
| `email`      | `string` | **Obrigatório**. Email do usuário há ser recuperado.  | 

### CUSTOMER.
```http 
  POST /api/v1/customer/credit-token 
``` 
| Parâmetro                            | Tipo     | Descrição                                                  | 
|:-------------------------------------|:---------|:-----------------------------------------------------------| 
| `customer`                           | `string` | **Obrigatório**. ID do cliente.                            | 
| `creditCard`                         | `object` | **Obrigatório**. Informações do cartão de crédito.         | 
| `creditCard.holderName`              | `string` | **Obrigatório**. Nome do titular do cartão de crédito.     | 
| `creditCard.number`                  | `string` | **Obrigatório**. Número do cartão de crédito.              | 
| `creditCard.expiryMonth`             | `string` | **Obrigatório**. Mês de validade do cartão de crédito.     | 
| `creditCard.expiryYear`              | `string` | **Obrigatório**. Ano de validade do cartão de crédito.     | 
| `creditCard.ccv`                     | `string` | **Obrigatório**. Código de segurança do cartão de crédito. | 
| `creditCardHolderInfo`               | `object` | **Obrigatório**. Informações do titular do cartão.         | 
| `creditCardHolderInfo.name`          | `string` | **Obrigatório**. Nome do titular do cartão.                | 
| `creditCardHolderInfo.email`         | `string` | **Obrigatório**. Email do titular do cartão.               | 
| `creditCardHolderInfo.cpfCnpj`       | `string` | **Obrigatório**. CPF ou CNPJ do titular do cartão.         | 
| `creditCardHolderInfo.postalCode`    | `string` | CEP do endereço do titular do cartão.                      | 
| `creditCardHolderInfo.addressNumber` | `string` | Número do endereço do titular do cartão.                   | 
| `creditCardHolderInfo.phone`         | `string` | **Obrigatório**. Telefone de contato do titular do cartão. | 
 
### SIMULATION.
```http 
  GET /api/v1/simulation 
``` 
| Parâmetro           | Tipo      | Descrição                                         | 
|:--------------------|:----------|:--------------------------------------------------| 
| `total_value`       | `float`   | **Obrigatório**. Valor total da transação.        | 
| `installment_count` | `int`     | **Obrigatório**. Número de parcelas da transação. | 
| `date_due`          | `string`  | **Obrigatório**. Data de vencimento das parcelas. | 
| `first_installment` | `boolean` | Indica se é a primeira parcela (true/false).      | 

### PAYMENT.
```http 
  POST /api/v1/payment 
``` 
| Parâmetro            | Tipo     | Descrição                                              | 
|:---------------------|:---------|:-------------------------------------------------------| 
| `customer`           | `string` | **Obrigatório**. ID do cliente.                        | 
| `store_id`           | `long`   | **Obrigatório**. ID da loja associada a este cliente.  | 
| `billing_type`       | `string` | **Obrigatório**. Tipo de cobrança (ex: "CREDIT_CARD"). | 
| `value`              | `float`  | **Obrigatório**. Valor da transação.                   | 
| `due_date`           | `string` | **Obrigatório**. Data de vencimento da transação.      | 
| `description`        | `string` | Descrição da transação.                                | 
| `external_reference` | `string` | Referência externa associada à transação.              | 
| `credit_card_token`  | `string` | Token do cartão de crédito associado à transação.      | 

## 🚧 FEATURES 🚧: 
### LOJA: 
1. [PUT] Atualização de LOJA. 
### USER 
1. [PUT] Atualização de USUÁRIO. 
2. [GET] Listagem de USUÁRIO por LOJA. 
### CUSTOMER 
1. [PUT] Atualização de CUSTOMER. 
2. [DELETE] Exclusão de CUSTOMER. 
### PAYMENT 
1. [DELETE] Exclusão de PAYMENT. 
2. [POST] Estorno de PAYMENT. 
3. [POST] PAYMENT parcelado. 
4. [GET] Recuperação de parcelamento. 
5. [GET] Recuperar comprovante de pagamento (PDF).
### FORMS_PAYMENT... 

## CONTRIBUIÇÃO: 
1. Faça um fork desse repositório; 
2. Cria uma branch com a sua feature: `git checkout -b minha-feature`; 
3. Faça commit das suas alterações: `git commit -m 'feat: Minha nova feature'`; 
4. Faça push para a sua branch: `git push origin minha-feature`. 
5. Depois que o merge da sua pull request for feito, você pode deletar a sua branch. 

## INTEGRAÇÃO: 
Asaas API (https://docs.asaas.com/).  

# 
Desenvolvido por Victor Hugo Arruda Jesus.  
