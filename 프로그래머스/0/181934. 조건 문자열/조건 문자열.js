const operations = {
    '>=' : (n, m) => n >= m,
    '<=' : (n, m) => n <= m,
    '>!' : (n, m) => n > m,
    '<!' : (n, m) => n < m,
}

function solution(ineq, eq, n, m) {
    const op = operations[ineq + eq];
    return +op(n, m);
}