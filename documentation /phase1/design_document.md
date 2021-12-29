
# Design Document


## SOLID Design Principles
SOLID Design Principles
SRP: Single Responsibility Principle
Comply: In order to comply with SRP, we made sure that each class has one responsibility. For example, the use case class AddEvent is only responsible for adding a new event, GetTodaysEvents is only responsible for returning a list of events taking place today, SortTodaysEvents is only responsible for sorting and returning the list of today's events, and GetEventsOfDate is only responsible for returning a list of events for the selected date. 
Violate: The use case class UserManager handles many responsibilities or "actions". UserManager is responsible for creating a user, accessing, changing or adding to User attributes such as name, list of courses, school, list of events, and list of courses. It's also responsible for calculating the user's current balance. If we had more time, we would have divided these "actions" into EditUser (for name, list of courses and school), EditEvent, EditExpense, EditEventList, EditExpenseList, AddExpense, etc. that way each class has one responsibility or one set of related responsibilities. Our controllers and some presenter responsibilities can be found in one the Android activity classes, if we had more time, we would divide these into Controllers, Getaways, and Presenters responsible for their own (set of) responsibilites.
OCP: Open-Closed Principle
Comply: To comply with OCP, we separated the Entity classes and Use Case classes by layer and by general responsibility. Although not all of the Use Case classes follow SRP, those that do follow SRP will make it easier to extend the app's behavior without needing to change their existing code. For example, all our Entities are protected from the use cases.
Violate: The activity classes (controller + presenter) for Android Studio have objects or call methods of the Event class so the activity classes are dependent on an Entity class instead of just on the use case classes. If we had more time, we would ensure that each clean architecture layer is separated and that the entities are protected from use Cases, use cases from controllers, presenters, and getaways, and those 3 from the UI.
LSP: Liskov Substitution Principle
Comply: To comply with LSP, we made sure that superclass objects were replaceable with subclass objects. For example, if we were creating a Deadline event object (which is a subclass of Event), we made sure that substituting the instance from type Deadline to type Event did not cause any problems.
ISP: Interface Segregation Principle
Comply: We did not create any interfaces, and therefore we also complied with ISP
DIP: Dependency Inversion Principle
Comply: To comply with DIP, we made sure that all high-level code was reusable and not affected by changes from low-level code which was done through abstraction.

## Clean Architecture
Clean Architecture
For clean architecture, we believe we mostly satisfied the design principles. For this program, our entities from Phase 0 didn't change, which includes User, Event (and its subclasses), and Expense. We access these Entities through their UseCases, namely Event`
Manager,serManager, and ExpenseManager. As these UseCases are within the same layer, and to pass information between each other, they may call each other's methods within themselves. For presenters, since we replaced our CLI program with the Android GUI application, it is now replaced by the Android layouts, and their corresponding java classes (under com.ult.generic). Our gateways are also replaced by the Android operating system, namely the UI of the app. It will serve the purpose to print information to user. 

For controllers, there may be slight violations in that there are cases where our presenter also served the purpose of checking the validity of user input or are used to directly deal with storing user information. Because we were mainly focused on developing the GUI interface and learning Android, this was slightly overlooked and we will further investigate and fix this design violation in the future.

## Design Patterns
Design Patterns
For our project, we thought about the Factory design pattern for creating Events. This is applicable because we have several different types of events, by creating a similar concrete class called EventFactory, we can then use the same logic for creating various types of events. This could reduce the amount of code we need to write each time we want to create an Event. Suppose the client wants to create a Deadline event, we can simply call EventFactory.getEvent(“Deadline”) to get a default instantiation of Deadline, then call the respective setter method for startDate, endDate, etc. But this may potentially come at a cost of needing to call the setter methods many times in order to set the events’ attributes, which needs to be discussed with the team. 

Additionally, we also thought about the decorator design patter. The Event super class has four instance variables which are shared amongst all the subclasses in the inheritance hierarchy. These include the name, priority, start date and end date. Assuming the user opts to use notifications to be alerted of the upcoming events, our group may use the Decorator Design Pattern to allow the user to select which of the event variables he/she wants to be displayed in the notification. For example, the standard notification could be: “You have a Deadline due soon!”. If the user chooses to decorate the notification with the name variable, the notification could be: “You have the Deadline event ’Phase 1’ due soon”. If the user also chooses to decorate the notification with the priority (assuming it’s a high priority), the notification could be: “You have a Deadline event ‘Phase 1’ due soon!!!”. If the user also decorates the notification with the end date, the notification could be "You have the deadline event "Phase 1" due on November 5th at 11:59 PM"

Use of GitHub Features
Link for ClickUp: https://app.clickup.com/14148297/v/b/7-14148297-2

## Major Design Decisions
Major Design Decisions
We reduced of the scope of phase 1, as we were having difficulties navigating android studio and realized there was a possibility of running out of time. Our other option was to attempt completing all elements of our app, including the more complex visual aspects and risk running out of time. 

## Packaging Strategies
Packaging Strategies
Since phase 0, we decided to organize our project by packaging by layer because it is the most intuitive. But this is not definitive and based on how things evolve in the future, we may change it to something else.
