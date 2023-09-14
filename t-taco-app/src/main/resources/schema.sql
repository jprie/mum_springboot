create table if not exists Taco_Order (
    id bigint auto_increment not null primary key,
    first_name varchar(20),
    last_name varchar(20),
    street varchar(40),
    street_nr varchar(5),
    zip_code varchar(6),
    city varchar(20),
    country varchar(20),
    cc_holder varchar(20),
    cc_number varchar(16),
    cc_expires varchar(5),
    cc_ccv varchar(3)
);

create table if not exists Taco (

  id identity,
  name varchar(12),
  order_id bigint --Foreign key
);

create table if not exists Ingredient (

    id varchar(4) not null primary key,
    name varchar(20),
    type varchar(10),
    price numeric(38,2)
);

create table if not exists Taco_Ingredients (
    taco_id bigint,
    ingredient_id varchar(4)
);

alter table Taco add foreign key (order_id) references Taco_Order(id);

alter table Taco_Ingredients add foreign key (taco_id) references Taco(id);
alter table Taco_Ingredients add foreign key (ingredient_id) references Ingredient(id);