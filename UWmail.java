///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            p2
// Files:            "UWmail.java"
//                   "UWmailDB.java"
//                   "Email.java"
//                   "Conversation.java"
//                   "ListADT.java"
//                   "Listnode.java"
//                   "DoublyLinkedList.java"
//                   "DoublyLinkedListIterator.java"
//                   "MalformedEmailException.java"
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
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     You Wu
// Email:            wu278@wisc.edu
// CS Login:         ywu
// Lecturer's Name:  Jim Skrentny
// Lab Section:      01
//
///////////////////////////////////////////////////////////////////////////////
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Integer;
import java.lang.NumberFormatException;

/**
 * The application program, UWmail, creates and uses a UWmailDB to
 * load and present the user their inbox.  
 * <p>Bugs: None known
 * @author Han Jiang & You Wu
 */

/** The main method of the UWmail class does the following:
 * Checks whether exactly 1 command-line argument is given;
 * if not, displays "Usage: java UWmail [EMAIL_ZIP_FILE]" and quits.
 * Checks whether the input zip file exists and is readable; 
 * if not, displays: "File <FileNameHere.zip> not found." and quit.
 * Loads the data from the .zip file and uses the contained .txt files 
 * to construct the database.
 * Prompts the user to enter command options and processes 
 * them until the user exits.
 */
public class UWmail {
	//Data fields
	private static UWmailDB uwmailDB = new UWmailDB();
	private static final Scanner stdin = new Scanner(System.in);

	public static void main(String args[]) 
			throws MalformedEmailException, ParseException {
		if (args.length != 1) {
			System.out.println("Usage: java UWmail [EMAIL_ZIP_FILE]");
			System.exit(0);
		}
		loadEmails(args[0]);
		//Loads the data from the .zip file and uses the contained .txt files
		displayInbox();
	}

	private static void loadEmails(String fileName) {
		try (ZipFile zf = new ZipFile(fileName);) {
			Enumeration<? extends ZipEntry> entries = zf.entries();
			while(entries.hasMoreElements()) {
				ZipEntry ze = entries.nextElement();
				if(ze.getName().endsWith(".txt")) {
					//read each txt.file
					InputStream in = zf.getInputStream(ze);
					Scanner sc = new Scanner(in);

					ListADT<String> eachemail = new DoublyLinkedList<String>();
					ListADT<String> body =  new DoublyLinkedList<String>();
					ListADT<String> reference =  new DoublyLinkedList<String>();
					String line = sc.nextLine();
					//if the email is the first email
					if(line.charAt(0) == 'D'){
						try{
							String d = line.substring(6);
							if(d.equals(""))
							{throw new MalformedEmailException();}
							eachemail.add(d);
							line = sc.nextLine();
							if(line.charAt(0) != 'M')
							{throw new MalformedEmailException();}
							String m = line.substring(12);
							eachemail.add(m);
							line = sc.nextLine();
							if(line.charAt(0) != 'S')
							{throw new MalformedEmailException();}
							String s = line.substring(9);
							eachemail.add(s);
							line = sc.nextLine();
							if(line.charAt(0) != 'F')
							{throw new MalformedEmailException();}
							String f = line.substring(6);
							eachemail.add(f);
							line = sc.nextLine();
							if(line.charAt(0) != 'T')
							{throw new MalformedEmailException();}
							String t = line.substring(4);
							eachemail.add(t);
							while(sc.hasNextLine()){
								line = sc.nextLine();
								body.add(line);
							}
							//read each lines, if missing or blank space,throws 
							//MalformedEmailException
							DateFormat df = 
									new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
							Date result =  df.parse(eachemail.get(0)); 
							//parse string d to Date
							if(m.equals("")||s.equals("")
									||f.equals("")||t.equals("")){
								throw new MalformedEmailException();}
							Email simpleEmail = 
									new Email(result,eachemail.get(1),eachemail.get(2),
											eachemail.get(3),eachemail.get(4),body);
							uwmailDB.addEmail(simpleEmail);}
						//Create a new email and add to the inbox
						catch(MalformedEmailException exp1){
						} 
						catch (ParseException e) {
						}


					}
					//if the email is the reply email
					else if(line.charAt(0) == 'I'){
						try{
							String i = line.substring(13);
							eachemail.add(i);	
							line = sc.nextLine();
							String r = line.substring(12);
							String[] ref = r.split(",");

							for(int n =0;n<ref.length;n++){
								reference.add(ref[n]);
							}
							line = sc.nextLine();
							if(line.charAt(0) != 'D')
							{throw new MalformedEmailException();}
							String d = line.substring(6);
							if(d.equals(""))
							{throw new MalformedEmailException();}
							eachemail.add(d);
							line = sc.nextLine();
							if(line.charAt(0) != 'M')
							{throw new MalformedEmailException();}
							String m = line.substring(12);
							eachemail.add(m);
							line = sc.nextLine();
							if(line.charAt(0) != 'S')
							{throw new MalformedEmailException();}
							String s = line.substring(9);
							eachemail.add(s);
							line = sc.nextLine();
							if(line.charAt(0) != 'F')
							{throw new MalformedEmailException();}
							String f = line.substring(6);
							eachemail.add(f);
							line = sc.nextLine();
							if(line.charAt(0) != 'T')
							{throw new MalformedEmailException();}
							String t = line.substring(4);
							eachemail.add(t);
							while(sc.hasNextLine()){
								line = sc.nextLine();
								body.add(line);
							}
							//read each lines, if missing or blank space,throws 
							//MalformedEmailException:
							DateFormat df = 
									new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
							Date result =  df.parse(eachemail.get(1)); 
							//parse string d to Date
							if(m.equals("")||s.equals("")
									||f.equals("")||t.equals("")){
								throw new MalformedEmailException();}
							Email replyEmail = 
									new Email(result,eachemail.get(2),eachemail.get(3),
											eachemail.get(4),eachemail.get(5),
											body,eachemail.get(0),reference);
							uwmailDB.addEmail(replyEmail);}
						//Create a new email and add to the inbox
						catch(MalformedEmailException exp1){
						} 
						catch (ParseException e) {
						}

					}

				}
			}
		}

		catch (ZipException e) {
			System.out.println("File "+fileName+" not found.");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("File "+fileName+" not found.");
			System.exit(1);
		} catch (SecurityException e) {
			System.out.println("File "+fileName+" not found.");
			System.exit(1);
		} 
		//Catch checked Exceptions

	}

