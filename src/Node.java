
public class Node {
	private int key;
	private Node left, right, bottom, top;
	private int height;
	
	
	
	public Node(int key) {
		super();
		this.key = key;
	}
	public Node(int key, int height) {
		super();
		this.key = key;
		this.height = height;
	}
	
	
	public Node getTop() {
		return top;
	}


	public void setTop(Node top) {
		this.top = top;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}
	public void incrementHeight(){
		this.height = this.height + 1;
	}


	public int getKey(){
		return this.key;
	}
	
	public void setKey(int key) {
		this.key = key;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public Node getBottom() {
		return bottom;
	}
	public void setBottom(Node bottom) {
		this.bottom = bottom;
	}
	public String toString(){
		if(this.key == -1) return "-inf";
		if(this.key == 100) return "inf";
		return this.key + "";
	}
}
