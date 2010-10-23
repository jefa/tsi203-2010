var geo;
var reasons;
var global_map;
var global_marker;
var markAddress;

function initGMap() {
    var latlng = new google.maps.LatLng(-34.397, 150.644);
    var myOptions = {
      zoom: 8,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
}

function addMarker(googlemap, lat, long) {
	var marker = new GMarker(new GLatLng(lat,long), {draggable:true}); 
	GEvent.addListener(marker, 'click', function() {
		marker.openInfoWindow('Matanga dijo la changa <br/>Si esta es la <font style="color:red;">  segunda</font> línea acepta html');
		});
	googlemap.addOverlay(marker);
}

function addMarkerWithInfo(googlemap, lat, long, info) {
	if(global_map)
		googlemap = global_map;
	if(markAddress)
		info = markAddress[info];
	eraseMarkers(googlemap);
	var marker = new GMarker(new GLatLng(lat,long), {draggable:true, map:map});
	document.getElementById('CreateEvent:hidden_coord_lat').value = marker.getLatLng().lat();
	document.getElementById('CreateEvent:hidden_coord_lng').value = marker.getLatLng().lng();
	global_map = googlemap;
	global_marker = marker;
	GEvent.addListener(marker, 'click', function() {
		if(info == null || info == -1)
			marker.openInfoWindow('<br/><a href="javascript: void 0" onclick="eraseMarkers(global_map);">Quitar marcador</a>');
		else
			marker.openInfoWindow(info + '<br/><a href="javascript: void 0" onclick="eraseMarkers(global_map);">Quitar marcador</a>');		
		});
	GEvent.addListener(marker, 'dragend', function() {
		document.getElementById('CreateEvent:hidden_coord_lat').value = marker.getLatLng().lat();
		document.getElementById('CreateEvent:hidden_coord_lng').value = marker.getLatLng().lng();
		});
	GEvent.addListener(marker, "dragstart", function() {  
		map.closeInfoWindow();
		});
	googlemap.addOverlay(marker);
}

function eraseMarkers(googlemap) {
	
	googlemap.clearOverlays();
	document.getElementById('CreateEvent:hidden_coord_lat').value = '';
	document.getElementById('CreateEvent:hidden_coord_lng').value = '';
}

function eraseMarker(googlemap, marker) {
	googlemap.clearOverlay(marker);
}

function addMarkerByAddress(gmap, geocoder, address) {
	if(geocoder) {
		geocoder.geocode( { 'address': address}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				gmap.setCenter(results[0].geometry.location);
				var marker = new google.maps.Marker({
					map: gmap, 
					position: results[0].geometry.location
				});
			} else {
				alert("Geocode was not successful for the following reason: " + status);
			}
		});
	}	
}

/*
function showAddress2(street, city, country, gmap) {
   // ====== Perform the Geocoding ======
  var search = document.getElementById(street).value + ', ';
  search += document.getElementById(city).value + ', ';
  search += document.getElementById(country).value;
  geo.getLocations(search, function (result)
    { 
      // If that was successful
      if (result.Status.code == G_GEO_SUCCESS) {
        // How many resuts were found
        alert("Found " +result.Placemark.length +" results");
        // Loop through the results, placing markers
        for (var i=0; i<result.Placemark.length; i++) {
          var p = result.Placemark[i].Point.coordinates;
          addMarkerWithInfo(gmap, p[1], p[0], result.Placemark[i].address);
          //var marker = new GMarker(new GLatLng(p[1],p[0]));
          //alert("<br>"+(i+1)+": "+ result.Placemark[i].address + marker.getPoint());
          //gmap.addOverlay(marker);
        }
        // centre the map on the first result
        var p = result.Placemark[0].Point.coordinates;
        gmap.setCenter(new GLatLng(p[1],p[0]),11);
      }
      // ====== Decode the error status ======
      else {
        var reason="Code "+result.Status.code;
        if (reasons[result.Status.code]) {
          reason = reasons[result.Status.code];
        } 
        alert('Could not find "'+search+ '" ' + reason);
      }
    }
  );
}
*/

