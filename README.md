# Spring Boot Microservices With IntelliJ IDEA - Workout Logger

See it deployed [here!](https://workout-logger-final-project.azurewebsites.net/)

## Introduction

My Workout Builder allows individuals to create sets of exercises into a custom workout plan based on their preferences and allotted time. Users can then log their workout and keep a history of how much they've exercised each day. Trainers are able to perform basic CRUD operations from a manage exercises panel.

## Storyboard

[Figma story boarding](https://www.figma.com/file/Go9DYpQzM3YUQwRWkNXNnN/Workout-Builder-Project?type=design&node-id=0%3A1&mode=design&t=VB0P9OIyYVFyBNt0-1)

## Requirements

### 1. As an athlete, I can log my workouts

#### Examples

**Given**: The user is logged in and has worked out

**When**: The users enters their workout with the exercises "Indoor Running," and "Dumbbell Curls" with a duration of 45 minutes

**Then**: The workout is saved with the exercised "Indoor Running," and Dumbbell Curls" with a duration of 45 minutes and the current date

---

**Given**: The user is logged in and has worked out

**When**: The users enters their workout with the exercises "Indoor Running," and "Dumbbell Curls" but does not enter a duration

**Then**: The workout is not saved and an error is given - "Must select workout duration"

---

**Given**: The user is logged in and has an incomplete workout planned

**When**: The user clicks a check-box on an incomplete workout to mark the workout as completed

**Then**: The workout will be logged and can be viewed as a completed workout

### 2. As an athlete, I can view my previous workouts

#### Examples

**Given**: User is logged in and a feed of exercises (workout) is available

**When**: The user selects the date 09/05/23

**Then**: The user can view the workout that they did for that date

---

**Given**: User is logged in and on home page

**When**: The user selects a date that has no workouts for it

**Then**: Fallback text states that there was no workout on that date


### 3. As an athlete, I can generate a custom workout plan 

#### Examples

**Given**: User is logged in and a feed of exercises is available

**When**: User selects the exercises "Squats" and "Push-ups", a duration of 30 minutes, and sets the planned date to 9/10/23

**Then**: The workout plan is created for the date 9/10/23, to do squats and push-ups for 30 minutes

---

**Given**: User is logged in and a feed of exercises is available

**When**: User selects the exercises "Squats" and "Push-ups", a duration of 30 minutes, and sets the planned date to 9/10/22 (in the past)

**Then**: The workout plan is not created and displays an error - "Please select a future date"

### 4. As an athlete, I can add an exercise

#### Examples

**Given**: The user is logged in and on the home page

**When**: The user has the new exercise name and description filled out, and an exercise type selected. They press the create exercise button.

**Then**: A new exercise is added and can be used when logging a workout

---

**Given**: The user is logged in and on the home page

**When**: The user has the new exercise name but no description filled out, and an exercise type selected. They press the create exercise button.

**Then**: The exercise is not created it an error is thrown - "Please add a description"

## Class Diagram

[Our Diagram (LucidCharts)](https://lucid.app/lucidchart/f9698650-ee07-4a2e-b23d-bee182d04a9c/edit?viewport_loc=-866%2C786%2C3238%2C1602%2C0_0&invitationId=inv_fa7f720f-869a-441c-b89c-e92efc2ee4ea)

### Class Diagram Description 

The Exercise class pulls in information about a specific exercise. This class calls for an ID that it will set itself, A name that you will provide, A description, A workout type (Chest, Arm, Abs, Cardio, etc.), and a duration for the exercise. The Workout class will gather information from the exercise class and create a workout plan. The User class brings in information on who uses the software. It will connect with the exercise so you can know who created the exercises. The Exercise Controller class will gather the Exercises and ID them. The Workout Controller is what builds the workouts. It will pull from the Exercises and create a workout based on the type of workout you want to do. 

## JSON Schema

This is what we plan to export to another app.

```
{
    "type": "object",
    "title": "Workout",
    "properties": {
        "date": {
            "type": "string",
            "format": "date-time",
        },
        "id": {
            "type": "integer",
        },
        "workout_type": {
            "type": "string",
        },
        "exercises": {
            "type": "array",
            "items": {
                "type": "object",
                "properties": {
                    // todo: Define properties for the Exercise object here 
                }
            },
        },
        "user": {
            "type": "object",
            "properties": {
                // todo: Define properties for the User object here 
            },
        }
    },
    "required": ["date", "id", "workout_type", "exercises", "user"]
}

```

## Team Members and Roles

**UI Specialist**: Alex Balogh, Steele Shreve

**Business Logic/Persistence**: Charles Hayes, Adam Tulloss

**DevOps/Product Owner/Scrum Master/GitHub Admin**: Adam Tulloss

## Milestones

[Milestone 1](https://github.com/discospiff/SpringBootMicroservicesWithIntelliJIDEA/milestone/1)

## Standup

[We meet 8:00 PM Eastern on Tuesday on Teams](https://teams.microsoft.com/l/meetup-join/19%3ameeting_NGU0OGU4MWMtMDU4MC00MTU1LWI0YTItZmZjZWNmY2I3MjE5%40thread.v2/0?context=%7b%22Tid%22%3a%22f5222e6c-5fc6-48eb-8f03-73db18203b63%22%2c%22Oid%22%3a%2238d37589-3436-44b5-a523-52f509e18081%22%7d)



