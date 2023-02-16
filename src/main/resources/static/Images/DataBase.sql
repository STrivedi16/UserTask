select *  from users

select * from users_task_entity

select * from tasks

select * from user_task_rating_entity

select u.name , t.task, ut.status ,ute.message, ute.ratedby, ute.rating from users u 
join users_task_entity ut on u.id=ut.user_id
join tasks t on ut.tasks_id=t.id
join user_task_rating_entity ute on ut.id=ute.usertask_id