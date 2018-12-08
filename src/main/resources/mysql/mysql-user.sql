use mysql;
grant all privileges on butik.* to butik@'127.0.0.1' identified by 'mypassword';
grant all privileges on butiktest.* to butik@'127.0.0.1' identified by 'mypassword';
flush privileges;

