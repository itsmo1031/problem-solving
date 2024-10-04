const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let input = [];

const isLowerCase = (ch) => {
    return ch == ch.toLowerCase();
}

rl.on('line', function (line) {
    input = [line];
}).on('close',function(){
    let split = [...input[0]];
    split = split.map((e) => {
        if (isLowerCase(e)) {
            return e.toUpperCase();
        }
        return e.toLowerCase();
    });
    
    console.log(split.join(''));
});