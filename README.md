# Instructor Version

## Goal
The goal of the SaveDriver Mobile application for driving instructors is to simplify the daily process of managing student driver appointments. This app complements the functionality available on the desktop via Google Calendar and YouCanBookMe. In other words, it is not meant to be "feature complete". The focus is on concise notifications and condensed calendars in the spirit of GTD (Getting Things Done).

## High-Level Features
1. Appointment management focused on near-term deliverables with context-based communications with upcoming students and route planning
1. Workflow-based onboarding students when beginning appointment
1. Facilitate teaching through video instructional materials
1. Streamline appointment rescheduling via one-click resolution mechanism
1. Consolidate disparate information stores about students into single pane interface
1. Customized authentication system for different instructors

![alt text](https://github.com/qsuelin/SaveDrivers/blob/master/demo_pics/Login.png "Login Page")


## Functionality
### Day-by-day Dashboard for Appointments
1. Demonstrate a summary of today's appointments on home screen with each appointment including:
    * time slot
    * name
    * address
    * zipcode
    * phone
    * 2nd phone(optional)
    * note from student(optional)
2. Swipe left/right to navigate to other dates for appointments.
3. Provide calendar picker to jump directly to a specific date.

![alt text](https://github.com/qsuelin/SaveDrivers/blob/master/demo_pics/Calendar.png "Calendar")


### Rapid Interaction with Students
1. Contact students directly from home screen appointment dashboard via:
   * phone call
   * short message
   * one-click to send template message to next student about estimated arrival time
1. Invoke camera to take photo of student's learning permit and store into database.
1. Invoke payment inside app and store billing information into database.
1. One-click route planning
    * Embed Google Maps Service inside app to generate arrival time automatically
    * One-click redirect to Google Maps by autofilling instructor's current location and next student's pickup address.

![alt text](https://github.com/qsuelin/SaveDrivers/blob/master/demo_pics/Appointments_View.png "Appointments View")

![alt text](https://github.com/qsuelin/SaveDrivers/blob/master/demo_pics/Appointment_Interaction.png "Appointment Interaction")


### Training Videos
1. Browse videos in Video section, categorized by themes.
1. Fully functioning play control embedded.

![alt text](https://github.com/qsuelin/SaveDrivers/blob/master/demo_pics/Instructions.png "Instructions")


### Student Database
1. Group historical appointments by student.
1. Browse details about given appointment/student in Student section
1. Use search tool to find items of interest.
1. Edit appointment/student record, including:
    * personal information: name, height, weight
    * contact information: address, phone, 2nd phone
    * learning permit photo
    * appointment history
    * notes from instructor
    * notes from student
    * learning status
    * billing information per appointment

![alt text](https://github.com/qsuelin/SaveDrivers/blob/master/demo_pics/Students.png "Students")

### Real-time Appointment Handling
1. Receive real-time notification of new appointment/rescheduling existing.
1. Check history notifications in one view with read/unread mark.
1. Real-time refresh of appointment dashboard
1. Admin options include:
    * one-click confirmation of new appointments
    * one-click redistribution to other instructors' calendar(s)

![alt text](https://github.com/qsuelin/SaveDrivers/blob/master/demo_pics/Notifications.png "Notifications")


### Authentication and Administration
1. Dedicated appointment dashboard by individual instructor
1. Role-based access control

# Student Version
## Goals
## Features
### easily check available appointment
### go to official webpage for booking
### check instructional videos
### register for waiting list and receive notification
