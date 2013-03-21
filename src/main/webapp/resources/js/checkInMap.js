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
    addAttendee: function(id, lat, lng) {
        this.attendees.push({id:id, lat:lat, lng:lng});
    },
    showCheckInPoint: function() {
        for (var i = 0; i < this.attendees.length; i++) {
            this.map.addOverlay(new BMap.Marker(new BMap.Point(this.attendees[i].lng, this.attendees[i].lat)));
        }
    }
};