package model;


import org.json.JSONObject;
import persistence.Writable;
import java.sql.Wrapper;
import java.util.Map;

//Represents a new task that has a task name and one of three status
// status: incomplete (1), completed (2), overdue (3)
public class Task implements Writable {


    private String taskName;
    private int status;
    private TaskStatus statusMapObj = new TaskStatus();


    // EFFECTS: constructs a task with taskName and initial status as incomplete.
    public Task(String name) {
        this.taskName = name;
        this.status = 1;
    }

    // EFFECTS: constructs an empty task
    public Task() {
    }

    //EFFECTS: returns status of task
    public int getStatus() {
        return this.status;
    }

    //EFFECTS: returns name of task
    public String getTaskName() {
        return this.taskName;
    }

    //MODIFIES: this
    //EFFECTS: sets task status to new status
    public void setStatus(int status) {
        this.status = status;
    }

    //MODIFIES: this
    //EFFECTS: sets task name to new name
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }


    //MODIFIES: this
    //EFFECTS: converts tasks to json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("taskName", this.taskName);
        json.put("status", defineStatus(this.status));
        return json;
    }

    //MODIFIES: status
    //EFFECTS: defines status numbers into strings
    public String defineStatus(int status) {
        String statusDef = "";

//
//        if (status == 1) {
//            statusDef = "Incomplete";
//        } else if (status == 2) {
//            statusDef = "Complete";
//        } else  if (status == 3) {
//            statusDef = "Overdue";
//        }
        return statusMapObj.statusMap.get(status);
    }

    //MODIFIES: status
    //EFFECTS: defines status strings into numbers
    public int statusToInt(String status) {
        int statusDef = 1;
        for (Map.Entry<Integer, String> entry : statusMapObj.statusMap.entrySet()) {
            if (entry.getValue().equals(status)) {
                return entry.getKey();
            }
        }
//        if (status.equals("Incomplete")) {
//            statusDef = 1;
//        } else if (status.equals("Complete")) {
//            statusDef = 2;
//        } else  if (status.equals("Overdue")) {
//            statusDef = 3;
//        }
        return -1;
    }


}