	/**This method displays the conversations in the inbox
	 * and Prompts the user to enter 
	 * command options and processes them
	 */
	private static void displayInbox(){
		boolean done = false;
		int i =0;
		ListADT<Conversation> inbox = uwmailDB.getInbox();
		if(inbox.size()==0){
			System.out.println("No conversation to show");
		}
		// System.out.println(inbox.size());
		System.out.println("Inbox:");
		System.out.println("-------------------------------------------------------------------------------");
		Iterator<Conversation> itr = inbox.iterator();
		while(itr.hasNext()){
			Conversation tmp = itr.next();
			System.out.println("["+i+"]"+" "
					+tmp.get(0).getSubject()+" "+"("+tmp.get(tmp.size()-1).getDate()+")");
			i++;
		}
		//print out the inbox here
		while (!done) 
		{
			System.out.print("Enter option ([#]Open conversation, [T]rash, " + 
					"[Q]uit): ");
			String input = stdin.nextLine();

			if (input.length() > 0) 
			{

				int val = 0;
				boolean isNum = true;

				try {
					val = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					isNum = false;
				}

				if(isNum) {
					if(val < 0) {
						System.out.println("The value can't be negative!");
						continue;
					} else if (val >= uwmailDB.size()) {
						System.out.println("Not a valid number!");
						continue;
					} else {
						displayConversation(val);
						continue;
					}

				}

				if(input.length()>1)
				{
					System.out.println("Invalid command!");
					continue;
				}
				/*If a negative number is entered, 
 you should display "The value can't be negative!" and continue taking input. 
 If a number that is not a valid conversation number is entered, 
 you should display "Not a valid number!" and continue taking input. 
 Otherwise, if it is not one of the commands above, 
 you must display "Invalid command!" and continue taking input.*/

				switch(input.charAt(0)){
				case 'T':
				case 't':
					displayTrash();
					break;

				case 'Q':
				case 'q':
					System.out.println("Quitting...");
					done = true;
					break;

				default:  
					System.out.println("Invalid command!");
					break;
				}
			} 
		} 
		System.exit(0);
	}

