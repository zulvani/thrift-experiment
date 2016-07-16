<?php

require_once __DIR__.'/dist/lib/Thrift/ClassLoader/ThriftClassLoader.php';
$GEN_DIR = __DIR__.'/../zulvani-thrift/thrift/gen-php';

use Thrift\ClassLoader\ThriftClassLoader;

$loader = new ThriftClassLoader();
$loader->registerNamespace('Thrift', __DIR__ . '/dist/lib');
$loader->registerDefinition('zulvani', $GEN_DIR);
$loader->register();

use Thrift\Protocol\TBinaryProtocol;
use Thrift\Transport\TSocket;
use Thrift\Transport\TBufferedTransport;
use Thrift\Exception\TException;


$client = null;
try {
	$socket = new TSocket('localhost', 9090);
	$transport = new TBufferedTransport($socket, 1024, 1024);
	$protocol = new TBinaryProtocol($transport);
	$client = new \zulvani\AdditionServiceClient($protocol);
} catch (TException $tx) {
	print 'TException: '.$tx->getMessage()."\n";
}

$transport->open();
echo $client->add(200, 300);
$transport->close();
?>