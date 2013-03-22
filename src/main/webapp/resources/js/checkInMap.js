function createCheckInMap(mapPane){
    "use strict";
    return {
        map: null,
        checkIns: [],
        init: function() {
            this.map = new BMap.Map(mapPane);
            this.map.centerAndZoom('上海');
            this.map.enableKeyboard();
            this.map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT, offset: new BMap.Size(10, 10)}));
            return this;
        },
        addCheckIn: function(key, lat, lng, address, time) {
            this.checkIns.push({key:key, lat:lat, lng:lng, address:address, time:time});
        },
        showCheckInPoint: function() {
            for (var i = 0; i < this.checkIns.length; i++) {
                var checkIn = this.checkIns[i];
                var marker = this.createMarker(checkIn);
                this.map.addOverlay(marker);
            }
        },
        onCheckIn: function(checkIn) {
            var index = -1;
            for (var i = 0; i < this.checkIns.length; i++) {
                if (this.checkIns[i].key === checkIn.key) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                this.map.removeOverlay(this.checkIns.splice(index)[0].marker);
            }
            var marker = this.createMarker(checkIn);
            this.checkIns.push(checkIn);
            this.map.addOverlay(marker);
        },
        createMarker: function(data) {
            var marker = new BMap.Marker(new BMap.Point(data.lng, data.lat));
            marker.checkIn = data;
            data.marker = marker;
            marker.addEventListener('mouseover', function() {
                marker.openInfoWindow(new BMap.InfoWindow(data.time + '<br>' + data.address));
            });
            return marker;
        }
    };
}