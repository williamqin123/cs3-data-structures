/*
COPYRIGHT (C) 2013-2020 PROCTORIO INC.
USE OF THIS SOFTWARE IS PROTECTED BY COPYRIGHT LAWS AND INTERNATIONAL COPYRIGHT TREATIES,
AS WELL AS OTHER INTELLECTUAL PROPERTY LAWS AND TREATIES.
UNAUTHORIZED REPRODUCTION, DISPLAY, MODIFICATION, OR DISTRIBUTION OF THIS SOFTWARE, OR ANY PORTION OF IT,
MAY RESULT IN SEVERE CIVIL AND CRIMINAL PENALTIES, AND WILL BE PROSECUTED TO THE FULL EXTENT PERMITTED BY LAW.
USE OF THIS SOFTWARE IS CONDITIONED ON YOUR ACCEPTANCE OF OUR TERMS OF SERVICE, FOUND AT PROCTORIO.COM/TOS.
OPEN SOURCE LICENSES CAN BE FOUND IN THE LICENSE.TXT FILE OR ONLINE AT PROCTORIO.COM/LICENSES.
*/
(function(v,d){function t(){if("#/proctorio"!=d.location.hash&&"undefined"==typeof localStorage.pio_ca_gb){var b=document.URL;-1<b.indexOf("quizzes")&&-1<b.indexOf("edit")||u()}}function u(){if(!document.getElementById("pio_admin_dashboard_launcher")){var b=document.getElementById("section-tabs");if(b){b=b.getElementsByTagName("a");if(b.length)for(var a=0;a<b.length;a++)if(-1<b[a].textContent.indexOf("Secure Exam Proctor")){var e=b[a].parentNode.cloneNode(!0),d=e.getElementsByTagName("a");if(d.length){d[0].textContent=
chrome.i18n.getMessage("B225");d[0].href="#";d[0].addEventListener("click",function(a){a.stopPropagation();a.preventDefault();document.getElementById("pio_admin_container")||f()});d[0].style.display="block";e.id="pio_admin_dashboard_launcher";e.style.display="list-item";b[a].parentNode.parentNode.insertBefore(e,b[a].parentNode);n.push(e);break}}if((b=document.getElementById("menu"))&&1<b.getElementsByTagName("li").length){var h=b.getElementsByTagName("li")[1].cloneNode(!0);a=h.getElementsByTagName("a");
if(b.getElementsByTagName("svg").length){if(h.classList.remove("ic-app-header__menu-list-item--active"),a.length){a[0].setAttribute("id","proctorio_admin_menu_launcher");a[0].href="#";var g=a[0].getElementsByClassName("menu-item-icon-container");g.length&&p.elbow.d84a9(["GET",chrome.extension.getURL("webassets/s35s.svg")],function(a){var b="",c=g[0].getElementsByTagName("svg");c.length&&(b=c[0].getAttribute("class"));g[0].innerHTML=a.responseText;g[0].getElementsByTagName("svg")[0].setAttribute("class",
b)});e=a[0].getElementsByClassName("menu-item__text");e.length&&(e[0].textContent=chrome.i18n.getMessage("A045"));a[0].addEventListener("click",function(a){a.stopPropagation();a.preventDefault();h.classList.contains("ic-app-header__menu-list-item--active")||document.getElementById("pio_admin_container")||f()})}}else e=h.getElementsByClassName("menu-item-drop"),e.length&&e[0].parentNode.removeChild(e[0]),a.length&&(a[0].textContent=chrome.i18n.getMessage("B225"),a[0].addEventListener("click",function(a){a.stopPropagation();
a.preventDefault();f()}));b.appendChild(h);n.push(h)}}}}function f(){var b=document.getElementById("content"),a=document.getElementById("breadcrumbs").getElementsByTagName("UL"),e=document.title,n=document.body.classList.contains("course-menu-expanded"),h="",g=document.getElementById("wrapper");g&&(h=d.getComputedStyle(g)["max-width"]);var f=function(d){b&&(b.style.display=d?"block":"none");n&&(d?document.body.classList.add("course-menu-expanded"):document.body.classList.remove("course-menu-expanded"));
var c=document.getElementById("right-side-wrapper");c&&(c.style.display=d?"block":"none");if(c=document.getElementById("footer"))c.style.display=d?"block":"none";if(a.length)if(d){for(c=0;c<a.length;c++)a[c].classList.contains("pio_breadcrumb")?a[c].style.display="none":a[c].style.display="block";document.title=e}else{if(1<a.length)for(c=0;c<a.length;c++)a[c].classList.contains("pio_breadcrumb")?a[c].style.display="block":a[c].style.display="none";else{var l=a[0].cloneNode(!0);l.classList.add("pio_breadcrumb");
var k=l.getElementsByTagName("LI");if(3<k.length)for(c=k.length-1;0<=c;c--){var m=k[c].getElementsByTagName("a");if(m.length)if(!m[0].href||"function"!=typeof m[0].href.split||3<m[0].href.replace(q,"").split("/").length)k[c].parentNode.removeChild(k[c]);else{m=k[c].cloneNode(!0);var f=m.getElementsByTagName("A");if(f){f[0].href="#";f[0].addEventListener("click",function(a){a.preventDefault()});f[0].textContent=chrome.i18n.getMessage("B225");k[c].parentNode.appendChild(m);break}}}else k[k.length-1].textContent=
chrome.i18n.getMessage("B225");a[0].style.display="none";a[0].parentNode.insertBefore(l,a[0])}document.title=chrome.i18n.getMessage("B225")}document.getElementsByClassName("ic-app-header__menu-list-item--active").length&&(c=document.getElementById("proctorio_admin_menu_launcher"),l=document.getElementById("global_nav_courses_link"),c&&l&&(d?(l.parentNode.classList.add("ic-app-header__menu-list-item--active"),c.parentNode.classList.remove("ic-app-header__menu-list-item--active")):(l.parentNode.classList.remove("ic-app-header__menu-list-item--active"),
c.parentNode.classList.add("ic-app-header__menu-list-item--active"))));d?g&&h&&(g.style.maxWidth=h):g&&(g.style.maxWidth="none")};f(!1);p.f18b07(b,f)}var p=function(){},r="",q="",n=[];d.addEventListener("hashchange",function(){if("#/proctorio"==d.location.hash)for(var b=0;b<n.length;b++)n[b].style.display="none"});(function(){chrome.runtime.sendMessage([11],function(b){chrome.runtime.lastError&&d.top.location.reload(!0);r=b[0];q=d.location.protocol+"//"+b[3]+b[4];p=new ddd0e0(r,q,"ca",d.location.hostname,
t)})})()})($,window);
