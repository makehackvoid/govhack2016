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
var fps = 20;
var elapsedTimePerSec = 60 * 60; // 1 hour per sec
var tick = elapsedTimePerSec / fps;
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
       
        blnplay = true;
        playButton.src = "images/pause_icon.png";
        playButton.alt = "Pause";
        let int_tick = setInterval(tick_function, 1000 / fps);
        int_ticks = int_tick
        //alert(int_tick);
        
                
    }else{
        blnplay= false;
        playButton.src = "images/play_icon.png";
        playButton.alt = "Play";
        clearInterval(int_ticks);
    }
}

var last_bubble_since;

function call_me_bubbles_maybe(event_item, lot) {
    var date = new Date();
    var now = date.getTime();
    if (last_bubble_since == null || now - last_bubble_since > 3000) {
        switch(event_item.eventType) {
            case 'news':
                last_bubble_since = now;
                push_bubble('', event_item.headLine, lot);
                break;
        }
    }
}

var last_slider_epoch_millis;
var event_list;
var event_list_i = 0;
var receiving_events = false;

function on_slider_change(slider_epoch_millis) {

    var floater = document.getElementById('datetime_floater');
	floater.innerHTML = new Date(slider_epoch_millis);

    if (!receiving_events) {
        if (last_slider_epoch_millis != null) {
            receiving_events = true;
            mhvApiGetEvents(last_slider_epoch_millis, slider_epoch_millis, function(arr) {
                var last_seen_lot;

                for(var i = 0; i < arr.length; i++)
                {
                    if (arr[i].eventType == 'park') {
                        last_seen_lot = arr[i].lotCode;
                    }
                    if (last_seen_lot != null) {
                        call_me_bubbles_maybe(arr[i], last_seen_lot);
                    }
                    mhvReceiveEvent(arr[i]);
                }
                receiving_events = false;
                last_slider_epoch_millis = slider_epoch_millis;
            });
        }
        else {
            last_slider_epoch_millis = slider_epoch_millis;
        }
    }
}

tick_function = function()
{
	seekbar = document.getElementById("seek-bar");

	sec += tick;
		 
	while (sec >= 60) 
	{
		min++;
		sec -= 60;
	}
	while (min >= 60) 
	{
		 hour++;
		 min -= 60;
	}
	
	while ( hour >= 24)
	{
		day++;
		hour -= 24;
	}

	if (day > 31)
	{
		day = 1;
	}

     seekbar.value = (day*24*60*60) + (hour*60*60) + (min*60) + sec;
     on_slider_change(dtstring_to_ts_special(day,hour,min,sec));
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
	
	return new Date(strYear + "-" + strMonth + "-" + strDay + " " + strHr + ":" + strMin + ":" + strSec).getTime();
}

// Change current viewing time when scrubbing through the progress bar
