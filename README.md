# Contacts Managment

A contact system to manage, add, search, edit and remove contacts. It has Two different functioning contact manager. 

One functionality is to do a test run in memory contacts, without having any persistent data. The data will be wiped every restart of the program.
Providing a file will make all your data persist and can be saved and loaded on command.

# Requirement
- Java version 17+ <a href="https://www.oracle.com/de/java/technologies/downloads/">Java download Link</a>

# Build - Run Project
- Clone repository and navigate into repo's directory
- Run project with save file `$ ./mvnw compile exec:java "-Dexec.args=path/to/file"`
- Run project in memory `$ ./mvnw compile exec:java`
- `-Dexec.args=""` takes arguments separated by spaces, if your path file include spaces in the name, use single quotes.
 Example: `-Dexec.args="'File with space in name' other/argument`

This application uses java serialization and deserialization API.

# Usage Commands

Follow instruction in terminal to print out, add, remove and update contacts.
Make sure to provide a file in command-line argument if you want any changes to your contacts to persist.

# Example Adding
```
$ java -jar contacts-1.0.jar contacts.data
[menu] Enter action (add, list, search, count, exit): add
Enter the type (person, organization): person
Enter the name: Emad
Enter the surname: Ali
Enter the birth date: 1989-10-01
Enter the gender: m
Enter the number: +49 232 11 232
The record added.

[menu] Enter action (add, list, search, count, exit): add
Enter the type (person, organization): person
Enter the name: Ali
Enter the surname: Ali
Enter the birth date: 2015-2-1
Enter the gender: m
Enter the number: +02 222 222
The record added.
```
# Example Counting
```
[menu] Enter action (add, list, search, count, exit): count
The Phone Book has 2 records.
```

# Example Listing
```console
[menu] Enter action (add, list, search, count, exit): list
1. Emad Ali
2. Ali Ali
[list] Enter action ([number], back):
2
Name: Ali
Surname: Ali
Birth date: 2015-2-1
Gender: M
Number: +02 222 222
Time created: 2023-03-08T14:34:47
Time last edit: 2023-03-08T14:34:47
```

# Example Editing
```
[record] Enter action (edit, delete, menu): edit
Select a field (name, surname, birth, gender, number): name
Enter name: Jake
Saved
Name: Ali
Surname: Ali
Birth date: 2015-2-1
Gender: M
Number: +02 222 222
Time created: 2023-03-08T14:34:47
Time last edit: 2023-03-08T14:34:47

[record] Enter action (edit, delete, menu): menu

[menu] Enter action (add, list, search, count, exit): list
1. Emad Ali
2. Jake Ali
[list] Enter action ([number], back):
2
Name: Jake
Surname: Ali
Birth date: 2015-2-1
Gender: M
Number: +02 222 222
Time created: 2023-03-08T14:34:47
Time last edit: 2023-03-08T14:35:15
```
# Example Searching
```
[menu] Enter action (add, list, search, count, exit): search
Enter search query: Ali
Found 2 result:
1. Emad Ali
2. Jake Ali

[search] Enter action ([number], back, again): again
Enter search query: 2015
Found 1 result:
1. Jake Ali
```
# Data will auto save when file provided as command-line argument
```
[menu] Enter action (add, list, search, count, exit): exit

$ java -jar contacts-1.0.jar contacts.data
[menu] Enter action (add, list, search, count, exit): list
1. Emad Ali
2. Jake Ali
[list] Enter action ([number], back):
back

[menu] Enter action (add, list, search, count, exit): exit
```
