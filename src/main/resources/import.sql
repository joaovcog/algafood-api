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


insert into restaurantes (nome, taxa_frete, cod_cozinha, data_cadastro, data_atualizacao, endereco_cod_cidade, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values ('Bar e Restaurante do Goiano', 7, 1, utc_timestamp, utc_timestamp, 1, '75500-000', 'Avenida Afonso Pena', '2200', 'Centro');
insert into restaurantes (nome, taxa_frete, cod_cozinha, data_cadastro, data_atualizacao) values ('Massas Gourmet', 5.5, 2, utc_timestamp, utc_timestamp);
insert into restaurantes (nome, taxa_frete, cod_cozinha, data_cadastro, data_atualizacao) values ('Comida Mineira', 0, 1, utc_timestamp, utc_timestamp);
insert into restaurantes (nome, taxa_frete, cod_cozinha, data_cadastro, data_atualizacao) values ('Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp);
insert into restaurantes (nome, taxa_frete, cod_cozinha, data_cadastro, data_atualizacao) values ('Lanchonete do Tio Sam', 11, 1, utc_timestamp, utc_timestamp);
insert into restaurantes (nome, taxa_frete, cod_cozinha, data_cadastro, data_atualizacao) values ('Bar da Maria', 6, 1, utc_timestamp, utc_timestamp);
insert into restaurantes (nome, taxa_frete, cod_cozinha, data_cadastro, data_atualizacao) values ('Chinese Express', 3, 3, utc_timestamp, utc_timestamp);


insert into formas_pagamentos (descricao) values ('Cartão de Crédito');
insert into formas_pagamentos (descricao) values ('Dinheiro');
insert into formas_pagamentos (descricao) values ('Pix');


insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('Panelinha de Galinhada', 'Arroz com frango, pequi, tomate, calabresa, bacon, palmito e muçarela', 50.00, true, 1);
insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('X Tudo', 'Pão, hamburguer 180gr, presunto, muçarela, ovo, bacon, calabresa, alface, tomate e cebola', 30.00, true, 1);
insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('Lasanha de frango', 'Lasanha contendo frango, palmito, azeitona, molho', 60.00, true, 2);
insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('Picanha na chapa', 'Acompanha arroz, feijão tropeiro, mandioca e farofa', 120.00, true, 3);
insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 7);
insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('Camarão ao alho', '16 camarões grandes ao molho picante', 110, 1, 7);
insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 1);
insert into produtos (nome, descricao, preco, ativo, cod_restaurante) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e tomate', 8, 1, 5);




insert into permissoes (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissoes (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');


insert into restaurantes_formas_pagamentos (cod_restaurante, cod_forma_pagamento) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

