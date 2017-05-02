import java.util.ArrayList;
import java.util.Collections;

public class Container {
	public ArrayList<Integer> nums = new ArrayList<Integer>();

	public Container() {
	}

	public int getSize() {
		return nums.size();
	}

	public int getElement(int index) {
		return nums.get(index);
	}

	public void addElement(int value) {
		nums.add(value);
	}

	public void removeElement(int i) {
		nums.remove(i);
	}

	public Container mergeContainer(Container a) {
		this.print();
		Container c = new Container();
		int new_Size = this.getSize() + a.getSize();
		while (c.getSize() < new_Size) {
			for(int i = 0; i < this.getSize(); i ++){
				c.addElement(this.getElement(i));
			}
			for(int i = 0; i < a.getSize(); i ++){
				c.addElement(a.getElement(i));
			}
		}
		Collections.sort(c.nums);
		return c;
	}

	public void print() {
		System.out.println("Numbers in container: " + nums.size());
		for (int i = 0; i < nums.size(); i++) {
			System.out.print(nums.get(i) + ",");
		}
		System.out.println("");

	}
}
