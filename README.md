# Plannr
Plannr is an organizational platform developed using Java, in the form of an Android app, that helps university students coordinate their everyday routine, from schoolwork and expenses to their personal life. Users are able to track their events and deadlines to fit their personal needs, by the means of a calendar and a to-do list (displays events, for which the user can check to complete tasks).

<p align="center">
  <img src="https://github.com/CSC207-UofT/plannr/blob/main/photos/plannr_blob_lavender.png" alt="Plannr" width="300">
</p>

## Built with
* [SQLite](https://www.sqlite.org/index.html) - Used to store user data
* [Android](https://developer.android.com/studio) - Used to create GUI

## Screenshots
<p float="left">
  <img src="https://github.com/CSC207-UofT/plannr/blob/main/photos/plannr_screenshot_login.png" alt="Log In" width="235">
  <img src="https://github.com/CSC207-UofT/plannr/blob/main/photos/plannr_screenshot_main.png" alt="Main View" width="235">
  <img src="https://github.com/CSC207-UofT/plannr/blob/main/photos/plannr_screenshot_school.png" alt="School View" width="235">
  <img src="https://github.com/CSC207-UofT/plannr/blob/main/photos/plannr_screenshot_add_event.png" alt="Add Event" width="235">
  <img src="https://github.com/CSC207-UofT/plannr/blob/main/photos/plannr_screenshot_view_event.png" alt="View Event" width="235">
  <img src="https://github.com/CSC207-UofT/plannr/blob/main/photos/plannr_screenshot_expenses.png" alt="Expenses View" width="235">
  <img src="https://github.com/CSC207-UofT/plannr/blob/main/photos/plannr_screenshot_add_expenses.png" alt="Add Expense" width="235">
  <img src="https://github.com/CSC207-UofT/plannr/blob/main/photos/plannr_screenshot_settings.png" alt="Settings View" width="235">
</p>

## How to Run
### System Setup
* Android SDK version 31
* Android Gradle Plugin Version 4.2.2
    * [Screenshot of Android SDK and Build tools configuration](https://imgur.com/a/4nw7WpB)
* Gradle Version 6.7.1
* Android Build Tools version 31.0.0 or 30.0.2 (if 31.0.0 is corrupted)
    * [Screenshot of Gradle and Gradle build tools configuration](https://imgur.com/a/4nw7WpB)
* Android Studio Arctic Fox | 2020.3.1 Patch 3
    * Build #AI-203.7717.56.2031.7784292, built on September 30, 2021
    * Screenshot of Android Studio Version

### Emulator Settings
* Device: Pixel 2 (5.0 1080x1920 xxhdpi)
* Android Version 11.0 x86 (API 30)
* Orientation: Portrait
* [Screenshot of emulator setup](https://imgur.com/a/quhXdCV)

### Launch Instructions
To launch our app, **please open our project in Android Studio, we don't guarantee correct function if launched with
other IDEs**. Make sure that you open the inner `plannr` directory as project, but not the root directory `plannr`
as project ([GIF walk-through](https://imgur.com/a/e682DPB)). Ensure that Android SDK 31 is installed with build tools
31.0.0. If you experience an issue where build tool 31.0.0 is corrupted, change the build tool to version 30.0.2, and you
should be able to launch our app. Additionally, you can also try to fix the corruption by following
this [StackOverflow](https://stackoverflow.com/questions/68387270/android-studio-error-installed-build-tools-revision-31-0-0-is-corrupted)
article.

After the system is set up correctly, please make sure that at the top left-hand side of your screen, right next to
the "Project" pane, you see Android as the selected view option ([example](https://imgur.com/PsrbryV)), if you see something
else, change it by clicking on it to invoke [the dropdown](https://imgur.com/a/WAPXVC8) menu, and select Android. You
can now run the app by clicking on the "Run" button at the top right of the window.

### Verify That You Launched Successfully
If you see [this](https://imgur.com/a/uoIiAwn) as the landing page and [this](https://imgur.com/a/pJIsR33) as the login
page, you have launched successfully ([GIF example](https://imgur.com/a/XUPTtWK))

## Authors
* **Dana Al Shekerchi** - *Full Stack Developer* - [dalshekerchi](https://github.com/dalshekerchi)
* **Bolade Amoussou** - *Backend Developer* - [cdw18](https://github.com/cdw18)
* **Tong (Daniel) Guan** - *Backend Developer* - [OKok-3](https://github.com/OKok-3)
* **Sari Hammad** - *Backend Developer* - [sarihammad](https://github.com/sarihammad)
* **Kathy Lee** - *Frontend Developer* - [hellokathylee](https://github.com/hellokathylee)
* **Evgenia Silajev** - *Frontend Developer* - [EvgeniaSila](https://github.com/EvgeniaSila)
