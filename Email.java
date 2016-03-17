import java.util.*;
import java.text.SimpleDateFormat;

public class Email {
	private Date date;//TODO private member variables
	private String messageID;
	private String subject;
	private String from;
	private String to;
	private ListADT<String> body;
	private String inReplyTo;
	private ListADT<String> references;
	Date today = new Date();


	public Email(Date date, String messageID, String subject, String from, String to, ListADT<String> body){
		this.date = date;
		this.messageID = messageID;
		this.subject = subject;
		this.from = from;
		this.to = to;
		this.body = body;
	}

	public Email(Date date, String messageID, String subject, String from, String to, ListADT<String> body, String inReplyTo, ListADT<String> references){
		this.date = date;
		this.messageID = messageID;
		this.subject = subject;
		this.from = from;
		this.to = to;
		this.body = body;
		this.inReplyTo = inReplyTo;
		this.references = references;
	}

	public String getDate() {
		SimpleDateFormat format;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date today = new Date();
		if(dateFormat.format(today).equals(dateFormat.format(this.date))){
			format  = new SimpleDateFormat("h:mm a");  
		}

		else{ format = new SimpleDateFormat("MMM d");}//TODO 
		String result = format.format(date);
		return result;
	}

	public String getMessageID() {
		return this.messageID;//TODO 
	}

	public String getSubject() {
		return this.subject;//TODO 
	}

	public String getFrom() {
		return this.from; //TODO 
	}

	public String getTo() {
		return this.to;//TODO 
	}

	public ListADT<String> getBody() {
		return this.body;//TODO 
	}

	public String getInReplyTo() {
		return this.inReplyTo;//TODO 
	}

	public ListADT<String> getReferences() {
		return this.references; //TODO 
	}
} 
