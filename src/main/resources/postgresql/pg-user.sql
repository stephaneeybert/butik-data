create user butik with encrypted password 'mypassword';
alter user butik with superuser;

revoke connect on database butik from public;
grant connect on database butik to butik;
grant all privileges on database butik to butik;
grant all privileges on all tables in schema public to butik;
grant all privileges on all sequences in schema public to butik;
revoke connect on database butiktest from public;
grant connect on database butiktest to butik;
grant all privileges on database butiktest to butik;
grant all privileges on all tables in schema public to butik;
grant all privileges on all sequences in schema public to butik;
