package serialization;

import main_entities.Lesson;
import java.util.ArrayList;

public interface Serializator {
        void serialize(ArrayList<Lesson> lessonList, String fileName) throws Exception;
        ArrayList<Lesson> deserialize(String fileName) throws Exception;
}
