package edu.kh.project.myPage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.service.MyPageService;

/*
 * @SessionAttributes 의 역할
 * - Model에 추가된 속성 중 key값이 일치하는 속성을 session scope로 변경
 * - SessionStatus 이용 시 session에 등록된 완료할 대상을 찾는 용도
 * 
 * @SessionAttribute 의 역할 (매개변수에 쓰는 것)
 * - Session에 존재하는 값을 key로 얻어오는 역할
 * */

@Controller
@RequestMapping("myPage")
public class MyPageController {

	@Autowired
	private MyPageService service;

	@GetMapping("info") // /myPage/info GET 방식 요청 매핑
	public String info(@SessionAttribute("loginMember") Member loginMember, Model model) {

		// 현재 로그인한 회원의 주소를 꺼내옴
		// 현재 로그인한 회원 정보 -> session에 등록된 상태(loginMember)

		String memberAddress = loginMember.getMemberAddress();
		// 13536^^^경기 성남시 분당구 판교역로 4^^^2555번지
		// 주소가 없다면 null

		// 주소가 있을 경우에만 동작
		if (memberAddress != null) {

			// 구분자 "^^^"를 기준으로
			// memberAddress 값을 쪼개어 String[]로 반환
			String[] arr = memberAddress.split("\\^\\^\\^");
			// -> 13536^^^경기 성남시 분당구 판교역로 4^^^2555번지
			// -> ['13536', '경기 성남시 분당구 판교역로 4', '2555번지']
			// 0번 인덱스 1번 인덱스 2번 인덱스

			model.addAttribute("postcode", arr[0]);
			model.addAttribute("address", arr[1]);
			model.addAttribute("detailAddress", arr[2]);

		}

		return "myPage/myPage-info";
	}

	// 프로필 이미지 변경 화면 이동
	@GetMapping("profile") // /myPage/profile GET 요청 매핑
	public String profile() {

		return "myPage/myPage-profile";
	}
	
	// 비밀번호 변경 화면 이동
	@GetMapping("changePw") // /myPage/changePw GET 요청 매핑
	public String changePw() {

		return "myPage/myPage-changePw";
	}
	
	// 회원 탈퇴 화면 이동
	@GetMapping("secession") // /myPage/secession GET 요청 매핑
	public String secession() {

		return "myPage/myPage-secession";
	}
	
	// 파일 업로드 테스트 화면 이동
	@GetMapping("fileTest") // /myPage/fileTest GET 요청 매핑
	public String fileTest() {

		return "myPage/myPage-fileTest";
	}
	
	/** 회원 정보 수정
	 * @param inputMember : (@ModelAttribute가 생략된 상태)
	 * 						제출된 수정된 회원 닉네임, 전화번호, 주소
	 * @param loginMember : 로그인한 회원 정보 (회원 번호 사용할 예정)
	 * @param memberAddress : 주소만 따로 받은 String[] 구분자 ^^^ 변경 예정
	 * @param ra
	 * @return
	 */
	@PostMapping("info")
	public String updateInfo(Member inputMember,
							@SessionAttribute("loginMember") Member loginMember,
							@RequestParam("memberAddress") String[] memberAddress,
							RedirectAttributes ra) {
		
		String message = null;
		
		// inputMember에 로그인한 회원 번호 추가
		inputMember.setMemberNo(loginMember.getMemberNo());
		// inputMember : 회원 번호, 회원 닉네임, 전화번호, 주소
		
		// 회원 정보 수정 서비스 호출
		int result = service.updateInfo(inputMember, memberAddress);
		
		if(result > 0) { // 회원 정보 수정 성공
			
			// loginMember 새로 세팅
			// 우리가 방금 바꾼 값으로 세팅
			
			// -> loginMember를 수정하면
			//    세션에 저장된 로그인한 회원 정보가 수정된다.
			// == 세션 데이터와 DB 데이터를 동기화
			
			loginMember.setMemberNickname(inputMember.getMemberNickname());
			loginMember.setMemberTel(inputMember.getMemberTel());
			loginMember.setMemberAddress(inputMember.getMemberAddress());
			
			message = "회원 정보 수정 성공!";
			
		} else {
			
			message = "회원 정보 수정 실패..";
		}
		
		ra.addFlashAttribute("message", message);
		return "redirect:info";
	}

}
