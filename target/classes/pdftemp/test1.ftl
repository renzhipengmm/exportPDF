<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>[ Powered by Ren ]</title>
<style>
	*{
		
		font-size:16px;
	}
	table,td {
		border : 1px solid red;
		text-align:center;
	}
	table{
		width:100%;
		border-collapse:collapse;
	}
	
</style>
</head>
<body>
<#list week as x>
<div style="height:100%;position:fixed;top:0;right:0;bottom:0;left:0;">
${x_index+1} / ${x} zhongguo 
</div>
</#list>
</body>
</html>