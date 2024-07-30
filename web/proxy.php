<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json");

$url = "https://www.alfa3a.org/search/ajax-establishments?city=&areaKm=";

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
$response = curl_exec($ch);
curl_close($ch);

echo $response;
?>
