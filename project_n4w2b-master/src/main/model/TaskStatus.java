package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//Represents the hashmap that takes care of status changes
public class TaskStatus implements Map {

    public final Map<Integer, String> statusMap;


    //EFFECTS: Constructs a hashmap with three values for each status
    public TaskStatus() {
        this.statusMap = new HashMap<Integer, String>();

        this.statusMap.put(1, "Incomplete");
        this.statusMap.put(2, "Complete");
        this.statusMap.put(3, "Overdue");
    }

    //EFFECTS: returns size of map
    @Override
    public int size() {
        return statusMap.size();
    }

    //EFFECTS: checks if map is empty
    @Override
    public boolean isEmpty() {
        return false;
    }

    //EFFECTS: checks if map contains certain key
    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    //EFFECTS: checks if map contains certain value
    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    //EFFECTS: returns value with a certain key
    @Override
    public Object get(Object key) {
        return statusMap.get(key);
    }

    //MODIFIES: this
    //EFFECTS: inserts value with a certain key into hashmap
    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    //MODIFIES: this
    //EFFECTS: removes value with a certain key from hashmap
    @Override
    public Object remove(Object key) {
        return null;
    }

    //MODIFIES: this
    //EFFECTS: inserts a whole map into another map
    @Override
    public void putAll(Map m) {

    }

    //MODIFIES: this
    //EFFECTS: removes all the mapping from map
    @Override
    public void clear() {

    }

    //EFFECTS: returns a set of all the keys in the map
    @Override
    public Set keySet() {
        return null;
    }

    //EFFECTS: returns a collection of all the values in the map
    @Override
    public Collection values() {
        return null;
    }

    //EFFECTS: returns a set view of mappings contained in map
    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
