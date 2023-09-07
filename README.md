# Spring Boot Microservices With IntelliJ IDEA - Workout Builder

## Introduction

My Workout Builder allows individuals to create sets of exercises into a custom workout plan based on their preferences and allotted time. Users can then log their workout and keep a history of how much they've exercised each day. Trainers are able to perform basic CRUD operations from a manage exercises panel.

## Storyboard

[Figma story boarding](https://www.figma.com/file/0bNbilsSdP1WYJjVXg4DsK/Bootstrap-5-Design-System---UI-Kit-(Community)?type=design&node-id=1%3A4820&mode=design&t=YTEGv7W80dwTvb6q-1)

## Requirements

1. As an athlete, I can log my workouts
2. As an athlete, I can view my previous workouts
3. As an athlete, I can generate a custom workout
4. As an athlete, I can add an exercise

## Examples

**Given**: User is logged in and a feed of exercises (workout) is available

**When**: The user selects the date 09/05/23

**Then**: The user can view the workout that they did for that date

---

**Given**: User is logged in and selects a date that has no workouts for it

**Then**: Fallback text states that there was no workout on that date

---

**Given**: A feed of exercises (workout) is available

**When**: User selects the type of exercise, Cardio, and the amount of time 45 minutes

**Then**: The workout generated with type cardio and time of 45 minutes

---

**Given**: The user visits a login screen

**When**: The users enters a username "EXAMPLE" and password "PASSWORD123"

**Then**: The user gets logged in

---

**Given**: The user is logged in and on the home page

**When**: The user has the new exercise description filled out, and an exercise type selected. They press the create exercise button.

**Then**: A new exercise is added and can be used when generating a workout

## Class Diagram

[Our Diagram (LucidCharts)](https://lucid.app/lucidchart/f9698650-ee07-4a2e-b23d-bee182d04a9c/edit?viewport_loc=-866%2C786%2C3238%2C1602%2C0_0&invitationId=inv_fa7f720f-869a-441c-b89c-e92efc2ee4ea)

### Class Diagram Description 

## JSON Schema

This is what we plan to export to another app.


> {
>  "type" : "object",
>  "properties" : {
>    "name" : {
>      "type" : "string"
>    },
>    "age" : {
>      "type" : "integer"
>    }
>  }
> }

## Team Memebers and Roles

UI Specialist: Brandan Jones
Business Logic/Persitence: Brandan Jones
DevOps/Product Owner/Scrum Master/GitHub Admin: Brandan Jones

## Milestones

[Milestone 1](https://github.com/discospiff/SpringBootMicroservicesWithIntelliJIDEA/milestone/1)

## Standup

[We meet 7:00 PM Eastern on Sunday on Teams](https://teams.microsoft.com/l/meetup-join/19%3af8fd74991d314a0987e34b7c91ed88be%40thread.tacv2/1598225206706?context=%7b%22Tid%22%3a%22f5222e6c-5fc6-48eb-8f03-73db18203b63%22%2c%22Oid%22%3a%225c29be2c-6de4-49ad-a444-dfb003838da6%22%7d)



