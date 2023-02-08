package com.example.Users.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Users.Interface.UsersPermission;
import com.example.Users.Interface.UsersTask;
import com.example.Users.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

	Users findByEmailIgnoreCase(String email);

	@Query(value = "select u.name , u.id , r.role , p.permissions from users u\r\n"
			+ "join user_role_entity ure on u.id=ure.users_id\r\n" + "join roles r on r.id= ure.roles_id\r\n"
			+ "join role_permission_entity rpe on r.id= rpe.roles1_id\r\n"
			+ "join permissions p on p.id= rpe.permissions_id where u.id= :idnumber", nativeQuery = true)
	List<UsersPermission> findById(@Param("idnumber") int id, Class<UsersPermission> class1);

//	List<Users> findById(int id);

	@Query(value = "select u.name , u.id, t.task, ute.status from users u \r\n"
			+ "join users_task_entity ute on u.id= ute.user_id\r\n"
			+ "join tasks t on ute.tasks_id=t.id  where u.id= :numberid", nativeQuery = true)
	List<UsersTask> findByid(@Param("numberid") int id, Class<UsersTask> class1);
}
