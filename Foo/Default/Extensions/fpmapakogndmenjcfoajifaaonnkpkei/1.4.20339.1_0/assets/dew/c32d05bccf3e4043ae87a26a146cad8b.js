/*
COPYRIGHT (C) 2013-2019 PROCTORIO INC.
USE OF THIS SOFTWARE IS PROTECTED BY COPYRIGHT LAWS AND INTERNATIONAL COPYRIGHT TREATIES,
AS WELL AS OTHER INTELLECTUAL PROPERTY LAWS AND TREATIES.
UNAUTHORIZED REPRODUCTION, DISPLAY, MODIFICATION, OR DISTRIBUTION OF THIS SOFTWARE, OR ANY PORTION OF IT,
MAY RESULT IN SEVERE CIVIL AND CRIMINAL PENALTIES, AND WILL BE PROSECUTED TO THE FULL EXTENT PERMITTED BY LAW.
USE OF THIS SOFTWARE IS CONDITIONED ON YOUR ACCEPTANCE OF OUR TERMS OF SERVICE, FOUND AT PROCTORIO.COM/TOS.
OPEN SOURCE LICENSES CAN BE FOUND IN THE LICENSE.TXT FILE OR ONLINE AT PROCTORIO.COM/LICENSES.
*/
(function(){function a(c,f,a){var d=!1,e=new MutationObserver(function(a){b()}),b=function(){var b=c.querySelector(f);!d&&b&&(e.disconnect(),d=!0,a(b))};e.observe(c,{attributes:!0,childList:!0,subtree:!0,characterData:!1});b()}document.body.getAttribute("data-pio-oc")||(document.body.setAttribute("data-pio-oc",1),a(document.body,"#similarity_detection_tools",function(c){c.style.display="block";a(document.body,"#similarity_detection_tool option",function(a){a.innerText="Proctorio"})}))})();
