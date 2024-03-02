package ru.yandex.tasktracker.service;

import ru.yandex.tasktracker.model.Epic;
import ru.yandex.tasktracker.model.Subtask;
import ru.yandex.tasktracker.model.Task;
import ru.yandex.tasktracker.model.Status;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private int idCounter = 0;

    public Task addTask(Task newTask) {
        newTask.setId(generateID());
        tasks.put(newTask.getId(), newTask);
        return newTask;
    }

    public Task updateTask(Task updatedTask) {
        tasks.put(updatedTask.getId(), updatedTask);
        return updatedTask;
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public Epic addEpic(Epic newEpic) {
        newEpic.setId(generateID());
        epics.put(newEpic.getId(), newEpic);
        return newEpic;
    }

    public Epic updateEpic(Epic updatedEpic) {

        Epic oldEpic = epics.get(updatedEpic.getId());
        oldEpic.setName(updatedEpic.getName());
        oldEpic.setDescription(updatedEpic.getDescription());
        return oldEpic;
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public void deleteEpic(int id) {
        Epic epic = epics.get(id);
        ArrayList<Integer> subtasksToRemove = epic.getSubtasksID();

        epics.remove(id);
        for (Integer subtask : subtasksToRemove) {
            subtasks.remove(subtask);
        }
    }

    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public Subtask addSubtask(Subtask newSubtask) {
        int epicID = newSubtask.getEpicID();
        Epic epic = epics.get(epicID);

        newSubtask.setId(generateID());
        subtasks.put(newSubtask.getId(), newSubtask);
        epic.getSubtasksID().add(newSubtask.getId());
        changeEpicStatus(epic);

        return newSubtask;
    }

    public Subtask updateSubtask(Subtask updatedSubtask) {
        subtasks.put(updatedSubtask.getId(), updatedSubtask);

        int epicID = updatedSubtask.getEpicID();
        Epic epic = epics.get(epicID);
        changeEpicStatus(epic);

        return updatedSubtask;
    }

    public Subtask getSubtask(int id) {
        return subtasks.get(id);
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void deleteSubtask(int id) {
        Subtask subtask = subtasks.get(id);
        int epicID = subtask.getEpicID();
        Epic epic = epics.get(epicID);

        subtasks.remove(id);
        epic.getSubtasksID().remove((Integer) id);
        changeEpicStatus(epic);
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubtasksID().clear();
            changeEpicStatus(epic);
        }
    }

    public ArrayList<Subtask> getSubtasksFromEpic(int epicID) {
        ArrayList<Subtask> subtasksList = new ArrayList<>();
        Epic epic = epics.get(epicID);
        ArrayList<Integer> subtasksID = epic.getSubtasksID();
        for (int id : subtasksID) {
            subtasksList.add(subtasks.get(id));
        }
        return subtasksList;
    }

    private void changeEpicStatus(Epic epic) {
        ArrayList<Subtask> subtasksList = new ArrayList<>();
        ArrayList<Integer> subtasksID = epic.getSubtasksID();
        for (int id : subtasksID) {
            subtasksList.add(subtasks.get(id));
        }

        if (epic.getSubtasksID().isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        }

        int newStatusCount = 0;
        int doneStatusCount = 0;

        for (Subtask subtask : subtasksList) {
            if (subtask.getStatus() == Status.NEW) {
                newStatusCount++;
            } else if (subtask.getStatus() == Status.DONE) {
                doneStatusCount++;
            } else {
                epic.setStatus(Status.IN_PROGRESS);
                return;
            }
        }

        if (newStatusCount == subtasksList.size()) {
            epic.setStatus(Status.NEW);
        } else if (doneStatusCount == subtasksList.size()) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

    private int generateID() {
        idCounter++;
        return idCounter;
    }
}