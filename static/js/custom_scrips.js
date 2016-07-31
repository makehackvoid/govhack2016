var count =0;
var int_ticks = null;

gettime = function(){
var seekBar = document.getElementById("seek-bar");


}
// creates the counter function 

var sec = 0;
var min = 0;
var hour = 0;
var day = 1;
var tick = 60000/20;
var timestr = null;
var seekBar;
//var volumeBar = document.getElementById("Speed");
//var seekbar = document.getElementById("seek-bar");
var blnplay = false;

//setcount_function = function(){
function setcount_function(){
     // Buttons
  var playButton = document.getElementById("play-pause");
    if (blnplay == false) {
       
        blnplay= true;
        playButton.innerHTML = "Pause";
        let int_tick = setInterval(tick_function,50);
        int_ticks = int_tick
        //alert(int_tick);
        
                
    }else{
        blnplay= false;
        playButton.innerHTML = "Play";
        clearInterval(int_ticks);
    }
}

tick_function = function(){
seekbar = document.getElementById("seek-bar");

sec = sec + tick;
     
 while (sec >= 60) {
    min = min+1;
    sec = sec - 60;
  }
 while (min >= 60) {
     hour = hour +1;
     min = 0;
   }
 while ( hour >= 24){
      day = day +1;
  hour = 0
}

if (day > 31){
    day = 1
}

seekbar.value = (day*24*60*60)+(hour*60*60)+(min*60)+sec




}

function dtstring_to_ts(strDateTime)
{
	return new Date(strDateTime).getTime();
}


function dtstring_rev_to_ts(strDateTime)
{
	var dt = strDateTime.split(/\-|\s/);
	return new Date(dt.slice(0,3).reverse().join('-') + ' ' + dt[3]).getTime();
}

function dtstring_to_ts_special(day,hr,min,sec)
{
	var strDay = day;
	var strMonth = "05";
	var strYear = "2016";
	var strHr = hr;
	var strMin = min;
	var strSec = sec
	
	if(day < 10)
		strDay = "0" + strDay;
	
	if(hr < 10)
		strHr = "0" + strHr;
		
	if(min < 10)
		strMin = "0" + strMin;
	
	if(sec < 10)
		strSec = "0" + strSec;
	
	alert(strYear + "-" + strMonth + "-" + strDay + " " + strHr + ":" + strMin + ":" + strSec);
	
	return new Date(strYear + "-" + strMonth + "-" + strDay + " " + strHr + ":" + strMin + ":" + strSec).getTime();
}

// Change current viewing time when scrubbing through the progress bar


