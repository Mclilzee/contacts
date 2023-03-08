# Contacts Managment

- Require Java version 17+ <a href="https://www.oracle.com/de/java/technologies/downloads/">Java download Link</a>

A contact system to manage, add, search, edit and remove contacts. It has Two different functioning contact manager. 

# Build project from scratch - Requires Maven
- Require Maven building tools <a href="https://maven.apache.org/install.html">Maven Installing Instructions</a>
- Start by building the project with `mvn package`
- Navigate to `./target/` directory

# Pre-build projects - Does not require Maven
- <a href="./release/contacts-1.0.jar">Download Jar</a>

# Launching Project
- Launching without arguments: `java -jar contacts-1.0.jar` normal functionality except for persistent data, the data used in Memory contacts will be wiped upon exiting the program.
- Launching with file argument: `java -jar contacts-1.0.jar "Path to file"` Command-line argument of the file type to load contacts from and save contacts to.
Note: If file does not already exist, it will be created.
 
This application uses java serialization and deserialization API.

# Usage Commands

# Example 1