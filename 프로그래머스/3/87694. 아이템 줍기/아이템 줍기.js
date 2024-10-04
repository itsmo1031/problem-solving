function solution(rectangle, characterX, characterY, itemX, itemY) {
    // 2배 확장한 배열 사용
    const graph = Array.from(new Array(102), () => new Array(102).fill(0));
    
    // 직사각형을 그리는 함수 호출
    rectangle.forEach((rec) => {
        draw(graph, rec);
    });
    
    // BFS 탐색
    const answer = bfs(graph, characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    return answer;
}

// 직사각형 테두리를 그리는 함수
const draw = (graph, rec) => {
    let [sr, sc, er, ec] = rec.map(e => e * 2);
    
    // 테두리 및 내부 구분
    for (let r = sr; r <= er; r++) {
        for (let c = sc; c <= ec; c++) {
            if (r == sr || r == er || c == sc || c == ec) {
                if (graph[r][c] != 2) { // 내부가 아닌 경우에만 테두리 설정
                    graph[r][c] = 1;
                }
            } else {
                graph[r][c] = 2; // 내부는 2로 설정
            }
        }
    }
}

// 방향 배열 (상하좌우)
const DIR = [[1, 0], [0, 1], [-1, 0], [0, -1]];

// BFS 탐색 함수
const bfs = (graph, sx, sy, ex, ey) => {
    let q = [[sx, sy, 0]];  // 시작 좌표와 이동 거리를 큐에 저장
    const visited = Array.from(new Array(102), () => new Array(102).fill(false));  // 방문 배열
    visited[sx][sy] = true;  // 시작점 방문 처리
    
    while (q.length != 0) {
        const [cx, cy, time] = q.shift();  // 현재 좌표와 시간(거리)
        
        if (cx == ex && cy == ey) {  // 아이템 위치 도달 시
            return time / 2;  // 2배로 확장했으니 다시 2로 나눠 원래 거리 반환
        }
        
        for (let [dx, dy] of DIR) {
            const nx = cx + dx;
            const ny = cy + dy;
            
            if (nx < 1 || nx > 101 || ny < 1 || ny > 101 || visited[nx][ny]) {
                continue;  // 범위를 벗어나거나 이미 방문한 경우 무시
            }
            
            if (graph[nx][ny] == 1) {  // 테두리(1)일 경우만 이동 가능
                visited[nx][ny] = true;
                q.push([nx, ny, time + 1]);  // 큐에 다음 좌표와 시간(거리) 추가
            }
        }
    }
}
