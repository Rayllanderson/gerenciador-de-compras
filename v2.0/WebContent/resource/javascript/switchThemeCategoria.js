function categoriaThemeSwitch(colorTheme, animation) {

    var wall = document.getElementById("wall");
    wall.style.backgroundImage = "none"

    let btnList = document.getElementById('btn-new-list');
    let navbar = document.getElementById('navbar');

    changeClassName(btnList, navbar, colorTheme)


    switch (colorTheme.toUpperCase()) {
        case 'PINK':
            //muda nome do class wall e carrega css
            wall.className = "wall pink";
            break;
        case 'BLUE':
            wall.className = "wall blue";
            break;
        case 'RED':
            wall.className = "wall red";
            break;
        case 'PURPLE':
            wall.className = "wall purple";
            break;
        case 'CIAN':
            wall.className = "wall cian";
            break;
        case 'GREEN':
            wall.className = "wall green";
            break;
        case 'BLACK':
            wall.className = "wall black";
            break;
        case 'ORANGE':
            wall.className = "wall orange";
            break;
        case 'GALAXY':
            wall.style.backgroundImage = ""
            wall.className = "wall galaxy";
            break;
        case 'DEFAULT':
            btnList.className = 'btn btn-success';
            navbar.className = 'navbar navbar-expand navbar-dark bg-primary';
            wall.className = "default";
            $("#tema").attr("href", 'resource/css/theme/categoria/DEFAULT.css')
            break;
    }
    activeAnimation(animation);
}

/**
 muda o nome das classes e o css se houver necessidade
 */
function changeClassName(btnList, navbar, colorTheme) {

    if (wall.className == 'default' || wall.className == "default animate" || wall.className.includes('default')) { //está no defaultT-theme
        btnList.className = 'btn btn-outline-success';
        navbar.className = 'navbar navbar-expand navbar-dark';
        $("#tema").attr("href", 'resource/css/theme/categoria/' + colorTheme.toUpperCase() + '.css');
    }//senao, nao tem necessidade de mudar, já que já estão no tema
}

function activeAnimation(animation) {
    if (animation) {
        let wall = document.getElementById("wall");
        wall.classList.add('animate');
        setTimeout(() => wall.classList.remove('animate'), 999);
    }
}





