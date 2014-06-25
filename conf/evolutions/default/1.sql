# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table invoice (
  invoice_id                varchar(255) not null,
  issuer                    integer,
  partner                   integer,
  currency                  integer,
  fullfil_date              date,
  issue_date                date,
  due_date                  date,
  number_of_copies          integer,
  constraint pk_invoice primary key (invoice_id))
;

create table organisation (
  org_id                    integer auto_increment not null,
  name                      varchar(255),
  bank_account              varchar(255),
  tax_id                    varchar(255),
  phone_number              varchar(255),
  email                     varchar(255),
  constraint pk_organisation primary key (org_id))
;

create table partner (
  partner_id                integer auto_increment not null,
  name                      varchar(255),
  bank_account              varchar(255),
  tax_id                    varchar(255),
  phone_number              varchar(255),
  email                     varchar(255),
  constraint pk_partner primary key (partner_id))
;

create table product (
  prod_id                   integer auto_increment not null,
  sku                       varchar(255),
  prod_name                 varchar(255),
  unit_of_measure           varchar(255),
  unit_price                double,
  currency                  integer,
  constraint pk_product primary key (prod_id))
;

create table products (
  invoice_invoice_id        varchar(255) not null,
  invoice_id                integer,
  prod_id                   integer)
;

create table i_user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  u_role                    varchar(1),
  constraint ck_i_user_u_role check (u_role in ('S','K','A')),
  constraint pk_i_user primary key (email))
;

alter table products add constraint fk_products_invoice_1 foreign key (invoice_invoice_id) references invoice (invoice_id) on delete restrict on update restrict;
create index ix_products_invoice_1 on products (invoice_invoice_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table invoice;

drop table organisation;

drop table partner;

drop table product;

drop table products;

drop table i_user;

SET FOREIGN_KEY_CHECKS=1;

