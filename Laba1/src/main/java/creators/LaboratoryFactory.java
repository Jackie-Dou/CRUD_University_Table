package creators;
import main_entities.*;

public class LaboratoryFactory implements LessonFactory {
    @Override
    public Laboratory createLesson() {
        return new Laboratory();
    }
}