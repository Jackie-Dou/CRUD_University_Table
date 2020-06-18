package serialization;

import main_entities.Laboratory;
import main_entities.Lecture;
import main_entities.Lesson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class OtherSer implements Serializator {

    @Override
    public void serialize(ArrayList<Lesson> lessonList, String fileName) throws Exception {
        FileWriter writer = new FileWriter(fileName);
        for (Lesson temp : lessonList){
            writer.append(temp.writeData());
            writer.append('\n');
        }
        writer.close();
    }

    @Override
    public ArrayList<Lesson> deserialize(String fileName) throws Exception {
        FileReader reader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(reader);
        ArrayList<Lesson> lessonList = new ArrayList<>();
        String line = bufferedReader.readLine();
        while(line != null){
            String[] tokens = line.split(";");
            Lesson lesson;
            switch (tokens[0]) {
                case "Laboratory":{
                    lesson = new Laboratory();
                    break;
                }
                case "Lecture":{
                    lesson = new Lecture();
                    break;
                }
                default:
                    return null;
            }
            lesson.readData(tokens);
            lessonList.add(lesson);
            line = bufferedReader.readLine();
        }
        reader.close();
        bufferedReader.close();
        return lessonList;
    }
}
