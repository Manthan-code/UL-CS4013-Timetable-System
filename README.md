<h1>UL-CS4013 Timetable System</h1>
<h4>A simple command-line timetable management system for UL modules, rooms, courses, students, and lecturers.<br></h4>

<b>->Project Overview</b>

This is a Java-based CLI application that loads timetable data from CSV files and allows different users (Admin, Lecturer, Student) to view or manage the timetable.<br>

The system reads data from:<br>
• timetable_data.csv<br>
• timetableModules.csv<br>
• timetableRooms.csv<br>
• courses.csv<br>
• users.csv<br>

Different user roles get different menus and permissions.<br>

<b>📁 Project Structure</b><br><br>
<b>Main Entery Files Description<br></b>
CLILauncher.java
Main CLI controller. Loads CSVs, handles login, and shows menus for Admin, Lecturer, and Student. Also prints the weekly grid timetable.<br>

<b>project_classes – Core Classes<br></b>
<b>Class	Description<br></b>
User.java	Stores email, role, group, and course for each user.<br>
LoginPrompt.java	Handles login by reading users.csv.<br>
Module.java	Represents a module (code, name, lecture/lab hours). Loaded from timetableModules.csv.<br>
Room.java	Represents a room (type, capacity, code). Loaded from timetableRooms.csv.<br>
TimeSlot.java	Stores start and end LocalTime values. Handles overlap checking.<br>
TimetableEntry.java	Represents a single timetable row containing day, timeslot, module, lecturer, group, room, and week range.<br>
TimetableManager.java	Loads, saves, filters, and stores all timetable entries. Handles conflict checking and printing.<br>
<br>
<b>project_data – Data Manager<br></b>
<b>File	Description<br></b>
DataManager.java	Loads modules, rooms, and course-module mappings from CSV files and provides helper lookups.
<br><br>
<b>project_io – CSV Utilities<br></b>
<b>File	Description<br></b>
CSVReader.java	Simple CSV reader that returns List&lt;String[]&gt;.<br>
CSVWriter.java	Writes timetable entries back to CSV format.
<br><br>
<b>🔧 Features</b>

1) Student, Lecturer, and Admin login system
2) View course, student, module, room, or lecturer timetables
3) Admin can add/edit/delete timetable entries
4) Time conflict checking
5) Prints a weekly grid timetable (Mon–Fri, 09:00–17:00)

<br>
<b>📄 CSV Files Used<br><br></b>
File Purpose<br><br>
1) users.csv	Email, Password, Role, Group, Course<br>
2) timetable_data.csv	Main timetable rows (day, time, module, room, lecturer, group, weeks)<br>
3) timetableModules.csv	Module details<br>
4) timetableRooms.csv	Room details<br>
5) courses.csv	Maps course codes (LM121 / LM174) to their module list
<br><br>
All CSV files must be stored in the project root.<br>
<br><br>
<b>--> How to Run</b><br><br>
<b> 1) Running from IntelliJ</b>

1) Open project
2) Right-click Src/ → Mark Directory as → Source Root
3) Open CLILauncher.java
4) Run main()

<br>
<b>2) Running from Terminal (JDK required)</b>

<b>Compile:</b>
javac -d out Src\project_main\*.java Src\project_classes\*.java Src\project_data\*.java Src\project_io\*.java


<b>Run:</b>
java -cp out project_main.CLILauncher

<br>
📂 Project Folder Structure<br>
Src/<br>
 ├─ project_main/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;→ main CLI launcher<br>
 ├─ project_classes/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;→ core logic (User, Module, Room, TimetableEntry, etc.)<br>
 ├─ project_data/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;→ DataManager loading CSVs<br>
 └─ project_io/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;→ CSV handling utilities<br>