create table food( 
	food_id int not null,
    name varchar(256) not null,
    collaborator_id int not null,
    primary key(food_id),
    foreign key (collaborator_id) references collaborator(collaborator_id)
);