package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Tests for TaskList
public class TaskListTest {
    private TaskList testTaskList;
    private Task testTask;
    private Task testTask2;
    private Task testTask3;
    private Task testTask4;
    private Task testTask5;

    @BeforeEach
    public void setUpTaskList() {
        testTaskList = new TaskList();
        testTask = new Task("testTask");
        testTask2 = new Task("testTask2");
        testTask3 = new Task("testTask3");
        testTask4 = new Task("testTask4");
        testTask5 = new Task("testTask5");

    }

    @Test
    public void testTaskListConstructor() {
        assertEquals(0, testTaskList.length());
    }

    @Test
    public void testAddTestToEmpty() {
        testTaskList.addTask(testTask);

        assertEquals(1, testTaskList.length());
    }

    @Test
    public void testAddTestToNotEmpty() {
        testTaskList.addTask(testTask);
        testTaskList.addTask(testTask2);

        assertEquals(2, testTaskList.length());
    }

    @Test
    public void testChangeStatus1To1() {
        try {
            testTaskList.addTask(testTask);
            testTaskList.changeStatus("1",1);

            assertEquals(1, testTaskList.getTaskList().get(0).getStatus());
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }

    }

    @Test
    public void testChangeStatus1To2() {
        try {
            testTaskList.addTask(testTask);
            testTaskList.changeStatus("1",2);

            assertEquals(2, testTaskList.getTaskList().get(0).getStatus());
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }

    }

    @Test
    public void testChangeStatus1To3() {
        try {
            testTaskList.addTask(testTask);
            testTaskList.changeStatus("1",3);

            assertEquals(3, testTaskList.getTaskList().get(0).getStatus());
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }

    }

    @Test
    public void testChangeStatus2To1() {
        try {
            testTaskList.addTask(testTask);
            testTaskList.changeStatus("1",2);
            assertEquals(2, testTaskList.getTaskList().get(0).getStatus());
            testTaskList.changeStatus("1",1);

            assertEquals(1, testTask.getStatus());
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }

    }

    @Test
    public void testChangeStatus2To2() {
        try {
            testTaskList.addTask(testTask);
            testTaskList.changeStatus("1",2);
            assertEquals(2, testTaskList.getTaskList().get(0).getStatus());
            testTaskList.changeStatus("1",2);

            assertEquals(2, testTaskList.getTaskList().get(0).getStatus());
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }

    }

    @Test
    public void testChangeStatus2To3() {
        try {
            testTaskList.addTask(testTask);
            testTaskList.changeStatus("1",2);
            assertEquals(2, testTaskList.getTaskList().get(0).getStatus());
            testTaskList.changeStatus("1",3);

            assertEquals(3, testTaskList.getTaskList().get(0).getStatus());
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }

    }

    @Test
    public void testChangeStatus3To1() {
        try {
            testTaskList.addTask(testTask);
            testTaskList.changeStatus("1",3);
            assertEquals(3, testTaskList.getTaskList().get(0).getStatus());
            testTaskList.changeStatus("1",1);

            assertEquals(1, testTask.getStatus());
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }

    }

    @Test
    public void testChangeStatus3To2() {
        try {
            testTaskList.addTask(testTask);
            testTaskList.changeStatus("1",3);
            assertEquals(3, testTaskList.getTaskList().get(0).getStatus());
            testTaskList.changeStatus("1",2);

            assertEquals(2, testTaskList.getTaskList().get(0).getStatus());
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }

    }

    @Test
    public void testChangeStatus3To3() {
        try {
            testTaskList.addTask(testTask);
            testTaskList.changeStatus("1",3);
            assertEquals(3, testTaskList.getTaskList().get(0).getStatus());
            testTaskList.changeStatus("1",3);

            assertEquals(3, testTaskList.getTaskList().get(0).getStatus());
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }

    }


    @Test
    public void testCheckTaskNumberValidEmpty() {
        try {
            assertFalse(testTaskList.checkTaskNumberValid("7"));
            assertFalse(testTaskList.checkTaskNumberValid("0"));
            assertFalse(testTaskList.checkTaskNumberValid("1"));
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }

    }

    @Test
    public void testCheckTaskNumberValid1() {
        try {
            testTaskList.addTask(testTask);
            assertTrue(testTaskList.checkTaskNumberValid("1"));
            assertFalse(testTaskList.checkTaskNumberValid("0"));
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }

    }

    @Test
    public void testIntegerParseIntFail() {
        try {
            Integer.parseInt("y");
            fail("NumberFormatException should have been thrown");
        } catch (NumberFormatException e) {
            //all good
        }
    }

    @Test
    public void testIntegerParseIntPass() {
        try {
            Integer.parseInt("23");

            assertEquals(23, Integer.parseInt("23"));
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }
    }

    @Test
    public void testCheckTaskNumberValidMultiple() {
        try {
            testTaskList.addTask(testTask);
            testTaskList.addTask(testTask2);
            testTaskList.addTask(testTask3);
            testTaskList.addTask(testTask4);
            testTaskList.addTask(testTask5);

            assertTrue(testTaskList.checkTaskNumberValid("1"));
            assertTrue(testTaskList.checkTaskNumberValid("2"));
            assertTrue(testTaskList.checkTaskNumberValid("3"));
            assertTrue(testTaskList.checkTaskNumberValid("4"));
            assertTrue(testTaskList.checkTaskNumberValid("5"));
            assertFalse(testTaskList.checkTaskNumberValid("0"));
            assertFalse(testTaskList.checkTaskNumberValid("6"));
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }
    }

    @Test
    public void testCheckTaskNumberNotValid() {
        testTaskList.addTask(testTask);
        assertFalse(testTaskList.checkTaskNumberValid("6"));
        assertFalse(testTaskList.checkTaskNumberValid("y"));
    }

    @Test
    public void testRemoveListWithOne(){
        try {
            testTaskList.addTask(testTask);
            testTaskList.removeTask("1");

            assertEquals(0,testTaskList.length());
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }
    }

    @Test
    public void testRemoveListWithMultipleFromMiddle(){
        try {
            testTaskList.addTask(testTask);
            testTaskList.addTask(testTask2);
            testTaskList.addTask(testTask3);
            testTaskList.removeTask("2");

            assertEquals(2,testTaskList.length());
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }
        assertEquals(2,testTaskList.length());
    }

    @Test
    public void testRemoveListWithMultipleFromEnd(){
        try {
            testTaskList.addTask(testTask);
            testTaskList.addTask(testTask2);
            testTaskList.addTask(testTask3);
            testTaskList.addTask(testTask4);
            testTaskList.removeTask("4");

            assertEquals(3,testTaskList.length());
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }
    }

    @Test
    public void testRemoveListWithMultipleTimesFromEnd(){
        try {
            testTaskList.addTask(testTask);
            testTaskList.addTask(testTask2);
            testTaskList.addTask(testTask3);
            testTaskList.addTask(testTask4);
            testTaskList.removeTask("4");
            testTaskList.removeTask("2");

            assertEquals(2,testTaskList.length());
        } catch (NumberFormatException e) {
            fail("NumberFormatException should not have been thrown");
        }
    }

}
