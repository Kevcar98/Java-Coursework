/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
/**
 *
 * @author kevca
 */
public class WriteFile {
    private String paths;
    private boolean append_to_file = false;
    
    public WriteFile(String file_path){
        paths = file_path;
    }
    public WriteFile( String file_path , boolean append_value ) {
        paths = file_path;
        append_to_file = append_value;

}
    public void writeToFile( String textLine ) throws IOException {
        FileWriter write = new FileWriter( paths , append_to_file);
        PrintWriter print_line = new PrintWriter( write );
        print_line.printf( "%s" + "%n" , textLine);
        print_line.close();
        


}
}
