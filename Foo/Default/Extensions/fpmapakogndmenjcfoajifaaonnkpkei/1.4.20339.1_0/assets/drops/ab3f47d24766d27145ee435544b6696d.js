/*
COPYRIGHT (C) 2013-2020 PROCTORIO INC.
USE OF THIS SOFTWARE IS PROTECTED BY COPYRIGHT LAWS AND INTERNATIONAL COPYRIGHT TREATIES,
AS WELL AS OTHER INTELLECTUAL PROPERTY LAWS AND TREATIES.
UNAUTHORIZED REPRODUCTION, DISPLAY, MODIFICATION, OR DISTRIBUTION OF THIS SOFTWARE, OR ANY PORTION OF IT,
MAY RESULT IN SEVERE CIVIL AND CRIMINAL PENALTIES, AND WILL BE PROSECUTED TO THE FULL EXTENT PERMITTED BY LAW.
USE OF THIS SOFTWARE IS CONDITIONED ON YOUR ACCEPTANCE OF OUR TERMS OF SERVICE, FOUND AT PROCTORIO.COM/TOS.
OPEN SOURCE LICENSES CAN BE FOUND IN THE LICENSE.TXT FILE OR ONLINE AT PROCTORIO.COM/LICENSES.
*/
(function(){function m(){if(!document.getElementById("pio_admin_dashboard_launcher"))for(var b=document.getElementsByClassName("modtype_lti"),e=0,d=b.length;e<d;e++)if("LI"==b[e].tagName){var a=b[e].getElementsByClassName("instancename");if(a.length&&0==a[0].textContent.indexOf("Secure Exam Proctor")){d=b[e].cloneNode(!0);d.id="pio_admin_dashboard_launcher";a=d.getElementsByClassName("instancename");a.length&&(a[0].textContent=chrome.i18n.getMessage("B225"));a=d.getElementsByClassName("activityicon");
a.length&&(a[0].src=chrome.extension.getURL("webassets/icon-32.webp"));a=d.getElementsByTagName("A");if(a.length)for(var f=0;f<a.length;f++)a[f].getElementsByClassName("instancename").length&&(a[f].removeAttribute("onclick"),a[f].href="#",a[f].addEventListener("click",function(a){a.preventDefault();n()}));a=d.getElementsByClassName("editing_move");a.length&&a[0].parentNode.removeChild(a[0]);a=d.getElementsByClassName("moodle-actionmenu");a.length&&a[0].parentNode.removeChild(a[0]);a=d.getElementsByClassName("editing_title");
a.length&&a[0].parentNode.removeChild(a[0]);b[e].parentNode.insertBefore(d,b[e]);d.style.display="list-item";break}}}function n(){for(var b=["page-content-wrapper","main","page","region-main"],e,d=0;d<b.length&&!e;d++)e=document.getElementById(b[d]);var a=document.getElementsByClassName("breadcrumb"),f=document.title;b=function(b){e&&(e.style.display=b?"block":"none");var c=document.getElementById("page-footer");c&&(c.style.display=b?"block":"none");var d=document.getElementsByClassName("navbutton");
for(c=0;c<d.length;c++)d[c].style.display=b?"block":"none";if(a.length)if(b){for(c=0;c<a.length;c++)a[c].classList.contains("pio_breadcrumb")?a[c].style.display="none":a[c].style.display="block";document.title=f}else{if(1<a.length)for(c=0;c<a.length;c++)a[c].classList.contains("pio_breadcrumb")?a[c].style.display="block":a[c].style.display="none";else{b=a[0].cloneNode(!0);b.classList.add("pio_breadcrumb");d=b.getElementsByTagName("LI");for(c=d.length-1;0<=c;c--){var g=d[c].getElementsByTagName("a");
if(g.length)if(g[0].href&&"function"==typeof g[0].href.endsWith&&g[0].href.endsWith("/course/index.php")){g[0].href="#";g[0].addEventListener("click",function(a){a.preventDefault()});g[0].textContent=chrome.i18n.getMessage("B225");break}else d[c].parentNode.removeChild(d[c])}a[0].style.display="none";a[0].parentNode.insertBefore(b,a[0])}document.title=chrome.i18n.getMessage("B225")}};b(!1);h.f18b07(e,b)}function p(){-1<document.URL.indexOf("/course/view.php?id=")&&m()}var h=function(){},k="",l="";
(function(){chrome.runtime.sendMessage([11],function(b){chrome.runtime.lastError&&window.top.location.reload(!0);k=b[0];l=window.location.protocol+"//"+b[3]+b[4];h=new ddd0e0(k,l,"mo",window.location.hostname,p)})})()})();
