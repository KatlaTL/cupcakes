var buyOrder = document.getElementById("buyOrder");
var userBalance = parseFloat(document.getElementById("userBalance").innerHTML);
var totalPrice = parseFloat(document.getElementById("price").innerHTML);
console.log(userBalance);
console.log(totalPrice);
buyOrder.onclick = function(){
    if(totalPrice > userBalance) {
        buyOrder.removeAttribute("href");
        var para = document.getElementById("notEnoughBalance");
        var node = document.createTextNode("You don't have enough balance to buy this!");
        para.appendChild(node);
    } else {
        buyOrder.setAttribute("href", "cupcakesServle");
    }
};