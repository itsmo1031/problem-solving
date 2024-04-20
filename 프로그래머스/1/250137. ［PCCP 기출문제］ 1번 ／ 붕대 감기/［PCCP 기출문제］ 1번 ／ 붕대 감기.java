class Solution {
    static int MAX_HEALTH;
    static int[] bandage;
    
    public int solution(int[] bandage, int health, int[][] attacks) {
        MAX_HEALTH = health;
        this.bandage = bandage;
        
        int lastAttackTime = attacks[attacks.length - 1][0];
        int attackIdx = 0;
        int nextAttackTime;
        int time = 0;
        int streak = 0;
        
        while (health > 0 && ++time <= lastAttackTime && attackIdx < attacks.length) {
            nextAttackTime = attacks[attackIdx][0];
            
            if (time == nextAttackTime) {
                health = attack(health, attacks[attackIdx++][1]);
                streak = 0;
                continue;
            }
            
            health = heal(health, ++streak);
            
            if (streak == bandage[0]) {
                streak = 0;
            }
        }
        
        return health <= 0 ? -1 : health;
    }
    
    // 몬스터로부터 공격을 받아 체력을 감소시키는 메서드
    static int attack(int currentHealth, int power) {
        return currentHealth - power;
    }
    
    // 현재 체력에서 붕대를 감아 체력을 회복하는 메서드
    // streak이 bandage[0]과 같으면 추가 체력(bandage[2])만큼 추가 회복
    static int heal(int currentHealth, int streak) {
        int plus = bandage[1];
        
        if (streak == bandage[0]) {
            plus += bandage[2];    
        }
        
        int result = currentHealth + plus;
        
        return result > MAX_HEALTH ? MAX_HEALTH : result;
    }
}