drop database if exists cupcakes;
create database cupcakes;

use cupcakes;
drop table if exists users;
create table users (
userId int(10) primary key auto_increment not null,
username varchar(50) unique not null,
password varchar(50) unique not null,
balance double not null
);

use cupcakes;
drop table if exists bottoms;
create table bottoms (
bottomId int(5) primary key auto_increment not null,
bottom varchar(50) not null,
price double not null
);

use cupcakes;
drop table if exists toppings;
create table toppings (
toppingId int(5) primary key auto_increment not null,
topping varchar(50) not null,
price double not null
);

use cupcakes;
drop table if exists invoice;
create table invoice (
invoiceId int(10) primary key auto_increment not null,
bottomId int(5) not null,
toppingId int(5) not null,
totalPrice double,
FOREIGN KEY (bottomId) REFERENCES bottoms(bottomId),
FOREIGN KEY (toppingId) REFERENCES toppings(toppingId)
);

use cupcakes;
drop table if exists orders;
create table orders (
ordersId int(10) primary key auto_increment not null,
userId int(5) not null,
FOREIGN KEY (userId) REFERENCES users(userId)
);

use cupcakes;
drop table if exists orderdetails;
create table orderdetails (
odID int(10) primary key auto_increment not null,
invoiceId int(10) not null,
ordersId int(10) not null,
FOREIGN KEY (invoiceId) REFERENCES invoice(invoiceId),
FOREIGN KEY (ordersId) REFERENCES orders(ordersId)
);

insert into users(username, password, balance) VALUES ('asger', 'asger123', 200);
insert into users(username, password, balance) VALUES ('bob', 'bob123', 300);
insert into users(username, password, balance) VALUES ('rasmus', 'rasmus123', 150);

insert into bottoms(bottom, price) VALUES ('Chocolate', 5.00);
insert into bottoms(bottom, price) VALUES ('Vanilla', 5.00);
insert into bottoms(bottom, price) VALUES ('Nutmeg', 5.00);
insert into bottoms(bottom, price) VALUES ('Pistacio', 6.00);
insert into bottoms(bottom, price) VALUES ('Almond', 7.00);

insert into toppings(topping, price) VALUES ('Chocolate', 5.00);
insert into toppings(topping, price) VALUES ('Blueberry', 5.00);
insert into toppings(topping, price) VALUES ('Rasberry', 5.00);
insert into toppings(topping, price) VALUES ('Crispy', 6.00);
insert into toppings(topping, price) VALUES ('Strawberry', 6.00);
insert into toppings(topping, price) VALUES ('Rum/Raisin', 7.00);
insert into toppings(topping, price) VALUES ('Orange', 8.00);
insert into toppings(topping, price) VALUES ('Lemon', 8.00);
insert into toppings(topping, price) VALUES ('Blue Cheese', 9.00);