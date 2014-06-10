package nz.co.kevindoran.menumodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import nz.co.kevindoran.subscriber.Subscriber;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuItem;



/*
 * This class has functionality that can be generalised to make a base for 
 * programmatic menus. Removing the "categoryPath" string and Category class
 * dependency will make it general purpose: allow programmers to decide how
 * to recreate a MenuItem.
 */
@Deprecated // As of Primefaces 4, this class will not work. See needed fix below.
public class MenuModel<T> extends
        DefaultMenuModel implements ActionListener {

    private static final long serialVersionUID = 784711253072089741L;
    protected T category;
    protected List<Subscriber<T>> subscribers = new ArrayList<>();
    private String updateID;
    
    
    public MenuModel() {
    }
	
    public void setContents(Collection<T> objects) {
        getElements().clear();
        for(T o : objects) {
            MenuItem menuItem = createMenuItem(o);
            addElement(menuItem);
        }
    }
    
    /**
     * Called by a MenuItem when it is clicked.
     * 
     * @throws AbortProcessingException 
     */
    @Override
    public void processAction(ActionEvent event)
            throws AbortProcessingException {
        if(event.getSource().getClass() == MenuItem.class) {
             MenuItem sourceItem = (MenuItem) event.getSource();
             T latestItem = (T) sourceItem.getParams().get("menuObject").get(0);
             updateSubscribers(latestItem);
        }
    }
    
    public void enableAjax(String updateID) {
        this.updateID = updateID;
    }
    

    /**
     * A utility method which creates a MenuItem.
     * 
     * @return the created MenuItem.
     */
    public MenuItem createMenuItem(T item) {
        DefaultMenuItem menuItem = new DefaultMenuItem();
        menuItem.setValue(item.toString());
        //menuItem.setId(uiViewRoot.createUniqueId());
        if(updateID == null) {
            menuItem.setAjax(false);
        }
        else {
            menuItem.setUpdate(updateID);
        }
        // TODO: find alternative
        //menuItem.addActionListener(this);
        menuItem.setParam("menuObject", item);
        return menuItem;
    }

    
    public void addSubscriber(Subscriber<T> subscriber) {
        subscribers.add(subscriber);
    }
    
    protected void updateSubscribers(T category) {
        for(Subscriber<T> s : subscribers) {
            s.update(category);
        }
    }
}
