package edu.kh.project.board.model.dto;

public class Board {
	// BOARD 테이블 컬럼
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriteDate;
	private String boardUpdateDate;
	private int readCount;
	private String boardDelFl;
	private int memberNo;
	private int boardCode;
	
	// MEMBER 테이블 조인
	private String memberNickname;
	
	// 목록 조회 시 상관쿼리 결과
	private int commentCount;  // 댓글 수
	private int likeCount;    // 좋아요 수
}
