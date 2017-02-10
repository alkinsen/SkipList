
public class Visualization {
	private Skiplist s;
	private int[][] values;
	public final int searchIndicator = -2;
	public final int searchInfIndicator = -10;
	public final int emptyCell = 0;

	public Visualization(Skiplist s){
		this.s = s;
		
		values = new int[s.getM()+1][s.getLoad()+2];
	}
	
	public void setS(Skiplist s) {
		this.s = s;
	}

	public void copyToLines(){
		Node bottomLineStart, current, parent;
		values = new int[s.getM()+1][s.getLoad()+2];
		
		current = s.getTopNegativeInf();
		while(current.getHeight() != 0){
			current= current.getBottom();
		}
		
		bottomLineStart = current;
		
		for(int j = 1; j < s.getLoad()+1; j++){
			int i = 0;
			current = current.getRight();
			parent = current.getTop();
			values[i][j] = current.getKey();
			
			while(parent != null){
				i++;
				values[i][j] = parent.getKey();
				parent = parent.getTop();
			}
		}
		for(int i = 0; i < s.getM()+1; i++){
			values[i][0] = s.negativeInf;
			values[i][s.getLoad()+1] = s.positiveInf;
		}
	}
	public void printList(){
		copyToLines();
		print();
	}
	public void print(){
		for(int i = s.getM(); i >=  0; i--){
			for(int j = 0; j < s.getLoad()+2; j++){
				if(values[i][j] == s.negativeInf){
					System.out.print("-inf");
				}else if(values[i][j] == s.positiveInf){
					System.out.print("---inf");
				}else if(values[i][j] == emptyCell){
					System.out.print("----");
				}else if(values[i][j] == searchIndicator){
					System.out.print("****");
				}else if(values[i][j] > 100){
					System.out.print("**" + (values[i][j]-100));
				}else{
					System.out.print("--" + values[i][j]);
				}
			}
			System.out.println();
			}
	}
	public void search(int num){
		copyToLines();
		Node current = s.getTopNegativeInf();
		int currentI = s.getM(), currentJ = 0;
		while(num > current.getKey()){
			if(current.getRight().getKey() <= num){
				currentJ++;
				while(values[currentI][currentJ] == emptyCell){
					values[currentI][currentJ] = searchIndicator;
					currentJ++;
				}
				if(values[currentI][currentJ]>=10 && values[currentI][currentJ]<=99){
					values[currentI][currentJ] = values[currentI][currentJ]+100;
				}
				current = current.getRight();
			}else if(current.getBottom() != null){
				current=current.getBottom();
				currentI--;
				if(currentI == 0) break;
			}
			
		}
		print();
	}
}
