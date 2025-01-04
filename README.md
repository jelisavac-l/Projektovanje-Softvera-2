# Projektovanje Softvera 2.0
![Static Badge](https://img.shields.io/badge/Luka%20Jelisavac-2022%2F0554-darkgreen) 

Drugi i nadam se smisleniji pokušaj seminarskog (projektnog) rada iz predmeta Projektovanje softvera.

##

## Opis operacija

Ispod je dat spisak osnovnih operacija na početku pisanja istih. Sigurno je da će ih biti barem duplo više od ovoga što je navedeno.

| Operacija           |    Argument     | Odgovor | Opis                                                                          |
|---------------------|:---------------:|:-------:|-------------------------------------------------------------------------------|
| PING                |     `NULL`      |    ?    | Pinguje server.                                                               |
| LOGIN               |   `Evaluator`   |    ?    | Šalje `Evaluator` objekat sa username i šifrom, dodbija kompletan pri uspehu. |
| REGISTER            |   `Evaluator`   |    ?    | Kreiranje novog korisnika - `Evaluator`.                                      |
| ACTIVITY_GET        |     `NULL`      |    ?    | Osnovna CRUD operacija.                                                       |
| ACTIVITY_NEW        |   `Activity`    |    ?    | Osnovna CRUD operacija.                                                       |
| ACTIVITY_UPDATE     |  `Activity[2]`  |    ?    | Osnovna CRUD operacija.                                                       |
| ACTIVITY_DELETE     |   `Activity`    |    ?    | Osnovna CRUD operacija.                                                       |
| ATHLETE_GET         |     `NULL`      |    ?    | Osnovna CRUD operacija.                                                       |
| ATHLETE_NEW         |    `Athlete`    |    ?    | Osnovna CRUD operacija.                                                       |
| ATHLETE_UPDATE      |    `Athlete`    |    ?    | Osnovna CRUD operacija.                                                       |
| ATHLETE_DELETE      |    `Athlete`    |    ?    | Osnovna CRUD operacija.                                                       |
| CLUB_GET            |     `NULL`      |    ?    | Osnovna CRUD operacija.                                                       |
| CLUB_GET_BY_ID      |     `Long`      |    ?    | Vraća klub prema šifri.                                                       |
| CLUB_NEW            |     `Club`      |    ?    | Osnovna CRUD operacija.                                                       |
| CLUB_UPDATE         |    `Club[2]`    |    ?    | Osnovna CRUD operacija.                                                       |
| CLUB_DELETE         |     `Club`      |    ?    | Osnovna CRUD operacija.                                                       |
| ER_GET              |     `NULL`      |    ?    | Osnovna CRUD operacija.                                                       |
| ER_NEW              |      `ER`       |    ?    | Osnovna CRUD operacija.                                                       |
| ER_START            |   `Object[2]`   |    ?    | Započinje novu ulogu za `Evaluator`.                                          |
| ER_END              |   `Object[2]`   |    ?    | Završava angažovanje pri datoj ulozi.                                         |
| EVALUATION_GET      |     `NULL`      |    ?    | Osnovna CRUD operacija.                                                       |
| EVALUATION_NEW      |  `Evaluation`   |    ?    | Osnovna CRUD operacija.                                                       |
| EVALUATION_UPDATE   | `Evaluation[2]` |    ?    | Osnovna CRUD operacija.                                                       |
| EVALUATION_DELETE   |  `Evaluation`   |    ?    | Osnovna CRUD operacija.                                                       |
| EVALUATOR_GET       |     `NULL`      |    ?    | Osnovna CRUD operacija.                                                       |
| EVALUATOR_UPDATE    | `Evaluator[2]`  |    ?    | Osnovna CRUD operacija.                                                       |
| EVALUATOR_DELETE    |   `Evaluator`   |    ?    | Osnovna CRUD operacija.                                                       |
| EVALUATOR_GET_ROLES |   `Evaluator`   |    ?    | Vraća sve uloge (prošle i tekuće) za datog `Evaluator`.                       |
| ROLE_GET            |     `NULL`      |    ?    | Osnovna CRUD operacija.                                                       |
| ROLE_NEW            |     `Role`      |    ?    | Osnovna CRUD operacija.                                                       |
| ROLE_UPDATE         |    `Role[2]`    |    ?    | Osnovna CRUD operacija.                                                       |
| ROLE_DELETE         |     `Role`      |    ?    | Osnovna CRUD operacija.                                                       |


