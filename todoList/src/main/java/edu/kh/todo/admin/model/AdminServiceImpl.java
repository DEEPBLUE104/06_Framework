package edu.kh.todo.admin.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.todo.admin.model.mapper.AdminMapper;
import edu.kh.todo.model.dto.Todo;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper mapper;

	@Override
	public List<Todo> selectTodoList() {
		return mapper.selectTodoList();
	}

	@Override
	public int addTodo(Todo todo) {
		return mapper.addTodo(todo);
	}

	@Override
	public int YNhandler(Todo todo) {
		return mapper.YNhandler(todo);
	}

	@Override
	public int deleteTodo(Todo todo) {
		return mapper.deleteTodo(todo);
	}
}
