set foreign_key_checks = 0;

delete from cidades;
delete from cozinhas;
delete from estados;
delete from formas_pagamentos;
delete from grupos_permissoes;
delete from grupos;
delete from permissoes;
delete from produtos;
delete from restaurantes;
delete from restaurantes_formas_pagamentos;
delete from restaurantes_usuarios_responsaveis;
delete from usuarios;
delete from usuarios_grupos;
delete from pedidos;
delete from itens_pedidos;
delete from produtos_fotos;

set foreign_key_checks = 1;


alter table cidades auto_increment = 1;
alter table cozinhas auto_increment = 1;
alter table estados auto_increment = 1;
alter table formas_pagamentos auto_increment = 1;
alter table grupos auto_increment = 1;
alter table permissoes auto_increment = 1;
alter table produtos auto_increment = 1;
alter table restaurantes auto_increment = 1;
alter table usuarios auto_increment = 1;
alter table pedidos auto_increment = 1;
alter table itens_pedidos auto_increment = 1;


insert into cozinhas (nome) values ('Brasileira');
insert into cozinhas (nome) values ('Italiana');
insert into cozinhas (nome) values ('Chinesa');
insert into cozinhas (nome) values ('Argentina');

insert into estados (nome) values ('Goiás');
insert into estados (nome) values ('Minas Gerais');
insert into estados (nome) values ('São Paulo');
insert into estados (nome) values ('Paraná');
insert into estados (nome) values ('Santa Catarina');


insert into cidades (nome, cod_estado) values ('Itumbiara', 1);
insert into cidades (nome, cod_estado) values ('Uberlândia', 2);
insert into cidades (nome, cod_estado) values ('Goiânia', 1);
insert into cidades (nome, cod_estado) values ('Curitiba', 4);


insert into restaurantes (nome, taxa_frete, cod_cozinha, data_cadastro, data_atualizacao, endereco_cod_cidade, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, ativo, aberto) values ('Bar e Restaurante do Goiano', 7, 1, utc_timestamp, utc_timestamp, 1, '75500-000', 'Avenida Afonso Pena', '2200', 'Centro', true, true);
insert into restaurantes (nome, taxa_frete, cod_cozinha, data_cadastro, data_atualizacao, ativo, aberto) values ('Massas Gourmet', 5.5, 2, utc_timestamp, utc_timestamp, true, true);
insert into restaurantes (nome, taxa_frete, cod_cozinha, data_cadastro, data_atualizacao, ativo, aberto) values ('Comida Mineira', 0, 1, utc_timestamp, utc_timestamp, true, true);
insert into restaurantes (nome, taxa_frete, cod_cozinha, data_cadastro, data_atualizacao, ativo, aberto) values ('Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, true, true);
insert into restaurantes (nome, taxa_frete, cod_cozinha, data_cadastro, data_atualizacao, ativo, aberto) values ('Lanchonete do Tio Sam', 11, 1, utc_timestamp, utc_timestamp, true, true);
insert into restaurantes (nome, taxa_frete, cod_cozinha, data_cadastro, data_atualizacao, ativo, aberto) values ('Bar da Maria', 6, 1, utc_timestamp, utc_timestamp, true, true);
insert into restaurantes (nome, taxa_frete, cod_cozinha, data_cadastro, data_atualizacao, ativo, aberto) values ('Chinese Express', 3, 3, utc_timestamp, utc_timestamp, true, true);

insert into formas_pagamentos (descricao) values ('Cartão de Crédito');
insert into formas_pagamentos (descricao) values ('Dinheiro');
insert into formas_pagamentos (descricao) values ('Pix');

insert into restaurantes_formas_pagamentos (cod_restaurante, cod_forma_pagamento) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);


insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('Panelinha de Galinhada', 'Arroz com frango, pequi, tomate, calabresa, bacon, palmito e muçarela', 50.00, true, 1);
insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('X Tudo', 'Pão, hamburguer 180gr, presunto, muçarela, ovo, bacon, calabresa, alface, tomate e cebola', 30.00, true, 1);
insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('Lasanha de frango', 'Lasanha contendo frango, palmito, azeitona, molho', 60.00, true, 2);
insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('Picanha na chapa', 'Acompanha arroz, feijão tropeiro, mandioca e farofa', 120.00, true, 3);
insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 7);
insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('Camarão ao alho', '16 camarões grandes ao molho picante', 110, 1, 7);
insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 1);
insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e tomate', 8, 1, 5);


insert into usuarios (nome, email, senha, data_cadastro) values ('João Victor', 'joaovictor.estudosweb+joao@gmail.com', '123', utc_timestamp);
insert into usuarios (nome, email, senha, data_cadastro) values ('Maria', 'joaovictor.estudosweb+maria@gmail.com', '123', utc_timestamp);
insert into usuarios (nome, email, senha, data_cadastro) values ('José', 'jose@email.com', '123', utc_timestamp);

