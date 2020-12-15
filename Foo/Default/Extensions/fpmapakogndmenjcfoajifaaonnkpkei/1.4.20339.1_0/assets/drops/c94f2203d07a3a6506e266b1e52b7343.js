/*
COPYRIGHT (C) 2013-2020 PROCTORIO INC.
USE OF THIS SOFTWARE IS PROTECTED BY COPYRIGHT LAWS AND INTERNATIONAL COPYRIGHT TREATIES,
AS WELL AS OTHER INTELLECTUAL PROPERTY LAWS AND TREATIES.
UNAUTHORIZED REPRODUCTION, DISPLAY, MODIFICATION, OR DISTRIBUTION OF THIS SOFTWARE, OR ANY PORTION OF IT,
MAY RESULT IN SEVERE CIVIL AND CRIMINAL PENALTIES, AND WILL BE PROSECUTED TO THE FULL EXTENT PERMITTED BY LAW.
USE OF THIS SOFTWARE IS CONDITIONED ON YOUR ACCEPTANCE OF OUR TERMS OF SERVICE, FOUND AT PROCTORIO.COM/TOS.
OPEN SOURCE LICENSES CAN BE FOUND IN THE LICENSE.TXT FILE OR ONLINE AT PROCTORIO.COM/LICENSES.
*/
(function(t,c){function p(){if(!document.getElementById("pio_admin_dashboard_launcher"))for(var b=document.getElementsByTagName("a"),a,d=0,c=b.length;d<c;d++)if(-1!=b[d].textContent.indexOf("Secure Exam Proctor")){var f=l.elbow.pioQuery.closest(b[d],".d2l-datalist-item-content"),e=f.cloneNode(!0);if(e&&(a=e.getElementsByClassName("d2l-link-main"),a.length||(a=e.getElementsByClassName("d2l-link")),a.length)){b=e.getElementsByClassName("d2l-contextmenu-ph");b.length&&b[0].parentNode.removeChild(b[0]);
a[0].textContent=chrome.i18n.getMessage("B225");a[0].title=chrome.i18n.getMessage("B225");a[0].href="#";a[0].addEventListener("click",function(a){a.stopPropagation();a.preventDefault();q()});a[0].style.display="block";e.id="pio_admin_dashboard_launcher";f.parentNode.insertBefore(e,f);e.style.display="block";break}}}function q(){var b=document.getElementsByClassName("d2l-twopanelselector-wrapper"),a=document.getElementsByClassName("d2l-background-global"),d=null,m=null,f=null,e=null,g=document.getElementsByClassName("d2l-page-bg"),
k=null,n=null;if(a.length){var h=c.getComputedStyle(a[0]);m=h["background-color"].toString();f=h.background.toString();d=a[0].getElementsByTagName("div");d.length&&(h=c.getComputedStyle(d[0]),e=h["background-image"].toString())}g.length&&g[0].firstElementChild&&(k=g[0].firstElementChild,h=c.getComputedStyle(k),n=h["background-color"].toString());g=function(c){b.length&&(b[0].style.display=c?"block":"none");a.length&&(c?(a[0].style.backgroundColor=m,a[0].style.background=f):(a[0].style.backgroundColor=
"#fff",a[0].style.background="#fff"),d.length&&(d[0].style.backgroundImage=c?e:"none"));k&&(k.style.background=c?n:"#fff")};g(!1);l.f18b07(b[0],g)}var l=function(){},r=setInterval(function(){document&&document.body&&null!=document.body.getAttribute("data-pp")&&(clearInterval(r),chrome.runtime.sendMessage([11],function(b){chrome.runtime.lastError&&c.top.location.reload(!0);courseId=b[0];hostname=c.location.protocol+"//"+b[3]+b[4];background_account_type=b[5];l=new ddd0e0(courseId,hostname,"d2",c.location.hostname,
p)}))})})($,window);
