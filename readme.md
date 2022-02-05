# Firebase Spring Boot project

* Create project at Firebase: https://console.firebase.google.com/
* Create firestore collection (no need for this)
* Project settings -> Service accounts -> Firebase Admin SDK  -> Generate new private key
* Set Firebase initializer bean using serviceAccountKey.json file
* No need for setting database url because serviceAccountKey.json file contains project_id

