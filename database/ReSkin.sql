drop database if exists ReSkin;
create database ReSkin;

use ReSkin;

create table Category (
categoryID int primary key auto_increment,
nome varchar(30) not null,
descrizione varchar(100) not null );

create table Product (
productID int primary key auto_increment,
nome varchar(30) not null,
descrizione varchar(100),
binaryImage blob not null,
larghezza double not null,
lunghezza double not null,
quantita int not null,
prezzo double not null,
categoryID int not null,
foreign key (categoryID) references Category(categoryID) on update CASCADE);

create table Customer (
customerID int primary key auto_increment,
nome varchar(30) not null,
cognome varchar(30) not null,
PIVA char(11) unique,
passwordHash varchar(300) not null,
email varchar(50) not null unique,
isAdmin boolean not null );

create table OrderPlace (
orderID int primary key auto_increment,
dataPiazzamento DATETIME not null,
statusOrder varchar(30) not null,
totale double not null,
via varchar(30) not null,
citta varchar(30) not null,
provincia varchar(30) not null,
cap char(5) not null,
stato varchar(30) not null,
customerID int not null, 
foreign key (customerID) references Customer(customerID) on update CASCADE);

create table OrderDetails (
orderDetailsID int primary key auto_increment,
quantita int not null,
productID int not null,
orderID int not null,
foreign key (orderID) references OrderPlace(orderID) on delete CASCADE on update CASCADE,
foreign key (productID) references Product(productID) on update CASCADE);

create table Cart (
cartID int primary key auto_increment,
productID int not null,
customerID int not null,
quantita int not null,
foreign key (customerID) references Customer(customerID) on delete CASCADE on update CASCADE,
foreign key (productID) references Product(productID) on delete CASCADE on update CASCADE);

create table POP (
POPID int primary key auto_increment,
testo varchar(150),
dataInvio DATETIME not null,
customerID int not null,
foreign key (customerID) references Customer(customerID) on delete CASCADE on update CASCADE);

insert into Customer values
    /* Admin111@ */
    (1,'admin','admin',null,'a52082800b5bb7562a9a81e3be3db2a1b0570507','admin@admin.com',true),
    /* Mariorossi1$ */
    (2,'Mario','Rossi','85968563851','37ce8c30b594bfff67a39267b01a88b555e15644','mariorossi@gmail.com',false);