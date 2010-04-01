function outer(){
    var x = 1;
    return function (){
        alert(x);
        x = x + 1;
    };
}
