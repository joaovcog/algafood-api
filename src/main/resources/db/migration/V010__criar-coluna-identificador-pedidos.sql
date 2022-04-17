alter table pedidos add identificador varchar(36) not null after codigo;

update pedidos set identificador = uuid();

alter table pedidos add constraint uk_pedidos_identificador unique (identificador);