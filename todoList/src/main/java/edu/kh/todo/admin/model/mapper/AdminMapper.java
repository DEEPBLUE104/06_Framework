package edu.kh.todo.admin.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.todo.model.dto.Todo;

@Mapper
public interface AdminMapper {

	List<Todo> selectTodoList();

	int addTodo(Todo todo);

	int YNhandler(Todo todo);

	int deleteTodo(Todo todo);
	
	
}
