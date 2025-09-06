Here's a comprehensive vision for your Flutter fitness app, expanded with useful features, followed by separate, concise prompts for another AI model.

---

**Expanded Vision for the Flutter Fitness Training App**

Your vision for a training app that tracks progress, manages plans, and guides users through workouts is excellent. Here's an expanded perspective to make it even more robust and user-friendly:

**Core Functionality (as per your request):**

1.  **User Authentication:** Secure Login and Registration.
2.  **Training Plan Management:**
    *   Ability to upload diverse formats (Word, PDF, Image) for parsing on a backend (with intelligent OCR/NLP capabilities).
    *   Alternatively, provide an in-app plan builder for manual entry of exercises, sets, reps, and rest times for simpler plan creation.
    *   Display all imported/created plans, with the current active plan prominently featured.
    *   Seamless switching between plans.
3.  **Daily Workout Guidance:**
    *   Intelligently determine the current day's workout based on the active plan and past sessions.
    *   Display a clear list of exercises for the day, including target sets, reps, and recommended weight (if available from previous sessions or plan).
4.  **Active Training Session:**
    *   Intuitive interface for logging sets, reps, and weight for each exercise.
    *   **Smart Rest Timer:** Configurable rest times (app defaults: 60s, 90s, 120s, 240s; or plan-specific). Visual countdown with alerts.
    *   **Recommended Rep Ranges:** Based on the plan or default (e.g., 6-8 reps).
    *   Clear flow to move to the next exercise.
    *   Motivational elements: Animations, encouraging messages, celebration screens for milestones.
5.  **Progress Tracking & Visualization:**
    *   Detailed history of all completed exercises, sets, reps, and weights.
    *   Dynamic, interactive graphs for individual exercises or overall progress, showing trends for weight lifted, total reps, and volume over time.
6.  **User Experience (UX):**
    *   Clean, modern, and intuitive user interface.
    *   Clear feedback mechanisms.

**Expanded Features:**

*   **Exercise Library:** A pre-populated database of common exercises with their muscle groups, descriptions, and perhaps animated demonstrations.
*   **Custom Exercise Creation:** Users can add their unique exercises to their personal library.
*   **Workout Templates:** Provide pre-defined workout templates (e.g., Full Body, Upper/Lower Split) to help users get started without needing to upload complex files.
*   **Workout Summary:** After each session, a summary screen showing total time, exercises completed, and personal bests (PBs) achieved.
*   **Personal Bests (PBs):** Automatically track and highlight personal records for each exercise (e.g., heaviest lift for 1 rep, most reps at a certain weight).
*   **Body Metrics Tracking:** (Optional) Allow users to track body weight, body fat percentage, measurements, etc., with corresponding graphs.
*   **Notifications:** Reminders for scheduled workouts.
*   **Data Synchronization:** If a backend is used, ensure data synchronization between devices.
*   **Offline Capability:** Basic functionality (viewing plans, logging workouts) should work offline, with data syncing when connectivity is restored.

---

**Instructions for the AI Model: Flutter Fitness App Development**

You will implement a Flutter fitness training application based on the following prompts. Each prompt covers a distinct set of functionalities. Focus on clean architecture, maintainable code, and a user-friendly interface. Assume a backend API will handle data storage and complex processing (like file parsing), and you will integrate with it using appropriate HTTP client libraries.

---

### **Prompt 1: Project Setup & User Authentication**

**Objective:** Set up the basic Flutter project structure and implement the core user authentication flow (Registration and Login).

**Instructions for the AI Model:**

1.  **Project Initialization:**
    *   Create a new Flutter project named `fitness_training_app`.
    *   Configure basic project settings.
2.  **Login Screen:**
    *   Design a user-friendly login screen with input fields for email/username and password.
    *   Include "Forgot Password" and "Don't have an account? Register" navigation links.
    *   Implement basic input validation (e.g., email format, password length).
    *   Integrate with a placeholder authentication service (e.g., `AuthService.login(email, password)`) which simulates an API call.
    *   On successful login, navigate to the main application dashboard.
3.  **Registration Screen:**
    *   Design a registration screen with input fields for email, password, and confirm password.
    *   Implement input validation, ensuring passwords match.
    *   Include a "Already have an account? Login" navigation link.
    *   Integrate with a placeholder authentication service (e.g., `AuthService.register(email, password)`) which simulates an API call.
    *   On successful registration, navigate to the login screen or directly to the dashboard.
4.  **User Session Management:**
    *   Implement a basic mechanism to persist the user's login state (e.g., using `shared_preferences` or `flutter_secure_storage`).
    *   On app start, check for an existing session and automatically navigate to the dashboard if the user is logged in.
