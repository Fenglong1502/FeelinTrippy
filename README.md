# Feelin Trippy 🌐

Feelin Trippy is an award-winning project, designed as part of the curriculum for IS3106 at the National University of Singapore, achieving 1st Runner Up in the 13th SoC Term Project Showcase (STePS) for IS3106. It provides a user-friendly interface to facilitate users in planning their next vacation trip, offering recommendations based on user preferences, a reward and payment system for bookings, and a shareable trip details feature.

## 🏆 Achievements
- 1st Runner Up for IS3106 at NUS 13th SoC Term Project Showcase (STePS)

## 🛠️ Technologies
- Java
- Managed Beans
- JSF (JavaServer Faces)
- JDBC Connector
- GlassFish Server
- ORM (Object-Relational Mapping)

## 📌 Features
- **Trip Recommendations:** Generates recommended places based on user-selected options, such as price range.
- **Reward System:** Integrates a reward system for user bookings.
- **Payment System:** Facilitates trip bookings through an integrated payment system.
- **Shareable Trips:** Allows users to share trip details with peers and family.
- **SMS Alerts:** Provides an SMS service using Twilio to notify customers upon sign-up or when sharing trip details.
- **Email Alerts:** Integrates an SMTP email service to alert customers on different activities like sign-up and trip sharing.

## 🌐 Modules
- **FeelinTrippy-ejb:** This module serves as the JavaBean backend, handling the logic and computations of the application.
- **FeelinTrippy-war:** This module is responsible for the frontend, managing the user interface and interactions.


## 🔧 Setup and Configuration
### Recommended IDE:
- NetBeans 8.2

### Steps:
1. Import the project into NetBeans 8.2.
2. Navigate to `FeelinTrippy-ejb`.
3. Right-click on `Libraries`, then select `Properties`.
4. Navigate to `Libraries`, and click `Add JAR/Folder`.
5. Add the respective files found in the `jar_files` folder into the libraries.

### Database Connection:
The project utilizes JDBC and GlassFish to connect to the database using ORM. Below are the configuration details:
```xml
<jdbc-resource enabled="true" jndi-name="jdbc/feelinTrippyDB" object-type="user" pool-name="feelinTrippyConnectionPool">
...
</jdbc-connection-pool>
```
## ⚠️ Disclaimer
This project, developed using technologies and practices relevant during its inception, is no longer actively maintained. It is advisable to review and possibly update the codebase to align it with contemporary development standards and practices before use.

## 📄 License
This project is licensed under the MIT License

## 📬 Contact
For further inquiries or clarifications regarding this project, please contact [zell_dev@hotmail.com](mailto:zell_dev@hotmail.com).
