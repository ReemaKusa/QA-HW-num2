package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

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
        assertNull(recipeBook.deleteRecipe(0), "The recipe should be null after deletion.");
    }

    @Test
    @Timeout(1)
    @DisplayName("Recipe operations should complete within timeout")
    void testRecipeOperationsTimeout() {
        recipeBook.addRecipe(recipe);
        assertNotNull(recipeBook.getRecipes()[0]);  // Access the recipe at index 0
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
