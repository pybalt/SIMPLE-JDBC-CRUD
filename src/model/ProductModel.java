package model;

public class ProductModel {
    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
    private Integer categoryID;
    public ProductModel(String name, String description, Integer quantity){
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }
    public ProductModel(Integer id, String name, Integer quantity){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }
    public ProductModel(Integer id, String name, String description, Integer quantity, Integer categoryID){
        this.name = name;
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.categoryID = categoryID;
    }
    @Override
    public String toString(){
        return String.format(
                "{id: %d,  name: %s,  description: %s, quantity:%d, category id: %d}",
                 this.id, this.name, this.description, this.quantity, this.categoryID);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getCategoryID() {
        return this.categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
