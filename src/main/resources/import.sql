insert into cozinhas (nome) values ('Brasileira');
insert into cozinhas (nome) values ('Italiana');
insert into cozinhas (nome) values ('Chinesa');

insert into restaurantes (nome, taxa_frete, cod_cozinha) values ('Bar e Restaurante do Goiano', 7, 1);
insert into restaurantes (nome, taxa_frete, cod_cozinha) values ('Massas Gourmet', 5.5, 2);


insert into formas_pagamentos (descricao) values ('Cartão de Crédito');
insert into formas_pagamentos (descricao) values ('Dinheiro');
insert into formas_pagamentos (descricao) values ('Pix');


insert into estados (nome) values ('Goiás');
insert into estados (nome) values ('Minas Gerais');
insert into estados (nome) values ('São Paulo');
insert into estados (nome) values ('Paraná');
insert into estados (nome) values ('Santa Catarina');


insert into cidades (nome, cod_estado) values ('Itumbiara', 1);
insert into cidades (nome, cod_estado) values ('Uberlândia', 2);
insert into cidades (nome, cod_estado) values ('Goiânia', 1);
insert into cidades (nome, cod_estado) values ('Curitiba', 4);


insert into permissoes (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissoes (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');