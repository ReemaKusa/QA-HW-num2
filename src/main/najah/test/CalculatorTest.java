package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

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
        assertEquals(5, calculator.add(2, 3));
        assertEquals(0, calculator.add(-1, 1));
    }

    @ParameterizedTest
    @CsvSource({"1,2,3", "2,3,5", "-1,-1,-2"})
    @DisplayName("Parameterized addition test")
    void testParameterizedAddition(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @DisplayName("Addition should complete within timeout")
    void testAdditionTimeout() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    @DisplayName("Division should complete within timeout")
    void testDivisionTimeout() {
        assertEquals(2, calculator.divide(4, 2));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @DisplayName("Factorial should complete within timeout")
    void testFactorialTimeout() {
        assertEquals(120, calculator.factorial(5)); // 5! = 120
    }


    @Test
    @Disabled("This test fails intentionally. Fix it by changing expected to 5.")
    @DisplayName("Intentional failure test")
    void testFail() {
        assertEquals(4, calculator.add(2, 3)); // Fix: Change 4 to 5
    }

    @Test
    @Order(2)
    void testDivision() {
        assertEquals(2, calculator.divide(4, 2));
    }
}
