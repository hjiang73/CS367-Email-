/*
 * Invoke this file as:
 *   javac ZipFileExample.java
 *   java ZipFileExample yourZipInput.zip
*/

import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.io.InputStream;
import java.util.Scanner;
import java.io.IOException;
import java.util.zip.ZipException;

public class ZipFileExample {
  public static void main(String args[]) throws MalformedEmailException {
	  List<String> eachemail = new ArrayList<String>();
	  List<String> body = new ArrayList<String>();
	
    if(args.length != 1) {
      System.exit(1);
    } else {
      try (ZipFile zf = new ZipFile(args[0]);) {
        //follow this approach for using <? extends ZipEntry>, even though we will not cover this in class.
        Enumeration<? extends ZipEntry> entries = zf.entries();
        while(entries.hasMoreElements()) {
          ZipEntry ze = entries.nextElement();
          if(ze.getName().endsWith(".txt")) {
            InputStream in = zf.getInputStream(ze);
            Scanner sc = new Scanner(in);
            String line = sc.nextLine();
    
            if(line.charAt(0) == 'D'){
            	String[] parts = line.split(":");
            	eachemail.add(parts[1]);
            	
            	for(int i = 0;i<4;i++){
            	line = sc.nextLine();
                String[] info = line.split(":");
                eachemail.add(info[1]);
                		
            	}
            	while(sc.hasNextLine()){
            		line = sc.nextLine();
            		body.add(line);
            		}
            	
            	
            	
            	
            	
            	}
            else if(line.charAt(0) == 'I'){
            	
            }
            else {
            	 throw new MalformedEmailException();
            }
            //while(sc.hasNextLine()) {
            //  String line = sc.nextLine();
         // eachemail.add(line);
          //  }
          }
         
        }
      } catch (ZipException e) {
        System.out.println("File "+args[0]+" not found.");
        System.exit(1);
      } catch (IOException e) {
        System.out.println("File "+args[0]+" not found.");
        System.exit(1);
      } catch (SecurityException e) {
        System.out.println("File "+args[0]+" not found.");
        System.exit(1);
        
 
      }
      
      
      
    
    	  
      
      
    }
  }
}
