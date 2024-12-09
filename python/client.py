import base64
import hashlib
import hmac
import json
import os

import requests


class GtiClient:
  def __init__(self, _user: str, _secret: str):
    """
    :param _user: Benutzername für geofox-auth-user
    :param _secret: Passwort/Schlüssel für die HMAC-Signatur
    """
    self.gti_user = _user
    self.gti_hmac_secret = _secret
    self.client = requests.Session()

  def _get_signature(self, request_body: str) -> str:
    """
    Erzeugt die HMAC-Signatur für die Authentifizierung.
    :param request_body: Der JSON-codierte Request-Body als Python-Dict
    :return: Base64-codierte Signatur als String
    """
    key = self.gti_hmac_secret.encode("utf-8")  # Passwort als Byte
    message = request_body.encode("utf-8")  # JSON-Body
    hmac_digest = hmac.new(key, message, hashlib.sha1).digest()  # HMAC mit SHA1
    signature = base64.b64encode(hmac_digest).decode("utf-8")  # Base64-codiert
    return signature

  def send(self, endpoint: str, request: dict) -> str:
    url = "https://gti.geofox.de/gti/public/" + endpoint

    request_body = json.dumps(request)
    headers = {
      'Accept': 'application/json',
      'geofox-auth-signature': self._get_signature(request_body),
      'geofox-auth-user': self.gti_user,
      'geofox-auth-type': 'HmacSHA1',
      'Content-Type': 'application/json',
      'Accept-Encoding': 'gzip, deflate',
    }
    response = requests.request("POST", url, headers=headers, data=request_body)
    return response.json()


if __name__ == "__main__":
  user = os.getenv("GTI_USER")
  secret = os.getenv("GTI_HMAC_SECRET")
  client = GtiClient(user, secret)
  initRequest = {
    "language": "de",
    "version": 59
  }
  init_response = client.send("init", initRequest)
  print(init_response)
