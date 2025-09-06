# Zuza's GainTracker: Your Personal Fitness Training Companion

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

## Project Description

**Zuza's GainTracker** is a comprehensive mobile application, meticulously crafted with Flutter, designed to empower you in your strength and resistance training journey. It provides a unified platform for intelligent training plan management, guided workout execution, detailed progress tracking, and insightful data visualization to help you achieve your fitness goals efficiently and stay motivated. It's built by Zuza, a fellow fitness enthusiast, with a deep understanding of what makes a tracking app truly useful.

## Vision & Goal

As someone passionate about fitness (that's me, Zuza!), I understand the challenges of staying consistent and effectively tracking progress. Many struggle with fragmented workout logging, manual plan adjustments, and a lack of clear insights into their gains. Zuza's GainTracker aims to solve these challenges by offering a seamless and intuitive experience.

My goal is to transform how you plan, execute, and analyze your workouts, making fitness tracking effortless and truly data-driven. I envision an app that adapts to your unique journey, ensuring every rep, set, and lift contributes meaningfully towards your personal best. This isn't just another tracker; it's a companion designed to help you, personally, unlock your full potential.

## Key Features

*   **Secure User Authentication:** Effortless registration and login with persistent session management.
*   **Flexible Training Plan Management:**
    *   Upload training plans from `.docx`, `.pdf`, or image files, intelligently parsed by the backend into a structured format.
    *   Manually create custom workout plans directly within the app.
    *   View all plans and easily switch between an "active" training plan.
*   **Intelligent Daily Workout Guidance:**
    *   Automatically determine and display the day's scheduled exercises based on the active plan.
    *   Show "last performance" data (weight, reps, sets) for each exercise to inform current efforts.
*   **Interactive Active Training Session:**
    *   Intuitive interface for logging sets, reps, and weight for each exercise.
    *   Smart Rest Timer with configurable or plan-specific durations and audible alerts.
    *   Recommended rep range display for guidance.
    *   Seamless navigation between exercises and session completion.
    *   Motivational messages and animations to celebrate achievements.
*   **Detailed Progress Tracking & Visualization:**
    *   Dynamic, interactive graphs for individual exercises, showing trends in weight lifted, total reps, and overall volume over time.
    *   Filters to view progress across different timeframes (e.g., 30 days, 3 months, all time).
    *   Automatic tracking and highlighting of Personal Bests (PBs).
*   **Comprehensive Workout History:**
    *   Chronological list of all past workout sessions with drill-down details.
    *   Filtering and sorting options for history.
*   **Body Metrics Tracking (Optional):**
    *   Log and visualize trends for body weight, body fat percentage, and measurements.
*   **User Profile Management:**
    *   View and update personal information and app settings.

## Technical Architecture

Zuza's GainTracker is built on a robust, multi-layered architecture:

*   **Frontend (Mobile App):**
    *   **Flutter:** A single codebase for native iOS and Android experience, ensuring a beautiful and performant user interface.
*   **Backend (API & Logic):**
    *   **Java Spring Boot:** A highly scalable and secure framework for all business logic, data persistence, and API endpoints.
    *   **Security:** Leverages **JWT (JSON Web Tokens)** for stateless and secure authentication.
*   **Database:**
    *   **MySQL:** A reliable relational database for storing all application data, including user profiles, training plans, workout sessions, exercise logs, and progress metrics.
*   **Intelligent File Parsing Service:**
    *   Integrated into the Spring Boot backend, this service intelligently extracts and transforms training plans from uploaded files (`.docx`, `.pdf`, image files) into a structured JSON format.
    *   Utilizes libraries like **Apache POI**, **Apache PDFBox**, and **Tesseract OCR (via Tess4j)** for text extraction.
    *   Employs **text chunking** and either **rule-based parsing** or **Language Model (LLM) integration** to accurately extract exercise details, even from large and complex documents.

## Development Roadmap Highlights

The project will follow an iterative, phase-based development approach:

1.  **Phase 1: Foundation & User Authentication:** Setup core database, Spring Boot backend, JWT security, and Flutter login/registration.
2.  **Phase 2: Training Plan Core Management:** Implement CRUD operations for plans, manual plan creation, and plan activation.
3.  **Phase 3: File Upload & Intelligent Parsing:** Develop file upload API, text extraction, chunking, and AI/rule-based parsing to generate structured JSON plans.
4.  **Phase 4: Daily Workout Execution:** Implement workout day logic, active training session screen, exercise logging, and rest timer.
5.  **Phase 5: Progress Tracking & History:** Develop APIs for aggregating progress data, history views, and body metric tracking.
6.  **Phase 6: Refinement, Testing & Deployment:** Focus on robustness, performance, comprehensive testing, and preparing for launch.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

*   Java Development Kit (JDK) 17+
*   Maven 3.x
*   MySQL 8.x
*   Flutter SDK (Stable Channel)
*   Android Studio / VS Code with Flutter and Dart plugins
*   (For OCR) Tesseract OCR installed on your system or server.

### Installation

**1. Clone the repository:**
```bash
git clone https://github.com/jona3424/ZuzasGainTracker.git
cd ZuzasGainTracker