5.  **Error Handling & Feedback:**
    *   Display appropriate error messages for failed login/registration attempts (e.g., invalid credentials, network errors).
    *   Show loading indicators during authentication processes.
6.  **Core Data Models:**
    *   Define a basic `User` model with properties like `id`, `email`, `token`.

---

### **Prompt 2: Dashboard & Training Plan Management**

**Objective:** Create the main application dashboard and screens for managing training plans, including viewing all plans and setting the current active plan.

**Instructions for the AI Model:**

1.  **Dashboard/Home Screen:**
    *   Design the main dashboard screen that users see after logging in.
    *   Prominently display information about the "Current Active Training Plan" (e.g., plan name, brief summary).
    *   Include navigation elements to other main sections: "Today's Workout," "My Plans," "Progress," "History," and "Profile."
    *   Implement a "Start Workout" button if a plan is active.
2.  **Training Plan Data Model:**
    *   Define a `TrainingPlan` model including properties like `id`, `name`, `description`, `isActive`, `exercises` (a list of `ExercisePlan` objects).
    *   Define an `ExercisePlan` model including `id`, `name`, `targetSets`, `targetReps`, `targetWeight` (optional), `recommendedRestSeconds` (optional), `muscleGroup`.
3.  **"My Plans" Screen:**
    *   Create a screen to list all available training plans (both imported and potentially manually created).
    *   Each plan item should clearly show its name and indicate if it's the "Current Active Plan."
    *   Allow users to tap on a plan to view its details or set it as the active plan.
    *   Implement a button to "Add New Plan" (which will lead to the upload/creation screen).
4.  **Plan Detail View:**
    *   When a plan is selected from "My Plans," display a detailed view showing all exercises within that plan, their target sets/reps, and rest times.
    *   Include a prominent "Set as Current Plan" button. If it's already current, display "Current Plan."
5.  **State Management:**
    *   Implement state management (e.g., Provider, Riverpod, BLoC) to manage the `currentActivePlan` throughout the app.
6.  **API Integration (Placeholder):**
    *   Create placeholder service methods (e.g., `PlanService.getAllPlans()`, `PlanService.setActivePlan(planId)`) that simulate fetching and updating plan data from a backend API.

---

### **Prompt 3: Training Plan Upload & Integration**

**Objective:** Implement the functionality for users to upload training plan files (Word, PDF, Image) and integrate with a backend service to parse them into JSON for the app.

**Instructions for the AI Model:**

1.  **"Add New Plan" Screen:**
    *   Design a screen accessible from the "My Plans" section for adding new training plans.
    *   Provide options to "Upload File" or "Create Manually" (focus on Upload File for this prompt).
2.  **File Upload UI:**
    *   Implement UI elements that allow users to select a file from their device.
    *   Support picking `.docx`, `.pdf`, and image formats (`.jpg`, `.png`). Use a package like `file_picker`.
    *   Display the selected file's name.
    *   Include an "Upload" button.
3.  **Backend API Integration for Upload:**
    *   Upon pressing "Upload," send the selected file to a designated backend API endpoint (e.g., `POST /api/plans/upload`).
    *   Use a suitable HTTP client (e.g., `dio` or `http`) to handle multipart form data for file uploads.
    *   Show a loading indicator during the upload and processing.
4.  **Receiving & Displaying Parsed JSON:**
    *   The backend is expected to process the file and return a `TrainingPlan` object (in JSON format).
    *   Upon successful upload and parsing, receive this `TrainingPlan` object.
    *   Present a confirmation screen or directly navigate to the plan's detail view (from Prompt 2) with the newly created plan.
    *   Handle potential errors from the backend (e.g., file too large, parsing failed) and display appropriate messages to the user.
5.  **"Create Manually" (Optional, simplified):**
    *   For future expansion or simpler implementation, provide a basic form where a user can enter a "Plan Name" and then add exercises one by one with their name, sets, reps. This can serve as an alternative to file upload if the backend parsing proves too complex initially.

---

### **Prompt 4: Daily Workout & Exercise Tracking**

**Objective:** Implement the logic for tracking training days, displaying the current day's exercises, and remembering past performance.

**Instructions for the AI Model:**

1.  **"Today's Workout" Screen:**
    *   Create a dedicated screen for displaying the current day's workout.
    *   This screen should be accessible from the dashboard.
    *   Prominently display the current date and the name of the active training plan.
2.  **Workout Day Logic:**
    *   Implement logic to determine which exercises from the `currentActivePlan` are scheduled for the current day. This might involve:
        *   Cycling through the plan's exercises in order.
        *   Tracking the last completed workout session's date to decide the "next" training day.
        *   Assume a simple sequential approach initially (e.g., "Day 1" exercises, then "Day 2", etc., or simply the next set of exercises in the plan).
    *   If no active plan, display a message and prompt the user to select one.
