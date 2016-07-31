var map, heatmap;
var heatmapData;
var parkingLots = new Array();

function initHeatMap()
{
	mhvApiGetLots(initHeatmap);
}

function initHeatmap(parkingLotData)
{
	var i;
	
	for (i = 0; i < parkingLotData.length; i++) 
	{
		parkingLots["#" + parkingLotData[i].lotCode] = [ parkingLotData[i].latitude, parkingLotData[i].longitude ];
	}
	
	heatmap = new google.maps.visualization.HeatmapLayer({
	  data: heatmapData,
	  map: map
	});
	
	heatmap.set('radius', 20);
	heatmap.set('opacity', 0.75);
}

function addHeatMapEvent(event)
{
    while (heatmapData.getLength() >= 200) {
        heatmapData.removeAt(0);
    }
	if (event.eventType == "park" && event.type == "arrival")
	{
		var latLong = parkingLots["#" + event.lotCode];
		
		if (latLong != undefined)
		{
			heatmapData.push(new google.maps.LatLng(latLong[0], latLong[1]));
		}
	}
}
