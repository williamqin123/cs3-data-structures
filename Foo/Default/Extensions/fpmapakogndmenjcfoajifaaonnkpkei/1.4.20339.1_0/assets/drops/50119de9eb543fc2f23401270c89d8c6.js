/*
COPYRIGHT (C) 2013-2020 PROCTORIO INC.
USE OF THIS SOFTWARE IS PROTECTED BY COPYRIGHT LAWS AND INTERNATIONAL COPYRIGHT TREATIES,
AS WELL AS OTHER INTELLECTUAL PROPERTY LAWS AND TREATIES.
UNAUTHORIZED REPRODUCTION, DISPLAY, MODIFICATION, OR DISTRIBUTION OF THIS SOFTWARE, OR ANY PORTION OF IT,
MAY RESULT IN SEVERE CIVIL AND CRIMINAL PENALTIES, AND WILL BE PROSECUTED TO THE FULL EXTENT PERMITTED BY LAW.
USE OF THIS SOFTWARE IS CONDITIONED ON YOUR ACCEPTANCE OF OUR TERMS OF SERVICE, FOUND AT PROCTORIO.COM/TOS.
OPEN SOURCE LICENSES CAN BE FOUND IN THE LICENSE.TXT FILE OR ONLINE AT PROCTORIO.COM/LICENSES.
*/
(function(l,d){if(!d.top.document.body.getAttribute("data-pio-aaa")){d.top.document.body.setAttribute("data-pio-aaa",1);var h=function(){var b=document.URL;(-1<b.indexOf("listContentEditable.jsp")||-1<b.indexOf("listContent.jsp"))&&g()},k=function(){var b=document.getElementById("globalNavPageContentArea"),e=document.title,a=function(a){b&&(b.style.display=a?"block":"none");document.title=a?e:chrome.i18n.getMessage("B225")};a(!1);pipe.f18b07(b,a)},g=function(){var b=document.querySelectorAll('li[id^="contentListItem"]');
if(b.length){for(var e=null,a=0,f=b.length;a<f;a++){var c=b[a].getElementsByTagName("a");if(c.length)for(var d=0,g=c.length;d<g;d++)if(-1!=c[d].textContent.indexOf("Secure Exam Proctor")){e=b[a];a=f;break}}if(null!=e&&!e.getAttribute("data-pio-admin-template")){e.setAttribute("data-pio-admin-template",1);b=e.cloneNode(!0);c=b.getElementsByTagName("span");a=0;for(f=c.length;a<f;a++)if(c[a].classList.contains("reorder"))c[a].style.display="none";else if(-1!=c[a].textContent.indexOf("Secure Exam Proctor")){c[a].textContent=
chrome.i18n.getMessage("B225");break}a=b.getElementsByTagName("img");a.length&&(a[0].src=chrome.extension.getURL("webassets/icon-32.webp"));c=b.getElementsByTagName("a");a=0;for(f=c.length;a<f;a++)c[a].href="#",-1==c[a].textContent.indexOf(chrome.i18n.getMessage("B225"))&&(c[a].style.display="none");e.parentNode.insertBefore(b,e);b.addEventListener("click",function(a){a.preventDefault();k()});b.style.display="list-item";b.style.cursor="default"}}};(function(){chrome.runtime.sendMessage([11],function(b){chrome.runtime.lastError&&
d.top.location.reload(!0);courseId=b[0];hostname=d.location.protocol+"//"+b[3]+b[4];background_account_type=b[5];pipe=new ddd0e0(courseId,hostname,"bb",d.location.hostname,h)})})()}})($,window);
