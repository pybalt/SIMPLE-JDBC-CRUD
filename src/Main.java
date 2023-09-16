import view.Status;
import view.View;

public class Main {
    public static void main(String[] args) {

        View view = new View();
        Status status = Status.RUNNING;
        while(status == Status.RUNNING){
            status = (Status) view.start();
        }

    }
}