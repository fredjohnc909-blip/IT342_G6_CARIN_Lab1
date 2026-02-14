# Connecting Web, Backend, and Mobile

Use this guide to run the full system with **XAMPP (MySQL `it342_carin_lab1`)**, **Spring Boot backend**, **React web**, and **Android mobile** using the same API.

---

## 1. Backend (Spring Boot + XAMPP MySQL)

1. **Start MySQL in XAMPP**
   - Open **XAMPP Control Panel** → start **MySQL**.
   - Your database **`it342_carin_lab1`** (with `users` table) will be used.

2. **Run the backend**
   - Open Command Prompt or PowerShell.
   - From the repo folder:
     ```text
     cd backend
     run-backend.cmd
     ```
   - If you see "JAVA_HOME is not set", set it first, e.g.:
     ```text
     set "JAVA_HOME=C:\Program Files\Java\jdk-19"
     ```
   - Wait until you see: **Started AuthBackendApplication**.
   - Backend is now at **http://localhost:8080** and uses DB **it342_carin_lab1**.

---

## 2. Web (React)

1. **Terminal**
   ```text
   cd web
   npm install
   npm run dev
   ```
2. Open the URL shown (e.g. **http://localhost:5173**).
3. Web talks to the backend via Vite proxy (no extra config).

---

## 3. Mobile (Android) – use the repo’s app

The app with **Register, Login, Dashboard, Logout** is in the repo’s **`mobile`** folder, not the default “IT342_Carin” template.

1. **In Android Studio**
   - **File → Open**
   - Select: **`d:\IT342_G6_CARIN_Lab1\mobile`** (the `mobile` folder inside this repo).
   - Click **OK**. Let Gradle sync.

2. **Backend URL**
   - **Emulator:** Already set to `http://10.0.2.2:8080/` (your PC’s localhost).
   - **Physical device:** In `mobile/app/src/main/java/com/it342/auth/ApiConstants.kt`, set `BASE_URL` to your PC’s IP, e.g. `http://192.168.1.100:8080/`. PC and phone must be on the same Wi‑Fi.

3. **Run**
   - Make sure the **backend is running** (step 1).
   - Run the app (▶) on emulator or device.
   - You should see Login → Register / Login → Dashboard (protected) → Logout.

---

## Summary

| Part    | URL / DB              | Purpose                    |
|---------|------------------------|----------------------------|
| XAMPP   | MySQL, DB `it342_carin_lab1` | Database for users         |
| Backend | http://localhost:8080  | Same API for Web + Mobile  |
| Web     | http://localhost:5173  | React app (proxy to 8080)  |
| Mobile  | 10.0.2.2:8080 (emulator) or PC IP:8080 (device) | Android app → backend      |

- **Passwords:** Sent to backend only; stored **encrypted (BCrypt)** in MySQL.
- **Protected screens:** Dashboard is only available when logged in (Web and Mobile).