3.  **Displaying Exercises for the Day:**
    *   List all exercises for the current day, clearly showing:
        *   Exercise Name.
        *   Target Sets and Reps (from `ExercisePlan`).
        *   **"Last Performance" Data:** Display the weight, reps, and sets the user performed *last time* they did this specific exercise. This requires looking up historical data.
    *   Each exercise should have an action (e.g., a button or tap) to "Start Exercise" or "Log Workout" which will navigate to the active training session screen.
4.  **Exercise Log Data Model:**
    *   Define an `ExerciseLog` model: `id`, `exerciseId`, `workoutSessionId`, `date`, `setNumber`, `reps`, `weight`.
    *   Define a `WorkoutSession` model: `id`, `planId`, `date`, `startTime`, `endTime`, `exerciseLogs` (list of `ExerciseLog`).
5.  **Retrieving Last Performance:**
    *   Implement a service method (e.g., `WorkoutService.getLastPerformance(exerciseId)`) that fetches the most recent `ExerciseLog` entry for a given `exerciseId` for the currently logged-in user. This will typically involve querying the backend.
6.  **Progress Tracking Indicators:**
    *   Optionally, provide visual cues for exercises completed today or exercises still pending.

---

### **Prompt 5: Active Training Session**

**Objective:** Build the interactive screen for guiding users through an active training session, including logging sets, managing rest times, and providing motivation.

**Instructions for the AI Model:**

1.  **Active Exercise Screen:**
    *   Design the screen for an active training session, showing one exercise at a time.
    *   Prominently display the `ExercisePlan` details: Exercise Name, Target Sets, Target Reps.
    *   Show "Last Performance" data for the current exercise (weight, reps, sets).
2.  **Input Fields for Sets & Reps:**
    *   For each set, provide input fields for users to log:
        *   Weight lifted.
        *   Reps completed.
        *   *Optional:* RPE (Rate of Perceived Exertion).
    *   Allow adding multiple sets dynamically.
3.  **Rest Timer:**
    *   Implement a visual countdown timer for rest periods.
    *   **Recommended Rest Time:**
        *   Default values (e.g., 60s, 90s, 120s, 240s) selectable by the user.
        *   Prioritize `recommendedRestSeconds` from the `ExercisePlan` if available.
    *   **Manual Timer Override:** Allow users to manually start/stop/reset the timer.
    *   Provide an option to "Skip Rest."
    *   Play an audible notification when the rest timer completes.
4.  **Recommended Rep Range:**
    *   Display a recommended rep range based on the `ExercisePlan` (e.g., "Target: 8-12 reps") or a default range (e.g., "6-8 reps").
5.  **Navigation & Workflow:**
    *   "Complete Set" button: Logs the set data and starts the rest timer.
    *   "Next Exercise" button: Becomes active after all sets for the current exercise are logged and rest is over (or skipped). Navigates to the next exercise in "Today's Workout."
    *   "End Workout" button: Available at any time to finish the session, navigating to a workout summary.
6.  **Motivation & Animations:**
    *   Integrate subtle animations (e.g., progress bar filling, checkmark animation on set completion).
    *   Display short, encouraging motivational words after completing an exercise or achieving a personal best (e.g., "Great work!", "Keep pushing!", "New PB!").
7.  **Data Persistence:**
    *   Temporarily store `ExerciseLog` data locally during the session.
    *   Upon "End Workout," send all session data (`WorkoutSession` and all `ExerciseLog`s) to the backend API (e.g., `POST /api/workout_sessions`).
8.  **Personal Best (PB) Tracking:**
    *   When an exercise is completed, compare the logged weight/reps against historical data for that exercise. If a new PB is achieved, highlight it prominently with a celebratory animation.

---

### **Prompt 6: Progress Tracking & Visualization (Graphs)**

**Objective:** Implement the screen for visualizing user progress through interactive graphs.

**Instructions for the AI Model:**

1.  **"Progress" Screen:**
    *   Create a dedicated "Progress" screen accessible from the dashboard.
    *   This screen will serve as a central hub for all performance visualizations.
2.  **Exercise Selection:**
    *   Include a dropdown or search bar to allow users to select a specific exercise from their `ExercisePlan` or `ExerciseLog` history.
    *   Display a default overview graph if no specific exercise is selected.
