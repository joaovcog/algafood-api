alter table restaurantes add aberto tinyint(1) not null;
update restaurantes set aberto = true;