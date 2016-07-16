<?php

require_once __DIR__.'/dist/lib/Thrift/ClassLoader/ThriftClassLoader.php';
$GEN_DIR = __DIR__.'/../zulvani-thrift/thrift/gen-php';

use Thrift\ClassLoader\ThriftClassLoader;

$loader = new ThriftClassLoader();
$loader->registerNamespace('Thrift', __DIR__ . '/dist/lib');
$loader->registerDefinition('zulvani', $GEN_DIR);
$loader->register();

use Thrift\Exception\TException;
use Thrift\Protocol\TBinaryProtocol;
use Thrift\Protocol\TMultiplexedProtocol;
use Thrift\Transport\TFramedTransport;
use Thrift\Transport\TSocket;


$client = null;
try {
	$transport = new TFramedTransport(new TSocket('localhost', 9090), 1024, 1024);
	$protocol = new TBinaryProtocol($transport);
	$mp = new TMultiplexedProtocol($protocol, 'AdditionService');
	$mpMultiply = new TMultiplexedProtocol($protocol, 'MultiplyService');
	$client = new \zulvani\AdditionServiceClient($mp);
	$clientMultiply = new \zulvani\MultiplyServiceClient($mpMultiply);
} catch (TException $tx) {
	print 'TException: '.$tx->getMessage()."\n";
}

$transport->open();
echo $client->add(200, 300);
echo '<br/>';
echo $clientMultiply->multiply(200, 300);
$transport->close();
?>