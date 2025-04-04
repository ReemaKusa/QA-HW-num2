package main.najah.test;

import org.junit.platform.suite.api.*;
import org.junit.jupiter.api.DisplayName;

@Suite
@SelectClasses({CalculatorTest.class, RecipeBookTest.class, ProductTest.class, UserServiceTest.class})
@DisplayName("All Tests Suite")

class AllTests {
}