3.  **Graph Types:**
    *   For the selected exercise, display interactive line graphs showing:
        *   **Weight Progress:** Heaviest weight lifted over time.
        *   **Reps Progress:** Average reps per set or total reps per session over time.
        *   **Volume Progress:** Total weight * reps (volume) over time.
    *   Use a charting library like `fl_chart` or `syncfusion_flutter_charts`.
4.  **Timeframe Filtering:**
    *   Allow users to filter the graph data by different timeframes (e.g., "Last 30 days," "Last 3 months," "Last 6 months," "All Time").
5.  **Data Retrieval for Graphs:**
    *   Implement service methods (e.g., `WorkoutService.getExerciseProgress(exerciseId, timeframe)`) to fetch aggregated `ExerciseLog` data from the backend specifically for charting. This data should be structured for easy consumption by the charting library.
6.  **Interactive Elements:**
    *   Enable touch interaction on graphs to show specific data points (e.g., date, weight, reps on a particular day).
7.  **Overall Progress (Optional):**
    *   Consider a simple "Overall Progress" graph on the main Progress screen, showing total workouts completed, or total volume across all exercises over a period, to give a high-level view.

---

### **Prompt 7: Exercise History & User Profile**

**Objective:** Implement screens to view past workout sessions and manage user profile information.

**Instructions for the AI Model:**

1.  **"History" Screen:**
    *   Create a dedicated "History" screen accessible from the dashboard.
    *   Display a chronological list of all `WorkoutSession`s completed by the user.
    *   Each list item should show: `date`, `planName`, `duration`, `totalExercisesCompleted`.
2.  **Workout Session Detail:**
    *   When a `WorkoutSession` is tapped, navigate to a detailed view.
    *   This detail view should list all `ExerciseLog`s for that session, showing: `exerciseName`, `setNumber`, `reps`, `weight`.
    *   Highlight any personal bests achieved during that specific session.
3.  **Filtering & Sorting History:**
    *   Implement options to filter history by `TrainingPlan` or sort by date (newest/oldest).
4.  **"Profile" Screen:**
    *   Create a "Profile" screen accessible from the dashboard.
    *   Display user information (e.g., email, username).
    *   Allow users to edit profile details (e.g., change password, update username - requires backend API integration).
    *   Include a "Logout" button.
5.  **Body Metrics (Optional but Recommended):**
    *   Provide input fields and a history for tracking body weight, height, and optionally other metrics (e.g., body fat percentage, bicep circumference).
    *   Integrate a simple line graph for body weight tracking over time on the profile screen.
6.  **Settings (Optional):**
    *   Include basic app settings like "Dark Mode toggle," "Notification preferences," "Default Rest Times."
7.  **API Integration (Placeholder):**
    *   Create placeholder service methods (e.g., `WorkoutService.getWorkoutHistory()`, `UserService.getUserProfile()`, `UserService.updateUserProfile()`) for fetching and updating user and history data.

---

### **Prompt 8: Cross-Cutting Concerns & Polish**

**Objective:** Address overall app architecture, state management consistency, API integration strategy, local data persistence, and UI/UX polish.

**Instructions for the AI Model:**

1.  **State Management Strategy:**
    *   Consistently apply a chosen state management solution (e.g., Provider, Riverpod, BLoC, GetX) across the entire application for all data flows. Document the chosen strategy.
2.  **API Integration Strategy:**
    *   Define a clear pattern for interacting with the backend API.
    *   Use a robust HTTP client (e.g., `dio`) with interceptors for error handling, authentication token refreshing, and logging.
    *   Implement a `Repository` pattern to abstract data sources (API, local database).
3.  **Local Data Persistence:**
    *   For caching and potential offline capabilities, implement a local database solution (e.g., `Hive` for simple key-value/object storage or `sqflite` for relational data).
    *   Cache `TrainingPlan` data and recent `WorkoutSession`s locally to improve performance and allow limited offline access.
4.  **Error Handling & User Feedback:**
    *   Implement a consistent strategy for displaying error messages (e.g., `SnackBar`, dialogs).
    *   Provide loading indicators for all asynchronous operations.
    *   Handle network connectivity changes gracefully.
5.  **UI/UX Best Practices:**
    *   Ensure a consistent visual theme (colors, typography, spacing) throughout the app.
    *   Implement smooth navigation transitions.
    *   Optimize for different screen sizes and orientations.
    *   Utilize Flutter's widget catalog effectively for a responsive and aesthetically pleasing design.
6.  **Code Structure:**
    *   Organize code into logical directories (e.g., `lib/models`, `lib/services`, `lib/repositories`, `lib/screens`, `lib/widgets`).
    *   Write clean, well-commented, and maintainable code.
7.  **Accessibility (Basic):**
    *   Ensure basic accessibility considerations (e.g., sufficient contrast, semantic labels for interactive elements).