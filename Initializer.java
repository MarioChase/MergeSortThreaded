import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Initializer {
	public static void main(String args[]) {
		Initializer a = new Initializer();
		try {
			a.go();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void go() throws InterruptedException {
		Container temp;
		ArrayList<Integer> records = generateRecords();
		ArrayList<Container> containers = new ArrayList<Container>();
		ArrayList<Callable<Container>> tasks = new ArrayList<Callable<Container>>();
		int thread_num = records.size() / 2;
		for (int i = 0; i < records.size(); i++) {
			temp = new Container();
			temp.nums.add(records.get(i));
			temp.print();
			containers.add(temp);
		}
		while (containers.size() > 1) {
			for (int i = 0; i < containers.size() - 1; i += 2) {
				temp = containers.get(i).mergeContainer(containers.get(i + 1));
				
				Callable<Container> task = new SortThread(containers.get(i), containers.get(i + 1));
				ExecutorService executor = Executors.newFixedThreadPool(thread_num);
				tasks.add(task);
				List<Future<Container>> futures = executor.invokeAll(tasks);
				for (Future<Container> f : futures) {
					try {
						System.out.println("Threaded");
						f.get().print();
						if(f.get().getSize() == records.size()){
							System.exit(1);
						}
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				containers.remove(i + 1);
				containers.remove(i);
				containers.add(temp);
				thread_num = containers.size() / 2;
			}
		}
	}

	public ArrayList<Integer> generateRecords() {
		Random rand = new Random();
		ArrayList<Integer> records = new ArrayList<Integer>();
		Scanner input = new Scanner(System.in);
		System.out.println("Enter number of numbers to produce: ");
		int nums = input.nextInt();
		for (int i = 0; i < nums; i++) {
			records.add(rand.nextInt(100));
			System.out.println(records.get(i));
		}
		return records;
	}
}
