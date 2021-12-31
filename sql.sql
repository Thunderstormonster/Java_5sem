use Java_Project;

select * from role;
select * from user;
select * from scooter;

insert into role (name)
values('ROLE_USER'),
('ROLE_ADMIN');

insert into scooter (cost,description,expiration_date,name)
values (20,'1984 tells the futuristic story of a dystopian, totalitarian world where free will and love are forbidden','2012-11-12','1984 by George Orwell');

insert into scooter (cost,description,expiration_date,name)
values (30,'Tolkien’s fantasy epic is one of the top must-read books out there','2010-10-22','The Lord of the Rings by J.R.R. Tolkein');

insert into scooter (cost,description,expiration_date,name)
values (35,'The Kite Runner is a moving story of an unlikely friendship between a wealthy boy and the son of his father’s servant','2010-10-22','The Kite Runner by Khaled Hosseini');

insert into scooter (cost,description,expiration_date,name)
values (35,'This global bestseller took the world by storm','2011-09-25','Harry Potter and the Philosopher’s Stone by J.K. Rowling');

insert into scooter (cost,description,expiration_date,name)
values (25,'Slaughterhouse-Five is arguably one of the greatest anti-war books ever written','2015-05-12','Slaughterhouse-Five by Kurt Vonnegut');

delete from user_rent where id=3;

delete from scooter where id=1;


insert into user (email,login,password,role_id)
values ('bbb@mail.com','admin','admin','2');

insert into user (email,login,password,role_id)
values ('ada@mail.com','login1','login1','1');