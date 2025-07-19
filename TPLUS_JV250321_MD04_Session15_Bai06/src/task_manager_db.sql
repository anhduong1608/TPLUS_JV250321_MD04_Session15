create database task_manager_db;
use task_manager_db;

create table tasks
(
    task_id   int auto_increment primary key,
    task_name varchar(100),
    status    boolean
);

delimiter //
create procedure get_all_tasks()
begin
    select t.task_id, t.task_name, t.status
    from tasks t;
end;
delimiter //

delimiter //
create procedure add_task(
    in_name varchar(100),
    in_status boolean
)
begin
    insert into tasks (task_name, status)
    values (in_name, in_status);
end;
delimiter //

delimiter //
create procedure update_task_status(
    in_id int,
    in_status varchar(100)
)
begin
    update tasks
    set status = in_status
    where task_id = in_id;
end;
delimiter //

delimiter //
create procedure delete_task(in_id int)
begin
    delete from tasks where task_id = in_id;
end;
delimiter //

delimiter //
create procedure search_task_by_id(in_id int)
begin
    select t.task_id, t.task_name, t.status
    from tasks t
    where task_id = in_id;
end;
delimiter //

delimiter //
create procedure search_task_by_name(in_name varchar(100))
begin
    select t.task_id, t.task_name, t.status
    from tasks t
    where task_name like concat('%', in_name,'%');
end;
delimiter //

delimiter //
create procedure task_statistic()
begin
    select t.status, count(t.task_id) as 'count_task'
    from tasks t
    group by t.status;
end;
delimiter //