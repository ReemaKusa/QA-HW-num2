package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Timeout;

import main.najah.code.Calculator;

@DisplayName("Calculator Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CalculatorTest {

    private Calculator calculator;

    @BeforeAll
    static void initAll() {
        System.out.println("Starting Calculator tests...");
    }

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        System.out.println("Setup complete.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tear down complete.");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Completed all Calculator tests.");
    }

	@Test
	@DisplayName("Valid addition works correctly")
	@Order(1)
	void testValidAddition() {
		// Valid addition tests
		assertEquals(5, calculator.add(2, 3));
		assertEquals(0, calculator.add(-1, 1));
	}
	
	@Test
	@DisplayName("Invalid addition input throws exception")
	void testInvalidAddition() {
	    assertThrows(IllegalArgumentException.class, () -> calculator.add(0, 0)); // Adjust as per the method logic 
	    }

	
	@ParameterizedTest
	@CsvSource({"1,2,3", "2,3,5", "-1,-1,-2"})
	@DisplayName("Parameterized addition test")
	void testParameterizedAddition(int a, int b, int expected) {
		assertEquals(expected, calculator.add(a, b));
	}
	
	@Test
	@Timeout(1)
	@DisplayName("Addition should complete within timeout")
	void testAdditionTimeout() {
		assertEquals(5, calculator.add(2, 3));
	}
	
	@Test
	@Disabled("This test fails intentionally. Fix it by correcting expected output.")
	@DisplayName("Intentional failure test")
	void testFail() {
	    assertEquals(4, calculator.add(2, 3)); // Change this to reflect the correct expected value
	}

	
	@Test
	@Order(2)
	void testDivision() {
		assertEquals(2, calculator.divide(4, 2));
	}
}	