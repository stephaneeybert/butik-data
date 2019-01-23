set foreign_key_checks = 0;

drop table if exists shop_product_id_seq;
create sequence shop_product_id_seq start with 1 increment by 10;

drop table if exists shop_product;
create table shop_product (
  id bigint(20) unsigned not null default (next value for shop_product_id_seq),
  version int(10) unsigned not null,
  created_on datetime,
  updated_on datetime,
  name varchar(255) not null,
  price varchar(10) not null,
  primary key (id)
);

drop table if exists shop_order_id_seq;
create sequence shop_order_id_seq start with 1 increment by 10;
drop table if exists shop_order;
create table shop_order (
  id bigint(20) unsigned not null default (next value for shop_order_id_seq),
  version int(10) unsigned not null,
  created_on datetime,
  updated_on datetime,
  order_ref_id int(10) not null,
  email varchar(50) not null,
  ordered_on datetime,
  unique key email (email),
  primary key (id)
);

drop table if exists shop_order_product_id_seq;
create sequence shop_order_product_id_seq start with 1 increment by 10;
drop table if exists shop_order_product;
create table shop_order_product (
  id bigint(20) unsigned not null default (next value for shop_order_product_id_seq),
  version int(10) unsigned not null,
  created_on datetime,
  updated_on datetime,
  shop_order_id bigint(20) unsigned not null,
  shop_product_id bigint(20) unsigned not null,
  price varchar(10) not null,
  key shop_order_id (shop_order_id),
  key shop_product_id (shop_product_id),
  unique key shop_order_product_u1 (shop_order_id, shop_product_id),
  constraint shop_order_product_fk1 foreign key (shop_order_id) references shop_order (id),
  constraint shop_order_product_fk2 foreign key (shop_product_id) references shop_product (id),
  primary key (id)
);
