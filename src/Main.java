public class Main {

    public static void main(String[] args) {

        System.out.println("Поехали!");

        TaskManager manager = new TaskManager();

        Task task1 = manager.addTask(new Task("Задача 1", "Создать задачу 1", Status.NEW));

        Epic epic1 = manager.addEpic(new Epic("Эпик 1", "Создать эпик 1", Status.NEW));
        Epic epic2 = manager.addEpic(new Epic("Эпик 2", "Создать эпик 2", Status.NEW));

        Subtask subtask1 = manager.addSubtask(new Subtask("Подзадача 1", "Создать подзадачу 1",
                Status.NEW, epic1.getId()), epic1);
        Subtask subtask2 = manager.addSubtask(new Subtask("Подзадача 2", "Создать подзадачу 2",
                Status.DONE, epic1.getId()), epic1);
        Subtask subtask3 = manager.addSubtask(new Subtask("Подзачача 3", "Создать подзадачу 3",
                Status.IN_PROGRESS, epic2.getId()), epic2);
        Subtask subtask4 = manager.addSubtask(new Subtask("Подзачача 4", "Создать подзадачу 4",
                Status.DONE, epic2.getId()), epic2);

        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());
        System.out.println(manager.getSubtasksFromEpic(epic1));
        System.out.println(manager.getSubtasksFromEpic(epic2));
        manager.changeEpicStatus(epic1);
        manager.changeEpicStatus(epic2);
        System.out.println(epic1.getStatus());
        System.out.println(epic2.getStatus());
    }
}