import java.util.ArrayList;

public class Epic extends Task {
    ArrayList<Subtask> subtasksInEpic = new ArrayList<>();

    public Epic(String name, String description, Status status) {
        super(name, description, status);
    }

    ArrayList<Subtask> getEpicSubtasks() {
        return subtasksInEpic;
    }
}

