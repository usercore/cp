//动态设置像素比
var iScale=0;
iScale=1/window.devicePixelRatio;
document.write('<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale='+iScale+', maximum-scale='+iScale+', minimum-scale='+iScale+'" />');
//动态设置html字体大小---适配屏幕宽度
var fontSize=document.documentElement.clientWidth/16;
	document.getElementsByTagName('html')[0].style.fontSize=fontSize+'px';
