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
var tick = 300;
var timestr = null;
var seekBar;
var volumeBar = document.getElementById("Speed");
//var seekbar = document.getElementById("seek-bar");
var blnplay = false;

//setcount_function = function(){
function setcount_function(){
     // Buttons
  var playButton = document.getElementById("play-pause");
    if (blnplay == false) {
       
        blnplay= true;
        playButton.innerHTML = "Pause";
        let int_tick = setInterval(tick_function,3000);
        int_ticks = int_tick
        //alert(int_tick);
        
                
    }else{
        blnplay= false;
        playButton.innerHTML = "Play";
        clearInterval(int_ticks);
    }
}

var last_tick_epoch_millis;

tick_function = function(){
    var this_tick_epoch_millis;
seekbar = document.getElementById("seek-bar");

if (tick < 60) {
    //running secongs
 sec = sec + tick;
 if (sec > 60) {
    min = min+1;
    sec = sec - 60;
 
  }
 if (min > 60) {
     hour = hour +1;
     min = 0;
 }
 if ( hour > 24){
  day = day +1;
  hour = 0;
   }
}else if (tick > 60){   
 min = min + Math.floor(tick/60);
 sec = sec + tick % 60;
     
 if (sec > 60) {
    min = min+1;
    sec = sec - 60;
  }
 if (min > 60) {
     hour = hour +1;
     min = 0;
   }
 if ( hour > 24){
      day = day +1;
  hour = 0
   }
}

    seekbar.value = (day*24*60*60)+(hour*60*60)+(min*60)+sec;
    this_tick_epoch_millis = dtstring_to_ts_special(day,hour,min,sec);
    if (last_tick_epoch_millis != null) {
        mhvApiGetEvents(last_tick_epoch_millis, this_tick_epoch_millis, function(arr) {
            var i;
            for(i = 0; i < arr.length; i++)
            {
                mhvReceiveEvent(arr[i]);
            }
        });
    }
    last_tick_epoch_millis = this_tick_epoch_millis;

}

function dtstring_to_ts(strDateTime)
{
	return new Date(strDateTime).getTime();
}


function hookseek(){
  //  seekBar.addEventListener('change', function()) {
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
	
	//alert(strYear + "-" + strMonth + "-" + strDay + " " + strHr + ":" + strMin + ":" + strSec);
	
	return new Date(strYear + "-" + strMonth + "-" + strDay + " " + strHr + ":" + strMin + ":" + strSec).getTime();
}

// Change current viewing time when scrubbing through the progress bar




// Event listener for the play/pause button
//playButton.addEventListener("click", function() {
//  if (blnplay.paused == true) {
    // Play the video
 //  btnvar = settimeout(setcount_function,360000);
    // Update the button text to 'Pause'
  //  
  //} else {
    // Pause the video
  //  blnplay.paused = true;

    // Update the button text to 'Play'
  //  playButton.innerHTML = "Play";
 // }
//});

