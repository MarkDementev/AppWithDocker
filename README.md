# Overview

Spring Boot REST application.
Provides storage and modification of some wallets amount data. It is stable to work with a multithreaded requests. 
The application and database are using docker-compose.

## How to use

Request:

POST api/v1/wallet

operationType: DEPOSIT or WITHDROW.
DEPOSIT - add amount to wallet.
WITHDROW - remove amount to wallet.

```sh
{
    "walletId": "8d1208fc-f401-496c-9cb8-483fef121234",
    "operationType": "WITHDRAW",
    "amount": "1000"
}
```

Responce:
```sh
{
    "amount": "2000"
}
```
