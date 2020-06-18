package serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import main_entities.Lesson;
import java.io.File;
import java.util.ArrayList;


public class JsonSer implements Serializator {
    @Override
    public void serialize(ArrayList<Lesson> lessonList, String fileName) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWrapper objectWrapper = new ObjectWrapper();
        objectWrapper.setAllEditions(lessonList);
        objectMapper.configure( SerializationFeature.INDENT_OUTPUT,true);
        objectMapper.writeValue(new File(fileName),objectWrapper);
    }


    @Override
    public ArrayList<Lesson> deserialize(String fileName) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWrapper objectWrapper = objectMapper.readValue(new File(fileName), ObjectWrapper.class);
        return objectWrapper.getAllEditions();
    }
}
