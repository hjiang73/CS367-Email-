public class UWmailDB {
	private DoublyLinkedList<Conversation> inbox;//TODO private member variables
	private DoublyLinkedList<Conversation> trash;
	boolean found = false;
	public UWmailDB() {
		inbox = new DoublyLinkedList<Conversation>();//TODO implement constructor
		trash = new DoublyLinkedList<Conversation>();
	}

	public int size() {
		return inbox.size();//TODO
	}

	//Pre-condition: e will be added to a conversation for which it is the oldest email.
	//    In other words, you can simply add it to the beginning of the list, if the list
	//    is sorted in chronological order.
	//    Also, the messageID of e is guaranteed to be included in the References field
	//    of all emails in the conversation that it belongs in.
	public void addEmail(Email e) {

		if(inbox.size()==0){
			Conversation newcon = new Conversation(e);
			inbox.add(newcon);
		}
		else{
			if(e.getReferences() == null){
				boolean found = false;
				for(int i=0; i<inbox.size(); i++){
					if(inbox.get(i).get(0).getReferences() != null){
						if(inbox.get(i).get(0).getReferences().get(0).equals(e.getMessageID())){
							inbox.get(i).add(e);
							found = true;
						}
					}
				}
				if(found == false){
					Conversation newconv = new Conversation(e);
					inbox.add(newconv);
				}
			}

			else{
				boolean found1 = false;
				for(int i=0; i<inbox.size(); i++){
					if(inbox.get(i).get(0).getReferences() != null){
						if(inbox.get(i).get(0).getReferences().get(0).equals(e.getReferences().get(0))){
							inbox.get(i).add(e);
							found1 = true;
						}
					}
				}
				if(found1 == false){
					Conversation newconv = new Conversation(e);
					inbox.add(newconv);

				}
			}
		}

	}
	public ListADT<Conversation> getInbox() {
		return inbox; //TODO
	}

	public ListADT<Conversation> getTrash() {
		return trash; //TODO
	}

	public void deleteConversation(int idx) {
		if(idx<0 || idx>=inbox.size()){
			throw new IndexOutOfBoundsException();
		}
		Conversation tmp = inbox.remove(idx);
		trash.add(tmp);//TODO
	}

}
