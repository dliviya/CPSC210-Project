# My 210 Personal Project

## A Time Scheduled Todo List

The task list will:
- Have three different status (incomplete, completed, overdue)
- Delete certain tasks if needed
- Provide the ability to change the status of tasks
- Add tasks

The application that I am providing will allow for people to keep track of their tasks. It will allow users to add tasks 
to a list. This task list will allow users to see the tasks and their status so that it is easy for them to keep track 
of their progress. There is also an option for users to delete tasks regardless of their status.

This application will be used by people that have a hard time keeping track of their tasks, such as students or working 
professionals. By keeping completed tasks in the list, users can see how much they have accomplished and
remain motivated as they do work.

This project is of interest to me because I have a hard time keeping track of what needs to be done. I usually use a 
calendar to write out all of my tasks, however then I find that my tasks are all over the place. As a results it is 
difficult to keep track of what I really need to do. By having the three status's rather than just incomplete and 
complete it allows users to see which tasks are actually urgent.



##User Stories

- As a user, I want to see the tasks on my list
- As a user, I want to be able to mark when a task is completed, incomplete, or overdue
- As a user, I want to be able to delete any task from my list regardless of its status
- As a user, I want to be able to add tasks to my list with a specified name
- As a user, I want to be able to see my tasks colors coded by status
- As a user, I want to be able to have a task list that looks visually nice to aid in providing a good work environment
- As a user, I want to save my task list at any time
- As a user, I want to load one previously saved list
- As a user, I want to be able to add to a previously saved list


##Phase 4: Task 2
I decided to use the Map interface. I created a new class in model called TaskStatus and I edited statusToInt() and 
defineStatus() in Task.

##Phase 4: Task 3
The first thing that we see when we look at the UML diagram is that there are a lot of dependencies in this code. There
several things I would do to refactor this code. The first thing is that I would make Task part of TaskList. This way
classes will depend on just TaskList rather than Task and TaskList. Another thing I would do is refactor TaskListGUI to
not use TaskApp as a field. This way I could do away with TaskApp and have everything controlled from one central location
(TaskListGUI). 

