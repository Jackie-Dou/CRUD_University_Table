import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import creators.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import main_entities.*;
import serialization.Serializator;
import serialization.serializatorFactory;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class AppController1 implements Initializable {

    private final ObservableList<String> typesList =
            FXCollections.observableArrayList("Lecture", "Laboratory");
    private final ObservableList<String> coursesList =
            FXCollections.observableArrayList("1", "2", "3", "4");
    private final ObservableList<String> timesList =
            FXCollections.observableArrayList("(1) 8.00-9.35", "(2) 9.45-11.20", "(3) 11.40-13.15", "(4) 13.25-15.00", "(5) 15.20-16.55", "(6) 17.05-18.40");
    private final ObservableList<String> newList =
            FXCollections.observableArrayList("Tutor", "Group", "Subject");
    private final ObservableList<String> facultyList =
            FXCollections.observableArrayList("FITY", "FKSIS", "FRE", "IEF");

    ArrayList<Lesson> existLessonsList = new ArrayList<>();
    ArrayList<Subject> existSubjectsList = new ArrayList<>();
    ArrayList<Tutor> existTutorsList = new ArrayList<>();
    ArrayList<Group> existGroupsList = new ArrayList<>();

    @FXML
    private TableView<Lesson> tblResults;
    @FXML
    private TableColumn<Lesson, String>  tblclmnTime;
    @FXML
    private TableColumn<Lesson, String> tblclmnSubject;
    @FXML
    private TableColumn<Lesson, String> tblclmnTutor;
    @FXML
    private TableColumn<Lesson, String> tblclmnFaculty;
    @FXML
    private TableColumn<Lesson, String> tblclmnCourse;

    @FXML
    private ComboBox<String> cmbbxTypeOfClass;
    @FXML
    private ComboBox<String> cmbbxTimeOfClass;
    @FXML
    private ComboBox<String> cmbbxCourse;
    @FXML
    private TextField txtfldTopic;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private ComboBox<String> cmbbxSubject;
    @FXML
    private ComboBox<String> cmbbxTutor;
    @FXML
    private ComboBox<String> cmbbxFaculty;
    @FXML
    private ComboBox<String> cmbbxGroup;
    @FXML
    private ComboBox<String> cmbbxNew;
    @FXML
    private ComboBox<String> cmbbxChoose;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDeleteDetails;
    @FXML
    private TextField txtfld1;
    @FXML
    private TextField txtfld2;
    @FXML
    private TextField txtfld3;
    @FXML
    private Label lblNew;

    ObservableList<String> groupsList = FXCollections.observableArrayList();
    ObservableList<String> tutorsList = FXCollections.observableArrayList();
    ObservableList<String> subjectsList = FXCollections.observableArrayList();

    boolean check = false;
    boolean isnew = false;

    Class zipArchive;
    URLClassLoader loader;
    Object obj;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File fileJar = new File("plagins.jar");
        URL urlWork = null;
        try {
            urlWork = fileJar.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        loader = new URLClassLoader(new URL[]{urlWork});
        try {
            zipArchive = Class.forName("archives.ZIP",true,loader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            obj = zipArchive.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        txtfldTopic.setVisible(false);
        cmbbxGroup.setVisible(false);
        cmbbxTypeOfClass.setItems(typesList);
        cmbbxTimeOfClass.setItems(timesList);
        cmbbxCourse.setItems(coursesList);
        cmbbxFaculty.setItems(facultyList);
        cmbbxNew.setItems(newList);
        txtfld1.setVisible(false);
        txtfld2.setVisible(false);
        txtfld3.setVisible(false);
        lblNew.setText(" If you want add new, fill fields and click \"Save\".\n If you want to edit entity, choose it, edit fields\n and click \"Save\"");
    }

    @FXML
    private void oncmbbxTypeOfClassChange() {
        if (cmbbxTypeOfClass.getValue().equals("Lecture")) {
            txtfldTopic.setVisible(true);
            cmbbxGroup.setVisible(false);
        }
        if (cmbbxTypeOfClass.getValue().equals("Laboratory")) {
            txtfldTopic.setVisible(false);
            cmbbxGroup.setVisible(true);
        }
    }

    @FXML
    private void oncmbbxNewChange() {
        isnew = true;
        if ("Tutor".equals(cmbbxNew.getValue())) {
            txtfld1.setPromptText("Name");
            txtfld1.setVisible(true);
            txtfld2.setPromptText("Degree");
            txtfld2.setVisible(true);
            txtfld3.setPromptText("Post");
            txtfld3.setVisible(true);
            cmbbxChoose.setItems(tutorsList);
        }
        if ("Subject".equals(cmbbxNew.getValue())) {
            txtfld1.setPromptText("Name");
            txtfld1.setVisible(true);
            txtfld2.setPromptText("Department");
            txtfld2.setVisible(true);
            txtfld3.setVisible(false);
            cmbbxChoose.setItems(subjectsList);
        }
        if ("Group".equals(cmbbxNew.getValue())) {
            txtfld1.setPromptText("Number");
            txtfld1.setVisible(true);
            txtfld2.setPromptText("Faculty");
            txtfld2.setVisible(true);
            txtfld3.setPromptText("Course");
            txtfld3.setVisible(true);
            cmbbxChoose.setItems(groupsList);
        }
    }

    @FXML
    private void oncmbbxChooseChange() {
        check = true;
        isnew = false;
        switch (cmbbxNew.getValue()) {
            case ("Tutor"):
                for (Tutor temp1 : existTutorsList) {
                    if (temp1.getName().equals(cmbbxChoose.getValue())) {
                        txtfld1.setText("You can't change name of tutor");
                        txtfld2.setText(temp1.getDegree());
                        txtfld3.setText(temp1.getPost());
                    }
                }
                break;
            case ("Subject"):
                for (Subject temp1 : existSubjectsList) {
                    if (temp1.getName().equals(cmbbxChoose.getValue())) {
                        txtfld1.setText("You can't change name of subject");
                        txtfld2.setText(temp1.getDepartment());
                    }
                }
                break;
            case ("Group"):
                for (Group temp1 : existGroupsList) {
                    if (temp1.getNumber().equals(cmbbxChoose.getValue())) {
                        txtfld1.setText("You can't change number of group");
                        txtfld2.setText(temp1.getFaculty());
                        txtfld3.setText(temp1.getCourse());
                    }
                }
                break;
        }
    }

    @FXML
    private void onbtnSaveClick() {
        switch (cmbbxNew.getValue()) {
            case ("Tutor"):
                if (check) {
                    for (Tutor temp1 : existTutorsList) {
                        if (temp1.getName().equals(cmbbxChoose.getValue())) {
                            temp1.setDegree(txtfld2.getText());
                            temp1.setPost(txtfld3.getText());
                            break;
                        }
                    }
                    check = false;
                }
                if (!check && isnew) {
                    for (String temp1 : tutorsList) {
                        if (temp1.equals(txtfld1.getText())) {
                            return;
                        }
                    }
                    Tutor newTutor = new Tutor();
                    newTutor.setName(txtfld1.getText());
                    newTutor.setDegree(txtfld2.getText());
                    newTutor.setPost(txtfld3.getText());
                    existTutorsList.add(newTutor);
                    tutorsList.add(txtfld1.getText());
                    cmbbxTutor.setItems(tutorsList);
                    isnew = true;
                }
                break;
            case ("Subject"):
                if (check) {
                    for (Subject temp1 : existSubjectsList) {
                        if (temp1.getName().equals(cmbbxChoose.getValue())) {
                            temp1.setDepartment(txtfld2.getText());
                            break;
                        }
                    }
                    check = false;
                }
                if (!check && isnew) {
                    for (String temp1 : subjectsList) {
                        if (temp1.equals(txtfld1.getText())) {
                            return;
                        }
                    }
                    Subject newSubject = new Subject();
                    newSubject.setName(txtfld1.getText());
                    newSubject.setDepartment(txtfld2.getText());
                    existSubjectsList.add(newSubject);
                    subjectsList.add(txtfld1.getText());
                    cmbbxSubject.setItems(subjectsList);
                    isnew = true;
                }
                break;
            case ("Group"):
                if (check) {
                    for (Group temp1 : existGroupsList) {
                        if (temp1.getNumber().equals(cmbbxChoose.getValue())) {
                            temp1.setFaculty(txtfld2.getText());
                            temp1.setCourse(txtfld3.getText());
                            break;
                        }
                    }
                    check = false;
                }
                if (!check && isnew) {
                    for (String temp1 : groupsList) {
                        if (temp1.equals(txtfld1.getText())) {
                            return;
                        }
                    }
                    Group newGroup = new Group();
                    newGroup.setNumber(txtfld1.getText());
                    newGroup.setFaculty(txtfld2.getText());
                    newGroup.setCourse(txtfld3.getText());
                    existGroupsList.add(newGroup);
                    groupsList.add(txtfld1.getText());
                    cmbbxGroup.setItems(groupsList);
                    isnew = false;
                }
                break;
        }
        ClearAll();
    }

    @FXML
    private void onbtnDeleteDetailsClick() {
        switch (cmbbxNew.getValue()) {
            case ("Tutor"):
                if (check) {
                    for (Tutor temp1 : existTutorsList) {
                        if (temp1.getName().equals(cmbbxChoose.getValue())) {
                            tutorsList.remove(temp1.getName());
                            existTutorsList.remove(temp1);
                            break;
                        }
                    }
                }
                break;
            case ("Subject"):
                if (check) {
                    for (Subject temp1 : existSubjectsList) {
                        if (temp1.getName().equals(cmbbxChoose.getValue())) {
                            subjectsList.remove(temp1.getName());
                            existSubjectsList.remove(temp1);
                            break;
                        }
                    }
                }
                break;
            case ("Group"):
                if (check) {
                    for (Group temp1 : existGroupsList) {
                        if (temp1.getNumber().equals(cmbbxChoose.getValue())) {
                            groupsList.remove(temp1.getNumber());
                            existGroupsList.remove(temp1);
                            break;
                        }
                    }
                }
                break;
        }
        ClearAll();
    }


    private void ClearAll() {
        cmbbxChoose.setItems(null);
        switch (cmbbxNew.getValue()) {
            case ("Tutor"):
                cmbbxChoose.setItems(tutorsList);
                break;
            case ("Subject"):
                cmbbxChoose.setItems(subjectsList);
                break;
            case ("Group"):
                cmbbxChoose.setItems(groupsList);
                break;
        }
        txtfld1.clear();
        txtfld2.clear();
        txtfld3.clear();
    }

    Comparator<Lesson> lessonTimeComparator = new Comparator<Lesson>() {
        @Override
        public int compare(Lesson l1, Lesson l2) {
            return l1.time.compareTo(l2.time);
        }
    };

    private void createLab() {
        LaboratoryFactory newFact = new LaboratoryFactory();
        Laboratory newLaboratory = newFact.createLesson();
        for (Group temp : existGroupsList) {
            cmbbxGroup.getValue();
            newLaboratory.setGroup(temp);
        }
        newLaboratory.setGroup_number(cmbbxGroup.getValue());
        newLaboratory.setFaculty(cmbbxFaculty.getValue());
        for (Tutor temp : existTutorsList) {
            cmbbxTutor.getValue();
            {
                newLaboratory.setTutor(temp); }
        }
        for (Subject temp : existSubjectsList) {
            cmbbxSubject.getValue();
            {
                newLaboratory.setSubject(temp); }
        }
        newLaboratory.setSubject_name(cmbbxSubject.getValue());
        newLaboratory.setTutor_name(cmbbxTutor.getValue());
        newLaboratory.setCourse(cmbbxCourse.getValue());
        newLaboratory.setTime(cmbbxTimeOfClass.getValue());
        newLaboratory.setType(cmbbxTypeOfClass.getValue());
        existLessonsList.add(newLaboratory);
        existLessonsList.sort(lessonTimeComparator);
    }

    private void createLect() {
        LectureFactory newFact = new LectureFactory();
        Lecture newLecture = newFact.createLesson();
        newLecture.setFaculty(cmbbxFaculty.getValue());
        newLecture.setTopic(txtfldTopic.getText());
        for (Tutor temp : existTutorsList) {
            cmbbxTutor.getValue();
            {
                newLecture.setTutor(temp); }
        }
        for (Subject temp : existSubjectsList) {
            cmbbxSubject.getValue();
            {
                newLecture.setSubject(temp); }
        }
        newLecture.setSubject_name(cmbbxSubject.getValue());
        newLecture.setTutor_name(cmbbxTutor.getValue());
        newLecture.setCourse(cmbbxCourse.getValue());
        newLecture.setTime(cmbbxTimeOfClass.getValue());
        newLecture.setType(cmbbxTypeOfClass.getValue());
        existLessonsList.add(newLecture);
        existLessonsList.sort(lessonTimeComparator);
    }

    @FXML
    private void onbtnAddClick() {
        if (cmbbxTypeOfClass.getValue()==null || cmbbxFaculty.getValue()==null || cmbbxTutor.getValue()==null || cmbbxSubject.getValue()==null || cmbbxCourse.getValue()==null || cmbbxTimeOfClass.getValue()==null) {
            return;
        }
        if (cmbbxTypeOfClass.getValue().equals("Laboratory")) {
            createLab();
        }
        if (cmbbxTypeOfClass.getValue().equals("Lecture")) {
            createLect();
        }
        drawTable();
    }

    private void drawTable() {
        ObservableList<Lesson> lessonsObservableList = FXCollections.observableList(existLessonsList);
        tblclmnTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        tblclmnSubject.setCellValueFactory(new PropertyValueFactory<>("subject_name"));
        tblclmnTutor.setCellValueFactory(new PropertyValueFactory<>("tutor_name"));
        tblclmnFaculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        tblclmnCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        tblResults.setItems(lessonsObservableList);
        tblResults.refresh();
    }

    Lesson choosenLesson;

    public void onTableClicked(MouseEvent mouseEvent) {
        if (!existLessonsList.isEmpty()) {
            Lesson selectedItem = tblResults.getSelectionModel().getSelectedItem();
            if (mouseEvent.getButton()==MouseButton.PRIMARY){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Addictional Information");
                alert.setHeaderText(null);
                alert.setContentText("Tutor name: "+selectedItem.getTutor().getName()+"\n  - Degree: "+selectedItem.getTutor().getDegree()+"\n  - Post: "+selectedItem.getTutor().getPost()+"\n");
                alert.setContentText(alert.getContentText()+"Subject name: "+selectedItem.getSubject().getName()+"\n  - Department: "+selectedItem.getSubject().getDepartment()+"\n");
                if (selectedItem.getType().equals("Lecture")) {
                    Lecture lect = (Lecture)selectedItem;
                    alert.setContentText(alert.getContentText()+"Topic: "+lect.getTopic()+"\n");
                }
                if (selectedItem.getType().equals("Laboratory")) {
                    Laboratory lab = (Laboratory)selectedItem;
                    alert.setContentText(alert.getContentText()+"Group: "+lab.getGroup_number()+"\n");
                }
                alert.showAndWait();
            }
            else {
                cmbbxTypeOfClass.setValue(selectedItem.getType());
                cmbbxSubject.setValue(selectedItem.getSubject_name());
                cmbbxTimeOfClass.setValue(selectedItem.getTime());
                cmbbxTutor.setValue(selectedItem.getTutor_name());
                cmbbxFaculty.setValue(selectedItem.getFaculty());
                cmbbxCourse.setValue(selectedItem.getCourse());
                if (selectedItem.getType().equals("Lecture")) {
                    Lecture lect = (Lecture)selectedItem;
                    txtfldTopic.setText(lect.getTopic());
                }
                if (selectedItem.getType().equals("Laboratory")) {
                    Laboratory lab = (Laboratory)selectedItem;
                    cmbbxGroup.setValue(lab.getGroup_number());
                }
                choosenLesson = selectedItem;
            }
        }

    }

    @FXML
    private void onbtnEditClick() {
        existLessonsList.removeIf(temp -> choosenLesson == temp);
        if (cmbbxTypeOfClass.getValue().equals("Laboratory")) {
            createLab();
        }
        if (cmbbxTypeOfClass.getValue().equals("Lecture")) {
            createLect();
        }
        drawTable();
    }

    @FXML
    public void onbtnDeleteClick() {
        existLessonsList.removeIf(temp -> choosenLesson == temp);
        drawTable();
    }


    private void workSaveExtension(String extension1, String extension2, String fileName) throws Exception {
        serializatorFactory serFact = new serializatorFactory();
        Serializator serialization = serFact.createSerializator(extension1);
        serialization.serialize(existLessonsList, fileName);
        if (extension2.equals("zip")) {
            Method extMethod = zipArchive.getMethod("archive", String.class);
            extMethod.invoke(obj, fileName);
        } else if (!extension2.equals("no")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect file extension, no such plugin");
            alert.showAndWait();
        }
    }

    private void workUploadExtension(String extension1, String extension2, String fileName, String dirName) throws Exception {
        if (extension2.equals("zip")) {
            Method extMethod1 = zipArchive.getMethod("fromArchieve", String.class, String.class);
            int index1 = dirName.lastIndexOf(".");
            dirName = dirName.substring(0, index1);
            extMethod1.invoke(obj, fileName, dirName);
            //int index = fileName.lastIndexOf(".");
            //fileName = fileName.substring(0, index);
            int index2 = dirName.lastIndexOf("\\");
            String newFileName = dirName.substring(index2+1);
            newFileName = dirName+"\\"+newFileName+"."+extension1;
            fileName = newFileName;
        } else if (!extension2.equals("no")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect file extension, no such plugin");
            alert.showAndWait();
        }
        serializatorFactory serFact = new serializatorFactory();
        Serializator serializator = serFact.createSerializator(extension1);
        existLessonsList = serializator.deserialize(fileName);
    }


    @FXML
    public void onbtnSaveFileClick() throws Exception {
        if (!existLessonsList.isEmpty()) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Document");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Binary files", "*.dat"),
                    new FileChooser.ExtensionFilter("JSON Documents", "*.json"),
                    new FileChooser.ExtensionFilter("Text files", "*.txt"),
                    new FileChooser.ExtensionFilter("ZIP(Binary files)", "*.dat.zip"),
                    new FileChooser.ExtensionFilter("ZIP(JSON Documents)", "*.json.zip"),
                    new FileChooser.ExtensionFilter("ZIP(Text files)", "*.txt.zip")
            );
            File file = fileChooser.showSaveDialog(lblNew.getScene().getWindow());
            //File selectedFile = fileChooser.showSaveDialog(mainStage);
            if (file != null) {
                String fileName = file.getPath();

                int index = fileName.lastIndexOf(".");
                String extension2 = fileName.substring(index + 1);
                String extension1;
                String innerName;
                if (extension2.equals("zip")) {
                    innerName = fileName.substring(0, index);
                    int index1 = innerName.lastIndexOf(".");
                    extension1 = innerName.substring(index1 + 1);
                } else {
                    extension1 = extension2;
                    extension2 = "no";
                    innerName = fileName;
                }
                workSaveExtension(extension1,extension2, innerName);
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Table is empty");
            alert.showAndWait();
        }
    }

    public void FullFillDetails () {
        existSubjectsList.clear();
        existTutorsList.clear();
        existGroupsList.clear();
        for (Lesson temp: existLessonsList) {
            if (!subjectsList.contains(temp.getSubject_name())){
                existSubjectsList.add(temp.getSubject());
                subjectsList.add(temp.getSubject_name());
            }
            if (!tutorsList.contains(temp.getTutor_name())){
                existTutorsList.add(temp.getTutor());
                tutorsList.add(temp.getTutor_name());
            }
            if (temp.getType().equals("Laboratory")) {
                Laboratory newtemp = new Laboratory();
                newtemp = (Laboratory) temp;
                if (!groupsList.contains(newtemp.getGroup_number())){
                    existGroupsList.add(newtemp.getGroup());
                    groupsList.add(newtemp.getGroup().getNumber());
                }
            }
        }
        cmbbxTutor.setItems(tutorsList);
        cmbbxSubject.setItems(subjectsList);
        cmbbxGroup.setItems(groupsList);
    }

    @FXML
    public void onbtnUploadFileClick() throws Exception {
        Window window = lblNew.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Document");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Binary files", "*.dat"),
                new FileChooser.ExtensionFilter("JSON Documents", "*.json"),
                new FileChooser.ExtensionFilter("Text files", "*.txt"),
                new FileChooser.ExtensionFilter("ZIP(Binary files)", "*.dat.zip"),
                new FileChooser.ExtensionFilter("ZIP(JSON Documents)", "*.json.zip"),
                new FileChooser.ExtensionFilter("ZIP(Text files)", "*.txt.zip")
        );
        File file = fileChooser.showOpenDialog(window);
        if (file != null) {
            String fileName = file.getPath();
            int index = fileName.lastIndexOf(".");
            String extension2 = fileName.substring(index + 1);
            String extension1;
            String innerName;
            if (extension2.equals("zip")) {
                innerName = fileName.substring(0, index);
                int index1 = innerName.lastIndexOf(".");
                extension1 = innerName.substring(index1 + 1);
            } else {
                extension1 = extension2;
                extension2 = "no";
                innerName = fileName;
            }
            workUploadExtension(extension1,extension2, fileName, innerName);


            FullFillDetails();

            drawTable();
        }
    }
}