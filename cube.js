
var currPage=1; //global variable, I am going to hell :O 

function rotateCube(page)
{
	//set transition time based on faces we must rotate
	transTime = Math.abs(currPage - page);
	currPage = page;	
	var degrees = (page - 1) * -90;
	var cssString = "-webkit-transform: rotateY(" + degrees + "deg ) ; -webkit-transform: translate3d(0deg, " + 
		degrees + "deg, 0deg); transform: rotateY(" + degrees + "deg ) ; transition: all " + transTime + 
		"s ease-in-out;-webkit-transform-style: preserve-3d; ";
	var cube = document.getElementById('object');
	cube.setAttribute('style' , cssString);
}