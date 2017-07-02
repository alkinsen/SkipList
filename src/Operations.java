import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Operations {
	private Skiplist s;
	public Operations(Skiplist s){
		this.s = s;
	}
	
	public void setS(Skiplist s) {
		this.s = s;
	}

	public Skiplist readInput(String filename){
		Skiplist result = new Skiplist(s.getNumElements());
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String line = br.readLine(), key, height;
			String[] linesplit;
			while(line != null){
				linesplit = line.split("\\s+");
				key = linesplit[0];
				height = linesplit[1];
				
				result.insert(Integer.parseInt(key), Integer.parseInt(height));
				line= br.readLine();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public void writeOutput(String filename){
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			
			Node currentBottom = s.getTopNegativeInf();
			
			while(currentBottom.getBottom() != null){
				currentBottom = currentBottom.getBottom();
			}
			
			currentBottom = currentBottom.getRight();
			while(currentBottom.getKey() < s.positiveInf){
				Node current= currentBottom;
				while(current.getTop() != null){
					current = current.getTop();
				}
				writer.write(current.getKey()+" "+current.getHeight() + "\r\n");
				currentBottom = currentBottom.getRight();
			}
			writer.flush();
			writer.close();
		
			System.out.println("Your skiplist has been saved.");	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
