package com.example.Users.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.example.Users.Interface.UsersTask;
import com.example.Users.Repository.UsersRepository;
import com.example.Users.Service.UserTaskService;
import com.example.Users.entity.Status;
import com.example.Users.entity.UsersTaskEntity;

@Service
public class DataMapper {

	@Autowired
	private UsersRepository repository;
	
	public Context setData(int id)
	{
		List<UsersTask>   task=this.repository.findByid(id, UsersTask.class);
		
		
		Context context=new Context();
		Map<String , Object> data=new HashMap<>(); 
		
		
		String name = null;
		String tasks=null;
		Status status=null;
		ArrayList<String> allTask=new ArrayList<>();
		ArrayList<Status> statusTask=new ArrayList<>();
		for(UsersTask newTask:task)
		{
			name=newTask.getName();
			tasks=newTask.getTask();
			status=newTask.getStatus();
			allTask.add(tasks);
			statusTask.add(status);
			
		}
		data.put("name", name);
		Object forEachTask[]=allTask.toArray();
		String eachTask[]=new String [forEachTask.length];
	
		Object statusForTask[]=statusTask.toArray();
		Status[] statusOfTask=new Status [statusForTask.length];
		
		for(int i=0;i<forEachTask.length;i++)
		{
			eachTask[i]=(String) forEachTask[i];
		}

		for(int i=0;i<statusForTask.length;i++)
		{
			statusOfTask[i]=(Status) statusForTask[i];
		}
		
		String userTask=null;
		String statusof=null;
		for(int i=0,j=0;i<eachTask.length||j<statusOfTask.length;i++,j++)
		{
			 userTask=eachTask[i];
			 statusof =" "+statusOfTask[i];
			
			
		}
		data.put("task", userTask);
		data.put("status", statusof);
		
		context.setVariables(data);
		
		return context;
			
	
	
	}
	
	
}
