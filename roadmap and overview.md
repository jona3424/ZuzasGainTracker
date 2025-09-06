
---

## **Fitness Training App: Vision, Functionality, Architecture & Roadmap**

---

### **1. App Overview & Vision**

**The "Apex Trainer" (or similar name) app aims to be a comprehensive and intelligent companion for individuals committed to strength and resistance training.** Its primary goal is to **streamline the training process**, **provide clear daily guidance**, and **offer insightful progress tracking** to help users achieve their fitness goals more effectively and stay motivated.

In today's fitness landscape, many struggle with unstructured workouts, inconsistent progress tracking, or the complexity of managing paper-based or disjointed digital training logs. This app solves these problems by offering a **unified platform** where users can effortlessly manage their training plans, execute workouts with precise guidance, track every set and rep, and visualize their journey towards strength and physique improvements.

**Key Benefits for Users:**
*   **Structured Training:** Always know exactly what exercises, sets, reps, and rest times are prescribed for the day.
*   **Intelligent Progress Tracking:** Visual graphs show weight, reps, and volume trends, highlighting achievements and areas for improvement.
*   **Motivation & Engagement:** Receive encouraging feedback and easily see personal bests.
*   **Convenience:** All training information, history, and plans are accessible in one intuitive mobile application.
*   **Adaptability:** Easily switch between training plans or create custom ones.

---

### **2. Core Functionality: How the App Works**

The app guides users through a seamless training experience, from planning to execution and analysis.

1.  **User Onboarding & Authentication:**
    *   New users can easily register with an email and password.
    *   Existing users can securely log in.
    *   The app maintains a persistent session, remembering the user's login state.

2.  **Training Plan Management:**
    *   **Plan Library:** Users can view all their imported or manually created training plans.
    *   **Active Plan:** One plan is always designated as "active" and prominently displayed. Users can easily switch their active plan.
    *   **Plan Creation/Import:**
        *   **File Upload:** Users can upload existing training plans in common formats like Word documents (`.docx`), PDF files (`.pdf`), or even images (`.jpg`, `.png`). These files are sent to a backend service for intelligent parsing.
        *   **Manual Creation:** For simpler plans or quick entries, users can manually create exercises, sets, reps, and rest times directly within the app.

3.  **Daily Workout Guidance:**
    *   Based on the active training plan, the app intelligently determines and displays the exercises scheduled for the current day.
    *   For each exercise, it shows the target sets, reps, and importantly, the user's **last performance** (weight, reps, sets) to inform their current workout.

4.  **Active Training Session:**
    *   When a user starts a workout, the app presents exercises one by one.
    *   **Set Logging:** For each set, users log the weight lifted and reps completed.
    *   **Smart Rest Timer:** After completing a set, a customizable rest timer starts, either using default durations (60s, 90s, etc.) or specific times prescribed by the training plan. It provides visual and auditory alerts.
    *   **Recommended Reps:** The app displays a recommended rep range (e.g., 6-8 reps) for guidance.
    *   **Progression:** After each exercise, the app smoothly transitions to the next, until the entire workout is completed.
    *   **Motivation:** Encouraging words and subtle animations celebrate achievements and keep users motivated.

5.  **Progress Tracking & Visualization:**
    *   **Graphs:** Dedicated screens display interactive graphs for each exercise, visualizing progress over time for:
        *   **Weight Lifted:** Showing trends in maximum or average weight.
        *   **Total Reps:** Tracking overall volume.
        *   **Workout Volume:** Calculated as (Weight x Reps) over time.
    *   **Timeframe Filters:** Users can filter graphs by various time periods (e.g., last 30 days, 3 months, all time).
    *   **Personal Bests (PBs):** The app automatically tracks and highlights new personal records achieved during workouts.

6.  **Workout History:**
    *   A comprehensive history section lists all past workout sessions, including the date, plan used, duration, and exercises completed.
    *   Users can drill down into any session to review specific set/rep/weight logs.

7.  **Body Metrics Tracking (Optional but Recommended):**
    *   Users can log body weight, body fat percentage, and body measurements.
    *   Graphs visualize trends in these metrics over time.

8.  **User Profile:**
    *   Users can view and update their profile information and manage app settings.

---

### **3. Technical Architecture**

The application follows a robust, multi-layered architecture to ensure scalability, maintainability, and security.

*   **Frontend:** Developed using **Flutter**, enabling a single codebase for both iOS and Android platforms, providing a native-like experience with beautiful UI/UX.
*   **Backend:** Built with **Java Spring Boot**, providing a highly performant and secure RESTful API.
    *   Handles all business logic, data persistence, and authentication.
    *   Orchestrates file parsing services.
*   **Database:** **MySQL** serves as the primary data store, ensuring relational integrity and efficient querying for user data, training plans, workout logs, and progress metrics.
*   **Authentication:** **JWT (JSON Web Tokens)** are used for secure, stateless user authentication between the frontend and backend.
*   **File Parsing Service:**
    *   **Text Extraction:** Utilizes Java libraries like **Apache POI** (for Word), **Apache PDFBox** (for PDF), and **Tesseract OCR via Tess4J** (for images) to extract raw text content from uploaded files.
    *   **Structured Data Extraction:** The extracted text is then processed by the Spring Boot backend. For optimal results, this involves:
        *   **Chunking:** Splitting large text into smaller, manageable pieces to avoid context limits.
        *   **Parsing Logic:** Either **rule-based parsing** (using Java Regular Expressions and custom logic for identifying exercises, sets, reps, rest times) or integration with a **Language Model (LLM)** (e.g., a fine-tuned open-source model or a cloud-based service like Gemini Pro API, for more flexible and intelligent extraction into the defined JSON structure).
        *   **Aggregation:** Combining parsed data from all chunks into a complete training plan object.

