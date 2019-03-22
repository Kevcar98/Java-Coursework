package coursework;

import java.awt.BorderLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
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
import java.io.FileWriter;
import java.io.PrintWriter;
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
import javax.swing.JScrollPane;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class Coursework extends JFrame implements ActionListener, KeyListener {

    CommonCode cc = new CommonCode();
    JPanel pnl = new JPanel(new BorderLayout());
    JTextArea txtNewNote = new JTextArea();
    JTextArea txtNewCNote = new JTextArea();
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
        text = text.replaceAll("[\r\n]+", " ");
        allNotes.addNote(allNotes.getMaxID(), crse, text);
        addAllNotes();
    }

    //code for adding a course
    private void addCourse(String text) {
        course.add(text);
        courseFolder();
        courseList.addItem(text);
        // Files.write("Courses\\" + , lines, Charset.forName("UTF-8"));
        File courseWork = new File("Courses\\" + text + "\\coursework.txt");
        try {
            courseWork.createNewFile();
        } catch (IOException ex) {
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
            while (!txtNewNote.getText().equals("")) {
                addNote(txtNewNote.getText());
                txtNewNote.setText("");
            }
        }
        if ("SearchKeyword".equals(ae.getActionCommand())) {
            String list1 = allNotes.searchAllNotesByKeyword("", search.getText());
            txtDisplayNotes.setText(list1);
        }
        if ("AddCourse".equals(ae.getActionCommand())) {
            String nCourseName = JOptionPane.showInputDialog(null, "What is your new course");
            addCourse(nCourseName);
        }
        if ("courseshow".equals(ae.getActionCommand())) {
            Path paths = Paths.get("courses\\" + crse + "\\coursework.txt");
            try {

                List<String> courseText = Files.readAllLines(paths);
                String coursetxt = "";
                for (int i = 0; i <= courseText.size() - 1; i++) {
                    coursetxt += courseText.get(i) + "\n";
                }
                txtDisplayNotes.setText(coursetxt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if ("addCourseWork".equals(ae.getActionCommand())) {
            Path path = Paths.get("courses\\" + crse + "\\coursework.txt");
            String paths = path.toString();
            try {
                WriteFile data = new WriteFile(paths);
                String ACourseW = txtNewNote.getText();
                FileWriter fw = new FileWriter(paths, true);
                //BufferedWriter writer gives better performance
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(ACourseW);
                //Closing BufferedWriter Stream
                bw.close();
                txtNewNote.setText("");

            } catch (IOException e) {
                e.printStackTrace();
                //exception handling left as an exercise for the reader
            }
        }

        if ("showCWR".equals(ae.getActionCommand())) {
            Path paths = Paths.get("courses\\" + crse + "\\CourseworkRequirement.txt");
            try {

                List<String> courseText = Files.readAllLines(paths);
                String coursetxt = "";
                for (int i = 0; i <= courseText.size() - 1; i++) {
                    coursetxt += courseText.get(i) + "\n";
                }
                txtDisplayNotes.setText(coursetxt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if ("addCWR".equals(ae.getActionCommand())) {
            Path path = Paths.get("courses\\" + crse + "\\CourseworkRequirement.txt");
            String paths = path.toString();
            try {
                WriteFile data = new WriteFile(paths);
                String ACourseWR = txtNewNote.getText();
                FileWriter fw = new FileWriter(paths, true);
                //BufferedWriter writer gives better performance
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(ACourseWR);
                //Closing BufferedWriter Stream
                bw.close();
                txtNewNote.setText("");

            } catch (IOException e) {
                e.printStackTrace();
                //exception handling left as an exercise for the reader
            }
        }

        if ("ConvertW".equals(ae.getActionCommand())) {

            Path paths = Paths.get("courses\\" + crse + "\\CourseworkRequirement.txt");

            try {
                XWPFDocument document = new XWPFDocument();
                FileOutputStream out = new FileOutputStream(new File("courses\\" + crse + "\\CourseworkRequirement.docx"));
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                List<String> courseText = Files.readAllLines(paths);
                String coursetxt = "";
                for (int i = 0; i <= courseText.size() - 1; i++) {
                    coursetxt += courseText.get(i) + "\n";
                }
                run.setText(coursetxt);//this is where the text goes to a .docx
                document.write(out);
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

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
        int pstn = 0;
        for (course.size(); pstn < course.size(); pstn++) {
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
        allNotes.addNote(nt.getNoteID(), nt.getCourse(), nt.getNote());

// // Take these out AFTER you have created the file.
        nt = new Note();
        nt.setNoteID(2);
        nt.setDayte(getDateAndTime());
        nt.setCourse(crse);
        nt.setNote("ArraysList can be added to and items can be deleted.");
        allNotes.addNote(nt.getNoteID(), nt.getCourse(), nt.getNote());
    
         */
    }

    private void comboSetup() {

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
        note.add(makeMenuItem("Add Course", "AddCourse", "Add a course to course list", fnt));
        menuBar.add(note);
        note.addSeparator();
        note.add(makeMenuItem("Show CourseWork", "courseshow", "Shows the coursework for the course", fnt));
        note.addSeparator();
        note.add(makeMenuItem("Show CourseWork Requirements", "showCWR", "Shows the coursework requirement for the course", fnt));

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
        button = makeButton("", "AddCourse", "Add course to the course list", "Add Course");// adds button to the tool bar for adding courses
        toolBar.add(button);
        button = makeButton("", "ConvertW", "Import Coursework Requirements into a word Document", "Coursework Req. to Word");// adds button to the tool bar for converting Coursework Requirements into a word Document
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
        search.setMaximumSize(new Dimension(6900, 100));
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
        JScrollPane scrolls = new JScrollPane(txtNewNote, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        txtNewNote.setFont(fnt);
        pnlWest.add(scrolls);

        JButton btnAddNote = new JButton("Add note...");
        btnAddNote.setActionCommand("NewNote");
        btnAddNote.addActionListener(this);
        pnlWest.add(btnAddNote);

        JButton btnAddCourseWork = new JButton("Add Coursework...");
        btnAddCourseWork.setActionCommand("addCourseWork");
        btnAddCourseWork.addActionListener(this);
        pnlWest.add(btnAddCourseWork);

        JButton btnAddCWR = new JButton("Add Coursework Requirements      ");
        btnAddCWR.setActionCommand("addCWR");
        btnAddCWR.addActionListener(this);
        pnlWest.add(btnAddCWR);

        add(pnlWest, BorderLayout.WEST);

        JPanel cen = new JPanel();
        cen.setLayout(new BoxLayout(cen, BoxLayout.Y_AXIS));
        cen.setSize(60, 60);

        ///////Notes Border
        //cen.setBorder(BorderFactory.createLineBorder(Color.black));
        txtDisplayNotes.setFont(fnt);
        JScrollPane scroll = new JScrollPane(txtDisplayNotes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cen.add(scroll);

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
