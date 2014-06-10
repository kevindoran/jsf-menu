package nz.co.kevindoran.jsfmenutable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import nz.co.kevindoran.subscriber.Subscriber;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Kevin
 */
public class MenuTable<T> {
    private List<Row<T>> rows;
    protected List<Subscriber<T>> subscribers = new ArrayList<>();
    private int noOfColumns;
    
    public MenuTable(int noOfColumns) {
        this.noOfColumns = noOfColumns;
    }
    
    public void setContents(Collection<T> objects) {
        rows = new ArrayList<>(); 
        int count = objects.size();
        int maxColumnLength = (int) Math.ceil(((double)count) / noOfColumns);
        for(int i=0; i<maxColumnLength; i++) {
            rows.add(new Row<T>());
        }        

        Iterator<T> it = objects.iterator();
        for(int col = 0; col < noOfColumns; col++) {
            double left = count - col;
            double temp = left / noOfColumns;
            int columnLength = (int) Math.ceil(temp);
            for(int i=0; i<columnLength; i++){
                rows.get(i).add(it.next());
            }
        }
    }

    public void setSelected(T selected) {
        updateSubscribers(selected);
    }
    
    private void updateSubscribers(T latest) {
        for(Subscriber<T> s : subscribers) {
            s.update(latest);
        }
    }

    public List<Row<T>> getRows() {
        return rows;
    }

    public int getNoOfColumns() {
        return noOfColumns;
    }
    
    public void addSubscriber(Subscriber<T> subscriber) {
        subscribers.add(subscriber);
    }
    
    public class Row<T> {
        private List<T> contents = new ArrayList<>();

        public List<T> getContents() {
            return contents;
        }

        public void add(T object) {
            contents.add(object);
        }
    }
}
