package model;

import java.util.ArrayList;
import java.util.List;

public class CategoryModel {
    private Integer id;

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public CategoryModel(Integer id, String name){
     this.id = id;
     this.name = name;
    }
    public CategoryModel(String name){
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString(){
        return String.format(
                "{id: %d, name: %s}",
                this.id, this.name);
    }
}