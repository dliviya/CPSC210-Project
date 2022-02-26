package persistence;

import model.Task;
import model.TaskList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Modelled on JSON code provided by instructors
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            TaskList tl = new TaskList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTasklist() {
        try {
            TaskList tl = new TaskList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTasklist.json");
            writer.open();
            writer.write(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTasklist.json");
            tl = reader.read();
            assertEquals(0, tl.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTasklist() {
        try {
            TaskList tl = new TaskList();
            tl.addTask(new Task("trial1"));
            tl.addTask(new Task("trial2"));
            tl.addTask(new Task("trial3"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTasklist.json");
            writer.open();
            writer.write(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTasklist.json");
            tl = reader.read();
            assertEquals(3, tl.length());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }




}
