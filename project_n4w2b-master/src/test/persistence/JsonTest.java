package persistence;

import model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Modelled on JSON code provided by instructors
public class JsonTest {
    private Task task;

    protected void checkTask(String name, Task task) {
        assertEquals(name, task.getTaskName());
    }
}
