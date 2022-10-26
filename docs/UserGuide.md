---
layout: page
title: User Guide
---
Teacher’s Pet is a desktop application for managing contacts of students and classes, optimised for use via a
Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast,
Teacher’s Pet can get your contact and class management tasks done faster than traditional GUI apps.

## Table of contents
* [Quick Start](#quick-start)
* [UI Overview](#ui-overview)
* [Features](#features)
    * [Viewing help: `help`](#viewing-help-help)
    * [Adding a student: `add`](#adding-a-student-add)
    * [Editing student details: `edit`](#editing-student-details-edit)
    * [Viewing all students: `list`](#viewing-all-students-list)
    * [Finding a student: `find`](#finding-a-student-find)
    * [Deleting a student: `delete`](#deleting-a-student-delete)
    * [Clearing all student: `clear`](#clearing-all-student-clear)
    * [Exiting the program : `exit`](#exiting-the-program-exit)
    * [Saving the data](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)
* [FAQ](#faq)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `teacherpet.jar` from https://github.com/AY2223S1-CS2103T-T09-4/tp/releases when made available.
3. Copy the file to the folder you want to use as the *home folder* for your application.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
   Note how the app contains some sample data.

![UI introduction](images/UG-screenshots/UiIntro.png)


### UI Overview

![Partition](images/UG-screenshots/UiPartition.png)

Our application is divided into 5 areas to maximise productivity, the specific uses are:

- Input Command - The dialog box where all user interaction are held.
- Application’s Reply - A short answer whether the application has executed the command, or an error message if the
  application did not understand the command.
- Student's Details - A window that will display the details of the student(s).
- Statistics Window - A window that shows all the statistics of the tutor, such as the number of students and
the money collected/owed.
- Day’s Schedule List - A scroll window which shows the schedule for the day, sorted by time.

Basic Instructions:
1. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open
   the help window. Some example commands you can try:
    - `list`: Lists all students.
    - `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`: Adds a student named
      `John Doe` to the student list.
    - `delete 3`: Deletes the 3rd student shown in the current list.
    - `clear`: Deletes all students.
    - `exit`: Exits the app.
2. Refer to the Features below for details of each command.

[Back to top](#table-of-contents)

---

## Features

**Notes about the command format:**

- Words in `UPPER_CASE` are the parameters to be supplied by the user. e.g. in `add n/NAME`, `NAME` is a parameter
  which can be used as `add n/John Doe`.
- Items in square brackets are optional. e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.
- Items with `…` after them can be used multiple times including zero times. e.g. `[t/TAG]…` can be used as ` ` (e.g.
  0 times), `t/friend`, `t/friend t/family` etc.
- Parameters can be in any order. e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME`
  is also acceptable.
- If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence
  of the parameter will be taken. e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.
- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will
  be ignored. e.g. if the command specifies `help 123`, it will be interpreted as `help`.

### Viewing help: `help`

Shows a message explaining how to access the help page.

Format: `help`

![Help](images/UG-screenshots/UiHelp.png)

[Back to top](#table-of-contents)

---

### Adding a student: `add`

Adds a student to the Teacher’s Pet.

1. Student’s Name:
    - Student’s Name must not be empty.
    - Student’s Name must only contain alphanumeric characters.
```yaml
Note: Multiple students may share the same name.
```

2. Student’s Contact Number:
   - Student’s contact number must not be empty. 
   - Student’s contact number must only contain numerical digits between `0` and `9`.
```yaml
Note: Contact number must contain at least 3 digits. Contact number must be unique.
```

3. Next of Kin’s Contact Number:
    - Next of Kin’s contact number must not be empty.
    - Next of Kin’s contact number must contain 8 numerical digits between `0` and `9`.
    - Next of Kin’s contact number must begin with `6`, `8` or `9`. 
```yaml
Note: Next of Kin’s contact number cannot be empty. It must contain at least 3 digits.
```

4. Address:
    - Address must not be empty.
    - Address may contain any kinds of character.
```yaml
Note: Address cannot be empty. It must contain at least 1 character.
```

5. Email:
    - Email must not be empty. 
    - Email should be in the format of `local@domain`, where:
      - Local address should only contain alphanumeric characters and these special characters `+_.-`.
      - Domain address should be least 2 characters long.

```yaml
Note: Email cannot be empty. It must fulfil the above requirements.
```

4. Tags:
    - Tags are optional.
    - A student can have any number of tags (including 0).
    - Tags must only contain alphanumeric characters.
```yaml
Note: Tags must contain at least 1 alphanumeric character and cannot contain spacings.
```

Format: `add n/NAME p/CONTACT_NUMBER np/NEXT_OF_KIN_CONTACT_NUMBER e/EMAIL a/ADDRESS`

Example:

- `add n/Ben Tan p/87201223 np/90125012 e/BenTan@gmail.com a/Avenue 712`

![Add](images/UG-screenshots/UiAdd.png)

```yaml
Note: Amount paid, Amount owed, Additional notes fields are to be updated via `edit` command.
```

[Back to top](#table-of-contents)

---

### Editing student details: `edit`

Edits an existing student in the list.

- Student’s Name
- Phone number
- Next of Kin’s phone number
- Address
- Class Date
- Amount paid
- Amount owed
- Additional notes

1. Student's Name, Phone number, Next of Kin’s phone number, Email, Address, and Class Date follow
the same convention as [adding a student](#adding-a-student-add).

2. Amount paid:
    - Amount paid can be an integer or a double.
    - Amount paid must be non-negative.

3. Amount owed:
    - Amount owed can be an integer or a double.
    - Amount owed must be non-negative.
    - Amount owed and Amount paid are modified independent of each other.

```yaml
Note: Amount paid, Amount owed can only be between $0 and $2147483647.
```

4. Additional notes:
    - Additional notes can be left empty.
    - Additional notes can take in any types of character.
```yaml
Important: Note **at least one** of these fields must exist in order to make the `edit` command valid.
```

Format: `edit INDEX [n/NAME] [p/CONTACT_NUMBER] [np/NEXT_OF_KIN_CONTACT_NUMBER] [e/EMAIL] [dt/CLASS_DATE] [a/ADDRESS]
[paid/AMOUNT_PAID] [owed/AMOUNT_OWED] [nt/ADDITIONAL_NOTES]`

Example:

- `edit 1 e/Ben2022@gmail.com`

![UiEdit](images/UG-screenshots/UiEdit.png)

[Back to top](#table-of-contents)

---

### Viewing all students: `list`

Allows the user to view students and their information which includes:

- Phone number
- Next of Kin’s number
- Address
- Email
- Class Date
- Amount paid
- Amount owed
- Additional notes

Format: `list`

![UiUList](images/UG-screenshots/UiList.png)

[Back to top](#table-of-contents)

---

### Finding a student: `find`

Finds students whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g. `alex` will match `Alex`.
- The order of the keywords does not matter. e.g. `Yeoh Alex` will match `Alex Yeoh`.
- Only the name is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`.
- Persons matching at least one keyword will be returned. e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

Example:

`find tan` returns `Tan Xiao Ming` and `John Tan`.

![UiFind](images/UG-screenshots/UiFind.png)

[Back to top](#table-of-contents)

---

### Deleting a student: 'delete'

Deletes the specified person from the student list.

Format: `delete INDEX ...`

- Deletes the person at the specified `INDEX`.
- The index refers to the index number shown in the Student's Details panel (bottom left).
- The index must be a positive integer. e.g. `1, 2, 3, ...`.

Examples:
- `list` followed by `delete 2` deletes the 2nd person in the Student's Details panel.
- `find Betsy` followed by `delete 1` deletes the 1st person in the Student's Details panel.

```yaml
❗ Caution: Deleting a student is irreversible! Please input the correct index number(s).
```

[Back to top](#table-of-contents)

---

### Clearing all student: 'clear'

Clears all students and their details from the list.

Format: `clear`

```yaml
❗ Caution: Clearing all students is irreversible!
```

[Back to top](#table-of-contents)

---

### Exiting the program: `exit`

Exits the program.

Format: `exit`

[Back to top](#table-of-contents)

---

### Saving the data

Teacher’s Pet data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[Back to top](#table-of-contents)

---

### Editing the data file

Students' data is saved as a JSON file `[JAR file location]/data/teachersPet.json`. Advanced users are welcome to edit the data file.

```yaml
❗ Caution: If your changes to the data file makes its format invalid, Teacher’s Pet will discard all data and start with an empty data file at the next run.
```

[Back to top](#table-of-contents)

---

## FAQ

Q: How do I transfer my data to another Computer?

A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Teacher's Pet home folder.

[Back to top](#table-of-contents)

---

## Command summary

| Action               | Format, Examples                                                                                                                                                                                                             |
|----------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Add a student        | add n/NAME p/CONTACT_NUMBER np/NEXT_OF_KIN_CONTACT_NUMBER a/ADDRESS e/EMAIL dt/CLASS_DATE [t/TAG] `e.g., add n/John Doe p/98765432 np/90123291 a/Street ABC e/johnd@example.com dt/2022-09-20 1800-2000 t/python t/beginner` |
| Edit a student       | edit INDEX [n/NAME] [p/CONTACT_NUMBER] [np/NEXT_OF_KIN_CONTACT_NUMBER] [e/EMAIL] [dt/CLASS_DATE] [a/ADDRESS] [paid/AMOUNT_PAID] [owed/AMOUNT_OWED] [nt/ADDITIONAL_NOTES] `e.g., edit 2 p/98765431`                           |
| Get help             | `help`                                                                                                                                                                                                                       |
| List all students    | `list`                                                                                                                                                                                                                       |
| Find a student       | find NAME `e.g., find John Doe`                                                                                                                                                                                              |
| Delete a student     | delete INDEX `e.g., delete 2`                                                                                                                                                                                                |
| Clear all students   | `clear`                                                                                                                                                                                                                      |
| Exit the application | `exit`                                                                                                                                                                                                                       |

[Back to top](#table-of-contents)
