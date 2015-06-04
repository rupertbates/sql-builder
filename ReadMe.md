#Sql Builder
A small library to help with the creation of SQL queries

This library is particularly targeted at simplifying the use of Sqlite on Android

## Usage

### Select queries

#### Simple select
    String select = select()
                 .from(Tables.USER_TABLE)
                 .build(); //returns "SELECT * FROM user"

#### Select with fields specified
    String select = select(Fields.FIRSTNAME, Fields.SURNAME, Fields.PHONE_NUMBER)
                 .from(Tables.USER_TABLE)
                 .build(); //SELECT firstname, surname, phoneNumber FROM user

#### Select with query
    String select = select()
                    .from(Tables.USER_TABLE)
                    .where(Fields.AGE)
                    .lessThan(18)
                    .build();   //SELECT * FROM user WHERE age < 18

#### Select with query
    String select = select()
                    .from(Tables.USER_TABLE)
                    .where(Fields.AGE)
                    .lessThan(18)
                    .build();   //SELECT * FROM user WHERE age < 18