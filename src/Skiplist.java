
public class Skiplist {
	private int numElements, m;
	private int load;
	private Node topNegativeInf, topPositiveInf;

	public final int negativeInf = -1;
	public final int positiveInf = 100;

	public int getNumElements() {
		return numElements;
	}
	
	public void setNumElements(int numElements) {
		this.numElements = numElements;
	}
	
	public int getLoad() {
		return load;
	}
	
	public void setLoad(int load) {
		this.load = load;
	}
	
	public Skiplist(int numElements) {
		this.numElements = numElements;
		this.m = (int) (Math.ceil((Math.log(this.numElements / Math.log(2))) + 1));

		topNegativeInf = new Node(negativeInf, m);
		topPositiveInf = new Node(positiveInf, m);
		topNegativeInf.setRight(topPositiveInf);
		topPositiveInf.setLeft(topNegativeInf);		

		Node left, right;
		right = topPositiveInf;
		left = topNegativeInf;

		for(int i = 1; i <= m; i++){
			left.setBottom( new Node(negativeInf, m-i));
			left.getBottom().setTop(left);
			right.setBottom( new Node(positiveInf, m-i));
			right.getBottom().setTop(right);
			
			left=left.getBottom();
			right=right.getBottom();
			left.setRight(right);
			right.setLeft(left);
			
		}

	}
	
	public Node getTopNegativeInf() {
		return topNegativeInf;
	}
	
	public void setTopNegativeInf(Node topNegativeInf) {
		this.topNegativeInf = topNegativeInf;
	}
	
	public Node getTopPositiveInf() {
		return topPositiveInf;
	}
	
	public void setTopPositiveInf(Node topPositiveInf) {
		this.topPositiveInf = topPositiveInf;
	}
	
	public int getM() {
		return m;
	}
	
	public void setM(int m) {
		this.m = m;
	}
	
	public int heightGenerator(int key){
		return key % this.m;
	}
	
	public void insert(int key, int height){
		Node currentLeft = topNegativeInf;
		Node current;
		load++;

		while(currentLeft.getHeight() > height){ 
			currentLeft = currentLeft.getBottom();
		}
		for(int i = currentLeft.getHeight(); i >= 0; i--){ 
			current = currentLeft;
			while(current.getRight().getKey() < key){
				current = current.getRight();
			}
			addAfter(current, new Node(key));
			currentLeft = currentLeft.getBottom();
		}		
	}
	
	public void insert(int key){
		Node currentLeft = topNegativeInf;
		Node current;
		int height = heightGenerator(key);
		load++;
		
		while(currentLeft.getHeight() > height){ //heighta kadar indim
			currentLeft = currentLeft.getBottom();
		}
		for(int i = currentLeft.getHeight(); i >= 0; i--){ //heighttan aşağıya eklicem
			current = currentLeft;
			while(current.getRight().getKey() < key){
				current = current.getRight();
			}
			addAfter(current, new Node(key));
			currentLeft = currentLeft.getBottom();
		}		
	}
	
	public void addAfter(Node parent, Node toBeAdded){
		Node temp = parent.getRight();

		parent.setRight(toBeAdded);
		temp.setLeft(toBeAdded);

		toBeAdded.setLeft(parent);
		toBeAdded.setRight(temp);
		toBeAdded.setHeight(parent.getHeight());
		
		Node topLevelStart;
		
		while(parent.getKey() != negativeInf){
			parent = parent.getLeft();
		}
		topLevelStart = parent.getTop();
		temp = topLevelStart.getRight();
		while(temp.getKey() < positiveInf){
			if(temp.getKey() == toBeAdded.getKey()){
				temp.setBottom(toBeAdded);
				toBeAdded.setTop(temp);
			}
			temp = temp.getRight();		
		}		
	}
}
