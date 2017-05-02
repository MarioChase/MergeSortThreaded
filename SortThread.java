import java.util.ArrayList;
import java.util.concurrent.Callable;

public class SortThread implements Callable<Container>{
	Container a_stored;
	Container b_stored;
	Container c_stored;
	public SortThread(Container a, Container b){
		a_stored = a;
		b_stored = b;
	}
	@Override
	public Container call() throws Exception {
		c_stored = a_stored.mergeContainer(b_stored);
		return c_stored;
	}
	
}
