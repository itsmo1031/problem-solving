class Solution {
    static boolean buildable;
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        int idx = 0;
        
        for(long number: numbers) {
            buildable = true;
            char[] bt = makeFullBinaryTree(number);
            check(bt, 0, bt.length);
            answer[idx++] = buildable ? 1 : 0;
        }
        
        return answer;
    }
    
    boolean check(char[] tree, int start, int end) {
        char root = tree[(start + end) / 2];
        
        if (end - start == 1) {
            return root == '1';
        }
        
        boolean left = check(tree, start, (start + end) / 2);
        boolean right = check(tree, (start + end) / 2 + 1, end);
        
        if (!buildable) {
            return false;
        }
        
        if ((left || right) && root == '0') {
            buildable = false;
            return false;
        }
        
        return root == '1';
    }
    
    char[] makeFullBinaryTree(long number) {
        String target = Long.toString(number, 2);
        
        int i = 0;
        int length;
        
        while ((length = (1 << i) - 1) < target.length()) {
            i += 1;
        }
        
        String answer = "0".repeat(length - target.length()) + target;
        
        return answer.toCharArray();
    }
}