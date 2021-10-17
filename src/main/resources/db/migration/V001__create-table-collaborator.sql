create table breakfast_control.collaborator(
	collaborator_id int not null auto_increment,
	name varchar(256) not null,
    cpf char(11) not null,
    primary key(collaborator_id)
);