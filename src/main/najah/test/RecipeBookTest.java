package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@Execution(ExecutionMode.CONCURRENT) // Parallel execution enabled
@DisplayName("RecipeBook Tests")
class RecipeBookTest {
    private RecipeBook recipeBook;
    private Recipe recipe;

    @BeforeEach
    void setUp() {
        recipeBook = new RecipeBook();
        recipe = new Recipe();
        recipe.setName("Coffee");
        System.out.println("Setup complete.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tear down complete.");
    }

    @Test
    @DisplayName("Adding a recipe succeeds")
    void testAddRecipe() {
        assertTrue(recipeBook.addRecipe(recipe));
        assertFalse(recipeBook.addRecipe(recipe)); // Same recipe can't be added again
    }

    @Test
    @DisplayName("Deleting a recipe works correctly")
    void testDeleteRecipe() {
        recipeBook.addRecipe(recipe);
        assertEquals("Coffee", recipeBook.deleteRecipe(0));
        assertNull(recipeBook.deleteRecipe(0)); // This is correct since second deletion should return null
    }
    
    @ParameterizedTest
    @CsvSource({
        "Coffee,true",
        "Coffee,false"  // Adding same recipe again should return false
    })
    @DisplayName("Parameterized add recipe test")
    void testAddRecipeParameterized(String name, boolean expected) {
        Recipe r = new Recipe();
        r.setName(name);
        assertEquals(expected, recipeBook.addRecipe(r));
    }


    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @DisplayName("Recipe operations should complete within timeout")
    void testRecipeOperationsTimeout() {
        recipeBook.addRecipe(recipe);
        assertNotNull(recipeBook.getRecipes()[0]);  // Access the recipe at index 0
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @DisplayName("Retrieving a recipe should complete within timeout")
    void testRetrieveRecipeTimeout() {
        recipeBook.addRecipe(recipe);
        assertEquals("Coffee", recipeBook.getRecipes()[0].getName());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @DisplayName("Recipe deletion should complete within timeout")
    void testRecipeDeletionTimeout() {
        recipeBook.addRecipe(recipe);
        assertEquals("Coffee", recipeBook.deleteRecipe(0));
    }

    @Test
    @Disabled("This test fails intentionally. Fix it by changing expected to 'Coffee'")
    @DisplayName("Intentional failure test")
    void testFail() {
        recipeBook.addRecipe(recipe);
        assertEquals("Tea", recipeBook.getRecipes()[0].getName()); // Fix: change 'Tea' to 'Coffee'
    }
    

    @BeforeAll
    static void initAll() {
        System.out.println("Starting RecipeBook tests...");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Completed all RecipeBook tests.");
    }
}
