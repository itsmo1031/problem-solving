function solution(cards1, cards2, goal) {
    let answer = "Yes";
    
    while(goal.length>0) {
        const target = goal.shift();
        if (cards1 && cards1[0] === target) {
            cards1.shift();
        } else if (cards2 && cards2[0] === target) {
            cards2.shift();
        } else {
            answer = "No";
            break;
        }
    }

    return answer;
}