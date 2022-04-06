alter table restaurantes add ativo tinyint(1) not null;
update restaurantes set ativo = true;