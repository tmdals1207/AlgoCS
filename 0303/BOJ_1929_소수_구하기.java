import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
/*
 *  소수를 구하는 알고리즘에는 에라토스테네스의 체 방식이 있다. m까지의 숫자중에 소수를 찾을 때 먼저 2부터 m까지의 
 * 모든 자연수를 배열에 나열한다. 그리고 남은 수 중에서 아직 처리하지 않은 가장 작은 수 i를 찾는다. 남은 수 중에서
 * i의 배수를 모두 제거하는데, 자기 자신인 i는 제거하지 않는다. 더 이상 반복할 수 없을 때까지 이 과정을 반복하게
 * 되면 2이상이고 m이하인 소수만 남아있게 된다.
 * 
 *  자세한 예시를 살펴보면 2부터 자기자신인 2를 제외한 4, 6, 8...처럼 2의 배수들을 각각 지워져 있는지 확인한
 * 후 지워져 있다면 다음 배수로 건너뛰고, 안지워져 있다면 지우고 다음 배수로 넘어간다. m까지 확인 다 끝나면,
 * 그 다음에는 3의 차례다. 자기 자신인 3은 제외하고 배수인 6, 9, 12...의 숫자들을 처러된 상태인지 확인하며
 * 처리가 이미 된 상태이면 다음 배수로 넘어가고 처리가 안됐다면 0으로 초기화 처리하고 다음 배수로 넘어간다. 이
 * 과정을 m까지 반복하게 되면 2~m 사이의 소수들만 남게 된다. 
*/
public class Baekjoon1929 {
	static Scanner scanner = new Scanner(System.in);
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		
		int array[] = new int[m + 1];
		
		for(int i = 2; i < m + 1; i++) { // 2부터 m까지의 수를 배열에 초기화
			array[i] = i;
		}
		
		for(int i = 2; i < m + 1; i++) { // 2부터 m까지의 에라토스테네스의 체 방식 적용
			if(array[i] == 0) { // 만약에 기준이 되는 수가 이미 처리된 상태(0으로 초기화)라면 다음 수로 넘어감
				continue;
			}
			// 기준이 되는 수가 처리된 상태가 아니라면 기준이 되는 수는 제외하고 그 수의 배수들을 0으로 초기화하는 과정
			for(int j = i * 2; j < m + 1; j += i) {
				if(array[j] != 0) { // 0으로 초기화 되지 않은 상태라면
					array[j] = 0; // 0으로 초기화
				}
			}
		}
		
		for(int i = n; i < m + 1; i++) {
			if(array[i] != 0) {
				bw.write(array[i] + "\n");
			}
		}
		
		bw.flush();
		bw.close();
	}
}
