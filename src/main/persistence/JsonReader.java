package persistence;

import model.Task;
import model.TaskList;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads tasklist from JSON data stored in file
//Modelled on JSON code provided by instructors
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads tasklist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TaskList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTaskList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses tasklist from JSON object and returns it
    private TaskList parseTaskList(JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        TaskList tl = new TaskList();
        addTasks(tl, jsonObject);
        return tl;
    }

    // MODIFIES: tl
    // EFFECTS: parses tasks from JSON object and adds them to tsklist
    private void addTasks(TaskList tl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("List");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(tl, nextTask);
        }
    }

    // MODIFIES: tl
    // EFFECTS: parses task from JSON object and adds it to tasklist
    private void addTask(TaskList tl, JSONObject jsonObject) {
        String taskName = jsonObject.getString("taskName");
        String status = jsonObject.getString("status");
 //       Category category = Category.valueOf(jsonObject.getString("category"));
        Task task = new Task();
        task.setTaskName(taskName);
        task.setStatus(task.statusToInt(status));
        tl.addTask(task);
    }
}
