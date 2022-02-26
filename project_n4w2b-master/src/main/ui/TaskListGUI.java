package ui;

import model.Task;
import model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

//Task List GUI Application
public class TaskListGUI implements ActionListener {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 700;
    private boolean isAddTask = false;
    private JPanel menuPanel;
    private JPanel resultPanel;
    private JPanel inputPanel;

    private JButton menuItem1;
    private JButton menuItem2;
    private JButton menuItem3;
    private JButton menuItem4;
    private JButton menuItem5;
    private JButton menuItem6;
    private JButton toggleSound;
    private JButton send;
    private JButton submitTaskButton;
    private JButton updateStatusButton;
    private JTextArea resultsLabel = new JTextArea("");
    private JTextField tf;
    private JRadioButton[] radioButton;
    private ButtonGroup radioButtonGroup = new ButtonGroup();
    private ButtonGroup statusButtonGroup = new ButtonGroup();
    private JComboBox<String> statusListDropdown;
    private TaskApp taskApp = new TaskApp();
    private ArrayList<Task> listOfTasks;
    private boolean isSoundOn = true;
    private TaskList taskList;
    private String sound = "sound/software-interface-start.wav";


    //MODIFIES: this
    //EFFECTS: creates panels for GUI
    public void createGUI() {
        JFrame frame = new JFrame();
        menuPanel = new JPanel();
        resultPanel = new JPanel();
        inputPanel = new JPanel();

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("My Task List");

        menuPanel(menuPanel);
        inputPanel(inputPanel);

        frame.getContentPane().add(BorderLayout.SOUTH, inputPanel);
        frame.getContentPane().add(BorderLayout.WEST, menuPanel);
        frame.getContentPane().add(BorderLayout.CENTER, resultPanel);
        frame.setVisible(true);
        playSound(sound);
    }

    //MODIFIES: this
    //EFFECTS: creates buttons for menu
    public void menuPanel(JPanel menuPanel) {
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        toggleSound = new JButton("Sound On/Off");
        menuItem1 = new JButton("1. Display Task List");
        menuItem2 = new JButton("2. Add a Task");
        menuItem3 = new JButton("3. Change Status of Task");
        menuItem4 = new JButton("4. Remove a Task");
        menuItem5 = new JButton("5. Save Task List");
        menuItem6 = new JButton("6. Load Task List");


        toggleSound.setForeground(Color.green);


        menuPanel.add(toggleSound);
        menuPanel.add(menuItem1);
        menuPanel.add(menuItem2);
        menuPanel.add(menuItem3);
        menuPanel.add(menuItem4);
        menuPanel.add(menuItem5);
        menuPanel.add(menuItem6);

        toggleSound.addActionListener(this);
        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menuItem3.addActionListener(this);
        menuItem4.addActionListener(this);
        menuItem5.addActionListener(this);
        menuItem6.addActionListener(this);

    }

