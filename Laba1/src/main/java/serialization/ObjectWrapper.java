package serialization;

import main_entities.Lesson;
import java.util.ArrayList;

public class ObjectWrapper {

    private ArrayList<Lesson> allEditions;

    public ArrayList<Lesson> getAllEditions() {
        return allEditions;
    }

    public void setAllEditions(ArrayList<Lesson> allEditions) {
        this.allEditions = allEditions;
    }
}
