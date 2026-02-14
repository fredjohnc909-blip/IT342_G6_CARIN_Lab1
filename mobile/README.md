# IT342 Auth – Android (Kotlin)

Register, Login, Dashboard (Profile), and Logout using the same Spring Boot backend as the web app.

**Important:** Open **this** `mobile` folder in Android Studio (File → Open → select the `mobile` folder inside the repo). Do not use a separate "IT342_Carin" template project; this folder already contains the full auth app.

## Requirements

- Android Studio (Hedgehog or newer recommended)
- JDK 17
- Backend running on host (e.g. `http://localhost:8080`) and MySQL (XAMPP, DB `it342_carin_lab1`)

## Backend URL

Edit **`app/src/main/java/com/it342/auth/ApiConstants.kt`**:

- **Emulator:** `http://10.0.2.2:8080/` (default – points to host machine’s localhost).
- **Physical device:** Use your computer’s IP, e.g. `http://192.168.1.100:8080/`, and ensure the device and PC are on the same network. Backend allows cleartext (HTTP) for development.

## Run

1. Open the **`mobile`** folder in Android Studio.
2. Sync Gradle (File → Sync Project with Gradle Files).
3. Start the backend (and MySQL) on your PC.
4. Run the app (▶) on an emulator or device.

## Features

- **Register** – username, email, password (min 6 chars); then auto-login and go to Dashboard.
- **Login** – email + password; JWT stored and used for `/api/user/me`.
- **Dashboard** – shows ID, username, email, date joined; **protected** (redirects to Login if not logged in).
- **Logout** – calls `/api/auth/logout`, clears token, returns to Login.

Passwords are sent over HTTPS/HTTP to the backend and are hashed (BCrypt) on the server only; they are not stored in plain text on the device.
