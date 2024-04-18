# Overview

Spring Boot REST application.
Provides storage and modification of wallets amount data. It is stable to work with a multithreaded requests. 
The application and database are using Docker Compose.

## How to use

### Amount value change

#### POST request:

```sh
POST api/v1/wallet + JSON
```

operationType: DEPOSIT or WITHDRAW.
DEPOSIT - add amount to wallet.
WITHDRAW - remove amount to wallet.

```sh
{
    "walletId": "8d1208fc-f401-496c-9cb8-483fef121234",
    "operationType": "WITHDRAW",
    "amount": 1000
}
```

#### Response:

```sh
{
    "walletId": "8d1208fc-f401-496c-9cb8-483fef121234",
    "amount": 2000
}
```

The amount of wallet with walletId = 8d1208fc-f401-496c-9cb8-483fef121234 has increased to 2000, before the request it was equal to 1000.

### Get wallet amount

#### GET request:

```sh
GET api/v1/wallets/{WALLET_UUID}
```

#### Request:

```sh
GET api/v1/wallets/8d1208fc-f401-496c-9cb8-483fef121234
```

#### Response:

```sh
2000
```
