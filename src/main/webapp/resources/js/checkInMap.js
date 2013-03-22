var checkInMap = {
    map: null,
    attendees: [],
    init: function() {
        this.map = new BMap.Map('mapPane');
        this.map.centerAndZoom('上海');
        this.map.enableKeyboard();
        this.map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT, offset: new BMap.Size(10, 10)}));
        return this;
    },
    addAttendee: function(id, lat, lng, address, checkInAt) {
        this.attendees.push({id:id, lat:lat, lng:lng, address:address, checkInAt:checkInAt});
    },
    showCheckInPoint: function() {
        for (var i = 0; i < this.attendees.length; i++) {
            var data = this.attendees[i];
            var marker = new BMap.Marker(new BMap.Point(data.lng, data.lat));
            marker.addEventListener('mouseover', (function(marker, data) {
                return function() {
                    var date = new Date(data.checkInAt);
                    var info = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() + ' ' + date.getHours() + ':' + date.getMinutes() +
                        '<br>' + data.address;
                    var infoWindow = new BMap.InfoWindow(info);
                    marker.openInfoWindow(infoWindow);
                }
            })(marker, data));
            this.map.addOverlay(marker);
        }
    }
};