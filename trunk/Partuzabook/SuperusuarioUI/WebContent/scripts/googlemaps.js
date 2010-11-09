var geo;
var reasons;
var global_map;
var global_marker;
var markAddress;
/*
function initGMap() {
    var latlng = new google.maps.LatLng(-34.397, 150.644);
    var myOptions = {
      zoom: 8,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
}
*/
/*
function addMarker(googlemap, lat, long) {
	var marker = new GMarker(new GLatLng(lat,long), {draggable:true}); 
	GEvent.addListener(marker, 'click', function() {
		marker.openInfoWindow('Matanga dijo la changa <br/>Si esta es la <font style="color:red;">  segunda</font> línea acepta html');
		});
	googlemap.addOverlay(marker);
}
*/
function addMarkerWithInfo(googlemap, lat, long, info, hidden_input_lat, hidden_input_lng) {
	if(global_map)
		googlemap = global_map;
	if(markAddress)
		info = markAddress[info];
	eraseMarkers(googlemap, hidden_input_lat, hidden_input_lng);
	if(lat == 0 && long == 0)
		return;
	var marker = new GMarker(new GLatLng(lat,long), {draggable:true, map:map});
	//document.getElementById('CreateEvent:hidden_coord_lat').value = marker.getLatLng().lat();
	//document.getElementById('CreateEvent:hidden_coord_lng').value = marker.getLatLng().lng();
	document.getElementById(hidden_input_lat).value = marker.getLatLng().lat();
	document.getElementById(hidden_input_lng).value = marker.getLatLng().lng();
	global_map = googlemap;
	global_marker = marker;
	GEvent.addListener(marker, 'click', function() {
		if(info == null || info == -1)
			marker.openInfoWindow('<br/><a href="javascript: void 0" onclick="eraseMarkers(global_map, \''+ hidden_input_lat +'\',\'' + hidden_input_lng + '\');">Quitar marcador</a>');
		else
			marker.openInfoWindow(info + '<br/><a href="javascript: void 0" onclick="eraseMarkers(global_map, \''+ hidden_input_lat +'\',\'' + hidden_input_lng + '\');">Quitar marcador</a>');		
		});
	GEvent.addListener(marker, 'dragend', function() {
		//document.getElementById('CreateEvent:hidden_coord_lat').value = marker.getLatLng().lat();
		//document.getElementById('CreateEvent:hidden_coord_lng').value = marker.getLatLng().lng();
		document.getElementById(hidden_input_lat).value = marker.getLatLng().lat();
		document.getElementById(hidden_input_lng).value = marker.getLatLng().lng();
		});
	GEvent.addListener(marker, "dragstart", function() {  
		map.closeInfoWindow();
		});
	
	googlemap.setZoom(15);
	googlemap.setCenter(marker.getLatLng());
	googlemap.addOverlay(marker);
}

function eraseMarkers(googlemap, hidden_lat, hidden_lng) {
	
	googlemap.clearOverlays();
	document.getElementById(hidden_lat).value = '';
	document.getElementById(hidden_lng).value = '';
}

function eraseMarker(googlemap, marker) {
	googlemap.clearOverlay(marker);
}

/*
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
*/
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

function showAddress(street, gmap, hidden_lat, hidden_lng) {
	  global_map = gmap;
	  search = document.getElementById(street).value;
	  geo.getLocations(search, function (result)
	    { 
	      // If that was successful
	      if (result.Status.code == G_GEO_SUCCESS) {
	        alert("Se encontraron " +result.Placemark.length +" resultados");
	        // Loop through the results, placing markers
	        markAddress = new Array(result.Placemark.length);
	        resetRow();
	        for (var i=0; i<result.Placemark.length; i++) {
	          var p = result.Placemark[i].Point.coordinates;
	          //addMarkerWithInfo(gmap, p[1], p[0], result.Placemark[i].address);
	          //res[i] = p;
	          markAddress[i] = result.Placemark[i].address;
	          addRow('global_map', p[1], p[0], i, hidden_lat, hidden_lng);
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
	        var reason="Codigo "+result.Status.code;
	        if (reasons[result.Status.code]) {
	          reason = reasons[result.Status.code];
	        } 
	        alert('No se pudo encontrar "'+search+ '" ' + reason);
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
	        alert("Se encontraron " +result.Placemark.length +" resultados");
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
	        var reason="Codigo "+result.Status.code;
	        if (reasons[result.Status.code]) {
	          reason = reasons[result.Status.code];
	        } 
	        alert('No se pudo encontrar "'+search+ '" ' + reason);
	      }
	    }
	 );
}


function  initGeoCoder(map, hidden_lat, hidden_lng) {
	
	GEvent.addListener(map,"click", function(overlay, point) {     
		  if (point) { 
		    addMarkerWithInfo(map, point.lat(), point.lng(), -1, hidden_lat, hidden_lng);
		  }
	});

	
	if (GBrowserIsCompatible()) { 
	
	  // ====== Create a Client Geocoder ======
	  geo = new GClientGeocoder(); 
	
	  // ====== Array for decoding the failure codes ======
	  reasons=[];
	  reasons[G_GEO_SUCCESS]            = "Exito.";
	  reasons[G_GEO_MISSING_ADDRESS]    = "Dirección faltante: No se escribió una dirección.";
	  reasons[G_GEO_UNKNOWN_ADDRESS]    = "Dirección desconocida:  No se pudo encontrar una locación geografica para la dirección ingresada.";
	  reasons[G_GEO_UNAVAILABLE_ADDRESS]= "Dirección no disponible:  La coordenadas para la dirección ingresada no pueden ser mostradas por condiciones legales.";
	  reasons[G_GEO_BAD_KEY]            = "Key errónea: La API key es inválida o no corresponde para el dominio dado.";
	  reasons[G_GEO_TOO_MANY_QUERIES]   = "Demasiadas consultas: La cutoa de consutlas diarias para este sitio fue excedida..";
	  reasons[G_GEO_SERVER_ERROR]       = "Error del servidor: El pedido no pudo ser prcesado satisfactoriamente.";
	  
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

function addRow(gmap, lat, lng, i, hidden_lat, hidden_lng) {
		var text = '<a href="javascript: void 0" onclick="addMarkerWithInfo(' + gmap + ',' + lat + ',' + lng + ',' + i + ',\'' + hidden_lat + '\',\'' + hidden_lng +'\');">' + markAddress[i] + '</a>';
        var tabBody=document.getElementById("markerTable");
	   	var newdiv = document.createElement('div');
	   	newdiv.innerHTML = text;
	    tabBody.appendChild(newdiv);
}
