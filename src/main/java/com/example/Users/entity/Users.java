package com.example.Users.entity;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@SQLDelete(sql = "update users set is_active=false where id=?")
@Where(clause = "is_active=true")
public class Users implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	private String add;

	private String city;

	@Column(unique = true)
	private String email;

	private String password;

	@CreationTimestamp
	private Timestamp Creationtime;

	@UpdateTimestamp
	private Timestamp updationtime;

	private boolean is_active = true;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	private List<UsersTaskEntity> task;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	@JsonIgnore
	private List<UserRoleEntity> role;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users1")
	@JsonIgnore
	private List<QueryPortal> qury;
	
	
	//List<Contect> contects;

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public List<UserRoleEntity> getRole() {
		return role;
	}

	public void setRole(List<UserRoleEntity> role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreationtime() {
		return Creationtime;
	}

	public void setCreationtime(Timestamp creationtime) {
		Creationtime = creationtime;
	}

	public Users(int id, String name, String add, String city, String email, String password, Timestamp creationtime,
			Timestamp updationtime, boolean is_active, List<UsersTaskEntity> task, List<UserRoleEntity> role) {
		super();
		this.id = id;
		this.name = name;
		this.add = add;
		this.city = city;
		this.email = email;
		this.password = password;
		this.Creationtime = creationtime;
		this.updationtime = updationtime;
		this.is_active = is_active;
		this.task = task;
		this.role = role;
	}

	public Timestamp getUpdationtime() {
		return updationtime;
	}

	public void setUpdationtime(Timestamp updationtime) {
		this.updationtime = updationtime;
	}

	public List<UsersTaskEntity> getTask() {
		return task;
	}

	public void setTask(List<UsersTaskEntity> task) {
		this.task = task;
	}

	public Users(int id, String name, String add, String city, String email, String password, Timestamp creationtime,
			Timestamp updationtime, List<UsersTaskEntity> task) {
		super();
		this.id = id;
		this.name = name;
		this.add = add;
		this.city = city;
		this.email = email;
		this.password = password;
		this.Creationtime = creationtime;
		this.updationtime = updationtime;
		this.task = task;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generate method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

//	@Override
//	public String toString() {
//		return "Users [name=" + name + ", add=" + add + ", city=" + city + ", email=" + email + ", password=" + password
//				  + "]";
//	}

	@Override
    public String toString() {
        return "{\"id\":" + id + ",\"name\":\"" + name + "\",\"email\":\"" + email + "\",\"password\":\"" + password
                + "\"}";
    }
	
	
	
	
	
	
	

}
