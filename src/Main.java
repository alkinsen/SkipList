import java.util.Scanner;

public class Main {

	public static void Main(String[] args) {
		Skiplist s = new Skiplist(128);
		s.insert(10, 3);
		s.insert(15, 1);
		s.insert(19, 5);
		s.insert(22, 1);
		s.insert(37, 2);
		s.insert(45, 3);
		s.insert(73, 3);
		s.insert(57);

		Visualization v = new Visualization(s);
		v.printList();
		
		Operations o = new Operations(s);
		
		
		while(true){
			Scanner scan = new Scanner(System.in);
			System.out.print("Enter your command:");
			
			String line = scan.nextLine();
			
			String[] linesplit = line.split("\\*");
			String command = linesplit[0];
			String key = linesplit[1];
			
			if(command.equalsIgnoreCase("insert")){
				v.search(Integer.parseInt(key));
				s.insert(Integer.parseInt(key));
				v.printList();
			}else if(command.equalsIgnoreCase("search")){
				v.search(Integer.parseInt(key));
			}else if(command.equalsIgnoreCase("save")){
				o.writeOutput(key);
			}else if(command.equalsIgnoreCase("load")){
				s = o.readInput(key);
				v.setS(s);
				o.setS(s);
				v.printList();
			}
			
		}
		
		
	
	}

}
