/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.kevindoran.category;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Category> parents = new ArrayList<>();

    public Category() {
        name = "Root";
        parents.add(this);
    }
    
    public Category(List<Category> parents) {
        this.parents = parents;
        this.parents.add(this);
        double d = Math.random();
        String ds = String.valueOf(d);
        name = parents.get(parents.size()-2).name + "." + ds.charAt(2);
    }

    public List<Category> getParentCategories() {
        return parents;
    }
    
    public List<Category> getChildCategories() {
        List<Category> childCategories = new ArrayList<>();
        for(int i=0; i<20; i++) {
            childCategories.add(new Category(new ArrayList<>(parents)));
        }
        return childCategories;
    }

    @Override
    public String toString() {
        return name;
    }
}