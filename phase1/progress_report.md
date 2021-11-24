# Progress Report
## Open Questions
+ Which design patterns should we implement?
+ How to create the pie-chart view for expenses?
+ How to create the option to switch between calendar views (weekly, monthly) without altering the layout?
+ How can we include dark mode without redesigning all the pages individually?

## Our Design
Our design is generally centred around the Event class and its subclasses, which works well because all the subclasses which represent different types of events inherit from the parent class Event. In phase 0, one of the subclasses Event was the only class with only an end date but for phase 1, we made it so that the end date is both the "start" and "end date". This made comparing the due dates of Deadlines with other Events easier to implement since now all Event subclasses had a "start date". It also made it easier to get the list of events of the day since we could look for all the start dates of the event instead of having an exception for Deadline.

The class inheritance of Event can also be seen in our UI design. Since all event types inherited from Event, they all shared similar attributes such as name, priority, start date and end date. Therefore, the layout when creating an event is pretty much same across all types. This will also make implementing Life events for phase 2 easier as well. Additionally, this is also a designed around the user by anticipating what a user would want to do with their events.

For Packaging, since phase 0, we decided to organize our project by packaging by layer because it is the most intuitive. But this is not definitive and based on how things evolve in the future, we may change it to something else.


## Group Progress Summary and Future Plans
We split our group into two teams: the frontend (GUI) and the backend team. The GUI team was responsible for setting up the views for our app in Android Studio as well as the activities that came with them. The backend team focused more on the internal layers such as the Entities, Use Cases, Controllers, Presenters, and Database. Prior to splitting the group, as a whole worked together on making the UI design for the app on Figma, made the tasks that each team will need to work on on ClickUp, and contributed to setting up Android Studio project in the repository. One thing that we realized when making our UI design was that our initial specification would have required a lot of work which we did not have the time for so we decided to simplify it for phase 1 and implement the rest in phase 2.

###  Evgenia
Evgenia was on the frontend team and focused on creating the UI using Android Studio. She had no previous experience creating Android apps so she took the time to learn how to code xml files. Evgenia created the Expenses Landing page, Add Expenses, Expenses List and Settings page. In addition, she added to the progress report by writing the specification and group progress summary/future plans and overall design of the UI on Figma. In the future, she will continue to develop and create more advanced features within all expenses pages (pie chart view, income, margin) and settings (personal preferences: currency, time etc.). Evgenia will also work on making the UI more visually appealing.

### Kathy
For phase 1, Kathy is apart of the frontend team, and was responsible for creating the Main View page, as well as Add Event pages for each school event type (Assessment, Deadline, Class, and Study Session). In addition, Kathy created the app logo and adjusted the design of additional UI pages to appear more visually cohesive.  
For the final phase of the project, Kathy intends on improving the overall UI: this includes  implementing the Add Event pages for the Life category, Edit Events (for both School and Life events), Delete Events, and perhaps implementing a dark theme mode, as well as event notifications.

### Dana
Dana was appointed to the frontend team for phase 1, and focused on building the Welcome page, Sign-Up page, and School activity pages. With regards to the School page, Dana worked on connecting the UI to backend, specifically for the action of adding a school event.
Looking ahead to phase 2, Dana intends on implementing an additional calendar view (weekly) into the School activity page, allowing more customization for the user. As well, she will be finishing merging the UI implementation of the To-do list with the To-dos page so the user can see their daily events and be able to sort them

### Bolade
Bolade was in the backend team and focused on implementing the remaining parts of Entities from phase 0 needed for the updated specification as well as the Use Case classes that were needed. She fully implemented Assessment, Deadline, GetTodaysEvents, SortTodaysEvents, GetEventsOfDate, the comparators for events by date and time, and AddEvent. She also contributed to the implementation of Events, Work, StudySession , Class, User, UserManager, and Expense. Her main focus was sorting the events of the current day in order to display them for the to-do list on the the main view. For the design document, she contributed to the UI design on Figma with her group, to the group summary and future plans part of the progress report, our design part of the progress report, and SOLID principles. For phase 2, she plans on implementing the remaining Entities (social, academic date, holiday), the Use Case classes that will be needed for Life events, and testing.

### Sari
Sari is in the backend team. His main focus was looking into the different ways of storing and choosing the most optimal method, which ended up being through a local database using SQLite. Furthermore, he assisted in implementing a few of the Event entities, helped create the UI design, and was able to connect the Sign up page and correctly store user credentials using a SharedPreferences object. For phase 2, he hopes to continue learning SQLite and correctly implement the database for storing events.

### Daniel
Daniel is on the backend team and focused on correcting previous issues in phase 0 and helped to review all backend pull requests. Additionally, he also contributed to brainstorming the design patterns that can be used for this project and developed the majority the tests available on Github. For phase 2, Daniel hopes to continue to help review tests and implement a design pattern and make sure the program adheres to the design principles covered in this course.

