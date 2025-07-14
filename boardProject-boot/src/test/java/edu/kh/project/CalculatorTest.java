package edu.kh.project;
// JUnit 테스트 코드는 반드시 src/test/java 경로에 위치해야함

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.kh.project.test.Calculator;

// src/main/java : 실제 애플리케이션 코드
// src/test/java : 테스트 코드 (JUnit 포함됨)

public class CalculatorTest {

	private static Calculator calculator;

	// @BeforeAll, @AfterAll - static 메서드에서 사용해야 함!
	@BeforeAll
	static void initALL() {
		// 모든 테스트 실행 전에 1번만 실행
		calculator = new Calculator();
		System.out.println("@BeforeAll - 테스트 시작 전 초기화");

	}

	@AfterAll
	static void end() {
		// 모든 테스트가 끝난 뒤 1번만 실행
		System.out.println("@AfterAll - 모든 테스트 완료");
	}

	@Test // 테스트 메서드 표시
	void testAdd() {
		System.out.println("testAdd 실행");
		assertEquals(9, calculator.add(2, 7), "2 + 7은 9이어야 합니다.");
		// assertEquals(expected, actual) : 두 값이 같은가?

	}

	@Test // 테스트 메서드 표시
	void testSubtract() {
		System.out.println("testAdd 실행");
		assertEquals(3, calculator.subtract(10, 7), "10 - 7은 3이어야 합니다.");
		// assertEquals(expected, actual) : 두 값이 같은가?

	}

	@Test // 테스트 메서드 표시
	void testmultiply() {
		System.out.println("testAdd 실행");
		assertEquals(16, calculator.multiply(8, 2), "8 * 2은 16이어야 합니다.");
		// assertEquals(expected, actual) : 두 값이 같은가?

	}

	@Test // 테스트 메서드 표시
	void testdivide() {
		System.out.println("testAdd 실행");
		assertEquals(7, calculator.divide(49, 7), "49 / 7은 7이어야 합니다.");
		// assertEquals(expected, actual) : 두 값이 같은가?

	}

	@Test
	void testTrueFalse() {
		int result = calculator.add(2, 2);
		assertTrue(result == 4, "결과가 4 이어야 합니다");
		assertFalse(result == 5, "결과가 5면 안 됩니다.");
	}
	
	@Test
	void testDivideByZero() {
		System.out.println("testDivideByZero 실행");
		
		// assertThrows(Exception.class, executable) : 예외 발생 여부
	}
	
}
