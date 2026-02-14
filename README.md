# IT342_G6_CARIN_Lab1

Authentication system: **Web** (React), **Mobile** (Android Kotlin), and **Backend** (Spring Boot).  
Same backend API for both clients; passwords stored encrypted (BCrypt).

---

## Repository structure

```
IT342_G6_CARIN_Lab1/
├── backend/     # Spring Boot (Java 17+, MySQL)
├── web/         # React + Vite
├── mobile/      # Android app (Kotlin)
├── docs/        # FRS PDF and other docs
├── README.md
└── TASK_CHECKLIST.md
```

---

## Backend (Spring Boot)

- **Port:** 8080  
- **Database:** MySQL – create DB `it342_carin_lab1` (or use XAMPP; DB created automatically with `createDatabaseIfNotExist=true`).  
- **Endpoints:**  
  - `POST /api/auth/register` – register  
  - `POST /api/auth/login` – login (returns JWT)  
  - `POST /api/auth/logout` – logout (client discards token)  
  - `GET /api/user/me` – current user (requires `Authorization: Bearer <token>`)

**Run (Command Prompt):**

1. Start MySQL (e.g. XAMPP).
2. From repo root:  
   `cd backend`  
   `run-backend.cmd`  
   (or set `JAVA_HOME` to JDK 17+ and run `.\mvnw.cmd spring-boot:run`)

---

## Web (React + Vite)

- **Port:** 5173 (or next available).  
- **Run:**  
  `cd web`  
  `npm install`  
  `npm run dev`  
- **Usage:** Register, Login, Dashboard (protected), Logout; all use the backend on port 8080 (via Vite proxy).

---

## Mobile (Android Kotlin)

- **App:** Register, Login, Dashboard (Profile), Logout; uses same backend API.
- **Backend URL:** In `mobile/app/src/main/java/com/it342/auth/ApiConstants.kt`:  
  - Emulator: `http://10.0.2.2:8080/` (host machine).  
  - Real device: set to your PC IP (e.g. `http://192.168.1.100:8080/`) and ensure backend is reachable.

**Run:**

1. Open `mobile/` in **Android Studio**.
2. Sync Gradle, then Run on emulator or device (backend and MySQL must be running).

---

## Docs

- Put the **final FRS PDF** (with Web + Mobile screenshots and any diagram updates) in `/docs`.
- Keep **TASK_CHECKLIST.md** updated (DONE + commit hashes) before submission.

---

## Submission (MS Teams)

- Same GitHub repository link.
- Repo must contain: working backend, web, and mobile; final FRS in `/docs`; final **TASK_CHECKLIST.md**.
