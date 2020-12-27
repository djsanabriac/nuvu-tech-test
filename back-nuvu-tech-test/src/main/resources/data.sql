--Tipos de documento
insert into id_type(id_type_id, abbreviation, description) values (1, 'CC', 'Cédula de ciudadanía');
insert into id_type(id_type_id, abbreviation, description) values (2, 'TI', 'Tarjeta de identidad');
insert into id_type(id_type_id, abbreviation, description) values (3, 'NIT', 'Número de identificación tributaria');
--Usuario superadmin
insert into user(user_id, type_id, id_number, name, last_name, email, phone_number, token, username, password)
    values (1, 1, '123456789', 'superadmin', 'superadmin', 'admin@admin.co', '3223432956', '', 'superadmin', '$2y$12$BhH4ezdvqc2WgU7sY5vSl.sSmNp2o46sdiBB.SEC4Pmdu7oY1gzMW');

--Roles
insert into role(role_id, description, role_name) values (1, 'Super admin', 'SUPER_ADMIN');

--Rol usuario
insert into user_role(user_id, role_id) values (1,1);

--Redes de pago
insert into payment_network(payment_network_id, name) values (1, 'Visa');
insert into payment_network(payment_network_id, name) values (2, 'MasterCard');
insert into payment_network(payment_network_id, name) values (3, 'American Express');

commit;
