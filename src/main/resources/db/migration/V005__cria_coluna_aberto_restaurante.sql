alter table restaurante ADD aberto TINYINT(1) NOT NULL;
UPDATE restaurante SET aberto = false;