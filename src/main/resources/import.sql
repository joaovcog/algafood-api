insert into cozinhas (nome) values ('Brasileira');
insert into cozinhas (nome) values ('Italiana');
insert into cozinhas (nome) values ('Chinesa');

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


insert into formas_pagamentos (descricao) values ('Cartão de Crédito');
insert into formas_pagamentos (descricao) values ('Dinheiro');
insert into formas_pagamentos (descricao) values ('Pix');




insert into permissoes (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissoes (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurantes_formas_pagamentos (cod_restaurante, cod_forma_pagamento) values (1, 1), (1, 2), (1, 3), (2,3), (3, 2), (3, 3);