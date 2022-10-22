set foreign_key_checks = 0;

lock tables cidades write, cozinhas write, estados write, formas_pagamentos write,
	grupos write, grupos_permissoes write, permissoes write,
	produtos write, restaurantes write, restaurantes_formas_pagamentos write,
	restaurantes_usuarios_responsaveis write, usuarios write, usuarios_grupos write,
	pedidos write, itens_pedidos write, produtos_fotos write, oauth2_registered_client write; 

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
delete from oauth2_registered_client;

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

insert into formas_pagamentos (descricao, data_atualizacao) values ('Cartão de Crédito', utc_timestamp);
insert into formas_pagamentos (descricao, data_atualizacao) values ('Dinheiro', utc_timestamp);
insert into formas_pagamentos (descricao, data_atualizacao) values ('Pix', utc_timestamp);

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


insert into usuarios (nome, email, senha, data_cadastro) values ('João Victor', 'joao@email.com', '$2a$12$OJcKEyHQilkeJskCo5ql0O.yWWOlZawppUrl87LWVEkw7GxMbj1au', utc_timestamp);
insert into usuarios (nome, email, senha, data_cadastro) values ('Maria', 'maria@email.com', '$2a$12$OJcKEyHQilkeJskCo5ql0O.yWWOlZawppUrl87LWVEkw7GxMbj1au', utc_timestamp);
insert into usuarios (nome, email, senha, data_cadastro) values ('José', 'jose@email.com', '$2a$12$OJcKEyHQilkeJskCo5ql0O.yWWOlZawppUrl87LWVEkw7GxMbj1au', utc_timestamp);
insert into usuarios (nome, email, senha, data_cadastro) values ('Débora', 'debora@email.com', '$2a$12$OJcKEyHQilkeJskCo5ql0O.yWWOlZawppUrl87LWVEkw7GxMbj1au', utc_timestamp);
insert into usuarios (nome, email, senha, data_cadastro) values ('Manoel', 'manoel@email.com', '$2a$12$OJcKEyHQilkeJskCo5ql0O.yWWOlZawppUrl87LWVEkw7GxMbj1au', utc_timestamp);
insert into usuarios (nome, email, senha, data_cadastro) values ('Ana', 'ana@email.com', '$2a$12$OJcKEyHQilkeJskCo5ql0O.yWWOlZawppUrl87LWVEkw7GxMbj1au', utc_timestamp);



insert into permissoes (codigo, nome, descricao) values (1, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
insert into permissoes (codigo, nome, descricao) values (2, 'EDITAR_FORMAS_PAGAMENTO', 'Permite criar ou editar formas de pagamento');
insert into permissoes (codigo, nome, descricao) values (3, 'EDITAR_CIDADES', 'Permite criar ou editar cidades');
insert into permissoes (codigo, nome, descricao) values (4, 'EDITAR_ESTADOS', 'Permite criar ou editar estados');
insert into permissoes (codigo, nome, descricao) values (5, 'CONSULTAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite consultar usuários');
insert into permissoes (codigo, nome, descricao) values (6, 'EDITAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite criar ou editar usuários');
insert into permissoes (codigo, nome, descricao) values (7, 'EDITAR_RESTAURANTES', 'Permite criar, editar ou gerenciar restaurantes');
insert into permissoes (codigo, nome, descricao) values (8, 'CONSULTAR_PEDIDOS', 'Permite consultar pedidos');
insert into permissoes (codigo, nome, descricao) values (9, 'GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos');
insert into permissoes (codigo, nome, descricao) values (10, 'GERAR_RELATORIOS', 'Permite gerar relatórios');

insert into grupos (nome) values ('Gerente');
insert into grupos (nome) values ('Vendedor');
insert into grupos (nome) values ('Secretária');
insert into grupos (nome) values ('Cadastrador');

# Adiciona todas as permissoes no grupo do gerente
insert into grupos_permissoes (cod_grupo, cod_permissao)
select 1, codigo from permissoes;

# Adiciona permissoes no grupo do vendedor
insert into grupos_permissoes (cod_grupo, cod_permissao)
select 2, codigo from permissoes where nome like 'CONSULTAR_%';

insert into grupos_permissoes (cod_grupo, cod_permissao)
select 2, codigo from permissoes where nome = 'EDITAR_RESTAURANTES';

# Adiciona permissoes no grupo do auxiliar
insert into grupos_permissoes (cod_grupo, cod_permissao)
select 3, codigo from permissoes where nome like 'CONSULTAR_%';

# Adiciona permissoes no grupo cadastrador
insert into grupos_permissoes (cod_grupo, cod_permissao)
select 4, codigo from permissoes where nome like '%_RESTAURANTES';


insert into usuarios_grupos (cod_usuario, cod_grupo) values (1, 1), (1, 2), (2, 2), (3, 3), (4, 4);



insert into restaurantes_usuarios_responsaveis (cod_restaurante, cod_usuario) values (1, 1), (2, 1), (3, 2), (4, 3), (5, 5), (6, 5);



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
	

	
	
INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('1', 'algafood-backend', '2022-08-16 19:04:12', '$2a$10$97f9cT/X/htp85ELK8.IhOBpCRHAmn0Z0cYOJVscCj6esvTIFYOrS', NULL, 'AlgaFood Backend', 'client_secret_basic', 'client_credentials', '', 'READ', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.core.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000]}');

INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('2', 'algafood-web', '2022-08-16 19:04:12', '$2a$10$ku07Df8C0xrgJ.lId5.Cie..VZH4AReQ0wNIKaqvcMlC3MrjT6IF2', NULL, 'AlgaFood Web', 'client_secret_basic', 'refresh_token,authorization_code', 'http://127.0.0.1:8080/swagger-ui/oauth2-redirect.html,http://127.0.0.1:8080/authorized', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":false,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",900.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.core.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",86400.000000000]}');

INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('3', 'food-analytics', '2022-08-16 19:04:12', '$2a$10$E5f93hZ5kq97tcZVVUEtru08Eg9KBkziAdyZegNT/cfgJItimzPwW', NULL, 'Food Analytics', 'client_secret_basic', 'authorization_code', 'http://www.foodanalytics.local:8082', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.core.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000]}');



unlock tables;