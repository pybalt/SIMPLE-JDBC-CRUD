package view;

import controller.CategoryController;
import controller.ProductController;
import model.CategoryModel;
import model.ProductModel;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

class Menu {
    private static boolean wasPreviouslyShowed = false;
    static void provideMessage(){
        String message;
        if(wasPreviouslyShowed){
            message = """
                We ought to remember you:
                Your options are...
                    """;
        }else{
            message = """
                Welcome to our application.
                With this application you will manage products
                in a database
                Your options are:
                    """;
        }
        message += """
                1. Create a new product.
                2. Update a product.
                3. Delete a product.
                4. List all products.
                5. List products by category.
                6. Create a new category.
                7. Update a category.
                8. Delete a category.
                9. List all categories
                10. Exit.
                    """;
        System.out.println(message);
        wasPreviouslyShowed = true;
    };
}
public class View {
    private CategoryController category;
    private ProductController product;
    private Scanner keyboard;
    public View(){
        this.category = new CategoryController();
        this.product  = new ProductController();
        this.keyboard = new Scanner(System.in);
        System.out.println("Press any key to start.");
    }

    public Enum<Status> start(){
        Menu.provideMessage();
            String inputtedOption = keyboard.nextLine();
            if (inputtedOption.equals("10") || inputtedOption.isBlank() || inputtedOption.isEmpty()){
                System.out.println("We appreciate that you use our application.");
                return Status.FINISHED;
            }
            try {
                switch (inputtedOption) {
                    case "1" -> {
                        System.out.println("You want to create a new product.");
                        String name = this.input("Please, write down its name.");
                        String description = this.input("Please, write down its description.");
                        Integer quantity = Integer.parseInt(this.input("Please, write down how many products will you insert."));
                        if (name.isEmpty() || name.isBlank()) throw new AssertionError();
                        ProductModel tempProduct = new ProductModel(
                                name, description, quantity
                        );
                        category.list().forEach(System.out::println);
                        int categoryID = Integer.parseInt(input("Choose a category."));
                        product.save(tempProduct, categoryID);
                    }
                    case "2" -> {
                        System.out.println("You want to update a product.");
                        System.out.println("Let us take a look to the existing products.");
                        List<ProductModel> listOfProducts = product.list();
                        listOfProducts.forEach(System.out::println);
                        int productID = Integer.parseInt(input("Please input the ID of the desired product."));
                        ProductModel productChosen = listOfProducts.get(productID - 1);
                        Map<Integer, String> mapOfFieldToEdit = Map.of(
                                1, "Name",
                                2, "Description",
                                3, "Quantity",
                                4, "Category ID",
                                5, "All fields");
                        mapOfFieldToEdit.forEach((k, v) -> System.out.printf("{%d : %s}\n", k, v));
                        int fieldToEdit = Integer.parseInt(input("Please input the ID of the field to edit."));

                        if(fieldToEdit == 5){
                            String name = input("Set a new name");
                            Integer quantity = Integer.valueOf(input("Set a new quantity"));
                            System.out.println("Select a category from the existing ones.");
                            category.list().forEach(System.out::println);
                            int categoryID = Integer.parseInt(input("Set a new category"));
                            String description = input("Set a new description");
                            product.edit(
                                    productChosen.getId(),
                                    name,
                                    description,
                                    quantity,
                                    categoryID);
                        }else if(fieldToEdit == 4){
                            Integer categoryID = Integer.valueOf(input("Set a new category ID from the existing ones."));
                            category.list().forEach(System.out::println);
                            product.edit(
                                    productChosen.getId(),
                                    productChosen.getName(),
                                    productChosen.getDescription(),
                                    categoryID,
                                    productChosen.getQuantity());
                        }else if(fieldToEdit == 3){
                            Integer quantity = Integer.valueOf(input("Set a new quantity."));
                            product.edit(
                                    productChosen.getId(),
                                    productChosen.getName(),
                                    productChosen.getDescription(),
                                    productChosen.getCategoryID(),
                                    quantity
                            );
                        }else if (fieldToEdit == 2) {
                            String description = input("Set a new description.");
                            if (description.isEmpty() || description.isBlank()) throw new AssertionError();
                            product.edit(
                                    productChosen.getId(),
                                    productChosen.getName(),
                                    description,
                                    productChosen.getCategoryID(),
                                    productChosen.getQuantity()
                            );
                        }else if (fieldToEdit == 1) {
                            String name = input("Set a new name.");
                            product.edit(productChosen.getId(),
                                    name,
                                    productChosen.getDescription(),
                                    productChosen.getCategoryID(),
                                    productChosen.getQuantity());
                        }
                    }
                    case "3" -> {
                        System.out.println("You want to delete a product.");
                        System.out.println("Let us take a look");
                        this.product.list().forEach(System.out::println);
                        Integer productID = Integer.valueOf(input("Select a product by providing its ID."));
                        product.eliminate(productID);
                    }
                    case "4" -> {
                        product.list().forEach(System.out::println);
                    }
                    case "5" -> {
                        System.out.println("Select a category");
                        List<CategoryModel> listOfCategories = category.list();
                        listOfCategories.forEach(System.out::println);
                        int categoryID = Integer.parseInt(input("Insert an ID"));
                        CategoryModel category = listOfCategories.get(categoryID - 1);
                        List<ProductModel> listOfProducts = product.list(category);
                        listOfProducts.forEach(System.out::println);
                    }
                    case "6" -> {
                        System.out.println("You want to create a new category.");
                        System.out.println("Before that, let us take a look to the existing categories.");
                        category.list().forEach(System.out::println);
                        System.out.println("If your category does not exist, let us continue.");
                        String name = this.input("Please, write down its name.");
                        if (name.isEmpty() || name.isBlank()) throw new AssertionError();
                        CategoryModel tempCategory = new CategoryModel(name);
                        category.insert(tempCategory);
                    }
                    case "7" -> {
                        System.out.println("You want to update a category.");
                        System.out.println("Let us take a look to the existing categories.");
                        List<CategoryModel> listOfCategories = category.list();
                        listOfCategories.forEach(System.out::println);
                        int categoryID = Integer.parseInt(input("Choose an id"));
                        CategoryModel categorySelected = listOfCategories.get(categoryID - 1);
                        categorySelected.setName(input("Please, write down a new name."));
                        category.update(categorySelected);
                    }
                    case"8" -> {
                        System.out.println("You want to delete a category.");
                        System.out.println("Let us take a look to the existing categories.");
                        List<CategoryModel> listOfCategories = category.list();
                        listOfCategories.forEach(System.out::println);
                        int categoryID = Integer.parseInt(input("Choose an id"));
                        CategoryModel categorySelected = listOfCategories.get(categoryID - 1);
                        System.out.printf("The following category will be eliminated:\n%s\n", categorySelected);
                        String response = input("Do you want to proceed?");
                        if(Pattern.matches("(?i)y", response)){
                            category.delete(categorySelected);
                        }
                    }
                    case "9" ->{
                        category.list().forEach(System.out::println);
                    }
                }
            }catch (AssertionError e){
                System.out.println("Parameter can not be empty.");
            }
        return Status.RUNNING;
    }
    public String input(String message){
        System.out.println(message);
        return keyboard.nextLine();
    }

}