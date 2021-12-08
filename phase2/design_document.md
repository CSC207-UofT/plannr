# Design Document

## Table of Contents
- [Specification](#specification)
- [CRC Cards](#crc-cards)
- [SOLID Principles](#solid-principles)
- [Clean Architecture](#clean-architecture)
- [Design Patterns](#design-patterns)
- [Use of GitHub Features](#use-of-github-features)
- [Code Style and Documentation](#code-style-and-documentation)
- [Testing](#testing)
- [Refactoring and Code Smells](#refactoring-and-code-smells)
- [Code Organization](#code-organization)
- [Design Document](#design-document)
- [Accessibility Report](#accessibility-report)
- [Progress Report](#progress-report)

***

## Specification
Plannr is an organizational platform in the form of an Android app that helps university students coordinate their everyday routine, including schoolwork and expenses. Users are first taken to the login page and prompted to log in by entering their email and password. If they do not have an account yet, they can click the sign-up button. They sign up by entering their full name, email, and password. After signing up or logging in, users are taken to the main view, which is a to-do list that allows users to see all school events that they created. The events within the to-do list can be sorted either by time or priority. Users can also create events by means of a calendar that offers a monthly view and an “add event” feature. A user can also view school-specific events in the form of a list underneath the calendar. Our platform currently focuses on two main categories:

* **Schoolwork:** As a user, I can add (use case) four school-related events (entity), including assessments, due dates, class times, and study sessions. Once I inputted the school event, I can view it in the list of school events underneath the calendar. To see my event details, I can go to the to-do list and click the school event to see more features, such as course or location, depending on the type of event.
* **Expenses:**  As a user, I can view my all-time expenses (entity) in the form of a list showing the name of the expense and the amount of money spent. By going to the add expenses page and inputting the name of the expense and the number of dollars, I can add (use case) an expense to the expense list. I can delete an expense from the expenses list page. I can also input my income, and the total displayed at the bottom is a difference between my income and total expenses. The text will be green if the total is less than my income, red if it is greater than my income and yellow if it is the same amount.
  Upon app launch, users see the today view and can click between different views such as schoolwork, expenses, and settings using a side menu that pops out when clicked. The settings page allows users to change their name and password at any time.

Upon app launch, users see the today view and can click between different views such as schoolwork, expenses, and settings using a side menu that pops out when clicked. The settings page allows users to change their name and password at any time

### Changes from Phase 0 Specification
Plannr, previously titled Uni Life Tracker, started out as a command-line interface with the goal of the Android app, allowing users to track their events and deadlines to fit their personal needs. It was meant to focus on three categories: **Schoolwork, Life and Expenses**. While in phase 2, Plannr only focuses on school, work and expenses. In addition, the to-do list does not have a checkbox feature, so users cannot check off tasks, but they can still view and sort them. Other additions include a sign-up, login and income feature, settings page, and a dark mode option.

## CRC Cards
Please see the linked for the updated [CRC Cards](https://docs.google.com/document/d/1wAnKPMUv0o_FJ9qT4U98Bf2eMEP8u2Y1_nC9lcLH76U/edit).

## SOLID Principles
**NOTE:** Some of this is implemented or some is to be implemented by Wednesday's submission.

### SRP: Single Responsibility Principle
* <u>Comply:</u>
    * In order to comply with the single responsibility principle, we made sure that each class was responsible for one thing.
    * Looking at the use cases, we have the `EventManager`, `UserManager`, and `ExpenseManager` which are each only responsible for their respective entity. `EventManager` is only responsible for maintaining anything `Event` related, `UserManager` is only responsible for maintaining anything `User` related, and `ExpenseManager` is only responsible for maintaining anything Expense related. This avoids
    * Similarly, for the gateways, we split the gateways for the three entities so that `EventGateway` is only
      responsible for saving and reading Events from the database, `ExpenseGateway` is responsible for saving and
      reading `Expenses` from the database and `UserGateway` is responsible for saving and reading Users from the
      database.
    * Now for the controllers and presenters and activities. Each view in android has its own android activity which
      also has its own controller and presenter. For example, we have the `MainActivity` which is the activity related
      to the main view of our app. The `MainActivity` has its own controller for the user's inputs such as wanting
      their events of the day to be sorted either by time or priority and its own presenter for formatting the outputs
      of the use cases in order to display in Android the sorted list of events.
* <u>Violations:</u>
    * A possible violation of SRP is that each entity's manager, that is `EventManager`, `ExpenseManager`
      and `UserManager`, could be thought to have too many responsibilities in the sense that for example, `UserManager`
      is responsible for anything `User` related but within that responsibility, there are other sub-responsibilities
      that could be their own class such as creating a user, editing any user information like name, finding that user
      by either email or ID, etc. These could be separate classes, but it depends on how we divide them up as it can lead
      to complying to SRP at an extreme. So this situation could be considered a violation, or it could not, depending on
      the person.

### OCP: Open-Closed Principle
* <u>Comply:</u>
  We believe that our program follows the Open-Closed Principle. For example, we have different types of events in our
  program, each with both unique and common properties. Our implementation of `Event` and its subclasses complies with
  OCP because common properties of an event is inherited from extending the superclass, any unique properties were added
  in the subclasses without the need to change the superclass. This makes the `Event` entity open and closed.

### LSP: Liskov Substitution Principle
* <u>Comply:</u>
    * In order to comply with the LSP, we made sure that the Event superclass could be interchangeable by its
      subclasses `Deadline`, `Assessment`, `StudySession` and `Class.` For example, a method involving getting
      information on an Event is usable by all 4 subclasses and these subclasses cna be interchangeable for that method
      without causing any problems. The basic behavior of an `Event` can be used and be applied to any of the four
      subclasses. In addition, the use of interfaces* for the gateways is also a good example of LSP being used in our
      program.
    * There is the `EventGatewayInterface`, the `ExpenseGatewayInterface`, and the `UserGatewayInterface` which are to
      be implemented by their respective gateway classes.

### ISP: Interface Segregation Principle
* <u>Comply:</u>
    * In order to comply with ISP, we implemented several interfaces in our program. Instead of having one interface for
      gateway classes, we implemented three different interfaces for each entity that we have: `Event`, `Expense`
      and `User`. This allows our use cases to use gateway classes related to their entity instead of one general one
      and to implement methods related to their entity database table. In addition, we also have interfaces for the
      input boundary between the use cases and the interface layer. Having different input boundary interfaces for each
      entity allows the use cases `EventManager`, `UserManager` and `ExpenseManager` to implement methods relevant to
      their responsibilities instead of having to implement all the methods which they might not need.

### DIP: Dependency Inversion Principle
* <u>Comply:</u>
    * Our program mostly complies with DIP. Using the OODesign example given in week 3 lecture, `Worker` is the `Event`
      and `SuperWorker` is the subclasses of `Event`. When we need to make a reference to a type of event, we can
      simply use `Event e` as a parameter, instead of explicitly specifying which type of event we want (i.e. instead
      of `Deadline e` )
* <u>Violation:</u>
    * It may be a violation as our `Event` superclass is not an interface, so it doesn't utilize abstraction. However, it
      does achieve the same goal as DIP.
* <u>Potential Fix:</u>
    * We can fix this by implementing an `Event` interface, and change the existing Event superclass to `GeneralEvent`
    
## Clean Architecture

### Clean Architecture Discussion
![alt text](https://github.com/CSC207-UofT/course-project-generic-name-1/blob/design_doc/phase2/images/uml_diagram.png "UML Diagram")
Our program is consistent with Clean Architecture because as we can see with the general UML diagram given above, we
made sure that the Entities were unaware of the Use Cases, the Use Cases are unaware of the
Controllers/Presenters/Gateways and the Controllers/Presenters are unaware of the Activities. If we wanted to violate
Clean Architecture, such as a Use Case class like EventManager saving an entity to a database, we used interfaces instead
of directly calling the class implementation. For example, EventManager creates an event and wants to save it to the
database, it would be a violation of clean architecture for EventManager to directly call EventGateway so instead, we
made an interface called EventGatewayInterface which is what the EventManager uses to save to the database and pass a
EventGateway object to it through the controller. That way EventManager remains unaware of the outer layer such as the
Controller and the Gateway. The flow goes from the activities to the controller to the use case, through the boundary
interfaces, to the entities back to the use cases, then presenter, then the activity and finally the UI.

### Scenario Walk-Through that shows Clean Architecture

<u>Scenario:</u> The user arrives at the main page which displays their list of events taking place that day and have
the option of sorting that displayed list by date-time or by priority. By default, it is first shown to be sorted by
date.

In this scenario, we start at the UI where the user can see their list of events sorted by date or by priority. To
start, the MainActivity class tells the MainController, the controller for the main view, that the user wants their list
of today's events to be displayed and sorted by date. So the controller then uses the EventInputBoundary interface to
tell the EventManager use class to retrieve today's events and sort them by date. Afterwards, EventManager sends that
information over to the MainPresenter, the presenter class that formats the list of events to be presentable for the UI
for the main view, through the MainPresenter output boundary interface, and the MainPresenter then formats the list of
event objects into something Android can print and display which is then sent to MainActivity who just displays the
result.

### Dependency Rule (w/ an example)

As once stated above, each layer of Clean Architecture in our program is unaware of the outer layers. That is, the
entities do not depend on nor are aware of the uses cases, who are not aware of the controllers, presenters and gateways,
who are not aware of the UI and SQLite database.

For example, the use case class EventManager saves events to the database using not EventGateway, a gateway class, but
instead EventGatewayInterface, which is an interface implemented by EventGateway. This prevents a violation of clean
architecture from happening since EventManager remains unaware and independent of any gateway classes. Therefore, the
dependencies still point inwards when looking at the clean architecture circle.

## Design Patterns

We received several suggestions with implement design patterns in our project. After considering all our options,
however, we felt that some may not be appropriate or adds unnecessary complexity to our program. We will address those
suggestions here and provide some supporting arguments.

### Strategy
The Strategy pattern is useful for the user information validation. Upon attempting to sign up or log in, we
  implemented a few conditionals to ensure a successful sign-up and log in. For sign up, we implemented a number of
  regex patterns that check for valid email and password inputs as well as a conditionals that check whether an email is
  already registered in the database upon signing up. For log in, we implemented conditionals that check whether log in
  inputs are valid by checking whether they match with one of the signed up users currently in the database. All in all,
  utilizing this design pattern should allow the sign-up/log in process to run smoothly.

### Simple Factory
We can use this in our controllers to check for errors, or use it to create default instantiations of events, see #4
in https://refactoring.guru/design-patterns/factory-comparison

### Factory/Builder
* For creating events (not currently implemented, but may in the future)
    * We think that neither builder nor factory would be appropriate, at least without adding unnecessary complexity to
      our program.
    * For builder, event is not a complex object that includes multiple objects, so implementing it would not be
      appropriate and could make the program unnecessarily complex
    * For factory, it is possible to implement but will add unnecessary complexity to our program. We would need an
      interface as a framework and multiple creators to create different types of events, which is rather unnecessary
      since each subclass of Event is explicitly instantiated the moment the client needs it, deferring instantiation
      would mean that we need to create a default instantiation of an Event (or its subclass) and use getter and setter
      methods to change its attributes -- quite extra and could make it too labour-intensive to use.
* In sign up user flow (maybe implemented)
    * Builder could be accomplished if we store a reference to the user's database inside the User class. This way,
      during sign up flow, we can let a builder to first create a database for the user and then create a `User` object
      by calling UserManager and then combine them into one by passing in the database

### Singleton
This may be appropriate to use for `UserManager` , `EventManager` , and `ExpensesManager` since we only need one
instance of each for our program. This will be implemented near the end because it requires too many breaking changes.

### Façade
This may be appropriate for `UserGateway`, `EventGateway`, and `ExpenseGateway` since each of these gateways has an
instance of `DatabaseClient` and utilizes the `getWritableDatabase()` method from the database client in order to open
the database for storing/retrieving relevant data, i.e., the implementation is moved from  `DatabaseClient` to the
gateway classes.

## Use of GitHub Features

### Issues
For phase 2, we made good use of the issue feature and had a total of 5 issues that were labeled as bugs and linked to
specific pull requests. We made sure to identify the most important bugs or problems and label them as issue in order to
bring attention to them. After being solved they were closed.

These issues included:
* [SignUp Page Password Validator Bug](https://github.com/CSC207-UofT/course-project-generic-name-1/issues/72)
* [Missing Keywords and Methods in UserManager and User](https://github.com/CSC207-UofT/course-project-generic-name-1/issues/73)
* [EventDateComparator Returns Wrong Values](https://github.com/CSC207-UofT/course-project-generic-name-1/issues/73)
* [Database Helper Methods Possibly In the Wrong Place](https://github.com/CSC207-UofT/course-project-generic-name-1/issues/52)
* [Sort button](https://github.com/CSC207-UofT/course-project-generic-name-1/issues/59)

### Pull Requests
Throughout phase 2, we consistently used pull requests to merge our branches into main and changed the GitHub settings
to reject the merge of an unreviewed pull request. Pull requests had to be reviewed by at least two other people before
they could be merged in. We have not made any changes to main directly, any and all changes were done on local
branches and after being submitted, reviewed and approved were merged into main.

### Project Board
We moved from using the task board on ClickUp to using the task board on GitHub, and we set up the automatic features
that would move a card into the correct column based on if it was created, reviewed or done. Every pull request or issue
or placed into the board, so we could keep track of everyone's tasks and progress.

## Code Style and Documentation
For phase 2, we made sure to add JavaDocs with our work, describing the classes and class attributes. We followed the
general guidelines to commenting JavaDocs and applied usage of tags. As well, for other file types such as our activity
layout files (XML), we commented headers describing each element. Previously, our project lacked many JavaDocs. While
there were several comments and pieces of documentation in certain files, overall they were not sufficient to express
what the functions and classes do. Additionally, we renamed class and variable names to be more comprehensive. From the
previous phase, a number of our class names were quite vague with their functions, thus we modified these to be more
clear with their use. Additionally, prior to each commit, we also addressed any form of warnings from IntelliJ that
could be resolved. Overall, we leveraged documentation to highlight and explain a more straightforward overview of our
work.

## Testing

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
other IDEs**. Make sure that you open `Uni_Life_Tracker` as project, but not the root directory `course-generic-name-1`
as project ([GIF walk-through](https://imgur.com/a/e682DPB)). Ensure that Android SDK 31 is installed with build tools
31.0.0. If you experience an issue where build tool 31.0.0 is corrupted, change the build tool to version 30.0.2, and you
should be able to launch our app. Additionally, you can also try to fix the corruption by following
this [StackOverflow](https://stackoverflow.com/questions/68387270/android-studio-error-installed-build-tools-revision-31-0-0-is-corrupted)
article or this question on [Piazza](https://piazza.com/class/kt4hlydpsym1bz?cid=10).

After the system is set up correctly, please make sure that at the top left-hand side of your screen, right next to
the "Project" pane, you see Android as the selected view option ([example](https://imgur.com/PsrbryV)), if you see something
else, change it by clicking on it to invoke [the dropdown](https://imgur.com/a/WAPXVC8) menu, and select Android. You
can now run the app by clicking on the "Run" button at the top right of the window.

### Verify That You Launched Successfully
If you see [this](https://imgur.com/a/bySL42L) as the landing page and [this](https://imgur.com/a/ijT3Z0F) as the login
page, you have launched successfully ([GIF example](https://imgur.com/a/HYjDbsf))

### How to Run Tests

Once you have loaded our project into Android Studio, you should be able to see two test packages, all colored green.
One labeled test, the other labeled AndroidTest. To run the tests, simply right-click on the subdirectory, beginning
with
`com.` and choose "Run...". [Here is a quick GIF demonstration](https://imgur.com/a/HYjDbsf)

* The java tests are written using JUnit 4, so the test behaviour would be the expected behaviour of any Java unit tests
* The tests for `Gateways` are written using Android Instrumented test, so the behaviour is a little different. When you
  click run, your emulator will launch, but the app will not actually launch in the emulator. The emulator only provides
  contextual support to the tests, so it is an expected behaviour and is not a bug.

#### Test Coverage
* Entities are not tested, this is because Use Case tests already use the great majority of the methods of the entities
  so specific tests for entities would be redundant
* Use Cases, Gateways are all tested with their respective tests in the program's Test Sources root directory

### Instructions for Use of Features

#### How to Sign Up
1. Click on the `SIGN UP!` button
2. Enter your details and password, password must be at least 6 characters with at least 1 upper chase, 1 number, and 1
   special character
3. Once you sign up, you should be taken to the To-Dos page, which looks something
   like [this](https://imgur.com/a/UEDegGD)

#### How to Log In

1. If you've already signed up, then you can simply use your email and password to login

#### How to Use Expenses
1. Click the expenses button on the sidebar menu
2. Enter an expense by selecting the plus sign on the top right of the expenses list view
3. Enter the name of the expense (a string) and the amount as a decimal (ex. 10.20), do not leave the fields blank
5. Select the check mark to save the expense
6. After being redirected to the expense list view, add an income by typing in the textbook
7. Input the amount as a decimal and click the save income button At the bottom you should see the total number amount
   appear as either red, green or yellow depending on if your total expenses are less than, greater than or equal to
   your income respectfully

#### How to Use School
1. Click the school button on the sidebar menu
2. Enter a school event by selecting the plus sign on the top right of the calendar view
3. Select the type of school event
4. Based on the type of school event, enter the name of the event (a string), the start/end time, the priority and the
   course name
5. Click the check mark at the top of the page to save

#### How to Use Settings
1. In the settings page, you will see two text fields, one representing name and the other representing university
2. To edit, click on the edit button
3. Make changes in the respective text fields
4. To save, click on the tick in the top right corner of the app, and all the changes will be saved

## Refactoring and Code Smells
Continuing from phase 1, we kept classes short with good documentation to avoid code smells. As suggested in our
feedback, we also deleted the old src directory and refactored the directory names. Unfortunately we could not change
the Uni_life_tracker folder name to Plannr, as it caused a bunch of errors that at this stage of the project would do
more harm than good and would take up time for other more valuable improvements

## Code Organization
Our program follows the [Java package naming convention](https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html), in
conjunction with CLEAN architecture layers.

* `com.generic.` is the naming convention of Java package which states that "companies use their reversed Internet
  domain name to begin their package names." As we wanted to make our skills learnt in this project transferable to the
  real world, we decided to follow the `com.` naming convention, followed by `generic` which is our team name
* `plannr` is our app's name
* `java` subdirectories will host all the java classes for our program, this is inherited from the examples given in
  class
* Other subdirectories are named by CLEAN architecture layers
  * e.g. `UseCases` hosts the use cases for our program, `Entities` host the entities of our program.

### Existing Problems
* Packages are not all lowercase as specified by Oracle's java naming convention
* `Database` should be in the `gateways` package (Should be solved after 's PR)
* Activities need to be properly categorized and moved to the respective packages

## Design Document

### Is all the above discussed in an organized way?
We, the group members of Generic name can confirm that all elements of the design document are present in order and
completed to the best of our ability.

### Does your design document convince your team that you deserve the grade you are hoping to earn?
Our design document is detailed and clear while also containing extra features such as instructions on how to set up the
app and how run certain features.

***

## Accessibility Report

### Universal Design Principles

#### Principle 1: Equitable Use
If released on the market, Plannr will be free to include users from different socioeconomic backgrounds. As well, our
app does not have an additional features that require users to have a more advanced skill level, so it does not create
segregation among users. It also offers the same password checking and account features for all users ensuring that
users will have a password-protected account. Finally, Plannr's UI is simple and easy to user, with a nice design
appealing to all users.

#### Principle 2: Flexibility in Use
The Sort feature in the main page gives users the flexibility and choice of how they would like to sort their events, as
users can sort either by time or priority. In the future, an instructions page offering details on how to use the app
can be added to Settings. Additionally, in the future, a more detailed setting page can be added to accommodate more
customizations. Preferences such as currency types (such as CAD or USD), the display of the time (24 hour vs. AM or PM)
or dark mode are features that can be included in the future.

#### Principle 3: Simple and Intuitive Use
At first launch, Plannr prompts the user to sign up or log-in in a straightforward manner, displaying large buttons with
clear text. When signing up, Plannr provides feedback for text fields, such as name, email and password. If they are
left blank or have the incorrect type of input, it suggests change from the user. As well, it also prompts the user to
enter a strong password that follows certain password requirements which are clearly printed after a failed attempt.
Finally, the language and vocabulary within Plannr is simple and comprehensive.

#### Principle 4: Perceptible Information
Plannr makes use of large bold text for important headings that represent different functions within the app, such as
Expenses, To Do's and adding school events. It also has large button with legible text that stands out. Plannr applies
icons for the different types of school events to avoid cluttering the add event view with text. The colour scheme for
the entire UI (black, white and purple) ensures there is high contrast and all features are visible.

#### Principle 5: Tolerance for Error
Plannr has fail-safe features that stop the user from inputting an empty school event or any empty expense with error
messages. Most used elements such as the To Do's list are displayed as the main view while settings and other less use
features have to be located using the navigation menu. In the future, Plannr should contain more detailed error checking
for text inputs such as the user's name.

#### Principle 6: Low Physical Effort
In the future, Plannr will allow users to add multiple expenses and events at once without being redirected to
main view or the main expenses view to minimize repetitive action. As a mobile app, Plannr should not require excessive
physical effort to use and does not have any advance graphic features that would drain battery life.

#### Principle 7: Size and Space for Approach and Use
This principle is not applicable for Plannr as it is a mobile app (software) and does not come with additional hardware
elements that would need to accommodate a variety of users.

### Target Market
The target market for Plannr would be students ranging from high school to university. Plannr offers an option for users
to input school events such as deadlines which are most commonly associated with student life. Students often have
difficulties balancing all their academic dates such as deadlines, tests or assignments so seeing all their events in
the to do list will encourage them to stay on track. In addition, many students are learning how to budget money
effectively for the fist time and being able to track their spending offers great visual support. When a user sees their
list of expenses and the total at the bottom, it will encourage them to be more mindful about their spending. Overall,
The Plannr UI targets a younger population with its playful design and colour scheme. Overall, it would be beneficial
for students who are looking for an organizational platform that combines both their academic and financial life for
easy access.

### Least Likely Demographic
People who are not students would not Plannr particularly useful. Specifically, younger students in elementary school or children would be less likely to user Planner. They would have no use for the expense features they are not managing their own finances. In addition, older adults who are retired or who are not students are less likely to use the school work related features. Finally, middle-aged adults will have no use for both the school work and the expenses features. Many employed adults already be using more advanced banking apps which automatically display their expenditures.

***

## Progress Report

### Evgenia
Throughout both phase 1 and 2 Evgenia was on the frontend team and focused on continuing to implement and enhance UI features added in phase 1 using Android Studio. Within the [`Expenses backend connection`](https://github.com/CSC207-UofT/course-project-generic-name-1/pull/70) pull request, she worked on connecting the add expenses page and the expenses list to the backend and database, ensuring the the user's inputted expense information could be stored in the database and then later retrieved for display. This pull request was essential for the overall functionality of Plannr as expenses are one of the key features for the app. She also added an income feature to enhance the overall functionality of expenses, allowing a user to see how much of their income they have left after calculating their total expenses. In addition, Evgenia worked on the initial design of the login page and made use of the user's name by creating a welcome message that greets that user with their name in the main page view. Evgenia also added to the design document by writing the specification, the accessibility report, the instructions for how to use features of Plannr, the GitHub feature section, refactoring section, the updated CRC cards and the progress report.

### Kathy
For phase 2 of the project, Kathy continued with her role as a frontend member of the team. As mentioned in the previous progress report, she continued to develop the UI, from implementing the logout button, to features for viewing and adding events, and ...
Throughout the project, she was responsible for ensuring that the overall UI was visually cohesive, and reformatted the activity layouts to reflect this consistency. This was strongly demonstrated with the [`reformat_and_documentation`](https://github.com/CSC207-UofT/course-project-generic-name-1/pull/82) pull request. Additionally in this branch, Kathy worked on code styling and cleanup, where she renamed some class names, added documentation, and discarded unused files. With regards to the design document, Kathy wrote for the Code Style and Documentation portion of the report.

### Dana

### Bolade
For phase 2, Bolade continued with her implementation of Use Cases as a backend team member. She also worked on a "clean up" plan which was to be used to clean up any clean architecture or SOLID principle violations. This is involved making a dependency graph of the classes that were there or were to be implemented along with a sort of UML diagram that contained each class/interface with their variables and/or methods and parameters. This was her most difficult task and took most of her time as this required her to understand clean architecture and SOLID principles well enough as well as come up with any solution to a problem that may have come to be in phase 1 or in phase 2. Prior to working on the clean up plan, she also went through the phase 1 rubric and made a list of questions which her teammates added to that were to be asked to the TA in order figure out what needed to change or improve for phase 2. This helped a lot with the clean up plan. She also aided the frontend team when they needed help understanding a class or method that was implemented in the inner layers such as the use case classes or controllers/presenters. Lastly, she also wrote parts of SOLID and wrote the Clean Architecture section of the design document.

### Sari
During his role as a backend member of the team, Sari was mainly tasked during Phase 1 to implement a working database. Despite the difficult task, Sari was able to learn the basics of SQLite, a local database used for Java, and share his learning experience with the frontend team that needed to know how the database worked. Although the required database helpers were successfully implemented, it was difficult for him to implement the database into the app during Phase 1 due to time constraints and some setbacks which included some missing UI features at the time which lead to his under-contribution. Sari became motivated to work harder during Phase 2 and successfully integrated the database whilst also keeping under consideration the features of SOLID and Clean Architecture in general by adding the necessary gateways and their respective interfaces which use the database to send/receive information to the use cases. This is partially demonstrated by the [`database-and-gateways`](https://github.com/CSC207-UofT/course-project-generic-name-1/pull/80) pull request.

In addition, he used his knowledge on the SOLID, Clean Architecture and Design Patterns to help in brainstorming and identifying potential and applicable Design Patterns and different ways to incorporate SOLID in the project as demonstrated in the pull request above as well as the design document.

### Daniel
For both phase 1 and phase 2, Daniel is on the backend team working on the backbone of the program. For phase 2, Daniel shifted his main focus on testing, while also participating in implementing and discussing design patterns. In the [`Use case tests`](https://github.com/CSC207-UofT/course-project-generic-name-1/pull/76)  pull request, Daniel implemented all tests for the program's use cases and found several errors and discrepancies involving the class `User`, `UserManager`, and `EventDateComparator`. He also found a bug in the regex validator for signing up, which prevented the user to use `.` as a special character. In addition, in the pull request [`Database tests`](https://github.com/CSC207-UofT/course-project-generic-name-1/pull/91), Daniel learnt and implemented Android instrumented tests to test SQLite databases (the gateway classes), and refactored test packages to increase interpretability.
In terms of design document, Daniel wrote Code Organization and Testing. He made short GIFs detailing system setup and how to run the program, which was a major issue in phase 1 where Evan was having issue launching our program. He also helped wrote the Design Patterns section together with Sari which discusses various different strategies wth backend team brainstormed. As well, he helped to write and the SOLID principle section of the progress report with Bolade, who wrote the majority of the section.