function showAddress(street, gmap) {
	  global_map = gmap;
	  search = document.getElementById(street).value;
	  geo.getLocations(search, function (result)
	    { 
	      // If that was successful
	      if (result.Status.code == G_GEO_SUCCESS) {
	        alert("Found " +result.Placemark.length +" results");
	        // Loop through the results, placing markers
	        markAddress = new Array(result.Placemark.length);
	        resetRow();
	        for (var i=0; i<result.Placemark.length; i++) {
	          var p = result.Placemark[i].Point.coordinates;
	          //addMarkerWithInfo(gmap, p[1], p[0], result.Placemark[i].address);
	          //res[i] = p;
	          markAddress[i] = result.Placemark[i].address;
	          addRow('global_map', p[1], p[0], i);
	          //document.getElementById('CreateEvent:hidden_coord_lat').value = marker.getLatLng().lat();
	      	  //document.getElementById('CreateEvent:hidden_coord_lng').value = marker.getLatLng().lng();
	          //var marker = new GMarker(new GLatLng(p[1],p[0]));
	          //alert("<br>"+(i+1)+": "+ result.Placemark[i].address + marker.getPoint());
	          //gmap.addOverlay(marker);
	        }
	        // centre the map on the first result
	        var p = result.Placemark[0].Point.coordinates;
	        gmap.setCenter(new GLatLng(p[1],p[0]),11);
	      }
	      // ====== Decode the error status ======
	      else {
	        var reason="Code "+result.Status.code;
	        if (reasons[result.Status.code]) {
	          reason = reasons[result.Status.code];
	        } 
	        alert('Could not find "'+search+ '" ' + reason);
	      }
	    }
	 );
}

function showAddress3(street, city, country, gmap, div) {
	  global_map = gmap;
	  var search = document.getElementById(street).value + ', ';
	  search += document.getElementById(city).value + ', ';
	  search += document.getElementById(country).value;
	  geo.getLocations(search, function (result)
	    { 
	      // If that was successful
	      if (result.Status.code == G_GEO_SUCCESS) {
	        alert("Found " +result.Placemark.length +" results");
	        // Loop through the results, placing markers
	        markAddress = new Array(result.Placemark.length);
	        for (var i=0; i<result.Placemark.length; i++) {
	          var p = result.Placemark[i].Point.coordinates;
	          //addMarkerWithInfo(gmap, p[1], p[0], result.Placemark[i].address);
	          //res[i] = p;
	          markAddress[i] = result.Placemark[i].address;
	          addRow('global_map', p[1], p[0], i);
	          //document.getElementById('CreateEvent:hidden_coord_lat').value = marker.getLatLng().lat();
	      	  //document.getElementById('CreateEvent:hidden_coord_lng').value = marker.getLatLng().lng();
	          //var marker = new GMarker(new GLatLng(p[1],p[0]));
	          //alert("<br>"+(i+1)+": "+ result.Placemark[i].address + marker.getPoint());
	          //gmap.addOverlay(marker);
	        }
	        // centre the map on the first result
	        var p = result.Placemark[0].Point.coordinates;
	        gmap.setCenter(new GLatLng(p[1],p[0]),11);
	      }
	      // ====== Decode the error status ======
	      else {
	        var reason="Code "+result.Status.code;
	        if (reasons[result.Status.code]) {
	          reason = reasons[result.Status.code];
	        } 
	        alert('Could not find "'+search+ '" ' + reason);
	      }
	    }
	 );
}


function  initGeoCoder(map) {
	
	GEvent.addListener(map,"click", function(overlay, point) {     
		  if (point) { 
		    addMarkerWithInfo(map, point.lat(), point.lng(), -1);
		  }
	});

	
	if (GBrowserIsCompatible()) { 
	
	  // ====== Create a Client Geocoder ======
	  geo = new GClientGeocoder(); 
	
	  // ====== Array for decoding the failure codes ======
	  reasons=[];
	  reasons[G_GEO_SUCCESS]            = "Success";
	  reasons[G_GEO_MISSING_ADDRESS]    = "Missing Address: The address was either missing or had no value.";
	  reasons[G_GEO_UNKNOWN_ADDRESS]    = "Unknown Address:  No corresponding geographic location could be found for the specified address.";
	  reasons[G_GEO_UNAVAILABLE_ADDRESS]= "Unavailable Address:  The geocode for the given address cannot be returned due to legal or contractual reasons.";
	  reasons[G_GEO_BAD_KEY]            = "Bad Key: The API key is either invalid or does not match the domain for which it was given";
	  reasons[G_GEO_TOO_MANY_QUERIES]   = "Too Many Queries: The daily geocoding quota for this site has been exceeded.";
	  reasons[G_GEO_SERVER_ERROR]       = "Server error: The geocoding request could not be successfully processed.";
	  
	} else {
	  alert("Lo sentimos, pero googleMap no es compatible con este navegador.");
	}
}

function resetRow() {
	var tabBody = document.getElementById("markerTable");
	while(tabBody.childNodes.length > 0) {
		tabBody.removeChild(tabBody.firstChild);
	}	
}

function addRow(gmap, lat, lng, i) {
		var text = '<a href="javascript: void 0" onclick="addMarkerWithInfo(' + gmap + ',' + lat + ',' + lng + ',' + i + ');">' + markAddress[i] + '</a>';
        var tabBody=document.getElementById("markerTable");
	   	var newdiv = document.createElement('div');
	   	newdiv.innerHTML = text;
	    tabBody.appendChild(newdiv);
}
