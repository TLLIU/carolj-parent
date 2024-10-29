drop table IF EXISTS SAMPLE_ENTITY;
create table SAMPLE_ENTITY (
  id BIGINT NOT NULL PRIMARY KEY,
  label VARCHAR(256) NOT NULL
);
insert into SAMPLE_ENTITY (id, label) values (1, 'Test 1');
insert into SAMPLE_ENTITY (id, label) values (2, 'Test 2');
