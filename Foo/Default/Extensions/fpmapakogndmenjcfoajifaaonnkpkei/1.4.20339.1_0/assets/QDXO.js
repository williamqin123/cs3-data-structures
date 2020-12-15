/*
COPYRIGHT (C) 2013-2020 PROCTORIO INC.
USE OF THIS SOFTWARE IS PROTECTED BY COPYRIGHT LAWS AND INTERNATIONAL COPYRIGHT TREATIES,
AS WELL AS OTHER INTELLECTUAL PROPERTY LAWS AND TREATIES.
UNAUTHORIZED REPRODUCTION, DISPLAY, MODIFICATION, OR DISTRIBUTION OF THIS SOFTWARE, OR ANY PORTION OF IT,
MAY RESULT IN SEVERE CIVIL AND CRIMINAL PENALTIES, AND WILL BE PROSECUTED TO THE FULL EXTENT PERMITTED BY LAW.
USE OF THIS SOFTWARE IS CONDITIONED ON YOUR ACCEPTANCE OF OUR TERMS OF SERVICE, FOUND AT PROCTORIO.COM/TOS.
OPEN SOURCE LICENSES CAN BE FOUND IN THE LICENSE.TXT FILE OR ONLINE AT PROCTORIO.COM/LICENSES.
*/
var $jscomp=$jscomp||{};$jscomp.scope={};$jscomp.findInternal=function(a,d,b){a instanceof String&&(a=String(a));for(var e=a.length,c=0;c<e;c++){var g=a[c];if(d.call(b,g,c,a))return{i:c,v:g}}return{i:-1,v:void 0}};$jscomp.ASSUME_ES5=!1;$jscomp.ASSUME_NO_NATIVE_MAP=!1;$jscomp.ASSUME_NO_NATIVE_SET=!1;$jscomp.SIMPLE_FROUND_POLYFILL=!1;
$jscomp.defineProperty=$jscomp.ASSUME_ES5||"function"==typeof Object.defineProperties?Object.defineProperty:function(a,d,b){a!=Array.prototype&&a!=Object.prototype&&(a[d]=b.value)};$jscomp.getGlobal=function(a){return"undefined"!=typeof window&&window===a?a:"undefined"!=typeof global&&null!=global?global:a};$jscomp.global=$jscomp.getGlobal(this);
$jscomp.polyfill=function(a,d,b,e){if(d){b=$jscomp.global;a=a.split(".");for(e=0;e<a.length-1;e++){var c=a[e];c in b||(b[c]={});b=b[c]}a=a[a.length-1];e=b[a];d=d(e);d!=e&&null!=d&&$jscomp.defineProperty(b,a,{configurable:!0,writable:!0,value:d})}};$jscomp.polyfill("Array.prototype.find",function(a){return a?a:function(a,b){return $jscomp.findInternal(this,a,b).v}},"es6","es3");
var e66631=function(a,d,b,e,c,g,q){if(b.length&&e){var k=!1;d.elbow.ec975(document,chrome.extension.getURL("webassets/q44s.css"));a=[["autohide","1"],["autoplay","1"],["modestbranding","1"],["playsinline","1"],["showinfo","0"],["rel","0"]];for(var l=0;l<q.length;l++)a.push(q[l]);var u=d.elbow.a9c59("https://www.youtube.com/embed/"+e,a),m=.5625*c;b=$('<div class="pio_youtube_holder"></div>').css("width",c).css("height",m).appendTo(b);var n=["maxresdefault","mqdefault","default","sddefault"],h=0,r=
$('<div class="spinner"><div class="bounce1"></div><div class="bounce2"></div><div class="bounce3"></div></div>'),p=function(){h<n.length&&(f.src="https://img.youtube.com/vi/"+e+"/"+n[h]+".jpg")},t=function(){r.appendTo(b);$("<iframe />",{width:c,height:m,frameBorder:0,src:u}).on("load",function(){k=!0;r.hide();b.find("img").hide();$(this).show()}).appendTo(b)};if(!g)b.html('<div class="play_button"><i class="play_triangle"></i></div>').find("div.play_button").on("click",function(){$(this).hide();
t()});var f=new Image;f.onload=function(){120!=this.width&&90!=this.height||h==n.length-1?k||(f.width=c,f.height=m,$(b).prepend(f)):k||(h++,p())};f.onerror=function(){h++;p()};p();g&&t()}};window.top.dispatchEvent(new CustomEvent("e66631"));
