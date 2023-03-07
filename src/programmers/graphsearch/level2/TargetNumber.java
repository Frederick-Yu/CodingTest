package programmers.graphsearch.level2;

import java.util.LinkedList;
import java.util.Queue;

/*-- 깊이/너비 우선 탐색(DFS/BFS): 타겟 넘버

문제 설명
n개의 음이 아닌 정수들이 있습니다. 이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다.
예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3
사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.

제한사항
- 주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
- 각 숫자는 1 이상 50 이하인 자연수입니다.
- 타겟 넘버는 1 이상 1000 이하인 자연수입니다.
입출력 예
numbers	            target	return
[1, 1, 1, 1, 1]	    3	    5
[4, 1, 2, 1]	    4	    2
입출력 예 설명
입출력 예 #1
문제 예시와 같습니다.

입출력 예 #2
+4+1-2+1 = 4
+4-1+2-1 = 4
총 2가지 방법이 있으므로, 2를 return 합니다.
 */
public class TargetNumber {
    public int solution(int[] numbers, int target) {
//        return dfs(numbers, target, 0, 0);
        return bfs(numbers, target);
    }

    private static int bfs(int[] numbers, int target) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, numbers[0]));
        q.add(new Pair(0, numbers[0] * -1));

        int answer = 0;
        while (!q.isEmpty()) {
            Pair p = q.poll();
            System.out.println(p.toString());

            if (p.position == numbers.length - 1) {
                if (p.sum == target) {
                    answer++;
                }
                continue;
            }

            int nPosition = p.position + 1;
            if (nPosition >= numbers.length) {
                continue;
            }

            q.add(new Pair(nPosition, p.sum + numbers[nPosition]));
            q.add(new Pair(nPosition, p.sum + numbers[nPosition] * -1));
        }

        return answer;
    }

    private static int dfs(int[] numbers, int target, int sum, int current) {
        int ans = 0;

        if (current == numbers.length) {
            if (target == sum) {
                return 1;
            }
            return 0;
        }

        ans += dfs(numbers, target, sum + numbers[current], current + 1);
        ans += dfs(numbers, target, sum + numbers[current] * -1, current + 1);

        return ans;
    }

    private static class Pair {
        int position;
        int sum;

        public Pair(int position, int sum) {
            this.position = position;
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "position: " + position + ", sum: " + sum;
        }
    }

    public static void main(String[] args) {
        System.out.println( new TargetNumber().solution(new int[]{1, 1, 1, 1, 1}, 3) ); // 5
        System.out.println( new TargetNumber().solution(new int[]{4, 1, 2, 1}, 4) ); // 2
    }
}
