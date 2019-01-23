<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html;charset=utf-8"/>
		<meta name="viewport" content="initail-scale=1.0,user=scalable=no"/>
		<style>
		</style>
		
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=P2o7XFISxs5XcRePZ8ovaUA2t0GbZ6Lu">
			
		</script>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<title>添加/删除覆盖物</title>
	</head>
	
	<body>
		<center>
			
				<input class="latlon" type="text" placeholder="latlon" name="lat_lng" size="100"/>
				<button class="submit">submit</button>
				<input class="res" type="text" size="100"/>
    	</center>
	</body>
	<script>
		$(function(){
			$('.submit').click(function(){
				let latlonArr = $('.latlon').val().split(';');
				let newLatlonArr=[];
				for(let i=0;i<latlonArr.length;i++){
					// 对于每一个输入的经纬度坐标
					let temp=latlonArr[i].split(',');
					newLatlonArr[i]=temp[1].substring(1)+","+temp[0];
				}
				var s=newLatlonArr.join(";");
				console.log(s);
				// $.get("http://api.map.baidu.com/geoconv/v1/?coords="+s+"&from=3&to=5&ak=P2o7XFISxs5XcRePZ8ovaUA2t0GbZ6Lu",function(res){
				// 	console.log(res)
				// 	let result=[];
				// 	for(let i=0;i<res.result.length;i++){
				// 		result[i]=res.result[i].x+","+res.result[i].y;
				// 	}
				// 	$('.res').val(result.join(";"))
				// })
				$.get("http://10.138.121.81:8080/find/goodsList",function(res){
					console.log(res);
					// let result=[];
					// for(let i=0;i<res.result.length;i++){
					// 	result[i]=res.result[i].x+","+res.result[i].y;
					// }
					// $('.res').val(result.join(";"))
				})
			})
			
		})
	</script>
</html>