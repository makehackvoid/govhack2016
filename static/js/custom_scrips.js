window.onload = function() {

  // Video
  //var video = document.getElementById("video");

 
  
  // Sliders
  var seekBar = document.getElementById("seek-bar");
  var volumeBar = document.getElementById("Speed");

} 
var count =0;
var int_tick = null;

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

var blnplay = false;

//setcount_function = function(){
function setcount_function(){
     // Buttons
  var playButton = document.getElementById("play-pause");
    if (blnplay == false) {
       
        blnplay= true;
        playButton.innerHTML = "Pause";
        let int_tick = setInterval(tick_function,3000);
        //alert(int_tick);
        
                
    }else{
        blnplay= false;
        playButton.innerHTML = "Play";
        clearInterval(int_tick);
    }
}

tick_function = function(){
  var seekbar = document.getElementById("seek-bar");

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
     alert(day & " " & hour & " " & min & " " & sec );
 }
 if ( hour > 24){
  day = day +1;
  seekbar.value = day
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
  seekbar.value = day
  hour = 0
   }
}

//alert(day + " " + hour + " " + min + " " + sec);
}

function dtstring_to_ts(strDateTime)
{
	return new Date(strDateTime).getTime();
}


// Change current viewing time when scrubbing through the progress bar
//seekBar.addEventListener('change', function() {
    // Calculate the new time
//    var time = video.duration * (seekBar.value / 100);
    // Update the video time
  //  video.currentTime = time;
//});



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