	/**This method displays the conversations in the trash
	 * same as in the inbox
	 * and Prompts the user to enter 
	 * command options and processes them
	 */
	private static void displayTrash(){
		boolean done = false;
		int i =0;
		ListADT<Conversation> trash = uwmailDB.getTrash();
		System.out.println("Trash");
		System.out.println("-------------------------------------------------------------------------------");
		if(trash.size()==0){
			System.out.println("No conversation to show");
		}
		Iterator<Conversation> itr = trash.iterator();
		while(itr.hasNext()){
			Conversation tmp = itr.next();
			System.out.println("["+i+"]"+" "
					+tmp.get(0).getSubject()+" "+"("+tmp.get(tmp.size()-1).getDate()+")");
			i++;
		}//print out the trash here 

		while (!done) 
		{
			System.out.print("Enter option ([I]nbox, [Q]uit): ");
			String input = stdin.nextLine();

			if (input.length() > 0) 
			{
				if(input.length()>1)
				{
					System.out.println("Invalid command!");
					continue;
				}

				switch(input.charAt(0)){
				case 'I':
				case 'i':
					displayInbox();
					break;

				case 'Q':
				case 'q':
					System.out.println("Quitting...");
					done = true;
					break;

				default:  
					System.out.println("Invalid command!");
					break;
				}
			} 
		} 
		System.exit(0);
	}

	/**This method displays the contents(emails) in each conversation
	 * and Prompts the user to enter 
	 * command options and processes them
	 */
	private static void displayConversation(int val) {
		// Check whether val is valid as a conversation number.
		//If not, takethe user back to the inbox view 
		//and continue processing commands.

		if(val < 0) {
			displayInbox();
		} else if (val >= uwmailDB.size()) {
			displayInbox();
		} else {


			boolean done = false;
			Conversation currconv = uwmailDB.getInbox().get(val);
			Email curremail = currconv.get(currconv.getCurrent());

			System.out.println("SUBJECT: "+currconv.get(0).getSubject());
			System.out.println("-------------------------------------------------------------------------------");
			for(int i = 0;i<currconv.getCurrent();i++){
				System.out.println(currconv.get(i).getFrom()+" | "+
						currconv.get(i).getBody().get(0)+" | "+
						currconv.get(i).getDate());
				System.out.println("-------------------------------------------------------------------------------");}
			System.out.println("From: "+curremail.getFrom());
			System.out.println("To: "+curremail.getTo());
			System.out.println(curremail.getDate());
			System.out.println(); 
			Iterator<String> bodyitr = curremail.getBody().iterator();
			while(bodyitr.hasNext()){
				System.out.println(bodyitr.next());
			}
			System.out.println("-------------------------------------------------------------------------------");
			for(int j = currconv.getCurrent()+1;j<currconv.size();j++){ 
				System.out.println(currconv.get(j).getFrom()+" | "+
						currconv.get(j).getBody().get(0)+" | "+
						currconv.get(j).getDate());
				System.out.println("-------------------------------------------------------------------------------");}

			//Print the conversation here
			while (!done) 
			{
				System.out.print("Enter option ([N]ext email, [P]revious email, " + 
						"[J]Next conversation, [K]Previous conversation, [I]nbox, [#]Move " +
						"to trash, [Q]uit): ");
				String input = stdin.nextLine();

				if (input.length() > 0) 
				{

					if(input.length()>1)
					{
						System.out.println("Invalid command!");
						continue;
					}

					switch(input.charAt(0)){
					case 'P':
					case 'p':
						//for this conversation, move the current email pointer back 
						//  using Conversation.moveCurrentBack().
						currconv.moveCurrentBack();
						displayConversation(val);
						break;
					case 'N':
					case 'n':
						//for this conversation, move the current email pointer 
						//  forward using Conversation.moveCurrentForward().
						//
						currconv.moveCurrentForward();
						displayConversation(val);
						break;
					case 'J':
					case 'j':
						//Display the next conversation
						if(val<uwmailDB.getInbox().size()-1){
							val++;
							displayConversation(val);}
						else
						{displayInbox();}
						break;

					case 'K':
					case 'k':
						//Display the previous conversation
						if(val!=0){
							val--;
							displayConversation(val);}
						else{displayInbox();}
						break;

					case 'I':
					case 'i':
						displayInbox();
						return;

					case 'Q':
					case 'q':
						System.out.println("Quitting...");
						done = true;
						break;

					case '#':
						//add delete conversation functionality. This conversation
						//should be moved to the trash when # is entered, and you should
						//take the user back to the inbox and continue processing input.
						//
						uwmailDB.deleteConversation(val);
						displayInbox();
						return;

					default:  
						System.out.println("Invalid command!");
						break;
					}
				} 
			} 
			System.exit(0);
		}
	}
}

