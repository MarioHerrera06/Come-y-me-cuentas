function cambiar(n) {
    var estrellas = document.getElementsByClassName("estrella");
    for(i=0; i <= n; i++){
        estrellas[i].src = 'img/Estrella.png';
        estrellas[i].style.width = 10 + "%";
    }
}


