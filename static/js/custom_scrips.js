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

tick_function = function(){
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

seekbar.value = (day*24*60*60)+(hour*60*60)+(min*60)+sec



}

function dtstring_to_ts(strDateTime)
{
	return new Date(strDateTime).getTime();
}

function hookseek(){
  //  seekBar.addEventListener('change', function()) {

    
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

