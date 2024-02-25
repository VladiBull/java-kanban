public class Subtask extends Task {
    private final int epicID;
    public Subtask(String name, String description, Status status, int epicID) {
        super(name, description, status);
        this.epicID = epicID;
    }
}
