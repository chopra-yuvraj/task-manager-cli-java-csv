# 🎯 Task Manager CLI

*A clean, fast, and simple task manager for busy students and developers*

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.java.net/)
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)](https://choosealicense.com/licenses/mit/)
[![CSV Storage](https://img.shields.io/badge/Storage-CSV-blue?style=for-the-badge)](https://en.wikipedia.org/wiki/Comma-separated_values)

> **"Sometimes the best tool is the simplest one."**

A lightweight command-line task manager that does exactly what you need without the bloat. Built by a student, for students (and anyone who values simplicity).

---

## 🚀 Why Task Manager CLI?

During my B.Tech studies at VIT, I got tired of complex task management apps that took longer to set up than actually doing the tasks. This CLI tool is my answer to that frustration - a **no-nonsense, lightning-fast command-line interface** that manages your tasks and gets out of your way.

### ✨ Key Features

🎯 **Four Simple Commands** - Add, List, Complete, Delete. That's it.  
⚡ **Blazing Fast** - From thought to task in under 2 seconds  
📁 **Local Storage** - Your data stays yours (CSV format)  
🔒 **Zero Dependencies** - Pure Java, no external libraries  
🎨 **Smart Prioritization** - HIGH, MEDIUM, LOW priority levels  
📅 **Due Date Tracking** - Never miss deadlines again  
💻 **Cross Platform** - Works on Windows, macOS, and Linux  

---

## ⚡ Quick Start

### Prerequisites
- Java 17 or higher ([Download here](https://adoptium.net/))
- A terminal/command prompt
- 2 minutes of your time

### Installation

**Step 1: Clone the repository**
git clone https://github.com/chopra-yuvraj/task-manager-cli-java-csv.git
cd task-manager-cli-java-csv

**Step 2: Compile**
mkdir out
javac -d out src/main/java/com/yuvraj/taskmanager/.java
src/main/java/com/yuvraj/taskmanager/model/.java
src/main/java/com/yuvraj/taskmanager/service/.java
src/main/java/com/yuvraj/taskmanager/store/.java

**Step 3: Your first task**
java -cp out com.yuvraj.taskmanager.Main help
java -cp out com.yuvraj.taskmanager.Main add "Learn something new today"
java -cp out com.yuvraj.taskmanager.Main list

**If you see your task listed, you're all set! 🎉**

---

## 📚 How to Use

### Basic Commands

| Command | What it does | Example |
|---------|-------------|---------|
| `help` | Show all commands | `java -cp out com.yuvraj.taskmanager.Main help` |
| `add` | Create a new task | `java -cp out com.yuvraj.taskmanager.Main add "Study for exam"` |
| `list` | Show all tasks | `java -cp out com.yuvraj.taskmanager.Main list` |
| `done` | Mark task complete | `java -cp out com.yuvraj.taskmanager.Main done 1` |
| `delete` | Remove task forever | `java -cp out com.yuvraj.taskmanager.Main delete 1` |

### Advanced Usage

**Add task with priority:**
java -cp out com.yuvraj.taskmanager.Main add "Submit assignment" p: HIGH

**Add task with due date:**
java -cp out com.yuvraj.taskmanager.Main add "Dentist appointment" due:2025-10-15

**Add task with both:**
java -cp out com.yuvraj.taskmanager.Main add "Final project" due:2025-12-01 p: HIGH

### Priority Levels
- 🔴 **HIGH** - Urgent, do this first
- 🟡 **MEDIUM** - Normal priority (default)
- 🟢 **LOW** - Do when you have time

---

## 🎬 Real-World Example

Let's plan a typical study day:

Morning setup

java -cp out com.yuvraj.taskmanager.Main add "Review yesterday's notes" p: MEDIUM

java -cp out com.yuvraj.taskmanager.Main add "Attend Data Structures lecture" p: HIGH

java -cp out com.yuvraj.taskmanager.Main add "Complete assignment 3" due:2025-09-15 p: HIGH\n

Check what needs to be done

java -cp out com.yuvraj.taskmanager.Main list

**Output:**

#1 [OPEN] Review yesterday's notes | P:MEDIUM | Due:- | Created:2025-09-10

#2 [OPEN] Attend Data Structures lecture | P:HIGH | Due:- | Created:2025-09-10

#3 [OPEN] Complete assignment 3 | P:HIGH | Due:2025-09-15 | Created:2025-09-10


After attending lecture

java -cp out com.yuvraj.taskmanager.Main done 2

Check progress

java -cp out com.yuvraj.taskmanager.Main list

**Updated output:**

#1 [OPEN] Review yesterday's notes | P:MEDIUM | Due:- | Created:2025-09-10

#2 [DONE] Attend Data Structures lecture | P:HIGH | Due:- | Created:2025-09-10

#3 [OPEN] Complete assignment 3 | P:HIGH | Due:2025-09-15 | Created:2025-09-10

---

## 🏗️ Project Structure

task-manager-cli-java-csv/
├── src/main/java/com/yuvraj/taskmanager/

│ ├── Main.java # CLI interface & command handling

│ ├── model/

│ │ └── Task.java # Task entity (immutable design)

│ ├── service/

│ │ └── TaskService.java # Business logic layer

│ └── store/

│ └── TaskStore.java # File I/O operations

├── out/ # Compiled classes go here

├── LICENSE # MIT License

├── README.md # You're reading this!

└── .gitignore # Git ignore rules

### Design Philosophy

**Clean Architecture:** Each layer has a single responsibility  

**Immutable Objects:** Tasks can't be accidentally modified  

**Zero Dependencies:** Pure Java - no external libraries  

**Fail-Safe Storage:** Data is saved immediately after each operation  


---

## 💾 Data Storage

Your tasks are stored locally in CSV format:

- **Location:** `~/.task-manager/tasks.csv`

- **Windows:** `C:\Users\YourName\.task-manager\tasks.csv`

- **macOS/Linux:** `/home/username/.task-manager/tasks.csv`

**Why CSV?**
- ✅ Human-readable
- ✅ Excel/Google Sheets compatible
- ✅ Easy to backup and share
- ✅ No database setup required

**Sample CSV content:**

1, Review notes, MEDIUM, false,2025-09-10T10:30:00,2025-09-10T10:30:00

2, Submit assignment, HIGH,2025-09-15, true,2025-09-10T11:00:00,2025-09-10T14:30:00

---

## 🛠️ Pro Tips

### Create a Shell Alias

Make the CLI even faster to use:

**For Bash/Zsh users:**

Add to your ~/.bashrc or ~/.zshrc

alias task='java -cp /path/to/task-manager-cli-java-csv/out com.yuvraj.taskmanager.Main'

Now you can use:

task add "New task"

task list

task done 1


**For Windows users:**

Create a batch file `task.bat` in your PATH:

@echo off

java -cp C:\path\to\task-manager-cli-java-csv\out com.yuvraj.taskmanager.Main %*

### Backup Your Tasks

Create timestamped backup

cp ~/.task-manager/tasks.csv ~/Desktop/tasks-backup-$(date +%Y%m%d).csv


Or sync to cloud storage

cp ~/.task-manager/tasks.csv ~/Dropbox/taskmanager-backup/

### Import/Export

Since tasks are stored as CSV, you can:

- Open in Excel for analysis

- Import tasks from other systems

- Share task lists with teammates

- Create reports and visualizations

---

## 🚨 Troubleshooting

### Common Issues & Solutions

**"javac: command not found"**

Install JDK 17+ and add to PATH

java -version # Should show Java 17 or higher

**"Could not find or load main class"**

Make sure you're in the project directory

Recompile everything:

rm -rf out

mkdir out

javac -d out src/main/java/com/yuvraj/taskmanager/.java

src/main/java/com/yuvraj/taskmanager//*.java



**"Error: Index X out of bounds"**

Check the task ID with the list command first

java -cp out com.yuvraj.taskmanager.Main list

Then use the correct ID number

java -cp out com.yuvraj.taskmanager.Main done 1

**Tasks not saving**

Check home directory permissions

ls -la ~/.task-manager/

If the directory doesn't exist, create it

mkdir -p ~/.task-manager

## 🎓 Learning Journey

Building this Task Manager CLI taught me valuable lessons:

### Technical Skills Developed
- **Clean Code Architecture** - Separating concerns properly
- **File I/O Management** - Robust data persistence
- **Command-line Interface Design** - Creating intuitive user experiences
- **Error Handling** - Graceful failure recovery
- **Testing** - Ensuring reliability across platforms

### Challenges Overcome
- **Flexible Argument Parsing** - Supporting flags in any order
- **Data Integrity** - Preventing corruption without databases  
- **Cross-platform Compatibility** - Testing on multiple OS
- **User Experience** - Making CLI tools feel natural

This project represents my growth as a developer and my commitment to building tools that solve real problems elegantly.

---

## 🤝 Contributing

I welcome contributions! Here's how you can help:

### 🐛 Bug Reports
- Use GitHub Issues
- Include Java version and OS
- Provide the exact command that failed
- Share error messages

### 💡 Feature Requests
- Keep it simple (remember our philosophy!)
- Explain your use case
- Consider backward compatibility

### 🔧 Code Contributions
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Make your changes
4. Test on multiple platforms
5. Commit your changes (`git commit -m 'Add amazing feature'`)
6. Push to the branch (`git push origin feature/amazing-feature`)
7. Open a Pull Request

### 📋 Contribution Guidelines
- Maintain zero external dependencies
- Follow existing code style
- Write clear commit messages  
- Test your changes thoroughly
- Update documentation if needed

---

## 📊 Project Stats

- 📝 **Lines of Code:** ~350 (including documentation)
- ⏱️ **Compile Time:** <3 seconds
- 🏃 **Runtime Performance:** <2 seconds for any operation
- 💾 **Memory Usage:** <15MB
- 📦 **JAR Size:** ~25KB (if packaged)
- 🌍 **Platforms:** Windows, macOS, Linux
- 🔗 **Dependencies:** 0

---

## 🎯 Future Ideas

While this CLI is designed to stay simple, here are some ideas being considered:

- [ ] **Task Categories/Tags** - Organize tasks by project
- [ ] **Search Functionality** - Find tasks quickly
- [ ] **Task Notes** - Add detailed descriptions
- [ ] **Recurring Tasks** - Daily/weekly repeating tasks
- [ ] **Export Formats** - JSON, XML export options
- [ ] **Color Output** - Better visual feedback in terminal

*Note: All features must maintain the core principle of simplicity*

---

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE.txt) file for details.

**What this means for you:**
- ✅ Use commercially
- ✅ Modify freely  
- ✅ Distribute copies
- ✅ Private use
- ℹ️ Just include the original license

---

## 🙏 Acknowledgments

Special thanks to:
- **My professors at VIT** for encouraging practical project development
- **My classmates** who provided feedback during development
- **The open-source community** for inspiration and best practices
- **Todo.txt** for proving that simple text-based tools can be powerful
- **My family** for supporting my coding journey

---

## 👨‍💻 About Me

**Yuvraj Chopra**  
🎓 B.Tech Computer Science Engineering - VIT Vellore  
🎓 B.S. Data Science - IIT Madras  
📍 Vellore, Tamil Nadu, India

*Passionate about building simple, effective solutions to everyday problems. Currently exploring the intersection of software engineering and data science.*

**What I'm working on:**
- 🔬 Machine Learning algorithms and applications
- 💻 Clean, maintainable software architectures  
- 📊 Data-driven problem solving
- 🎯 Productivity and automation tools

**Connect with me:**
- 🐙 GitHub: [@chopra-yuvraj](https://github.com/chopra-yuvraj)
- 💼 LinkedIn: [Connect with me](https://www.linkedin.com/in/chopra-yuvraj)
- 📧 Email: yuvrajchopra19@gmail.com

---

<div align="center">

### ⭐ Star this repository if Task Manager CLI helped you stay organized!

*"The best code is the code you don't have to write."*

**Made with ❤️ and ☕ by Yuvraj Chopra**

[🔗 **View on GitHub**](https://github.com/chopra-yuvraj/task-manager-cli-java-csv)

</div>
