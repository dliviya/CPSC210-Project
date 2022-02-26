package ui;

import model.Task;
import model.TaskList;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//citation for isNumeric method: https://www.baeldung.com/java-check-string-number
//Task List Application
public class TaskApp {
    private static final String JSON_STORE = "./data/taskList.json";
    Scanner scanner = new Scanner(System.in);
    TaskList taskList = new TaskList();

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processOption(String option) {
        switch (option) {
            case "1":
                displayTasks();
                break;
            case "2":
                addTaskToList();
                break;
            case "3":
                statusUpdate();
                break;
            case "4":
                removeTask();
                break;
            case "5":
                saveLoadTasksListOption(option);
                break;
            case "6":
                exitApp();
                break;
            default:
                System.out.println("Select a valid option!");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input for saving and loading task lists
    private void saveLoadTasksListOption(String option) {
        saveLoadMenu();
        String command = scanner.next();
        switch (command) {
            case "1":
                saveTaskList(taskList);
                break;
            case "2":
                loadTaskList();
                break;
            default:
                System.out.println("Select a valid option!");
        }
    }



    // MODIFIES: this
    // EFFECTS: processes user input
    public void runTaskList() {
        boolean keepGoing = true;
        String command = null;
        scanner = new Scanner(System.in);

        while (keepGoing) {
            menu();
            command = scanner.next();

            if (command.equals("6")) {
                keepGoing = false;
//                processOption(command);
                exitApp();
            } else {
                processOption(command);
            }
        }

        System.out.println("Goodbye! Stay Productive!");
    }

    //EFFECTS: prints out goodbye message and exits program
    public void exitApp() {
        System.out.println("Goodbye! Stay Productive!");
        System.exit(0);
    }

    //EFFECTS: gets user input for menu selection
    public String getOption() {
        menu();
        String option = scanner.next();

        return option;
    }

    //EFFECTS: displays tasks, if the list is empty says list is empty.
    //        also converts numerical representation of status to words.
    public void displayTasks() {

        ArrayList<Task> listOfTasks = taskList.getTaskList();
        String statusMessage = new String();
        if (listOfTasks.isEmpty()) {
            System.out.println("The list is empty");
            runTaskList();
        } else {
            for (int i = 0; i < listOfTasks.size(); i++) {
                if (listOfTasks.get(i).getStatus() == 1) {
                    statusMessage = "Incomplete";
                } else if (listOfTasks.get(i).getStatus() == 2) {
                    statusMessage = "Complete";
                } else if (listOfTasks.get(i).getStatus() == 3) {
                    statusMessage = "Overdue";
                }
                System.out.println(statusMessage + " " + (i + 1) + ". " + listOfTasks.get(i).getTaskName());

            }

        }
    }

    public TaskList returnTaskListObj() {

        //displayTasks();
        return taskList;
    }

    //MODIFIES: this
    //EFFECTS:  if the command given is numeric then check if it is a valid command.
    //          in either case if input is not valid or a numeric then print that input is not valid
    //          if it is valid and numeric then remove corresponding task from list.
    public void removeTask() {
        displayTasks();
        System.out.println("Choose task number to remove");
        String taskNumber;
        boolean check = false;
        while (!check) {
            taskNumber = scanner.next();
            if (isNumeric(taskNumber)) {
                if (!taskList.checkTaskNumberValid(taskNumber)) {
                    System.out.println("Select a valid option!");

                } else {
                    taskList.removeTask(taskNumber);
                    check = true;
                }
            } else {
                System.out.println("Select a valid option!");
                check = false;
            }


        }

    }


    // EFFECTS: displays menu of options to user
    public void menu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1.  Display Task List");
        System.out.println("\t2.  Add a Task");
        System.out.println("\t3.  Change Status of Task");
        System.out.println("\t4.  Remove a Task");
        System.out.println("\t5.  Save or Load Task List");
        System.out.println("\t6.  Exit Application");

    }

    // EFFECTS: displays save or load options to user
    public void saveLoadMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1.  Save Task List");
        System.out.println("\t2.  Load Task List");

    }

// EFFECTS: displays status menu of options to user
    public void statusUpdate() {
        displayTasks();
        System.out.println("Choose task number to change");

        boolean flag1 = false;
        boolean flag2 = false;
        while (!flag1) {
            String taskNumber = scanner.next();
            if (isNumeric(taskNumber)) {
                if (!taskList.checkTaskNumberValid(taskNumber)) {
                    System.out.println("Select a valid option!");
                } else {
                    statusMenu();
                    while (!flag2) {
                        flag1 = true;
                        flag2 = validateStatusChange(taskNumber,flag2);
                    }
                }
            } else {
                System.out.println("Select a valid option!");
                flag1 = false;
            }
        }
    }

    //EFFECTS: Prints status menu for tasks
    public void statusMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1.  Change status to incomplete");
        System.out.println("\t2.  Change status to complete");
        System.out.println("\t3.  Change status to overdue");
    }

    //EFFECTS: checks if status change is a valid input, returns true if it is, else returns false
    public boolean validateStatusChange(String taskNumber, boolean flag2) {
        String statusUpdate = scanner.next();
        if (statusUpdate.equals("1")) {
            taskList.changeStatus(taskNumber, 1);
            flag2 = true;
        } else if (statusUpdate.equals("2")) {
            taskList.changeStatus(taskNumber, 2);
            flag2 = true;
        } else if (statusUpdate.equals("3")) {
            taskList.changeStatus(taskNumber, 3);
            flag2 = true;
        } else {
            System.out.println("Select a valid option!");
        }

        return flag2;
    }

    //EFFECTS: Takes in user input and adds a task
    public void addTaskToList() {
        System.out.println("Input task name: ");
        String taskName = scanner.next();
        addTask(taskName);
    }

    public void addTask(String taskName) {
        Task task = new Task(taskName);
        taskList.addTask(task);
    }

    //EFFECTS: checks if inputted string is purely numeric.
    //          if this string is empty return false, if string is made of integers returns true
    public boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    // EFFECTS: saves the tasklist to file
    public void saveTaskList(TaskList tl) {
        JsonWriter jw = new JsonWriter(JSON_STORE);
        try {
            jw.open();
            jw.write(tl);
            jw.close();
            System.out.println("Your list has been saved!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadTaskList() {
        JsonReader jr = new JsonReader(JSON_STORE);
        try {
            taskList  = jr.read();
            System.out.println("Loaded Tasklist" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public TaskList getTaskList() {
        return taskList;
    }

}
