package coursework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTextField;
public class Coursework extends JFrame implements ActionListener, KeyListener {

    CommonCode cc = new CommonCode();
    JPanel pnl = new JPanel(new BorderLayout());
    JTextArea txtNewNote = new JTextArea();
    JTextArea txtDisplayNotes = new JTextArea();
    ArrayList<String> note = new ArrayList<>();
    ArrayList<String> course = new ArrayList<>();
    JComboBox courseList = new JComboBox();
    String crse = "";
    AllNotes allNotes = new AllNotes();
    JTextField search = new JTextField();
    public static void main(String[] args) {
// This is required for the coursework.
//JOptionPane showMessageDialog(null, "Andy Wicks ~ wa02");
    Coursework prg = new Coursework();

    }
// Allows user to add notes through the GUI

    private void addNote(String text) {
        allNotes.addNote(allNotes.getMaxID(), crse, text);
        addAllNotes();
    }
    //Calculates the most noted course

    //code for adding a course
    private void addCourse(String text){
       course.add(text);
       courseFolder();
       courseList.addItem(text);
       // Files.write("Courses\\" + , lines, Charset.forName("UTF-8"));
       File courseWork = new File("Courses\\" + text + "\\coursework.txt");
       try {
            courseWork.createNewFile();
       }
       catch (IOException ex) {
            Logger.getLogger(Coursework.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
// Using MVC

    public Coursework() {
        model();
        view();
        controller();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ("Close".equals(ae.getActionCommand())) {

        }
        if ("Course".equals(ae.getActionCommand())) {
            crse = courseList.getSelectedItem().toString();
            System.out.println(crse);
        }
        if ("Exit".equals(ae.getActionCommand())) {
            System.exit(0);
        }
        if ("NewNote".equals(ae.getActionCommand())) {
            addNote(txtNewNote.getText());
            txtNewNote.setText("");
        }
        if ("SearchKeyword".equals(ae.getActionCommand())) {
            String list1 = allNotes.searchAllNotesByKeyword("", search.getText());
            txtDisplayNotes.setText(list1);
        }
        if ("AddCourse".equals(ae.getActionCommand())){
            String nCourseName = "";
            nCourseName = JOptionPane.showInputDialog(null, "What is your new course");
            addCourse(nCourseName);
        }
        
        if("mostCommonCourse".equals(ae.getActionCommand())){
            int i = 0;
            File[] courseDirectory = new File("Courses\\").listFiles(File::isDirectory);
            for(int n = courseDirectory.length; i <= n - 1; i++){
                System.out.print(courseDirectory[i].getName() + " " + allNotes.mostNotedCourse(courseDirectory[i].getName(), 0) + " ");
            }
        }
        if("courseshow".equals(ae.getActionCommand())){
            Path path = Paths.get("courses\\" + crse + "\\coursework.txt");
            try {
			
			List<String> courseText = Files.readAllLines(path);
                        String coursetxt = "";
                        for(int i = 0; i <= courseText.size() - 1; i++ ){
			coursetxt += courseText.get(i) + "\n";
                        }
                        txtDisplayNotes.setText(coursetxt);
		} catch (Exception e) {
			e.printStackTrace();
		}
        }
        /*
        if("addCourseWork".equals(ae.getActionCommand())){
            txtNewNote.getText();
            try {
                Files.write(Paths.get("myfile.txt"), "the text".getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
    
            }
           
        }
        */
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println("keyTyped has not been coded yet.");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.println("keyPressed has not been coded yet.");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.println("keyReleased has not been coded yet.");
    }
    private void courseFolder() {
        int pstn= 0;
        for (course.size();pstn < course.size(); pstn++) {
            File dir = new File("Courses\\" + course.get(pstn));
            dir.mkdir();
        }
    }
    private void model() {
        File[] courseDirectory = new File("Courses\\").listFiles(File::isDirectory);
        int i = 0;
        for (int n = courseDirectory.length; i < courseDirectory.length; i++) {
            course.add(courseDirectory[i].getName());
        }
        /*
        course.add("COMP1752");
        course.add("COMP1753");
        course.add("COMP1713");
        course.add("COMP1645");
        */
        crse = course.get(0);
        
       
        courseFolder();
        
        /*
// // Take these out AFTER you have created the file.
        Note nt = new Note();
        nt.setNoteID(1);
        nt.setDayte(getDateAndTime());
        nt.setCourse(crse);
        nt.setNote("Arrays are of fixed length and are inflexible.");
        allNotes.addNote(nt.getNoteID(), nt.(), nt.getNote());

// // Take these out AFTER you have created the file.
        nt = new Note();
        nt.setNoteID(2);
        nt.setDayte(getDateAndTime());
        nt.setCourse(crse);
        nt.setNote("ArraysList can be added to and items can be deleted.");
        allNotes.addNote(nt.getNoteID(), nt.getCourse(), nt.getNote());
    
         */
    }
    private void comboSetup(){
        
    }
    private void view() {
        Font fnt = new Font("Georgia", Font.PLAIN, 24);

        JMenuBar menuBar = new JMenuBar();
        JMenu note = new JMenu();

        note = new JMenu("Note");
        note.setToolTipText("Note tasks");
        note.setFont(fnt);

        note.add(makeMenuItem("New", "NewNote", "Create a new note.", fnt));
        note.addSeparator();
        note.add(makeMenuItem("Close", "Close", "Clear the current note.", fnt));
        note.addSeparator();
        note.add(makeMenuItem("Add Course", "AddCourse","Add a course to course list", fnt));
        menuBar.add(note);
        note.addSeparator();
        note.add(makeMenuItem("Show CourseWork", "courseshow", "Shows the coursework for the course", fnt));
        
        menuBar.add(makeMenuItem("Exit", "Exit", "Close this program", fnt));
        

// This will add each course to the combobox
        for (String crse : course) {

            courseList.addItem(crse);
        }
        courseList.setFont(fnt);
        courseList.setMaximumSize(courseList.getPreferredSize());
        courseList.addActionListener(this);
        courseList.setActionCommand("Course");
        menuBar.add(courseList);
        this.setJMenuBar(menuBar);

        JToolBar toolBar = new JToolBar();
// Setting up the ButtonBar
        JButton button = null;
        button = makeButton("Create", "NewNote",
                "Create a new note.",
                "New");
        toolBar.add(button);
        button = makeButton("closed door", "Close",
                "Close this note.",
                "Close");
        toolBar.add(button);
        button = makeButton("","AddCourse", "Add course to the course list","Add Course");// adds button to the tool bar for adding courses
        toolBar.add(button);
        button = makeButton("","mostCommonCourse", "calculates and displays the most common course","Most common course");// adds button to the tool bar for adding courses
        toolBar.add(button);
        toolBar.addSeparator();
        button = makeButton("exit", "Exit",
                "Exit from this program.",
                "Exit");
        toolBar.add(button);
        
        add(toolBar, BorderLayout.NORTH);
        toolBar.addSeparator();
        // This forces anything after it to the right.
        toolBar.add(Box.createHorizontalGlue());
        search.setMaximumSize(new Dimension(6900, 30));
        search.setFont(fnt);
        toolBar.add(search);
        toolBar.addSeparator();
        button = makeButton("search", "SearchKeyword",
                "Search for this text.",
                "Search");
        toolBar.add(button);
        JPanel pnlWest = new JPanel();
        pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
        pnlWest.setBorder(BorderFactory.createLineBorder(Color.black));

        txtNewNote.setFont(fnt);
        pnlWest.add(txtNewNote);

        JButton btnAddNote = new JButton("Add note");
        btnAddNote.setActionCommand("NewNote");
        btnAddNote.addActionListener(this);
        pnlWest.add(btnAddNote);
        
        JButton btnAddCourseWork = new JButton("Add Coursework");
        btnAddCourseWork.setActionCommand("addCourseWork");
        btnAddCourseWork.addActionListener(this);
        pnlWest.add(btnAddCourseWork);

        add(pnlWest, BorderLayout.WEST);

        JPanel cen = new JPanel();
        cen.setLayout(new BoxLayout(cen, BoxLayout.Y_AXIS));
        cen.setBorder(BorderFactory.createLineBorder(Color.black));
        txtDisplayNotes.setFont(fnt);
        cen.add(txtDisplayNotes);

        add(cen, BorderLayout.CENTER);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Coursework — Andy Wicks");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void controller() {
        addAllNotes();
    }

    protected JMenuItem makeMenuItem(
            String txt,
            String actionCommand,
            String toolTipText,
            Font fnt) {

        JMenuItem mnultem = new JMenuItem();
        mnultem.setText(txt);
        mnultem.setActionCommand(actionCommand);
        mnultem.setToolTipText(toolTipText);
        mnultem.setFont(fnt);
        mnultem.addActionListener(this);
        return mnultem;
    }

    protected JButton makeButton(
            String imageName,
            String actionCommand,
            String toolTipText,
            String altText) {

//Create and initialize the button.
        JButton button = new JButton();
        button.setToolTipText(toolTipText);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);

//Look for the image.
        String imgLocation = System.getProperty("user.dir")
                + "\\icons\\"
                + imageName
                + ".png";

        File fyle = new File(imgLocation);
        if (fyle.exists() && !fyle.isDirectory()) {
            // image found
            Icon img;
            img = new ImageIcon(imgLocation);
            button.setIcon(img);
        } else {
            // image NOT found
            button.setText(altText);
            System.err.println("Resource not found: " + imgLocation);
        }

        return button;

    }
/*
    private void addNote(String text) {
        note.add(txtNewNote.getText());
        addAllNotes();

    }
*/
    private void addAllNotes() {
        String txtNotes = "";

        for (Note n : allNotes.getAllNotes()) {
            txtNotes += n.getNote() + "\n";
        }

        txtDisplayNotes.setText(txtNotes);
    }

    private String getDateAndTime() {
        String UK_DATE_FORMAT_NOW = "dd-MM-yyyy HH:mm:ss";
        String ukDateAndTime;
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat uksdf = new SimpleDateFormat(UK_DATE_FORMAT_NOW);
        ukDateAndTime = uksdf.format(cal.getTime());

        return ukDateAndTime;

    }

}
