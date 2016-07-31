
api_url_lots = "https://govhack2016.makehackvoid.com/api/java/lots";

var xmlhttp = new XMLHttpRequest();

var aMarkers = new Array();

xmlhttp.onreadystatechange = function() {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
        var myArr = JSON.parse(xmlhttp.responseText);
        myFunction(myArr);
    }
};

function myFunction(arr) {
	var i = 0;	
    for(i = 0; i < arr.length; i++)
	{
		item = arr[i];
		lotCode = item.lotCode;
		lat = Number(item.latitude);
		lon = Number(item.longitude);
		
		var temp_loc = {lat: lat, lng: lon};
		
		var temp_marker = new google.maps.Marker({
          position: temp_loc,
          map: map,
		  visible: false
        });
		
		aMarkers[lotCode] = {lat:lat,lon:lon,lotCode:lotCode,marker:temp_marker,pop:null};
	}	
}

function init_markers()
{
	xmlhttp.open("GET", api_url_lots, true);
	xmlhttp.send();
}

function pop_the_bubble(lotCode)
{
	console.log('popped bubble ' + lotCode);
	aMarkers[lotCode].pop.close();
	aMarkers[lotCode].marker.setVisible(false);
}

function push_bubble(title,content,lotCode,timeout=3000)
{
	var contentString = '<div id="content">'+
		'<div id="siteNotice">'+
		'</div>'+
		'<h3>' + title + '</h3>'+
		'<div id="bodyContent">'+
		'<p>' + content + '</p>'+
		'</div>'+
		'</div>';

	var infowindow = new google.maps.InfoWindow({
	  content: contentString
	});
	
	aMarkers[lotCode].pop = infowindow;
	aMarkers[lotCode].marker.setVisible(true);
	
	console.log('set bubble ' + lotCode);
	infowindow.open(map, aMarkers[lotCode].marker);
	setTimeout(function() {pop_the_bubble(lotCode);},3000);
}