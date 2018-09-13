import java.util.Scanner;
class Sender{
	public static void main(String[] args){
		Sender sender = new Sender();
		Scanner s = new Scanner(System.in);

		int maxCapacity = s.nextInt();
		int maxSequence = s.nextInt();

		Receiver receiver = new Receiver();

		for(int i=0; i<(maxCapacity/maxSequence); i++){
			Boolean flag = true;
			int j;
			for(j =0; j<maxSequence; j++){
				String message = sender.send();
				Boolean status = receiver.receive(message);
				if(!status){
					flag = false;
					
				}
				else{
					System.out.println("Packet Acknowledgement " + (i*maxSequence+j) + " Received");
				}
			}
			if(!flag){

					System.out.println("Since a packet was Dropped sending again "+ (i*maxSequence)+ "th packet.");
					i--;
			}
		}
		System.out.println("Transmission Complete");
	}

	static String send(){
		String message;
		System.out.println("Enter message to send");
		Scanner s = new Scanner(System.in);
		message = s.next();
		return message;
	}
}

class Receiver{
	static Boolean receive(String message){
		System.out.println("Message:" + message + " --Recieved");
		//Instead of timeOut this Codition helps to identify which packet to be Dropped.
		if(message.length() > 10){
			return false;
		}
		return true;
	}
}
