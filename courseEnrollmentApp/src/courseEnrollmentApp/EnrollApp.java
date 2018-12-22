package courseEnrollmentApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EnrollApp extends Application implements EventHandler<ActionEvent>{

	public static void main(String[] args) {
		launch(args);
	}
	
	Pane landing, main, signupP;
	Label appHeader, signupHeader, studentIdlbl, passwordlbl, firstNamelbl, lastNamelbl, macidlbl, passlbl, courseName, courseDesc, tuition, 
			balance;
	int balanceValue;
	TextField studentId, firstName, lastName, macid;
	PasswordField password, passfield;
	Button login, signup, exit, submit, add, enroll, remove;
	ImageView landingImg, logo, strip;
	Scene loginPage, mainPage, signupPage;
	ArrayList<String> students = new ArrayList<String>();
	boolean foundAccount;
	ChoiceBox <String> courses;
	ListView<String> list = new ListView<String>();
	ObservableList<String> items =FXCollections.observableArrayList (
	    "My Courses: ");
	Rectangle tuitionBar;
	
	public void start (Stage window) throws Exception {
		window.setTitle("Course Enrollment");
		window.initStyle(StageStyle.UNDECORATED);
		
		balanceValue = 0;
		
		final Font titleFont = Font.loadFont(EnrollApp.class.getResource("TechnicBold.ttf").toExternalForm(), 10);
		
		landing = new Pane();
		main = new Pane();
		signupP = new Pane();
		
		tuitionBar = new Rectangle(350, 150, 272, 255);
		tuitionBar.opacityProperty().set(0.3);
		
		list.setItems(items);
		list.setPrefWidth(295);
		list.setPrefHeight(255);
		list.setEditable(true);
		
		list.setCellFactory(ComboBoxListCell.forListView(items)); 
		
		mainPage = new Scene(main, 640, 480);
		loginPage = new Scene(landing, 640, 480);
		signupPage = new Scene(signupP, 640, 480);
		
		appHeader = new Label("COURSE ENROLLMENT");
		appHeader.setId("title");
		signupHeader = new Label("CREATE ACCOUNT");
		signupHeader.setId("title");
		studentIdlbl = new Label("MAC ID: ");
		studentIdlbl.setId("basicLabel");
		firstNamelbl = new Label("First Name: ");
		firstNamelbl.setId("basicLabel");
		lastNamelbl = new Label("Last Name: ");
		lastNamelbl.setId("basicLabel");
		macidlbl = new Label("MAC ID: ");
		macidlbl.setId("basicLabel");
		tuition = new Label("Tuition Balance Due: ");
		tuition.setId("titleLabel");
		balance = new Label("$ 0.00");
		balance.setId("infoLabel");
		courseName = new Label("");
		courseName.setId("titleLabel");
		courseName.setFont(titleFont);
		courseDesc = new Label("");
		courseDesc.setFont(titleFont);
		courseDesc.setId("infoLabel");
		courseDesc.setPrefWidth(265);
		courseDesc.setWrapText(true);
		firstName = new TextField();
		firstName.setId("textbox");
		lastName = new TextField();
		lastName.setId("textbox");
		macid = new TextField();
		macid.setId("textbox");
		passwordlbl = new Label("Password: ");
		passwordlbl.setId("basicLabel");
		studentId = new TextField();
		studentId.setId("textbox");
		password = new PasswordField();
		password.setId("textbox");
		login = new Button("Login");
		login.setId("button");
		add = new Button("Add to Cart");
		add.setId("buttonMain");
		add.setOnAction(this);
		enroll = new Button("Enroll");
		enroll.setId("buttonMain");
		enroll.setOnAction(this);
		remove = new Button("Remove from Cart");
		remove.setId("buttonMain");
		remove.setOnAction(this);
		passlbl = new Label("Set Password: ");
		passlbl.setId("basicLabel");
		passfield = new PasswordField();
		passfield.setId("textbox");
		courses = new ChoiceBox<>();
		courses.getItems().addAll("Math", "Physics", "Chemistry", "Biology", "Microeconomics", "Computer Science");
		courses.setValue("");
		courses.setMinWidth(200);
		
		//opens the next scene
		login.setOnAction(e -> {
			String searchCriteria = studentId.getText() + " " + password.getText();
			search(searchCriteria, students);
			if (foundAccount == true) {
				window.setScene(mainPage);
			}
			else {
				
			}
		});
		
		signup = new Button("Sign Up");
		signup.setId("button");
		
		signup.setOnAction(e -> {
			window.setScene(signupPage);
		});
		
		submit = new Button("Submit");
		submit.setId("button");
		
		submit.setOnAction(e -> {
			String newStudent = macid.getText() + " " + passfield.getText();
			students.add(newStudent);
			firstName.clear();
			lastName.clear();
			macid.clear();
			passfield.clear();
			window.setScene(loginPage);
			System.out.println(students);
		});
		
		landing.setId("background");
		main.setId("background");
		signupP.setId("background");
		
		landingImg = new ImageView();
	    Image uniImg = new Image("courseEnrollmentApp/MacU.jpg", 320, 480, false, false);
	    landingImg.setImage(uniImg);
	    
	    logo = new ImageView();
	    Image logoImg = new Image("courseEnrollmentApp/Mac Enroller.png", 320, 100, false, false);
	    logo.setImage(logoImg);
	    
	    strip = new ImageView();
	    Image stripImg = new Image("courseEnrollmentApp/strip.jpg", 640, 70, false, false);
	    strip.setImage(stripImg);
		
		landing.getChildren().addAll(studentIdlbl, passwordlbl, studentId, password, login, signup, landingImg, logo);
		main.getChildren().addAll(strip, appHeader, courses, add, remove, enroll, list, tuitionBar, courseName, courseDesc, tuition, balance);
		signupP.getChildren().addAll(firstNamelbl, lastNamelbl, macidlbl, submit, firstName, lastName, macid, passlbl, passfield, signupHeader);
		
		//landing page
		studentIdlbl.setLayoutX(60);
		studentIdlbl.setLayoutY(130);
		passwordlbl.setLayoutX(60);
		passwordlbl.setLayoutY(190);
		studentId.setLayoutX(60);
		studentId.setLayoutY(155);
		password.setLayoutX(60);
		password.setLayoutY(215);
		login.setLayoutX(60);
		login.setLayoutY(270);
		signup.setLayoutX(60);
		signup.setLayoutY(340);
		landingImg.setLayoutX(320);
		logo.setLayoutX(0);
		logo.setLayoutY(10);
		
		
		//sign up page
		signupHeader.setLayoutX(30);
		signupHeader.setLayoutY(20);
		firstNamelbl.setLayoutX(60);
		firstNamelbl.setLayoutY(110);
		lastNamelbl.setLayoutX(60);
		lastNamelbl.setLayoutY(170);
		firstName.setLayoutX(60);
		firstName.setLayoutY(135);
		lastName.setLayoutX(60);
		lastName.setLayoutY(195);
		macidlbl.setLayoutX(60);
		macidlbl.setLayoutY(235);
		macid.setLayoutX(60);
		macid.setLayoutY(260);
		passlbl.setLayoutX(60);
		passlbl.setLayoutY(295);
		passfield.setLayoutX(60);
		passfield.setLayoutY(320);
		submit.setLayoutX(60);
		submit.setLayoutY(390);
		
		//mainPage
		appHeader.setLayoutX(30);
		appHeader.setLayoutY(20);
		strip.setLayoutX(0);
		courses.setLayoutX(30);
		courses.setLayoutY(100);
		add.setLayoutX(30);
		add.setLayoutY(420);
		remove.setLayoutX(185);
		remove.setLayoutY(420);
		enroll.setLayoutX(480);
		enroll.setLayoutY(420);
		list.setLayoutX(30);
		list.setLayoutY(150);
		courseName.setLayoutX(360);
		courseName.setLayoutY(160);
		courseDesc.setLayoutX(360);
		courseDesc.setLayoutY(190);
		tuition.setLayoutX(350);
		tuition.setLayoutY(100);
		balance.setLayoutX(550);
		balance.setLayoutY(105);
		
		ChangeListener<String> changeListener = new ChangeListener<String>() { 
            @Override
            public void changed(ObservableValue<? extends String> observable, //
                    String oldValue, String newValue) {
                if (newValue != null) {
                	
                	String title = courses.getValue();
                	courseName.setFont(titleFont);
                    courseName.setText(title);
                    if (title.equals("Math")) {
                    	courseDesc.setText("Ordinary differential equations, Laplace transforms, eigenvalues and eigenvectors, applications. Three lectures, one tutorial, one lab (two hours) every other week; one term");
                    } else if (title.equals("Physics")) {
                    	courseDesc.setText("A course for engineering students. Oscillations and waves, interference; electrostatics, electric potential, circuit elements; magnetic fields. Three lectures, one lab (three hours) every other week; one term");
                    } else if (title.equals("Chemistry")) {
                    	courseDesc.setText("An introduction to chemical principles for Engineering students, including reactivity, bonding, structure, energetics and electrochemistry. Three lectures, one lab (two and one half hours) every other week; one term");
                    } else if (title.equals("Biology")) {
                    	courseDesc.setText("Structure, molecular composition and function in sub-cellular and cellular systems. Three hours (lectures, web modules), one lab (two hours); one term");
                    } else if (title.equals("Computer Science")) {
                    	courseDesc.setText("Fundamental concepts of programming languages: data types, assignment, control constructs, basic data structures, iteration, etc.; imperative and object-orientated paradigms; Three lectures, one tutorial, one lab every other week; first term");
                    } else if (title.equals("Microeconomics")) {
                    	courseDesc.setText("explore basic concepts of demand and supply, consumers and producers, market structure and policy implications. One hour (lectures, web modules) every week; one term");
                    } else {
                    	courseDesc.setText("");
                    }
                }
            }
        };
        
        courses.getSelectionModel().selectedItemProperty().addListener(changeListener);
		
		loginPage.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
		mainPage.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
		signupPage.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
		
		
		window.setScene(loginPage);
		window.show();
		
	} //end of start
	
	public void handle (ActionEvent e) {
		while (true) {
			if (e.getSource() == add) {
				String currCourse = courses.getValue();
				if (currCourse.equals("")) {
					break;
				} else {
					balanceValue += 1050;
					balance.setText("$ " + balanceValue + ".00");
					items.add(currCourse);
					courses.getItems().remove(currCourse);
					courses.setValue("");
					break;
				}
			} else if (e.getSource() == remove) {
				int selectedIdx = list.getSelectionModel().getSelectedIndex();
				String remCourse = items.get(selectedIdx);
				if (selectedIdx == 0 || remCourse.equals("")) {
					break;
				} else {
					balanceValue -= 1050;
					balance.setText("$ " + balanceValue + ".00");
					System.out.println(selectedIdx);
					System.out.println(remCourse);
					courses.getItems().add(remCourse);
					items.remove(selectedIdx);
					break;
				}
			} else if (e.getSource()== enroll) {
				Alert confirm = new Alert(AlertType.CONFIRMATION);
				confirm.setTitle("Mac Enroller");
				confirm.setHeaderText("Confirm Enrollment");
				confirm.setContentText("Are you sure you want to proceed with enrolling in these courses?");
				
				Optional<ButtonType> result = confirm.showAndWait();
				if (result.get() == ButtonType.OK){
				    System.exit(0);
				} 
				else 
				{
					break;
				}
			}
		}
	} //end of handle
	
	public void search(String searchStr, ArrayList<String> aList) 
		{	 
		boolean found = false;
		Iterator<String>  iter = aList.iterator();
		String curItem="";
		int pos=0;
		 
		while ( iter .hasNext() == true )
		{
		    pos=pos+1;
		    curItem =(String) iter .next();
		    if (curItem.equals(searchStr)  ) {
			    found = true;
			    break;
		    }
		 
		}
		 
		if ( found == false ) {
			pos=0;
		}
		 
		if (pos!=0)
		 {
		    foundAccount = true;
		 } else {
		    foundAccount = false;
		 }
	} //end of search method
	
}//end of program

