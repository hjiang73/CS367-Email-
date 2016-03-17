///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MalformedEmailException.java
// File:             DoublyLinkedList.java
// Semester:         CS 367 Fall 2015
//
// Author:           Han Jiang
// Email:            hjiang73@wisc.edu
// CS Login:         hjiang
// Lecturer's Name:  Jim Skrentny
// Lab Section:      02
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     You Wu
// Email:            wu278@wisc.edu
// CS Login:         ywu
// Lecturer's Name:  Jim Skrentny
// Lab Section:      01
//
///////////////////////////////////////////////////////////////////////////////
/**
* a checked exception class to be used when the parsed email does not conform to the guidelines given above, namely:
* 1.If there is a blank value for any of the required fields (Date, Message-ID, Subject, From, To)
* 2.If one of the required fields is omitted
 * <p>Bugs: None known
 * @author Han Jiang & You Wu
 */

public class MalformedEmailException extends Exception{
public MalformedEmailException(){
	 super();
 }
}
