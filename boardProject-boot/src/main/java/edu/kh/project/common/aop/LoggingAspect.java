package edu.kh.project.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import edu.kh.project.member.model.dto.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
	
	/** 컨트롤러 수행 전 로그 출력(클래스/메서드/ip)
	 * @param jp
	 */
	@Before("PointcutBundle.controllerPointcut()")
	public void beforeController(JoinPoint jp) {
		
		// 클래스명 얻어오기
		String className = jp.getTarget().getClass().getSimpleName();
		
		// 메서드명 얻어오기
		String methodName = jp.getSignature().getName();
		
		// 요청한 클라이언트의 HttpServletRequest 객체 얻어오기
		HttpServletRequest req =
		((ServletRequestAttributes)RequestContextHolder
				.currentRequestAttributes()).getRequest();
		
		// 클라이언트 ip 얻어오기
		String ip = getRemoteAddr(req);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("[%s.%s] 요청 / ip : %s",className,methodName,ip));
		// [MainController.mainPage] 요청 / ip : 159.30.23.19
		
		// 로그인 상태인 경우
		if(req.getSession().getAttribute("loginMember") != null ) {
			
			String memberEmail = ((Member)req.getSession().getAttribute("loginMember"))
								  .getMemberEmail();
			
			sb.append(String.format(",요청 회원 : %s", memberEmail));
		}
		
		log.info(sb.toString());

	}
	
	/**
	 * 접속자 IP 얻어오는 메서드
	 *
	 * @param request
	 * @return ip
	 */
	private String getRemoteAddr(HttpServletRequest request) {
		String ip = null;
		ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-RealIP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}




	
	
}
