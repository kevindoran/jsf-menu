/*
 * Copyright 2013 Kevin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nz.co.kevindoran.breadcrumb;

import java.util.ArrayList;
import java.util.List;
import nz.co.kevindoran.subscriber.Subscriber;

/**
 *
 * @author Kevin
 */
public class Breadcrumb<T> {
    protected List<Subscriber<T>> subscribers = new ArrayList<>();
    private List<T> items;
    
    public Breadcrumb() {
    }
    
    public void setContents(List<T> items) {
        this.items = items;
    }
    
    public List<T> getItems() {
        return items;
    }
    
    public void setSelected(T selected) {
        updateSubscribers(selected);
    }
    
    public void addSubscriber(Subscriber<T> subscriber) {
        subscribers.add(subscriber);
    }
    
    protected void updateSubscribers(T selected) {
        for(Subscriber<T> s : subscribers) {
            s.update(selected);
        }
    }
}
