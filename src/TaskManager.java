import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private static int idCounter = 0;

    private static int generateID() {
        idCounter++;
        return idCounter;
    }

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
        return new ArrayList<>((tasks.values()));
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
        tasks.put(updatedEpic.getId(), updatedEpic);
        return updatedEpic;
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>((epics.values()));
    }

    public void deleteEpic(int id) {
        epics.remove(id);
    }

    public void deleteAllEpics() {
        epics.clear();
    }

    public Subtask addSubtask(Subtask newSubtask, Epic epic) {
        newSubtask.setId(generateID());
        subtasks.put(newSubtask.getId(), newSubtask);
        epic.subtasksInEpic.add(newSubtask);
        return newSubtask;
    }

    public Subtask updateSubtask(Subtask updatedSubtask) {
        subtasks.put(updatedSubtask.getId(), updatedSubtask);
        return updatedSubtask;
    }

    public Subtask getSubtask(int id) {
        return subtasks.get(id);
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>((subtasks.values()));
    }

    public void deleteSubtask(int id) {
        subtasks.remove(id);
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
    }

    public ArrayList<Subtask> getSubtasksFromEpic(Epic epic) {
        return epic.subtasksInEpic;
    }

    public void changeEpicStatus(Epic epic) {
        ArrayList<Subtask> subtasksList = epic.getEpicSubtasks();

        if (epic.getEpicSubtasks().isEmpty()) {
            epic.setStatus(Status.NEW);
        }

        int newStatusCount = 0;
        int doneStatusCount = 0;

        for (Subtask subtask : subtasksList) {
            if (subtask.getStatus() == Status.NEW) {
                newStatusCount++;
            } else if (subtask.getStatus() == Status.DONE) {
                doneStatusCount++;
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
}