package ru.yandex.tasktracker;

import ru.yandex.tasktracker.service.TaskManager;
import ru.yandex.tasktracker.model.Epic;
import ru.yandex.tasktracker.model.Subtask;
import ru.yandex.tasktracker.model.Task;
import ru.yandex.tasktracker.model.Status;

public class Main {

    public static void main(String[] args) {

        System.out.println("Поехали!");

        TaskManager manager = new TaskManager();

        Task task1 = manager.addTask(new Task("Задача 1", "Создать задачу 1", Status.NEW));

        Epic epic1 = manager.addEpic(new Epic("Эпик 1", "Создать эпик 1"));
        Epic epic2 = manager.addEpic(new Epic("Эпик 2", "Создать эпик 2"));

        Subtask subtask1 = manager.addSubtask(new Subtask("Подзадача 1", "Создать подзадачу 1",
                Status.NEW, epic1.getId()));
        Subtask subtask2 = manager.addSubtask(new Subtask("Подзадача 2", "Создать подзадачу 2",
                Status.DONE, epic1.getId()));
        Subtask subtask3 = manager.addSubtask(new Subtask("Подзачача 3", "Создать подзадачу 3",
                Status.IN_PROGRESS, epic2.getId()));
        Subtask subtask4 = manager.addSubtask(new Subtask("Подзачача 4", "Создать подзадачу 4",
                Status.DONE, epic2.getId()));

        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());
        System.out.println(manager.getSubtasksFromEpic(2));
        System.out.println(manager.getSubtasksFromEpic(3));
        System.out.println(epic1.getStatus());
        System.out.println(epic2.getStatus());
        System.out.println(epic1.getSubtasksID());
        manager.deleteSubtask(5);
        System.out.println(epic1.getSubtasksID());
    }
}