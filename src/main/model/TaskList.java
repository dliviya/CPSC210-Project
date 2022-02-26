package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import persistence.Writable;
// Represents an a TaskList that is made of Tasks

public class TaskList implements Writable {
    private ArrayList<Task> listOfTasks;
    private Task task;


    //EFFECTS: creates an empty taskList
    public TaskList() {
        listOfTasks = new ArrayList<>();
    }


    //EFFECTS: returns a taskList
    public ArrayList<Task> getTaskList() {
        return this.listOfTasks;
    }

    //EFFECTS: returns size of taskList
    public int length() {
        return listOfTasks.size();
    }

    //MODIFIES: this
    //EFFECTS: adds a task to a taskList
    public void addTask(Task task) {
        this.listOfTasks.add(task);
    }

    //MODIFIES: this
    //EFFECTS: if the task number is valid, then turns the string taskNumber
    //          into an integer and changes the old status of the task.
    //          else does nothing.
    public void changeStatus(String taskNumber, int statusUpdate) {
        if (checkTaskNumberValid(taskNumber)) {
            try {
                int intTaskNumber = Integer.parseInt(taskNumber);
                task = this.listOfTasks.get(intTaskNumber - 1);
                task.setStatus(statusUpdate);
            } catch (NumberFormatException e) {
                return;
            }

        }
    }

    //EFFECTS: turns string taskNumber into integer, if taskNumber is in the valid range
    //          (1 and size of listOfTasks), then returns true.
    //          else returns false.
    public boolean checkTaskNumberValid(String taskNumber) {
        try {
            int intTaskNumber = Integer.parseInt(taskNumber);
            if (intTaskNumber >= 1 && intTaskNumber <= this.listOfTasks.size()) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //MODIFIES: this
    //EFFECTS: turns string input into an integer and then removes corresponding task from list.
    public void removeTask(String taskNumber) {
        try {
            int intTaskNumber = Integer.parseInt(taskNumber);
            this.listOfTasks.remove(intTaskNumber - 1);
        } catch (NumberFormatException e) {
            return;
        }
    }

    //MODIFIES: this
    //EFFECTS: converts tasklist to json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", "My Tasklist");
        json.put("List", taskListToJson());
        return json;
    }


    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray taskListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : listOfTasks) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }


}
