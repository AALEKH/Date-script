<?php
function getDaysBetweenDates($from,$to,$addLastDay = false){
    $d1 = new DateTime($from);
    $d2 = new DateTime($to);
    if($addLastDay){
        $d2->add(new \DateInterval('P1D'));
    }
    return $d1 -> diff($d2)->days;
}
//10
$a = getDaysBetweenDates('2013-03-01','2013-03-11',true)."\n";
$i = 0;
$arr = array();
while ($i < $a) {

$Y = '2013-03-11'.-$i." "." "."days";
$y= strval($Y);
$output = date('Y-m-d', strtotime($y));
array_push($arr, $output);
$i++;
}

foreach ($arr as $key) {
	# code...
	echo $key."\n";
}
echo "next is month    ";

$datetime1 = date_create('2012-03-01');
$datetime2 = date_create('2013-12-11');
$interval = date_diff($datetime1, $datetime2);
//echo $interval->format('%m months')."\n";
$a = $datetime1->diff($datetime2)->m + ($datetime1->diff($datetime2)->y*12)."\n";
$i = 1;
$arr_two = array();
array_push($arr_two, '2013-12-11');
while ($i <= $a) {
$Y = '2013-12-11'.-$i." "."months";
$y= strval($Y);
$output = date("Y-m-t", strtotime($y));
array_push($arr_two, $output);
$i++;
}
foreach ($arr_two as $key) {
	# code...
	echo "\n".$key."\n";
}
echo "next is year    ";
$arr_three = array();
$date1 = '2008-03-01';
$date2 = '2013-12-11';

$diff = abs(strtotime($date2) - strtotime($date1));

$selected_days = floor($diff / (365*60*60*24));
$i = 1;
array_push($arr_three, '2013-12-11');
while ($i <= $selected_days) {
$Y = date("Y",strtotime('2013-12-11')).'-12-31'.-$i." "."years";
$y= strval($Y);
$output = date("Y-m-t", strtotime($y));
array_push($arr_three, $output);
$i++;
}

foreach ($arr_three as $key) {
	# code...
	echo $key."\n";
}

?>
<!--
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title></title>
  <style type="text/css">
#header {
	z-index: 1;
	position: fixed;
	width: 97.5%;
	margin-top: -20px;
	height: 60px;
	background-color: #6CEB70;
	margin-bottom: 10px;
}
#name {
	float:left;
	margin-left: 5px;
	padding-top: 25px;
	font-size: 16px;
	font-family: Verdana, sans-serif;
	color: #ffffff;
}

select {
    -moz-appearance: none;
    text-indent: 0.01px;
    text-overflow: '';
}

#email{
	float:right;
	margin-right: 5px;
	padding-top: 25px;
	font-size: 16px;
	font-family: Verdana, sans-serif;
	color: #ffffff;
}
  </style>
</head>
<body>
	<div id="header">
			<div id="name"><select style="width:200px;"><option>aaaaa</option><option>bbb</option></select></div>
			<div><div id="email"><select style="width:200px;"><option>Monthly</option><option>Yearly</option><option>Days</option></select></div></div>
			<div style="padding-top:25px;position:absolute;margin-left:450px;"><input type="date" name="startdate"/><input type="date" name="enddate"/></div>
		</div>

</body>
</html>
-->
