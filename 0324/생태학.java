import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Baekjoon4358 {
	static Scanner scanner = new Scanner(System.in);
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		HashMap<String, Integer> hashmap = new HashMap<>();
		int count = 0;
		
		while(scanner.hasNextLine()) {
			String woodName = scanner.nextLine().trim();
			
			if(woodName.equals("")) {
				break;
			}else {
				count++;
				
				if(!hashmap.containsKey(woodName)) {
					hashmap.put(woodName, 1);
				}else {
					hashmap.replace(woodName, hashmap.get(woodName) + 1);
				}
			}
		}
		
		List<String> keyset = new ArrayList<>(hashmap.keySet());
		Collections.sort(keyset);
		
		for(String name: keyset) {
			double ratio = (double)hashmap.get(name) / (double)count * 100;
		    bw.write(name + " " + String.format("%.4f", ratio) + "\n");
		}
		
		bw.flush();
		bw.close();
	}
}