insert into permissoes (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissoes (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');
insert into permissoes (nome, descricao) values ('CONSULTAR_RESTAURANTES', 'Permite consultar restaurantes');
insert into permissoes (nome, descricao) values ('EDITAR_RESTAURANTES', 'Permite editar restaurantes');
insert into permissoes (nome, descricao) values ('CRIAR_PEDIDOS', 'Permite criar pedidos');
insert into permissoes (nome, descricao) values ('CONSULTAR_PEDIDOS', 'Permite consultar pedidos');
insert into permissoes (nome, descricao) values ('VISUALIZAR_RELATORIOS_PEDIDOS', 'Permite visualizar relatórios de pedidos');

insert into grupos (nome) values ('Administrador');
insert into grupos (nome) values ('Funcionário');
insert into grupos (nome) values ('Cliente');

insert into grupos_permissoes (cod_grupo, cod_permissao) values (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (2, 3), (2, 4), (2, 6), (3, 3), (3, 5), (3, 6);

insert into usuarios_grupos (cod_usuario, cod_grupo) values (1, 1), (1, 2), (2, 3);

insert into restaurantes_usuarios_responsaveis (cod_restaurante, cod_usuario) values (1, 1), (2, 1), (3, 2), (4, 3);



insert into pedidos (codigo, identificador, cod_restaurante, cod_usuario_cliente, cod_forma_pagamento, endereco_cod_cidade, endereco_cep, 
    	endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
    	status, data_criacao, subtotal, taxa_frete, valor_total)
	values (1, '560764a9-bea4-11ec-a999-fc4596f48122', 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
		'CRIADO', utc_timestamp, 298.90, 10, 308.90);

insert into itens_pedidos (codigo, cod_pedido, cod_produto, quantidade, preco_unitario, preco_total, observacao)
	values (1, 1, 1, 1, 78.9, 78.9, null);

insert into itens_pedidos (codigo, cod_pedido, cod_produto, quantidade, preco_unitario, preco_total, observacao)
	values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');


insert into pedidos (codigo, identificador, cod_restaurante, cod_usuario_cliente, cod_forma_pagamento, endereco_cod_cidade, endereco_cep, 
        endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
        status, data_criacao, subtotal, taxa_frete, valor_total)
	values (2, '560781b4-bea4-11ec-a999-fc4596f48122', 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
		'CRIADO', utc_timestamp, 79, 0, 79);

insert into itens_pedidos (codigo, cod_pedido, cod_produto, quantidade, preco_unitario, preco_total, observacao)
	values (3, 2, 6, 1, 79, 79, 'Ao ponto');
	
	
insert into pedidos (codigo, identificador, cod_restaurante, cod_usuario_cliente, cod_forma_pagamento, endereco_cod_cidade, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (3, 'b5741512-8fbc-47fa-9ac1-b530354fc0ff', 1, 1, 1, 1, '38400-222', 'Rua Natal', '200', null, 'Brasil',
        'ENTREGUE', '2019-10-30 21:10:00', '2019-10-30 21:10:45', '2019-10-30 21:55:44', 110, 10, 120);

insert into itens_pedidos (codigo, cod_pedido, cod_produto, quantidade, preco_unitario, preco_total, observacao)
values (4, 3, 2, 1, 110, 110, null);


insert into pedidos (codigo, identificador, cod_restaurante, cod_usuario_cliente, cod_forma_pagamento, endereco_cod_cidade, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (4, '5c621c9a-ba61-4454-8631-8aabefe58dc2', 1, 2, 1, 1, '38400-800', 'Rua Fortaleza', '900', 'Apto 504', 'Centro',
        'ENTREGUE', '2019-11-02 20:34:04', '2019-11-02 20:35:10', '2019-11-02 21:10:32', 174.4, 5, 179.4);

insert into itens_pedidos (codigo, cod_pedido, cod_produto, quantidade, preco_unitario, preco_total, observacao)
values (5, 4, 3, 2, 87.2, 174.4, null);


insert into pedidos (codigo, identificador, cod_restaurante, cod_usuario_cliente, cod_forma_pagamento, endereco_cod_cidade, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (5, '8d774bcf-b238-42f3-aef1-5fb388754d63', 1, 3, 2, 1, '38400-200', 'Rua 10', '930', 'Casa 20', 'Martins',
        'ENTREGUE', '2019-11-02 21:00:30', '2019-11-02 21:01:21', '2019-11-02 21:20:10', 87.2, 10, 97.2);

insert into itens_pedidos (codigo, cod_pedido, cod_produto, quantidade, preco_unitario, preco_total, observacao)
values (6, 5, 3, 1, 87.2, 87.2, null);
	
