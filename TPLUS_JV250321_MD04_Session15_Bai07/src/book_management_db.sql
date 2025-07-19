create database book_management_db;
use book_management_db;

create table books
(
    id             int auto_increment primary key,
    title          varchar(255)   not null,
    author         varchar(255)   not null,
    published_year year           not null,
    price          decimal(10, 2) not null
);

delimiter //
create procedure add_book(
    in_title varchar(255),
    in_author varchar(255),
    in_published_year year,
    in_price decimal(10, 2)
)
begin
    insert into books(title, author, published_year, price)
    values (in_title, in_author, in_published_year, in_price);
end;
delimiter //

delimiter //
create procedure find_book_by_id(in_id int)
begin
    select b.id, b.title, b.author, b.published_year, b.price
    from books b
    where b.id = in_id;
end;
delimiter //

delimiter //
create procedure update_book(
    in_id int,
    in_title varchar(255),
    in_author varchar(255),
    in_published_year year,
    in_price decimal(10, 2)
)
begin
    update books b
    set b.price          = in_price,
        b.published_year = in_published_year,
        b.author         = in_author,
        b.title          = in_title
    where b.id = in_id;
end //
delimiter ;

delimiter //
create procedure delete_book(in_id int)
begin
    delete from books where books.id = in_id;
end //
delimiter ;

delimiter //
create procedure find_books_by_author(in_author varchar(255))
begin
    select id, title, author, published_year, price
    from books b
    where b.author like concat('%', in_author, '%');
end //
delimiter ;

delimiter //
create procedure show_all_books()
begin
    select id, title, author, published_year, price from books b;
end //
delimiter ;

delimiter //
create procedure find_books_by_title_and_author(
    in_title varchar(255),
    in_author varchar(255)
)
begin
    select id, title, author, published_year, price
    from books b
    where b.title like concat('%', in_title, '%')
      and b.author like concat('%', in_author, '%');
end //
delimiter ;