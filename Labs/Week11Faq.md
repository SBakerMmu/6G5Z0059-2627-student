# Software Design and Architecture Week11 FAQs

# Assessment Resources
The assessment brief and copies of the lecture slides are available on **Moodle**.

All labs, lab solutions and code examples are available from the [Student Git Repo](https://github.com/SBakerMmu/6G5Z0039-2526-student)

The notes that go with the lectures are in the [Software Design and Architecture Textbook](https://github.com/SBakerMmu/6G5Z0039-2526-student/wiki).

If you are stuck getting started with the assessment task components, there are two projects in the Student Git Repo to help:

- **1CWK100Game** contains some hints and stater code about writing the game logic
- **1CWK100Architecture** contains a starter project for a Clean Architecture implementation of your game

A Standard Markdown tutorial on Markdown is available from [commonmark.org](https://commonmark.org/help/).

A Jetbrains tutorial on how to create a Markdown file in IntelliJ is available at [https://www.jetbrains.com/help/idea/markdown.html](https://www.jetbrains.com/help/idea/markdown.html)

For advanced Markdown features, see GitHub Flavour Markdown at [https://docs.github.com/en/get-started/writing-on-github](https://docs.github.com/en/get-started/writing-on-github).

# FAQs
## User Interface
There is no need to write a UI (either graphical or command line). The only UI that is required is a bit of console application to ask which game the user wants to replay (if you are attempting the advanced feature to replay a game).

It is perfectly fine to run one game after another, but ideally your code will output a clear start and end of the game (so it is obvious where one game ends and other one starts) and the configuration that was used (for example, the output in the Appendix of the assessment brief shows a presentation of the game configuration).

## Do I need to show all possible combinations of variations
No, there are a large number of combinations of variations, but it is only necessary to demonstrate each variation once.

- Show your 1 and 2 dice variations using random dice
- It is much easier to develop and demonstrate the game features such as the END rules using a sequence of dice rolls.

## The game can get stuck with Exact End rules and 2 dice, because the minimum value of 2 dice is 2.

- If using random dice you will need to put some sort of check around the game to terminate it if the number of turns exceeds some sensible number.
- If you use fixed dice sequences then you can demonstrate Exact End rules, carefully choosing your dice sequences to avoid the problem.

## Can I overwrite my game file on save

Yes, you either create a new file each time or overwrite the existing file. The requirement is to replay a previously saved game, so it's fine to just replay a game from the most recent session.

You could (for higher marks) implement different persistence strategies (for example, saving to different files, or saving in different formats such as JSON or just persisting in memory) and use Spring Dependency Injection to choose one persistence strategy at runtime. Ways of doing this were covered in the labs.


## Can I submit my code via GitHub
No, the University requires that you submit via Moodle, because this is how we know if you have met the submission deadline or not.

Your submission should be in the form of a ZIP file containing:
- The complete source code for your submission as an IntelliJ project.
- A readme file written in Markdown.

- Please name your submission file as
Firstname_Lastname_StudentIDNumber.zip (Example: Steve_Williams_12345678.zip).

Moodle submission inboxes cannot accept files larger than 100 MB. It is unlikely that you will exceed this for this assessment.

Should you need to submit files exceeding this size you are required to upload your project to your university-supplied OneDrive account and submit a link as part of the package you submit to Moodle (e.g., pasted into a text file).

Prior to submission via Moodle, please ensure that you archive your project folders into a zip file and upload them to your OneDrive account.

If submitting via OneDrive, ensure that your tutors `steve.baker@mmu.ac.uk` and `ashley.williams@mmu.ac.uk` have access to the work. **Do not** alter your work after the deadline.


## Checklist

| Check                                                                                                                                                                                                                                                                                           | Chapter in Textbook                                                                 |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------|
| ☑ Have you written your code using a coding standard such as the Google Java Style Guide?                                                                                                                                                                                                       |                                                                                     |
| ☑ Have you chosen sensible, ‘intention revealing’ names for classes, interfaces, methods and variables?                                                                                                                                                                                         |                                                                                     |
| ☑ Have you organised related classes into packages and used access control to hide internal implementation behind a public application programming interface (API)?                                                                                                                             | Single class design (1): Access control                                             |
| ☑ In your classes, have you thought about validation of inputs (preconditions), checking postconditions and checking any class invariants?                                                                                                                                                      | Single class design (2): Contracts                                                  |
| ☑ Have you written dedicated classes that represent values in your software design (the Value Object pattern)?                                                                                                                                                                                  | Single class design (3): Value Objects                                              |
| ☑ Have you looked at each class and asked if it has too many responsibilities (the Single Responsibility Principle) and refactored your classes accordingly?                                                                                                                                    | Designing with multiple classes (1): Maintainable Code.                             |
| ☑ Are you handling variation between algorithms by encapsulating the algorithms behind a common interface, so the algorithm can be varied at runtime?                                                                                                                                           | Polymorphism, Handling variation using a Strategy,  More ways of handling variation |
| ☑ Are you using the Dependency Injection container from Spring Boot Framework to assemble your software product?                                                                                                                                                                                | Dependency Injection                                                                |
| ☑ Is your application code completely independent from the infrastructure and there is no dependency on a 'technology' such as Spring Boot, Java input, output and error streams (`System.out`, `System.in`, `System.err`) and Java file handling methods? Do all dependencies 'point inwards'? | Ports and Adapters (Hexagonal Architecture)                                         |
| ☑ Have you organised your software product using the Clean Architecture style?                                                                                                                                                                                                                  | Clean Architecture                                                                  |
| ☑ Have you included a README file named `readme.md` at the root of the project and written in Markdown?                                                                                                                                                                                         | Documenting software design                                                         |
| ☑ Does your README file use the accurate design and architecture terminology?                                                                                                                                                                                                                   | Documenting software design                                                         |
| ☑ Does your README file list the variations and advanced features attempted and explain their design?                                                                                                                                                                                           |                                                                                     |
| ☑ Does your README file include an explanation of where and why design patterns have been used (naming the design pattern)?                                                                                                                                                                     |                                                                                     |
| ☑ Does your README file have an explanation of where and why SOLID principles have been followed (naming the principle)?                                                                                                                                                                        |                                                                                     |
| ☑ Does your README file include an explanation of how you have applied clean or “ports and adapters” architecture with reference to dependencies?                                                                                                                                               |                                                                                     |
| ☑ Does your README conclude with an overall evaluation of your implementation (what do you like, loath, want to improve)? Ask yourself how extensible is my product, how maintainable is it?                                                                                                    |                                                                                     |
| ☑ Have you checked that your submitted ZIP file contains the complete source code (compiles and runs) for your submission as an IntelliJ project and your README.md file?                                                                                                                       |                                                                                     |
| ☑ Is your submission file named Firstname_Lastname_StudentIDNumber.zip?                                                                                                                                                                                                                         |                                                                                     |
