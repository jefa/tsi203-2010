function addMarkerWithInfo(googlemap, lat, long, info) {
	eraseMarkers(googlemap);	
	if(lat == 0 && long == 0)
		return;
	var marker = new GMarker(new GLatLng(lat,long), {draggable:false, map:map});
	googlemap.addOverlay(marker);
	//googlemap.setCenter(new GLatLng(lat + 0.07653234864629, long + 0.09681701660152),8);
	
}

function eraseMarkers(googlemap) {	
	googlemap.clearOverlays();
}


function noStrong(str) {
	var array1 = str.split("<");
	var array2 = array1[1].split(">");
	return array2[1];
}