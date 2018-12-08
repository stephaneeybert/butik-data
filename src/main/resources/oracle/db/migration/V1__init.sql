create table shop_product (
  id number(10) not null,
  version number(10) not null,
  created_on date,
  updated_on date,
  name varchar2(255) not null,
  price varchar2(10) not null,
  constraint shop_product_pk primary key (id)
);
create sequence shop_product_id_seq increment by 10 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_shop_product
before insert 
on shop_product
for each row
declare
begin
  if (:new.id is null)
  then
    select shop_product_id_seq.nextval into :new.id from dual;
  end if;
end;
/

create table shop_order (
  id number(10) not null,
  version number(10) not null,
  created_on date,
  updated_on date,
  order_ref_id number(10) not null,
  email varchar2(255) not null,
  constraint shop_order_u1 unique (email),
  constraint shop_order_pk primary key (id)
);
create sequence shop_order_id_seq increment by 10 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_shop_order
before insert 
on shop_order
for each row
declare
begin
  if (:new.id is null)
  then
    select order_id_seq.nextval into :new.id from dual;
  end if;
end;
/

create table shop_order_product (
  id number(10) not null,
  version number(10) not null,
  created_on date,
  updated_on date,
  shop_order_id number(10) not null,
  shop_product_id number(10) not null,
  price varchar2(10) not null,
  constraint shop_order_product_pk primary key (id),
  constraint shop_order_product_u1 unique (shop_order_id, shop_product_id),
  constraint shop_order_product_fk1 foreign key (shop_order_id) references shop_order (id),
  constraint shop_order_product_fk2 foreign key (shop_product_id) references shop_product (id)
);
create sequence shop_order_product_id_seq increment by 10 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_shop_order_product 
before insert 
on shop_order_product
for each row
declare
begin
  if (:new.id is null)
  then
    select shop_order_product_id_seq.nextval into :new.id from dual;
  end if;
end;
/

quit;
