package creators;
import main_entities.*;

public class LectureFactory implements LessonFactory {
    @Override
    public Lecture createLesson() {
        return new Lecture();
    }
}