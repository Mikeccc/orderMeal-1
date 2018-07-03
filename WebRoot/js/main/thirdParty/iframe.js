function iframeAutoFit()
{
	try
	{
		if(window!=parent)
		{
			var a = parent.document.getElementsByTagName("iframe");
			for(var i=0; i<a.length; i++) //author:meizz
			{
				if(a[i].contentWindow==window)
				{
					var h1=0, h2=0, d=document, dd=d.documentElement;
					a[i].parentNode.style.height = a[i].offsetHeight +"px";
					a[i].style.height = "10px";

					if(dd && dd.scrollHeight) h1=dd.scrollHeight;
					if(d.body) h2=d.body.scrollHeight;
					var h=Math.max(h1, h2);

					if(document.all) {h += 4;}
					if(window.opera) {h += 1;}
					a[i].style.height = a[i].parentNode.style.height = h +"px";
				}
			}
		}
	}
	catch (ex){}
}
if(window.attachEvent)
{
	window.attachEvent("onload",  iframeAutoFit);
	//window.attachEvent("onresize",  iframeAutoFit);
}
else if(window.addEventListener)
{
	window.addEventListener('load',  iframeAutoFit,  false);
	//window.addEventListener('resize',  iframeAutoFit,  false);
}
