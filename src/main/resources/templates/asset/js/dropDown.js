const gnbDepBtn = document.querySelectorAll(".js-gnb-dep");
const menuBar = document.querySelector(".js-gnb");
const menuBtn = document.getElementById("js-menu-btn");
const menuIcon = document.getElementById("js-icon");
const VISIBLE = "visible";
const NONE = "none";

const MENU_BTN = "fas fa-bars";
const CLOSE_BTN = "fas fa-times";

function resetGnb(){
    const lists = document.querySelectorAll(".js-gnb-2dep");
    console.log(lists);
    lists.forEach(list => {
        list.setAttribute("style", `height: 0px; visibility: ${NONE};`);
    });
}

function gnbDepClick(e){
    const btnWrap = e.target.parentNode;
    const gnb2Dep = btnWrap.querySelector(".js-gnb-2dep");
    const depHeight = gnb2Dep.children[0].clientHeight;
    const gnbChildrenLength =  gnb2Dep.children.length;

    // resetGnb();

    if(gnb2Dep.style.visibility !== VISIBLE){
        resetGnb();
        gnb2Dep.setAttribute("style", `height: ${depHeight * gnbChildrenLength}px; visibility: ${VISIBLE};`);
    }else{
        gnb2Dep.setAttribute("style", `height: 0px; visibility: ${NONE};`);
    }

    // console.log(gnb2Dep.style.visibility);
    // console.log(depHeight);
    // console.dir(gnbLength);
    // console.log(e.target.parentNode);
}

function init(){
    menuBtn.onclick = function(e){
        let hambergMenuBar = e.target.className === MENU_BTN ? CLOSE_BTN : MENU_BTN;
        console.dir(menuIcon);
        menuIcon.className = hambergMenuBar;

        if(menuIcon.className !== MENU_BTN){
            // when drawer is closed
            menuBar.setAttribute("style", "right: -15%");
            resetGnb();
        }else{
            // when drawer is opened
            menuBar.setAttribute("style", "right: -100%");
        }
    }
    gnbDepBtn.forEach(btn => {
        btn.addEventListener("click", gnbDepClick);
    });
}

init();