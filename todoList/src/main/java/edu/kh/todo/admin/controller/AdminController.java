package edu.kh.todo.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.todo.admin.model.AdminService;
import edu.kh.todo.model.dto.Todo;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("admin")
@Slf4j
public class AdminController {

	@Autowired
	private AdminService service;

	/** todoList 목록 조회
	 * @return
	 */
	@GetMapping("selectTodoList")
	public ResponseEntity<Object> selectTodoList() {
		try {
			List<Todo> todoList = service.selectTodoList();

			return ResponseEntity.status(HttpStatus.OK).body(todoList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@PostMapping("addTodoList")
	public ResponseEntity<Object> addTodoList(@RequestBody Todo todo){
	    try {
	    
	        int result = service.addTodo(todo);

	        return ResponseEntity.status(HttpStatus.CREATED).body(result);
	    } catch(Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("서버 오류: " + e.getMessage());
	    }
	}
	
	@PutMapping("YNhandler")
	public ResponseEntity<Object> YNhandler(@RequestBody Todo todo){
		try {
			
			int result = service.YNhandler(todo);
			
			return ResponseEntity.status(HttpStatus.OK).body(result);
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@DeleteMapping("deleteTodo")
	public ResponseEntity<Object> deleteTodo(@RequestBody Todo todo) {
		try {
			int result = service.deleteTodo(todo);
			System.out.println(todo);
			
			return ResponseEntity.status(HttpStatus.OK).body(result);
			
		} catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			
		}
		
	}
}
