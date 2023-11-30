const now = new Date();
const currentDateTime = now.toLocaleString();
document.getElementById('datetime').innerHTML = currentDateTime;

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min) + min);}
const orderNum = getRandomInt(1,100);
document.getElementById('Order #').innerHTML = orderNum;

/*const toppingArray = ['Pepperoni', 'Bacon', 'Beef', 'Mushrooms', 'Peppers', 'Onions', 'Black Olives'];  
function randomTopping(){ return toppingArray[Math.floor(Math.random() * toppingArray.length)]; }

const pizzaArray = ['Meat Lovers', 'Supreme', 'Hawaiian', 'Cheese Bread', 'Garlic Knots', 'Custom Pizza', 'Custom Pizza', 'Custom Pizza'];  
const randomPizza = pizzaArray[Math.floor(Math.random() * pizzaArray.length)]; 

const sizeArray = ['Small', 'Medium', 'Large', 'Extra Large'];  
const randomSize = sizeArray[Math.floor(Math.random() * sizeArray.length)]; 

const crustArray = ['Thin Crust', 'Regular Crust', 'Stuffed Crust'];  
const randomCrust = crustArray[Math.floor(Math.random() * crustArray.length)]; 

function getRandomPizza(){
    //thisArray = [randomPizza, randomSize, randomCrust, randomTopping]
    //return thisArray;
    thisPizza = randomPizza;
    if (thisPizza == 'Cheese Bread' || thisPizza == 'Garlic Knots'){
        const thisArray = [thisPizza]
        return thisArray
    }
    else if(thisPizza == 'Custom Pizza'){
        thisSize = randomSize;
        thisCrust = randomCrust;
        num = getRandomInt(1,5);
        const thisArray = [thisPizza, thisSize, thisCrust]
        for (let i = 0; i < num; i++){
            console.log("topping activated")
            thisArray.push(randomTopping())
        }
        return thisArray;
    }
    else{
        thisSize = randomSize;
        thisCrust = randomCrust;
        const thisArray = [thisPizza, thisSize, thisCrust]
        return thisArray;
    }
}*/
//randomPizza = getRandomPizza();
//document.getElementById('itemName').innerHTML = randomPizza;
/*const testArray = getRandomPizza();

if (testArray.length == 1){
    document.getElementById('itemName').innerHTML += '<p>' + testArray[0] + '</p>';
}
else{
    document.getElementById('itemName').innerHTML += '<p>' + testArray[0] + '</p>';
    document.getElementById('itemSize').innerHTML += '<p>' + testArray[1] + '</p>';
    document.getElementById('topping').innerHTML += '<p>' + testArray[2] + '</p>';
    //document.getElementById('itemPrice').innerHTML = getRandomPrice();
    if (testArray.length > 3){
for (let i = 3; i < testArray.length; i++){
    document.getElementById('topping').innerHTML += '<p>' + testArray[i] + '</p>';
    //document.getElementById('itemPrice').innerHTML = getRandomPrice();
}
}
}

function getRandomPrice(min,max){
    var randomnum = Math.floor(Math.random() * (max - min) + 100) / 100;
    console.log(randomnum)
    return randomnum;
}
//getRandomPrice();
document.getElementById('itemPrice').innerHTML += '<p>' + getRandomPrice(1800,5000) + '</p>';
//document.getElementById('itemName').innerHTML += '<p>' + testArray + '</p>';
//document.getElementById('itemSize').innerHTML = testArray[1];
//document.getElementById('crustType').innerHTML = testArray[2];
//console.log("Test");
//console.log(getRandomPizza());

console.log(testArray);*/