
package org.example.demoproject.Week1.lab1_1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WarehouseStore<T extends Product> {

    private final List<T> items = new ArrayList<>();

    // Add product
    public void addItem(T item) {
        items.add(item);
    }

    // Remove by ID using Iterator (safe removal)
    public boolean removeItem(String id) {
        Iterator<T> iterator = items.iterator();

        while (iterator.hasNext()) {
            T item = iterator.next();
            if (item.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    // Find by category
    public List<T> findByCategory(String cat) {
        List<T> result = new ArrayList<>();

        for (T item : items) {
            if (item.getCategory().equalsIgnoreCase(cat)) {
                result.add(item);
            }
        }
        return result;
    }
    public List<T> getAll() {
        return items;
    }
}
