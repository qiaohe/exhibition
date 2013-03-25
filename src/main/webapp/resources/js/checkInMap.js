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
            this.checkIns.forEach(function(checkIn) {
                this.map.addOverlay(this.$createMarker(checkIn));
            }, this);
        },
        onCheckIn: function(checkIn) {
            for (var i in this.checkIns) {
                if (this.checkIns[i].key == checkIn.key) {
                    this.map.removeOverlay(this.checkIns.splice(i)[0].marker);
                    break;
                }
            }
            var marker = this.$createMarker(checkIn);
            this.checkIns.push(checkIn);
            this.map.addOverlay(marker);
            marker.setAnimation(BMAP_ANIMATION_BOUNCE);
            setTimeout((function(marker) {
                return function() {
                    marker.setAnimation(null);
                }
            })(marker), 3000);
        },
        $createMarker: function(checkIn) {
            var marker = new BMap.Marker(new BMap.Point(checkIn.lng, checkIn.lat));
            marker.checkIn = checkIn;
            checkIn.marker = marker;
            marker.addEventListener('mouseover', function() {
                marker.openInfoWindow(new BMap.InfoWindow(checkIn.time + '<br>' + checkIn.address));
            });
            marker.setAnimation(BMAP_ANIMATION_DROP);
            return marker;
        }
    };
}