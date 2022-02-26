package persistence;

import org.json.JSONObject;

//Modelled on JSON code provided by instructors
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}