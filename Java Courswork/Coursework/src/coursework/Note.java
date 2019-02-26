/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

/**
 *
 * @author kevca
 */
public class Note extends CommonCode {
     private int noteID = 0;
 private String course = "";
 private String dayte = "";
 private String note = "";
 private CommonCode cc = new CommonCode();

 public Note() {

 }
 public void setNoteID(int n) {
 int nid = n;
 // Any validation goes here.
 noteID = nid;
 }
 public void setCourse(String c) {
 String crse = c;
 // Any validation goes here.
 course = crse;
 }
 public int getNoteID() {
 // Any checking goes here.
 return noteID;
 }
 public String getCourse() {
 // Any checking goes here.
 return course;
 }
 public void setDayte() {
 dayte = cc.orderedDate;
 }
 public void setDayte(String d) {
 dayte = d;
  }
 public String getDayte() {
 return dayte;
 }
 public void setNote(String n) {
 // Any validation goes here.
 note = n;
 }
 public String getNote() {
 // Any checking goes here.
 return note;
 }
}
