set session_replication_role = replica;

drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 10;

drop sequence if exists shop_product_id_seq;
create sequence shop_product_id_seq start with 1 increment by 10;
drop table if exists shop_product;
create table shop_product (
  id bigint default nextval('shop_product_id_seq') primary key,
  version int not null,
  created_on timestamp,
  updated_on timestamp,
  name varchar(255) not null,
  price varchar(10) not null
);

drop sequence if exists shop_order_id_seq;
create sequence shop_order_id_seq start with 1 increment by 10;
drop table if exists shop_order;
create table shop_order (
  id bigint default nextval('shop_order_id_seq') primary key,
  version int not null,
  created_on timestamp,
  updated_on timestamp,
  order_ref_id int not null,
  email varchar(50) not null,
  constraint email unique (email)
);

drop sequence if exists shop_order_product_id_seq;
create sequence shop_order_product_id_seq start with 1 increment by 10;
drop table if exists shop_order_product;
create table shop_order_product (
  id bigint default nextval('shop_order_product_id_seq') primary key,
  version int not null,
  created_on timestamp,
  updated_on timestamp,
  shop_order_id bigint not null,
  shop_product_id bigint not null,
  price varchar(10) not null,
  constraint shop_order_product_u1 unique (shop_order_id, shop_product_id),
  constraint shop_order_product_id unique (id),
  constraint shop_order_product_fk1 foreign key (shop_order_id) references shop_order (id),
  constraint shop_order_product_fk2 foreign key (shop_product_id) references shop_product (id)
);
create index shop_order_product_order_id on shop_order_product (shop_order_id);

set session_replication_role = default;