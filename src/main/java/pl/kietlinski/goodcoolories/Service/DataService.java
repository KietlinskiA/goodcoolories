package pl.kietlinski.goodcoolories.Service;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.kietlinski.goodcoolories.Entity.*;
import pl.kietlinski.goodcoolories.Model.OrderBuilder;
import pl.kietlinski.goodcoolories.Model.UserBuilder;
import pl.kietlinski.goodcoolories.Repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Data
public class DataService {

    private RecipeRepository recipeRepository;
    private IngredientRecipeRepository ingredientRecipeRepository;
    private IngredientRepository ingredientRepository;
    private DishRepository dishRepository;
    private OrderRepository orderRepository;
    private DietRepository dietRepository;
    private UserRepository userRepository;

    @Autowired
    public DataService(RecipeRepository recipeRepository, IngredientRecipeRepository ingredientRecipeRepository, IngredientRepository ingredientRepository, DishRepository dishRepository, OrderRepository orderRepository, DietRepository dietRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRecipeRepository = ingredientRecipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.dishRepository = dishRepository;
        this.orderRepository = orderRepository;
        this.dietRepository = dietRepository;
        this.userRepository = userRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initDatabase() {
        User user1 = new UserBuilder()
                .setName("Jan Kowal").setEaddress("dietetyk@goodcoolories.hub.pl").setPhone("111111111").setStreet("Kowalska 1")
                .setZip("11-111").setCity("Kowalów").setAge(25).setHeight(187).setSex("Mężczyzna")
                .createUser();
        User user2 = new UserBuilder()
                .setName("Kamil Ul").setEaddress("dietetyk@goodcoolories.hub.pl").setPhone("222222222").setStreet("Ulowa 3")
                .setZip("22-222").setCity("Ulów").setAge(18).setHeight(173).setSex("Mężczyzna")
                .createUser();
        User user3 = new UserBuilder()
                .setName("Ala Lis").setEaddress("dietetyk@goodcoolories.hub.pl").setPhone("333333333").setStreet("Lisia 2")
                .setZip("33-333").setCity("Lisów").setAge(45).setHeight(162).setSex("Kobieta")
                .createUser();

        Order order1 = new OrderBuilder()
                .setActivity("0-2").setDietWish("Schudnąć").setDishCount(5)
                .setComment("Nie lubie salaty i pomidora")
                .setStatus("Zakończony")
                .createOrder();
        Order order2 = new OrderBuilder()
                .setActivity("3-5").setDietWish("Utrzymać").setDishCount(4)
                .setComment("Nie lubie twarogu i ryżu")
                .setStatus("Nowy")
                .createOrder();
        Order order3 = new OrderBuilder()
                .setActivity("7").setDietWish("Przytyć").setDishCount(5)
                .setComment("Nie lubie makaronu i sera żółtego")
                .setStatus("Nowy")
                .createOrder();

        order1.setUser(user1);
        order2.setUser(user2);
        order3.setUser(user3);

        Diet diet1 = new Diet("111");
        Diet diet2 = new Diet("222");
        Diet diet3 = new Diet("333");

        order1.setDiet(diet1);
        order2.setDiet(diet2);
        order3.setDiet(diet3);

        Dish dish1 = new Dish("Ryż z tuńczykiem", "https://www.kwestiasmaku.com/sites/v123.kwestiasmaku.com/files/salatka-ryzowa-z-tunczykiem-00.jpg");
        Dish dish2 = new Dish("Makaron z szynką", "https://www.kwestiasmaku.com/sites/v123.kwestiasmaku.com/files/pappardelle_z_szynka_parmenska_02.jpg");
        Dish dish3 = new Dish("Bułki z jajkiem i ogórkiem", "https://www.kwestiasmaku.com/sites/v123.kwestiasmaku.com/files/salatka_jajko_feta_01.jpg");
        Dish dish4 = new Dish("Zupa fasolowa", "https://www.kwestiasmaku.com/sites/v123.kwestiasmaku.com/files/zupa_fasolkowa_01.jpg");
        Dish dish5 = new Dish("Kasza manna z dżemem malinowym", "https://cdn.aniagotuje.com/pictures/articles/2019/11/1490094-v-720x935.jpg");

        diet1.setDishSet(Set.of(dish1, dish2, dish3, dish4, dish5));
        diet2.setDishSet(Set.of(dish1, dish2, dish4, dish5));
        diet3.setDishSet(Set.of(dish1, dish2, dish3, dish4, dish5));

        dish1.setDietSet(Set.of(diet1, diet2, diet3));
        dish2.setDietSet(Set.of(diet1, diet2, diet3));
        dish3.setDietSet(Set.of(diet1, diet3));
        dish4.setDietSet(Set.of(diet1, diet2, diet3));
        dish5.setDietSet(Set.of(diet1, diet2, diet3));

        List<Recipe> recipeList = List.of(
                new Recipe("trudny", 25,
                        "Ryż ugotować w osolonej wodzie. (Ryż powinien być ugotowany na sypko, nie rozgotowany). Następnie " +
                                "dobrze odcedzić na sitku i pozostawić do ostygnięcia. Jajka ugotować na twardo, ostudzić, obrać ze " +
                                "skorupek i pokroić w kostkę. Tuńczyka i kukurydzę odsączyć. Ryż przełożyć do miski. Dodać odsączonego " +
                                "tuńczyka, kukurydzę i jajka. Doprawić solą i pieprzem. Sałatkę można podawać od razu. " +
                                "Można ją również schłodzić w lodówce."),
                new Recipe("łatwy", 25,
                        "Gotujemy makaron według przepisu i odcedzamy. Szynkę kroimy w kosteczkę.\n" +
                                "Łączymy makaron z szynką i podgrzewamy razem na patelni.\n" +
                                "Dodajemy pesto i gotowe.\n" +
                                "Można posypać startym serem lub parmezanem.\n" +
                                "Smacznego"),
                new Recipe("łatwy", 10,
                        "Bułki pokrojone w kromeczki posmarować masłem.\n" +
                                "Jajka ugotowane na twardo i ogórki pokroic w plasterki.\n" +
                                "Pokładać na kromeczki, posypać solą i pieprzem.\n"),
                new Recipe("trudny", 60,
                        "Fasolę namaczamy dzień wcześniej. Gotujemy w osolonej wodzie z namaczania przez 30 " +
                                "minut dodatkiem liścia laurowego i ziela angielskiego.\n" +
                                "Kroimy kiełbasę i smażymy na suchej patelni, po usmażeniu dodajemy pokrojoną cebulę.\n" +
                                "Do fasoli dodajemy pokrojone w kostkę ziemniaki, marchew i pietruszkę, gotujemy 10 " +
                                "minut, dolewamy rosołu i dokładamy kiełbasę z cebulą z patelni oraz kukurydzę.\n" +
                                "Przykrywamy i gotujemy do miękkości ziemniaków. Doprawiamy pieprzem i majerankiem."),
                new Recipe("łatwy", 10,
                        "Z 300 ml mleka odlać do szklanki około 100 ml, wymieszać z kaszą manną. Pozostałe mleko " +
                                "zagotować dodając cukier i cukier waniliowy. Gdy mleko się zagotuje dolać część mleka " +
                                "wymieszaną z kaszą i gotować na małym ogniu często mieszając. Kasza stopniowo będzie gęstnieć, " +
                                "po około 5-10 minutach powinna uzyskać odpowiednią konsystencję. Nałożyć do miseczki, przybrać " +
                                "dżemem malinowym.")
        );

        recipeList.get(0).setDish(dish1);
        recipeList.get(1).setDish(dish2);
        recipeList.get(2).setDish(dish3);
        recipeList.get(3).setDish(dish4);
        recipeList.get(4).setDish(dish5);

        List<IngredientRecipe> ingredientRecipeList = List.of(
                // 1 Ryż
                new IngredientRecipe(0.3),
                new IngredientRecipe(0.3),
                new IngredientRecipe(0.5),
                new IngredientRecipe(0.4),
                // 2 Makaron
                new IngredientRecipe(1.7),
                new IngredientRecipe(0.8),
                new IngredientRecipe(0.2),
                // 3 Bułki
                new IngredientRecipe(0.6),
                new IngredientRecipe(1.2),
                new IngredientRecipe(1.2),
                // 4 Zupa
                new IngredientRecipe(0.5),
                new IngredientRecipe(1.1),
                new IngredientRecipe(0.4),
                new IngredientRecipe(0.75),
                new IngredientRecipe(0.5),
                // 5 Kasza manna
                new IngredientRecipe(2),
                new IngredientRecipe(0.375),
                new IngredientRecipe(0.2),
                new IngredientRecipe(0.5)
        );

        List<Ingredient> ingredientList = List.of(
                // 1 Ryż
                new Ingredient("Ryż", 130, 3, 1, 30),
                new Ingredient("Tuńczyk", 131, 28, 1.3, 0),
                new Ingredient("Kukurydza", 365, 9, 4.7, 74),
                new Ingredient("Jajko", 155, 13, 11, 1.1),
                // 2 Makaron
                new Ingredient("Makaron", 131, 5, 1.1, 25),
                new Ingredient("Szynka", 145, 21, 6, 1.5),
                new Ingredient("Pesto", 569, 4, 37, 7),
                // 3 Bułki
                new Ingredient("Bułka", 310, 11, 6, 52),
                new Ingredient("Ogórek kiszony", 11, 0.3, 0.2, 2.3),
                // JAJKO JEST
                // 4 Zupa
                new Ingredient("Fasola", 347, 21, 1.2, 63),
                new Ingredient("Cebula", 39, 1.1, 0.1, 9),
                new Ingredient("Kiełbasa śląska", 214, 18, 16, 0),
                new Ingredient("Ziemniaki", 76, 2, 0.1, 17),
                // KUKURYDZA JEST
                // 5 Kasza manna
                new Ingredient("Mleko", 40, 3.4, 0.5, 5),
                new Ingredient("Kasza manna", 359, 13, 1.1, 73),
                new Ingredient("Cukier", 386, 0, 0, 100),
                new Ingredient("Dżem malinowy", 139, 1, 0, 33)
        );

        Recipe currentRecipe;
        // 1 Ryż
        currentRecipe = recipeList.get(0);
        ingredientRecipeList.get(0).setRecipe(currentRecipe);
        ingredientRecipeList.get(0).setIngredient(ingredientList.get(0));
        ingredientRecipeList.get(1).setRecipe(currentRecipe);
        ingredientRecipeList.get(1).setIngredient(ingredientList.get(1));
        ingredientRecipeList.get(2).setRecipe(currentRecipe);
        ingredientRecipeList.get(2).setIngredient(ingredientList.get(2));
        ingredientRecipeList.get(3).setRecipe(currentRecipe);
        ingredientRecipeList.get(3).setIngredient(ingredientList.get(3));
        // 2 Makaron
        currentRecipe = recipeList.get(1);
        ingredientRecipeList.get(4).setRecipe(currentRecipe);
        ingredientRecipeList.get(4).setIngredient(ingredientList.get(4));
        ingredientRecipeList.get(5).setRecipe(currentRecipe);
        ingredientRecipeList.get(5).setIngredient(ingredientList.get(5));
        ingredientRecipeList.get(6).setRecipe(currentRecipe);
        ingredientRecipeList.get(6).setIngredient(ingredientList.get(6));
        // 3 Bułki
        currentRecipe = recipeList.get(2);
        ingredientRecipeList.get(7).setRecipe(currentRecipe);
        ingredientRecipeList.get(7).setIngredient(ingredientList.get(7));
        ingredientRecipeList.get(8).setRecipe(currentRecipe);
        ingredientRecipeList.get(8).setIngredient(ingredientList.get(8));
        ingredientRecipeList.get(9).setRecipe(currentRecipe);
        ingredientRecipeList.get(9).setIngredient(ingredientList.get(3));
        // 4 Zupa
        currentRecipe = recipeList.get(3);
        ingredientRecipeList.get(10).setRecipe(currentRecipe);
        ingredientRecipeList.get(10).setIngredient(ingredientList.get(9));
        ingredientRecipeList.get(11).setRecipe(currentRecipe);
        ingredientRecipeList.get(11).setIngredient(ingredientList.get(10));
        ingredientRecipeList.get(12).setRecipe(currentRecipe);
        ingredientRecipeList.get(12).setIngredient(ingredientList.get(11));
        ingredientRecipeList.get(13).setRecipe(currentRecipe);
        ingredientRecipeList.get(13).setIngredient(ingredientList.get(12));
        ingredientRecipeList.get(14).setRecipe(currentRecipe);
        ingredientRecipeList.get(14).setIngredient(ingredientList.get(2));
        // 5 Kasza manna
        currentRecipe = recipeList.get(4);
        ingredientRecipeList.get(15).setRecipe(currentRecipe);
        ingredientRecipeList.get(15).setIngredient(ingredientList.get(13));
        ingredientRecipeList.get(16).setRecipe(currentRecipe);
        ingredientRecipeList.get(16).setIngredient(ingredientList.get(14));
        ingredientRecipeList.get(17).setRecipe(currentRecipe);
        ingredientRecipeList.get(17).setIngredient(ingredientList.get(15));
        ingredientRecipeList.get(18).setRecipe(currentRecipe);
        ingredientRecipeList.get(18).setIngredient(ingredientList.get(16));

        // Dish
        dishRepository.save(dish1);
        dishRepository.save(dish2);
        dishRepository.save(dish3);
        dishRepository.save(dish4);
        dishRepository.save(dish5);
        // Diet
        dietRepository.save(diet1);
        dietRepository.save(diet2);
        dietRepository.save(diet3);
        // User
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        // Order
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        // Ingredient
        ingredientRepository.saveAll(ingredientList);
        // Recipe
        recipeRepository.saveAll(recipeList);
        // IngredientRecipe
        ingredientRecipeRepository.saveAll(ingredientRecipeList);
    }
}
