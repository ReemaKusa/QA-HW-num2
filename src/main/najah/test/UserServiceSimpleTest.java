package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import main.najah.code.UserService;

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
}
