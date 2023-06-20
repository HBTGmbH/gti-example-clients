<?php

require __DIR__ . '/vendor/autoload.php';
require_once('GtiClient.php');

$gtiClient = new GtiClient();
$initResponse = $gtiClient->init(['version' => 53, 'language' => 'de']);
?>

<table>
    <?php foreach($initResponse as $key => $value): ?>
    <tr>
        <td><?=$key;?></td><td><?=$value;?></td>
    </tr>
    <?php endforeach; ?>
</table>
