create table restaurantes_usuarios_responsaveis (
	cod_restaurante bigint not null, 
	cod_usuario bigint not null, 
	
	primary key (cod_restaurante, cod_usuario)
) engine=InnoDB default charset=utf8mb4;

alter table restaurantes_usuarios_responsaveis add constraint fk_restaurantes_usuarios_restaurantes 
foreign key (cod_restaurante) references restaurantes (codigo);

alter table restaurantes_usuarios_responsaveis add constraint fk_restaurantes_usuarios_usuarios 
foreign key (cod_usuario) references usuarios (codigo);