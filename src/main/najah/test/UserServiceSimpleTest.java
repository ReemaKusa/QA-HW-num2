package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import main.najah.code.UserService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("UserService Tests")
class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        System.out.println("Setup complete.");
    }

    @Test
    @DisplayName("Valid email test")
    void testValidEmail() {
        assertTrue(userService.isValidEmail("test@example.com"));
        assertFalse(userService.isValidEmail("invalid-email"));
    }

    @Test
    @DisplayName("Authentication test")
    void testAuthentication() {
        assertTrue(userService.authenticate("admin", "1234"));
        assertFalse(userService.authenticate("user", "password"));
    }

    @Test
    @Timeout(1)
    @DisplayName("Authentication should complete within timeout")
    void testAuthenticationTimeout() {
        assertTrue(userService.authenticate("admin", "1234"));
    }

    @Test
    @Timeout(1)
    @DisplayName("Email validation should complete within timeout")
    void testEmailValidationTimeout() {
        assertTrue(userService.isValidEmail("user@example.com"));
    }

    @Test
    @Timeout(1)
    @DisplayName("Invalid email check should complete within timeout")
    void testInvalidEmailTimeout() {
        assertFalse(userService.isValidEmail("no-at-symbol"));
    }
    
    @ParameterizedTest
    @CsvSource({
        "test@example.com,true",
        "invalid-email,false",
        "user@site,false"
    })
    @DisplayName("Parameterized email validation test")
    void testEmailValidation(String email, boolean expected) {
        assertEquals(expected, userService.isValidEmail(email));
    }


    @Test
    @Disabled("This test fails intentionally. Fix it by using the correct password '1234'.")
    @DisplayName("Intentional failure test")
    void testFail() {
        assertTrue(userService.authenticate("admin", "wrongPassword")); // Fix: Change to '1234'
    }

    
}
