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
delete from usuarios;
delete from usuarios_grupos;

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


insert into usuarios (nome, email, senha, data_cadastro) values ('João Victor', 'joaov', '123', utc_timestamp);
insert into usuarios (nome, email, senha, data_cadastro) values ('Maria', 'maria', '123', utc_timestamp);

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