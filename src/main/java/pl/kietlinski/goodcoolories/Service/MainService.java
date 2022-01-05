package pl.kietlinski.goodcoolories.Service;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.kietlinski.goodcoolories.Entity.*;
import pl.kietlinski.goodcoolories.Repository.*;

import java.util.Set;

@Service
@Data
public class MainService {

    private RecipeRepository recipeRepository;
    private IngredientRecipeRepository ingredientRecipeRepository;
    private IngredientRepository ingredientRepository;
    private DishRepository dishRepository;
    private OrderRepository orderRepository;
    private DietRepository dietRepository;

    @Autowired
    public MainService(RecipeRepository recipeRepository, IngredientRecipeRepository ingredientRecipeRepository, IngredientRepository ingredientRepository, DishRepository dishRepository, OrderRepository orderRepository, DietRepository dietRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRecipeRepository = ingredientRecipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.dishRepository = dishRepository;
        this.orderRepository = orderRepository;
        this.dietRepository = dietRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initDatabase() {
        Order order1 = new OrderBuilder()
                .setName("Jan Kowal").setEaddress("j.k@wp.pl").setPhone("111111111").setStreet("Kowalska 1")
                .setZip("11-111").setCity("Kowalów").setAge(25).setHeight(187).setSex("Mężczyzna")
                .setActivity("0-2").setDietWish("Schudnąć").setDishCount(5)
                .setComment("Nie lubie salaty i pomidora")
                .setStatus("Zakończony")
                .createOrder();
        Order order2 = new OrderBuilder()
                .setName("Ala Lis").setEaddress("a.l@wp.pl").setPhone("222222222").setStreet("Lisia 2")
                .setZip("22-222").setCity("Lisów").setAge(45).setHeight(162).setSex("Kobieta")
                .setActivity("3-5").setDietWish("Utrzymać").setDishCount(4)
                .setComment("Nie lubie twarogu i ryżu")
                .setStatus("Nowy")
                .createOrder();
        Order order3 = new OrderBuilder()
                .setName("Kamil Ul").setEaddress("k.u@wp.pl").setPhone("333333333").setStreet("Ulowa 3")
                .setZip("33-333").setCity("Ulów").setAge(18).setHeight(173).setSex("Mężczyzna")
                .setActivity("7").setDietWish("Przytyć").setDishCount(5)
                .setComment("Nie lubie makaronu i sera żółtego")
                .setStatus("Nowy")
                .createOrder();

        Diet diet1 = new Diet("111");
        Diet diet2 = new Diet("222");
        Diet diet3 = new Diet("333");

        order1.setStatus("Zakończony");
        order1.setDiet(diet1);
        order2.setDiet(diet2);
        order3.setDiet(diet3);

        Dish dish1 = new Dish("Sałatka z tuńczykiem", "https://cdn.aniagotuje.com/pictures/articles/2021/10/20278230-v-1500x1500.jpg");
        Dish dish2 = new Dish("Makaron z piersią z kurczaka", "https://www.kwestiasmaku.com/sites/v123.kwestiasmaku.com/files/makaron_z_kurczakiem_szpinakiem_w_sosie_curry_01.jpg");
        Dish dish3 = new Dish("Filet z Dorsza z ziemniakami i surówką", "http:\\ryba");
        Dish dish4 = new Dish("Pierogi ruskie", "http:\\ryba");
        Dish dish5 = new Dish("Zupa meksykańska", "https://d3iamf8ydd24h9.cloudfront.net/pictures/articles/2019/09/1237586-v-1500x1500.jpg");
        Dish dish6 = new Dish("Zupa pomidorowa z grzankami", "http:\\ryba");
        Dish dish7 = new Dish("Szaszłyk wieprzowy z warzywami", "http:\\ryba");
        Dish dish8 = new Dish("Płatki owsiane z orzechami", "http:\\ryba");
        Dish dish9 = new Dish("Kasza manna z dżemem malinowym", "https://cdn.aniagotuje.com/pictures/articles/2019/11/1490094-v-720x935.jpg");
        Dish dish10 = new Dish("Kanapki z twarożkiem i szczypiorkiem", "http:\\ryba");

        diet1.setDishSet(Set.of(dish9, dish1, dish2, dish5));
        diet2.setDishSet(Set.of(dish2));
        diet3.setDishSet(Set.of(dish3));

        dish1.setDietSet(Set.of(diet1));
        dish2.setDietSet(Set.of(diet1, diet2));
        dish3.setDietSet(Set.of(diet3));
        dish5.setDietSet(Set.of(diet1));
        dish9.setDietSet(Set.of(diet1));

        Recipe recipe1 = new Recipe("łatwy", 35,
        "Ryż ugotować w osolonej wodzie. (Ryż powinien być ugotowany na sypko, nie rozgotowany). Następnie dobrze odcedzić na sitku i pozostawić do ostygnięcia.\n" +
                "Jajka ugotować na twardo, ostudzić, obrać ze skorupek i pokroić w kostkę.\n" +
                "Tuńczyka i kukurydzę odsączyć.\n" +
                "Ryż przełożyć do miski. Dodać odsączonego tuńczyka, kukurydzę i jajka. Dodać posiekaną zieloną cebulkę lub szczypiorek i majonezu do smaku (ok. 4 łyżek). Doprawić solą i pieprzem.\n" +
                "Sałatkę można podawać od razu. Można ją również schłodzić w lodówce.");
        Recipe recipe2 = new Recipe("trudny", 40,
                "Ugotować makaron w osolonej wodzie. Na większej patelni na oliwie poddusić pokrojoną w kosteczkę cebulę, dodać pokrojoną w kosteczkę pierś kurczaka i obsmażyć na większym ogniu.\n" +
                        "Pod koniec dodać starty czosnek, doprawić solą, pieprzem, oregano oraz chili.\n" +
                        "Doprawić solą, pieprzem oraz łyżeczką cukru. Gotować na średnim ogniu przez ok. 10 minut.\n" +
                        "Dodać śmietankę oraz kilka posiekanych liści bazylii, wymieszać i zagotować delikatnie mieszając. Podawać z makaronem. Można posypać parmezanem, ale niekoniecznie.");
        Recipe recipe3 = new Recipe("łatwy", 10, "Przepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybe");
        Recipe recipe4 = new Recipe("łatwy", 10, "Przepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybe");
        Recipe recipe5 = new Recipe("średni", 60,
                "W szerokim garnku na łyżce oliwy zeszklić pokrojoną w kostkę cebulę, w międzyczasie dodać starte ząbki czosnku. Przesunąć na bok, wlać drugą łyżkę oliwy i mieszając podsmażyć mielone mięso. Wsypać przyprawy i dokładnie wymieszać.\n" +
                        "Dodać pokrojoną w półplasterki marchewkę oraz szklankę gorącego bulionu. Przykryć i dusić na małym ogniu 20 min.\n" +
                        "Doprawić solą, pieprzem i szczyptą cukru. Wymieszać i gotować bez przykrycia 5 min.\n" +
                        "Dodać resztę gorącego bulionu oraz odcedzoną kukurydzę i fasolę z puszki. Gotować na małym ogniu z lekko uchyloną pokrywką ok. 25 minut.");
        Recipe recipe6 = new Recipe("łatwy", 10, "Przepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybe");
        Recipe recipe7 = new Recipe("łatwy", 10, "Przepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybe");
        Recipe recipe8 = new Recipe("łatwy", 10, "Przepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybe");
        Recipe recipe9 = new Recipe("łatwy", 10,
                "Z 300 ml mleka odlać do szklanki około 100 ml, wymieszać z kaszą manną. Pozostałe mleko " +
                        "zagotować dodając cukier i cukier waniliowy. Gdy mleko się zagotuje dolać część mleka " +
                        "wymieszaną z kaszą i gotować na małym ogniu często mieszając. Kasza stopniowo będzie gęstnieć, " +
                        "po około 5-10 minutach powinna uzyskać odpowiednią konsystencję. Nałożyć do miseczki, przybrać " +
                        "dżemem malinowym.");
        Recipe recipe10 = new Recipe("łatwy", 10, "");

        recipe1.setDish(dish1);
        recipe2.setDish(dish2);
        recipe3.setDish(dish3);
        recipe4.setDish(dish4);
        recipe5.setDish(dish5);
        recipe6.setDish(dish6);
        recipe7.setDish(dish7);
        recipe8.setDish(dish8);
        recipe9.setDish(dish9);
        recipe10.setDish(dish10);

        // Kasza manna
        IngredientRecipe ingredientRecipe1 = new IngredientRecipe(3);
        IngredientRecipe ingredientRecipe2 = new IngredientRecipe(0.375);
        IngredientRecipe ingredientRecipe3 = new IngredientRecipe(0.375);
        IngredientRecipe ingredientRecipe4 = new IngredientRecipe(0.5);

        // Kasza manna
        Ingredient ingredient1 = new Ingredient("Mleko", 40, 3.4, 0.5, 5);
        Ingredient ingredient2 = new Ingredient("Kasza manna", 359, 13, 1.1, 73);
        Ingredient ingredient3 = new Ingredient("Cukier", 386, 0, 0, 100);
        Ingredient ingredient4 = new Ingredient("Dżem malinowy", 139, 1, 0, 33);
//        Ingredient ingredient5 = new Ingredient("Ryż", 130, 2.7, 0.3, 28);
//        Ingredient ingredient6 = new Ingredient("Tuńczyk", 131, 10, 10, 10);

        // Kasza manna
        ingredientRecipe1.setRecipe(recipe9);
        ingredientRecipe1.setIngredient(ingredient1);
        ingredientRecipe2.setRecipe(recipe9);
        ingredientRecipe2.setIngredient(ingredient2);
        ingredientRecipe3.setRecipe(recipe9);
        ingredientRecipe3.setIngredient(ingredient3);
        ingredientRecipe4.setRecipe(recipe9);
        ingredientRecipe4.setIngredient(ingredient4);

        // Zapis do bazy danych
        dishRepository.save(dish1);
        dishRepository.save(dish2);
        dishRepository.save(dish3);
        dishRepository.save(dish4);
        dishRepository.save(dish5);
        dishRepository.save(dish6);
        dishRepository.save(dish7);
        dishRepository.save(dish8);
        dishRepository.save(dish9);
        dishRepository.save(dish10);

        dietRepository.save(diet1);
        dietRepository.save(diet2);
        dietRepository.save(diet3);

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        ingredientRepository.save(ingredient1);
        ingredientRepository.save(ingredient2);
        ingredientRepository.save(ingredient3);
        ingredientRepository.save(ingredient4);

        recipeRepository.save(recipe1);
        recipeRepository.save(recipe2);
        recipeRepository.save(recipe3);
        recipeRepository.save(recipe4);
        recipeRepository.save(recipe5);
        recipeRepository.save(recipe6);
        recipeRepository.save(recipe7);
        recipeRepository.save(recipe8);
        recipeRepository.save(recipe9);
        recipeRepository.save(recipe10);

        ingredientRecipeRepository.save(ingredientRecipe1);
        ingredientRecipeRepository.save(ingredientRecipe2);
        ingredientRecipeRepository.save(ingredientRecipe3);
        ingredientRecipeRepository.save(ingredientRecipe4);
    }
}
