# IT342 – User Registration and Authentication (Lab1)

Repository: **IT342_G6_CARIN_Lab1**  
Session 1: Backend + Web Application (no mobile in this session).

---

## Structure

```
IT342_G6_CARIN_Lab1/
├── web/          # React (Vite) – Register, Login, Dashboard, Logout
├── backend/      # Spring Boot – REST API, MySQL, BCrypt, JWT
├── mobile/       # (Empty for now – next session)
├── docs/         # FRS PDF and documentation
├── README.md
└── TASK_CHECKLIST.md
```

---

## Prerequisites

- **Java 17**, **Maven**
- **Node.js 18+**, **npm**
- **MySQL** (local or remote; default: `localhost:3306`, user `root`, no password)

---

## Backend (Spring Boot)

1. **Java 17+:** Set `JAVA_HOME` to your JDK (e.g. `C:\Program Files\Java\jdk-19`).
2. **Default run (no MySQL):** The app uses an in-memory H2 database by default so it runs without MySQL.
3. Run:
   ```bash
   cd backend
   .\mvnw.cmd spring-boot:run
   ```
   On Windows without Maven installed, use `.\mvnw.cmd` (Maven wrapper).  
   API base: **http://localhost:8080**

To use **MySQL** instead: in `application.properties` remove or comment out `spring.profiles.active=dev`, then create DB `it342_auth` and set `spring.datasource.password` if needed.

### API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register (body: `username`, `email`, `password`) |
| POST | `/api/auth/login` | Login (body: `email`, `password`) |
| GET | `/api/user/me` | Current user (protected; header: `Authorization: Bearer <token>`) |

Passwords are hashed with **BCrypt**.

---

## Web (React)

1. Install and run:
   ```bash
   cd web
   npm install
   npm run dev
   ```
2. Open **http://localhost:5173**
3. Use **Register** → **Login** → **Dashboard** → **Logout**.

---

## Documentation

- Put the updated **FRS PDF** (with ERD, UML, and Web UI screenshots: Register, Login, Dashboard, Logout) in the **`/docs`** folder.
- See **`docs/FRS_INSTRUCTIONS.md`** for what to include.

---

## Submission (Session 1)

- Public GitHub repository link (this repo).
- Working backend and web app.
- Updated FRS PDF in `/docs` (Web screenshots only for this session).
- Updated **TASK_CHECKLIST.md** (with commit hashes for DONE tasks).
