package serialization;
import main_entities.Lesson;

import java.io.*;
import java.util.ArrayList;

public class BinarySer implements Serializator {

    @Override
    public void serialize(ArrayList<Lesson> lessonList, String fileName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(lessonList);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList deserialize(String fileName) throws Exception {
        ArrayList lessonList;
        FileInputStream fileStream = new FileInputStream(fileName);
        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
        lessonList = (ArrayList) objectStream.readObject();
        fileStream.close();
        objectStream.close();
        return lessonList;
    }

}
