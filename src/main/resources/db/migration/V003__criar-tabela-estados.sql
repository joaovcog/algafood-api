create table estados (
	codigo bigint not null auto_increment, 
	nome varchar(80) not null, 
	
	primary key(codigo)
) engine=InnoDB default charset=utf8mb4;

insert into estados (nome) select distinct nome_estado from cidades;

alter table cidades add column cod_estado bigint not null;

update cidades c set c.cod_estado = (select e.codigo from estados e where e.nome = c.nome_estado);

alter table cidades add constraint fk_cidade_estado foreign key (cod_estado) references estados(codigo);

alter table cidades drop column nome_estado;

alter table cidades change nome_cidade nome varchar(80) not null;