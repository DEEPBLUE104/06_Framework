package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

	/** 게시판 종류 조회 서비스(Intercertor)
	 * @return
	 */
	List<Map<String, Object>> selectBoardTypeList();

}
