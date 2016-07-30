// MHV GovHack 2016
// JS interface to Java API

// Base url for MHV Java API
var mhvApiBaseUrl = "https://govhack2016.makehackvoid.com/api/java/"

// Get all events from <from> to <to>
// They'll be passed to the given callback as an array.
function mhvApiGetEvents(from, to, callback) 
{
	var xmlhttp = new XMLHttpRequest();
	var url = mhvApiBaseUrl + "events?from=" + from + "&to=" + to;

	xmlhttp.onreadystatechange = function() 
	{
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) 
		{
			var myArr = JSON.parse(xmlhttp.responseText);
			callback(myArr);
		}
	};
	
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}

// Get the event closest to <closestTo>, using a range of 1 hour.
// It will be passed to the given callback in an array.
function mhvApiGetEvent(closestTo, callback) 
{
	var xmlhttp = new XMLHttpRequest();
	var url = mhvApiBaseUrl + "events?closest=" + closestTo;

	xmlhttp.onreadystatechange = function() 
	{
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) 
		{
			var myArr = JSON.parse(xmlhttp.responseText);
			callback(myArr);
		}
	};
	
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}

// Get the entire set of parking lots.
// They will be passed to the given callback in an array.
function mhvApiGetLots(callback) 
{
	var xmlhttp = new XMLHttpRequest();
	var url = mhvApiBaseUrl + "lots";

	xmlhttp.onreadystatechange = function() 
	{
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) 
		{
			var myArr = JSON.parse(xmlhttp.responseText);
			callback(myArr);
		}
	};
	
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}

// Get the data for the parking lot with lot code <lotCode>.
// It will be passed to the given callback in an array.
function mhvApiGetLot(lotCode, callback) 
{
	var xmlhttp = new XMLHttpRequest();
	var url = mhvApiBaseUrl + "lots?lot=" + lot;

	xmlhttp.onreadystatechange = function() 
	{
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) 
		{
			var myArr = JSON.parse(xmlhttp.responseText);
			callback(myArr);
		}
	};
	
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}
