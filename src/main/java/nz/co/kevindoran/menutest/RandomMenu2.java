/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.kevindoran.menutest;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import nz.co.kevindoran.category.Category;
import nz.co.kevindoran.menumodel.MenuModel;
import nz.co.kevindoran.subscriber.Subscriber;

/**
 *
 * @author Kevin
 */
@ManagedBean
@ViewScoped
public class RandomMenu2 {
    
    private MenuModel<Category> menuModel;
    private Category currentCategory = new Category();
    
    public RandomMenu2() {
        menuModel = new MenuModel<>();
        menuModel.setContents(currentCategory.getChildCategories());
        MenuClickListener menuListener = new MenuClickListener();
        menuModel.addSubscriber(menuListener);
    }
    
    private class MenuClickListener implements Subscriber<Category> {

        @Override
        public void update(Category change) {
            currentCategory = change;
            menuModel.setContents(currentCategory.getChildCategories());
        }
    }
    
    public MenuModel<Category> getMenuModel() {
        return menuModel;
    }

    public Category getCurrentCategory() {
        return currentCategory;
    }
}