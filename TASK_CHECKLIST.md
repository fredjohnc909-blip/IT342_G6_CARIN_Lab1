# IT342 Lab – Task Checklist

**Repository:** IT342_G6_&lt;Lastname&gt;_Lab1  
**Team/Author:** G6 CARIN

---

## Lab 2 + Lab 3 (Final) Tasks

| # | Task | Status | Commit Hash (when DONE) |
|---|------|--------|-------------------------|
| 1 | Backend: Spring Boot auth API (register, login, JWT) | DONE | *(add after commit)* |
| 2 | Backend: MySQL/XAMPP integration (it342_carin_lab1) | DONE | *(add after commit)* |
| 3 | Backend: Logout endpoint `/api/auth/logout` | DONE | *(add after commit)* |
| 4 | Backend: Validation and consistent API error responses | DONE | *(add after commit)* |
| 5 | Web: Register screen | DONE | *(add after commit)* |
| 6 | Web: Login screen | DONE | *(add after commit)* |
| 7 | Web: Dashboard/Profile (protected) | DONE | *(add after commit)* |
| 8 | Web: Logout functionality | DONE | *(add after commit)* |
| 9 | Web: Connection to Spring Boot backend | DONE | *(add after commit)* |
| 10 | Mobile (Android Kotlin): Register screen | DONE | *(add after commit)* |
| 11 | Mobile (Android Kotlin): Login screen | DONE | *(add after commit)* |
| 12 | Mobile (Android Kotlin): Dashboard/Profile (protected) | DONE | *(add after commit)* |
| 13 | Mobile (Android Kotlin): Logout functionality | DONE | *(add after commit)* |
| 14 | Mobile (Android Kotlin): Connection to same Spring Boot backend | DONE | *(add after commit)* |
| 15 | Docs: FRS PDF with Web + Mobile screenshots | PENDING | — |
| 16 | Repository structure: /web, /mobile, /backend, /docs, README, TASK_CHECKLIST | DONE | *(add after commit)* |

---

## Notes

- **Passwords:** Stored hashed (BCrypt) on backend; never sent or stored in plain text.
- **Protected pages:** Dashboard/Profile is only accessible when logged in (Web: AuthContext; Mobile: token check + redirect to Login).
- **JWT logout:** Backend exposes `POST /api/auth/logout` for client consistency; actual logout is client-side (discard token).

---

*Update "PENDING" to "DONE" and fill in commit hashes after each relevant git commit.*
