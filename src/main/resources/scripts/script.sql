-- create new user bd
create user zak with encrypted password 'loko88';


-- Delete all session with database 'your_offer' (login root postgres/)
SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE pg_stat_activity.datname = 'your_offer' AND pid <> pg_backend_pid();

-- drop and create a new database
drop database your_offer;
create database your_offer;
grant all privileges on database your_offer to zak;