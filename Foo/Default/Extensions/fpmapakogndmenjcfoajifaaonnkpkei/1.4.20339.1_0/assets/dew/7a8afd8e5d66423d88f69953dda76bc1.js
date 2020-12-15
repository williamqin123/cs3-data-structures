/*
COPYRIGHT (C) 2013-2020 PROCTORIO INC.
USE OF THIS SOFTWARE IS PROTECTED BY COPYRIGHT LAWS AND INTERNATIONAL COPYRIGHT TREATIES,
AS WELL AS OTHER INTELLECTUAL PROPERTY LAWS AND TREATIES.
UNAUTHORIZED REPRODUCTION, DISPLAY, MODIFICATION, OR DISTRIBUTION OF THIS SOFTWARE, OR ANY PORTION OF IT,
MAY RESULT IN SEVERE CIVIL AND CRIMINAL PENALTIES, AND WILL BE PROSECUTED TO THE FULL EXTENT PERMITTED BY LAW.
USE OF THIS SOFTWARE IS CONDITIONED ON YOUR ACCEPTANCE OF OUR TERMS OF SERVICE, FOUND AT PROCTORIO.COM/TOS.
OPEN SOURCE LICENSES CAN BE FOUND IN THE LICENSE.TXT FILE OR ONLINE AT PROCTORIO.COM/LICENSES.
*/
(function(){if(!document.body.getAttribute("data-pio-3p")){document.body.setAttribute("data-pio-3p",1);var d=0,e=function(){document.body.removeEventListener("2fd3c9",e);d=1},f=function(){var a=document.querySelectorAll("section.t");if(a.length)for(var b=0;b<a.length;b++)a[b].style.display="none";a=document.querySelectorAll("section.i");if(a.length)for(b=0;b<a.length;b++)a[b].style.display="none";a=document.querySelectorAll("footer.fty");if(a.length)for(b=0;b<a.length;b++)a[b].style.display="none";
d||setTimeout(f,250)},g=function(a){"?d603645bf7d642a6bcc240f98694c4c9"!=window.location.search.substring(0,33)&&"?3f62c6e6c689476c8283c8f179b0f7bb"!=window.location.search.substring(0,33)?a(!1):window.location.search.substring(33)?1>=window.location.hash.length?a(!1):a(!0):a(!1)};f();(function(a){var b=document.createElement("link");b.href=chrome.extension.getURL("webassets/q36s.css");b.rel="stylesheet";document.head.appendChild(b);b=document.createElement("link");b.href=chrome.extension.getURL("webassets/q38s.css");
b.rel="stylesheet";document.head.appendChild(b);var c=new XMLHttpRequest;c.open("GET",chrome.extension.getURL("webassets/q38s.html"));c.onreadystatechange=function(){c.readyState==c.DONE&&200==c.status&&(document.body.insertAdjacentHTML("beforeend",c.responseText),a())};c.send()})(function(){g(function(a){if(a)chrome.runtime.sendMessage([1,window.location.origin,"3p"]),document.body.addEventListener("2fd3c9",e);else{if(a=document.getElementById("proctorio_3p_preparing_test"))a.style.display="none";
if(a=document.getElementById("proctorio_3p_invalid_test"))a.style.display="block"}})})}})();
