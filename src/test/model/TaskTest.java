package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Tests for Task
public class TaskTest {
    private Task testTask;

    @BeforeEach
    public void setUpTask() {
        testTask = new Task("testTask");
    }

    @Test
    public void testTaskConstructor() {
        assertEquals("testTask", testTask.getTaskName());
        assertEquals(1, testTask.getStatus());
        assertEquals("Incomplete",testTask.defineStatus(1));
    }

    @Test
    public void testStatsDef() {
        testTask.setStatus(2);
        assertEquals(2,testTask.getStatus());
        assertEquals("Complete",testTask.defineStatus(2));

        testTask.setStatus(3);
        assertEquals(3,testTask.getStatus());
        assertEquals("Overdue",testTask.defineStatus(3));

//        testTask.setStatus(4);
//        assertEquals("",testTask.defineStatus(4));
    }



}
