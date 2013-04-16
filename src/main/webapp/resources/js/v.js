var gCtx = null;
var gCanvas = null;
var imageData = null;
var c = 0;
var stype = 0;
var gUM = false;
var webkit = false;
var v = null;

function initCanvas(ww, hh) {
    gCanvas = document.getElementById("qr-canvas");
    var w = ww;
    var h = hh;
    gCanvas.style.width = w + "px";
    gCanvas.style.height = h + "px";
    gCanvas.width = w;
    gCanvas.height = h;
    gCtx = gCanvas.getContext("2d");
    gCtx.clearRect(0, 0, w, h);
    imageData = gCtx.getImageData(0, 0, 320, 240);
}

function captureToCanvas() {
    if (stype != 1)
        return;
    if (gUM) {
        gCtx.drawImage(v, 0, 0);
        try {
            qrcode.decode();
        } catch (e) {
            setTimeout(captureToCanvas, 500);
        }
    }
}

function read(a) {
    document.getElementById("result").innerHTML = a;
    setTimeout(captureToCanvas, 500);
}

function isCanvasSupported() {
    var elem = document.createElement('canvas');
    return !!(elem.getContext && elem.getContext('2d'));
}

function success(stream) {
    if (webkit) {
        v.src = window.webkitURL.createObjectURL(stream);
    } else {
        v.src = stream;
    }
    gUM = true;
    setTimeout(captureToCanvas, 500);
}

function error(error) {
    gUM = false;
    return;
}

function load(cb) {
    initCanvas(800, 600);
    qrcode.callback = cb || read;
}

function setWebCam() {
    if (stype == 1) {
        setTimeout(captureToCanvas, 500);
        return;
    }
    var n = navigator;
    if (n.getUserMedia) {
        v = document.getElementById("live_video");
        n.getUserMedia({video: true, audio: false}, success, error);
    } else if (n.webkitGetUserMedia) {
        v = document.getElementById("live_video");
        webkit = true;
        n.webkitGetUserMedia({video: true, audio: false}, success, error);
    } else if (n.mozGetUserMedia) {
        v = document.getElementById("live_video");
        n.mozGetUserMedia({video: true, audio: false}, success, error);
    }
    stype = 1;
    setTimeout(captureToCanvas, 500);
}
