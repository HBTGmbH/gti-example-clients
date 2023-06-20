<?php

class GtiClient {
    private string $gtiUser;
    private string $gtiHmacSecret;
    private \GuzzleHttp\Client $client;

    function __construct() {
        $this->gtiUser = getenv("GTI_USER");
        $this->gtiHmacSecret = getenv("GTI_HMAC_SECRET");
        $this->client = new \GuzzleHttp\Client();
    }

    function init(array $initRequest) {
        $payload = json_encode($initRequest);
        $response = $this->client->post('https://www.geofox.de/gti/public/init', [
            'body' => $payload,
            'headers' => [
                'Geofox-Auth-User' => $this->gtiUser,
                'Geofox-Auth-Signature' => $this->hmac($payload),
                'Content-Type' => 'application/json'
            ]
        ]);
        return json_decode($response->getBody());
    }

    private function hmac(string $payload) {
        return base64_encode(hash_hmac("sha1", $payload, $this->gtiHmacSecret, true));
    }
}

?>