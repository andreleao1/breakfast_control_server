create table breakfast_control.food( 
	food_id int not null auto_increment,
    name varchar(256) not null,
    collaborator_id int,
    primary key(food_id),
    foreign key (collaborator_id) references collaborator(collaborator_id)
);