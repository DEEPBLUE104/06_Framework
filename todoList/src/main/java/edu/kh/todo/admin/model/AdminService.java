package edu.kh.todo.admin.model;

import java.util.List;

import edu.kh.todo.model.dto.Todo;

public interface AdminService {

	List<Todo> selectTodoList();

	int addTodo(Todo todo);

	int YNhandler(Todo todo);

	int deleteTodo(Todo todo);

}