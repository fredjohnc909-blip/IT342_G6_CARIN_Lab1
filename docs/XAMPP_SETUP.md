# Connect Backend to XAMPP MySQL

## 1. Start MySQL in XAMPP

1. Open **XAMPP Control Panel**.
2. Click **Start** next to **MySQL**.
3. Wait until MySQL shows as running (green).

## 2. (Optional) Set root password

XAMPP default: user **root**, password **empty**.  
If you set a password for `root` in phpMyAdmin or MySQL, set it in the backend:

- Edit `backend/src/main/resources/application.properties`
- Set: `spring.datasource.password=your_password`

## 3. Run the backend

In a terminal, from the project folder:

```cmd
cd c:\Users\L13Y17W12\Documents\IT342_G6_CARIN_Lab1\backend
set JAVA_HOME=C:\Program Files\Java\jdk-19
.\mvnw.cmd spring-boot:run
```

On first run, the app will **create the database** `it342_auth` and the `users` table automatically.

## 4. Verify in phpMyAdmin (optional)

1. In XAMPP, click **Admin** next to MySQL (opens phpMyAdmin).
2. You should see database **it342_auth** with table **users** after the backend has started at least once.

## Run without MySQL (H2 in-memory)

If you want to run without XAMPP/MySQL:

```cmd
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev
```
