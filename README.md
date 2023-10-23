# Simple Banking Application

This is a simple banking application developed with Java and Spring Boot. It uses MySQL as the database to store account information and transactions.

## Getting Started

Follow these steps to set up the project locally:

### Database Setup

1. Create a MySQL database named `bank`.

2. Run the following SQL script to create the necessary tables:
https://github.com/emrekursatt/simplebanking/blob/main/bank.sql

The application will start on http://localhost:8080.
Usage
Use the provided API endpoints to interact with the banking application.
### Swagger Documentation
Explore and test the APIs using Swagger. Access the Swagger UI at http://localhost:8080/swagger-ui/

### Insert Operation
To perform an insert operation, use the following JSON data as an example:

<pre>
{
  "accountNumber": "0123",
  "balance": 12500,
  "owner": "Bank",
  "transactions": [
    {
      "amount": 250,
      "approvalCode": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "date": "2023-10-22T20:00:38.437Z",
      "type": "DepositTransaction"
    }
  ]
}

</pre>

### Debit Operation
<pre>
    account_numer = 0123
{
  "account": {
    "accountNumber": "string",
    "balance": 0,
    "owner": "string",
    "transactions": [
      {
        "amount": 2500,
        "approvalCode": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "date": "2023-10-22T19:56:43.284Z",
        "type": "WithdrawalTransaction"
      }
    ]
  },
  "amount": 2500,
  "approvalCode": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "date": "2023-10-22T19:56:43.284Z",
  "type": "WithdrawalTransaction"
}
</pre>
    
### Credit Operation
        
<pre>
    account_numer = 0123
{
  "account": {
    "accountNumber": "string",
    "balance": 0,
    "owner": "string",
    "transactions": [
      {
        "amount": 8000,
        "approvalCode": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "date": "2023-10-22T20:04:04.924Z",
        "type": "DepositTransaction"
      }
    ]
  },
  "amount": 8000,
  "approvalCode": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "date": "2023-10-22T20:04:04.924Z",
  "type": "DepositTransaction"
}
    </pre>

### Get Acoount Operation
        
<pre>
    account_numer = 0123
</pre>

## Method added to ModelTest class
<pre>testAccountTransactions</pre> 
    
