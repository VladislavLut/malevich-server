$(document).ready(function() {
    $("#popup-dishcard").hide();
});
//Функция отображения PopUp
function PopUpDishCardShow(el) {

    var parentBox = el.parentNode,
        itemImg = parentBox.querySelector('.dish-img').src,
        itemTitle = parentBox.querySelector('.dish-title').innerHTML,
    itemDescr = parentBox.querySelector('.dish-description').innerHTML;
    document.getElementById("pop-dishcard-img").src = itemImg;
    document.getElementById("pop-dishcard-title").innerHTML = itemTitle;
    document.getElementById("pop-dishcard-description").innerHTML = itemDescr;
  $("#popup-dishcard").show();
  currentScroll=$(window).scrollTop();
  $(window).bind('scroll', lockscroll);
}
//Функция скрытия PopUp
function PopUpDishCardHide() {
    
  $("#popup-dishcard").hide();
   $(window).unbind('scroll');
   scrollCur();
   return;
}

var currentScroll=0;
function lockscroll(){
    $(window).scrollTop(currentScroll);
}

function scrollCur() {
    $("html, body").animate({
        scrollTop: currentScroll
      }, 6);
}


