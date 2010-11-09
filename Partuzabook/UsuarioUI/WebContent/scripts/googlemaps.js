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