    //MODIFIES: this
    //EFFECTS: creates panel for task inout
    public void inputPanel(JPanel inputPanel) {
        JLabel label = new JLabel("Enter Text");
        tf = new JTextField(50);
        send = new JButton("Send");
        inputPanel.add(label);
        inputPanel.add(tf);
        inputPanel.add(send);
        send.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: creates panels for GUI that will update and show what is happening
    public void resultsPanel(JComponent comp) {
        resultPanel.removeAll();
        resultPanel.updateUI();
        resultsLabel.setEditable(false);
        resultPanel.add(comp);
        resultPanel.revalidate();
    }

    //EFFECTS: displays tasks
    public void displayListGUI() {
        listOfTasks = taskApp.returnTaskListObj().getTaskList();
        String statusMessage = new String();
        String list = "";
        if (listOfTasks.isEmpty()) {
            resultsLabel.setText("The list is empty");

        } else {
            for (int i = 0; i < listOfTasks.size(); i++) {
                if (listOfTasks.get(i).getStatus() == 1) {
                    statusMessage = "Incomplete";
                } else if (listOfTasks.get(i).getStatus() == 2) {
                    statusMessage = "Complete";
                } else if (listOfTasks.get(i).getStatus() == 3) {
                    statusMessage = "Overdue";
                }
                list = list + statusMessage + " " + listOfTasks.get(i).getTaskName() + "\n";
            }
            resultsLabel.setText(list);

        }
        resultsPanel(resultsLabel);

    }

    //MODIFIES: this
    //EFFECTS: adds task and if input is empty then say task is empty. Then display result
    public void addTaskGUI() {
        if (isAddTask) {
            String taskName = tf.getText();
            if (!taskName.isEmpty()) {
                taskApp.addTask(taskName);
                resultsLabel.setText("Task Added Successfully!!");
                tf.setText("");

            } else {
                resultsLabel.setText("Input is empty!!");
            }
            resultsPanel(resultsLabel);

        }
    }

    //MODIFIES: this
    //EFFECTS: proccesses user command, give action to each button
    @Override
    public void actionPerformed(ActionEvent e) {
        soundManager(e);
        if (e.getSource() == menuItem1) {
            displayListGUI();
        } else if (e.getSource() == menuItem2) {
            isAddTask = true;
            resultsLabel.setText("Input Task below and click \"send\"");
            resultsPanel(resultsLabel);
        } else if (e.getSource() == menuItem3) {
            //updatestatus
         //   displayUpdateStatusListGUI();
            updateStatusGUI();
        } else if (e.getSource() == menuItem4) {
            //remove task
            removeTaskGUI();
        } else if (e.getSource() == menuItem5) {
            saveTaskListGUI();
        } else if (e.getSource() == menuItem6) {
            //load
            loadTaskListGUI();
        } else if (e.getSource() == send) {
            addTaskGUI();
        } else if (e.getSource() == submitTaskButton) {
            removeTaskAction(radioButtonGroup.getSelection().getActionCommand());
        } else if (e.getSource() == updateStatusButton) {
            updateStatusAction(radioButtonGroup.getSelection().getActionCommand(),
                    statusListDropdown.getSelectedItem().toString());
        }
    }

    //MODIFIES: this
    //EFFECTS: loads tasklist onto results panel
    private void loadTaskListGUI() {
        taskApp.loadTaskList();
        resultsLabel.setText("List successfully loaded!!");
        resultsPanel(resultsLabel);
    }

    //EFFECTS: saves tasklist
    private void saveTaskListGUI() {
        taskApp.saveTaskList(taskApp.getTaskList());
        resultsLabel.setText("List successfully saved!!");
        resultsPanel(resultsLabel);
    }

    //MODIFIES: this
    //EFFECTS: acts as status converter
    private void updateStatusAction(String taskNumber, String status) {
        taskList = taskApp.getTaskList();
        int statusNumber = 1;
        if (status.equals("Incomplete")) {
            statusNumber = 1;
        }
        if (status.equals("Complete")) {
            statusNumber = 2;
        }
        if (status.equals("Overdue")) {
            statusNumber = 3;
        }
        taskList.changeStatus(taskNumber, statusNumber);
        displayUpdateStatusListGUI();

    }

    //MODIFIES: this
    //EFFECTS: removes task from list
    private void removeTaskAction(String taskNumber) {

        taskList = taskApp.getTaskList();
        taskList.removeTask(taskNumber);
        removeTaskGUI();
    }

    //MODIFIES: this
    //EFFECTS: displays updated list after removing task
    private void removeTaskGUI() {

        displayRemoveListGUI();
        resultPanel.add(submitTaskButton);
        submitTaskButton.addActionListener(this);
        resultPanel.revalidate();
    }

    //MODIFIES: this
    //EFFECTS: processes user command with radio buttons to select which tasks to remove
    private void displayRemoveListGUI() {

        submitTaskButton = new JButton("Remove");
        listOfTasks = taskApp.returnTaskListObj().getTaskList();
        radioButton = new JRadioButton[listOfTasks.size()];
        String statusMessage = new String();
        resultPanel.removeAll();
        resultPanel.updateUI();
        if (listOfTasks.isEmpty()) {
            resultsLabel.setText("The list is empty");
            resultsPanel(resultsLabel);

        } else {

            resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
            for (int i = 0; i < listOfTasks.size(); i++) {
                statusMessage = convertionStatus(i);
                radioButton[i] = new JRadioButton(statusMessage + " " + listOfTasks.get(i).getTaskName());
                radioButton[i].setActionCommand(String.valueOf(i + 1));
                radioButtonGroup.add(radioButton[i]);
                resultPanel.add(radioButton[i]);

            }
            radioButton[0].setSelected(true);

        }


    }

    //MODIFIES: this
    //EFFECTS: acts as status converter
    private String convertionStatus(int i) {
        String statusMessage = new String();
        if (listOfTasks.get(i).getStatus() == 1) {
            statusMessage = "Incomplete";
        } else if (listOfTasks.get(i).getStatus() == 2) {
            statusMessage = "Complete";
        } else if (listOfTasks.get(i).getStatus() == 3) {
            statusMessage = "Overdue";
        }
        return statusMessage;
    }

    //MODIFIES: this
    //EFFECTS: converts status of messages
    private void displayUpdateStatusListGUI() {

        updateStatusButton = new JButton("Update");
        listOfTasks = taskApp.returnTaskListObj().getTaskList();
        radioButton = new JRadioButton[listOfTasks.size()];
        String statusMessage = new String();
        resultPanel.removeAll();
        resultPanel.updateUI();
        if (listOfTasks.isEmpty()) {
            resultsLabel.setText("The list is empty");
            resultsPanel(resultsLabel);

        } else {

            resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
            for (int i = 0; i < listOfTasks.size(); i++) {
                statusMessage = convertionStatus(i);
                radioButton[i] = new JRadioButton(statusMessage + " "  + listOfTasks.get(i).getTaskName());
                radioButton[i].setActionCommand(String.valueOf(i + 1));
                radioButtonGroup.add(radioButton[i]);
                resultPanel.add(radioButton[i]);

            }
            radioButton[0].setSelected(true);
            addStatusRadioButtonGUI();
            resultPanel.add(updateStatusButton);
            updateStatusButton.addActionListener(this);


        }
    }


    //EFFECTS: shows updates results of status change
    private void updateStatusGUI() {
        displayUpdateStatusListGUI();
        resultPanel.revalidate();

    }

    //EFFECTS: make button to select status change
    private void addStatusRadioButtonGUI() {


        String[] statusList = {"Incomplete", "Complete", "Overdue"};
        JLabel listLabel = new JLabel("Update status:");
        statusListDropdown = new JComboBox<String>(statusList);
        resultPanel.add(listLabel);
        resultPanel.add(statusListDropdown);

    }

    //EFFECTS: plays sound when button is pressed
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: when sound is on, button is green and can play sound, else is red and is silent
    private void soundManager(ActionEvent e) {
        if (isSoundOn) {
            playSound(sound);
        }
        if (e.getSource() == toggleSound) {
            if (isSoundOn) {
                isSoundOn = false;
                toggleSound.setForeground(Color.red);
            } else {
                isSoundOn = true;
                toggleSound.setForeground(Color.green);
            }
        }
    }


}