---

### **4. Development Roadmap**

The project will be developed in a phased, iterative manner, ensuring a solid foundation before building out complex features.

**Phase 1: Foundation & User Authentication (Backend-First)**
*   **Goal:** Establish core database schema and secure user login/registration.
*   **Deliverables:**
    *   Complete MySQL database schema (users, initial plan/exercise structure).
    *   Spring Boot project setup with essential dependencies (JPA, Security, JWT).
    *   Backend APIs for user registration (`POST /api/auth/register`).
    *   Backend APIs for user login (`POST /api/auth/login`).
    *   JWT token generation and validation for secure API access.
    *   Basic Spring Security configuration.
    *   Flutter frontend: Login and Registration screens, basic navigation after authentication.

**Phase 2: Training Plan Core Management (Backend & Frontend)**
*   **Goal:** Enable users to view, create (manually), and manage their training plans.
*   **Deliverables:**
    *   Refined `training_plans` and `exercises` database tables.
    *   Backend APIs for CRUD (Create, Read, Update, Delete) operations on training plans.
    *   Backend API to set a specific plan as "active" for a user.
    *   Flutter frontend:
        *   Dashboard displaying the active plan.
        *   "My Plans" screen to list all plans.
        *   Plan detail view.
        *   Functionality to set a plan as active.
        *   Basic manual plan creation interface.

**Phase 3: File Upload & Intelligent Parsing Integration (Backend-Heavy)**
*   **Goal:** Implement the complex functionality of uploading various file types and transforming them into structured training plans.
*   **Deliverables:**
    *   Backend API for receiving file uploads (`POST /api/plans/upload`).
    *   Integration of Apache POI, PDFBox, and Tesseract for text extraction.
    *   Backend logic for text chunking and iterative processing of file content.
    *   Implementation of rule-based parsing or integration with a selected LLM for converting extracted text chunks into the defined JSON `TrainingPlan` structure.
    *   Backend service for aggregating parsed chunks into a complete `TrainingPlan` and saving it to the database.
    *   Flutter frontend: "Upload Plan" interface, file picker integration, loading indicators during upload/parsing.

**Phase 4: Daily Workout Execution (Backend & Frontend)**
*   **Goal:** Guide users through their daily workouts with logging and a rest timer.
*   **Deliverables:**
    *   Database tables for `workout_sessions` and `exercise_logs`.
    *   Backend API to determine "Today's Workout" based on the active plan and past sessions.
    *   Backend APIs for starting a workout session (`POST /api/workouts/sessions/start`).
    *   Backend APIs for logging individual exercise sets (`POST /api/workouts/sessions/{sessionId}/log`).
    *   Backend APIs for ending a workout session (`PUT /api/workouts/sessions/{sessionId}/end`).
    *   Backend logic for retrieving "last performance" data for exercises.
    *   Flutter frontend:
        *   "Today's Workout" screen displaying exercises and last performance.
        *   Active training session screen with input fields for sets/reps/weight.
        *   Interactive rest timer (default/plan-specific, manual override).
        *   Recommended rep range display.
        *   Navigation between exercises.
        *   Basic motivational messages/animations.

**Phase 5: Progress Tracking & History (Backend & Frontend)**
*   **Goal:** Provide comprehensive visualization of user progress and historical workout data.
*   **Deliverables:**
    *   Database table for `body_metrics` (optional, if included).
    *   Backend APIs for aggregating `exercise_logs` into progress data points (weight, reps, volume over time) for graphs.
    *   Backend APIs for fetching workout history lists and detailed session views.
    *   Backend APIs for logging and retrieving body metrics.
    *   Flutter frontend:
        *   "Progress" screen with interactive graphs (line charts for weight, reps, volume).
        *   Exercise selection and timeframe filters for graphs.
        *   "History" screen listing past workout sessions.
        *   Detailed workout session view.
        *   "Profile" screen for viewing/updating user info and (optionally) logging/tracking body metrics.

**Phase 6: Refinement, Testing & Deployment**
*   **Goal:** Ensure the app is robust, user-friendly, secure, and ready for launch.
*   **Deliverables:**
    *   Comprehensive unit and integration tests for backend.
    *   Extensive UI/UX testing on Flutter frontend across devices.
    *   Global error handling and consistent user feedback mechanisms (e.g., loading states, error messages).
    *   Security hardening (rate limiting, input sanitization).
    *   Performance optimization for both backend and frontend.
    *   Accessibility improvements.
    *   API documentation (e.g., Swagger/OpenAPI for Spring Boot).
    *   Deployment configurations for backend and frontend (e.g., Docker, cloud platforms, App Store/Play Store).

---

This roadmap provides a clear path for developing your comprehensive Flutter fitness training app, from initial concept to a fully functional and user-ready product.