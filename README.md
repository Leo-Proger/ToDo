# ToDo

## Description
A simple application for managing your tasks. Developed as a console application using Java 22.

## Features
- Adding new tasks
- Deleting tasks
- Marking tasks as completed or uncompleted
- Searching tasks by text
- Automatic saving of tasks to a json file

## Installation
1. Clone the repository: `git clone https://github.com/Leo-Proger/ToDo.git`
2. Navigate to the project directory: `cd ToDo`
3. Compile the program: `javac -cp "lib/*;." -d out/production/ToDo src/main/java/*.java`

    _Note_: On Unix-like systems (Linux, macOS), use a colon instead of a semicolon: `javac -cp "lib/*:." -d out/production/ToDo src/main/java/*.java`
4. Run the application: `java -cp "out/production/ToDo;lib/*" main.java.Main`

    _Note_: On Unix-like systems (Linux, macOS), use: `java -cp "out/production/ToDo:lib/*" main.java.Main`

## Project Structure
```
ToDo
├───lib                # External libraries (including Gson)
├───src
│   └───main
│       ├───java       # Java source code
│       └───resources  # Resource files (.json)
└───out
    └───production
        └───ToDo       # Compiled classes
```

## Dependencies
The project uses external libraries:
- Gson - for working with JSON (located in the lib/ directory)

## Usage
The application is controlled through commands:
```
add <text>            - Add a new task
delete <numbers>      - Delete tasks with specified numbers
complete <numbers>    - Mark tasks with specified numbers as completed
uncomplete <numbers>  - Mark tasks with specified numbers as uncompleted
search <text>         - Find tasks by text
list                  - Show all tasks
help                  - Display command help
exit                  - Exit the program
```
