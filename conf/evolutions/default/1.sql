# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table organisation (
  org_id                    bigint auto_increment not null,
  owner                     varchar(255),
  name                      varchar(255),
  tax_id                    integer,
  org_registation_num       integer,
  eutax_id                  integer,
  con_mail                  varchar(255),
  con_phone                 varchar(255),
  constraint pk_organisation primary key (org_id))
;

create table i_user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  u_role                    varchar(1),
  constraint ck_i_user_u_role check (u_role in ('S','K','A')),
  constraint pk_i_user primary key (email))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table organisation;

drop table i_user;

SET FOREIGN_KEY_CHECKS=1;

