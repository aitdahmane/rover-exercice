# 🚀 Mars Rover Simulation

**Robotic Rover Navigation System**  
*A Java project following SOLID principles with TDD*

---

## 📖 Table of Contents

### 📚 Documentation
- [Project Overview](#-key-features)
- [Development Stack](#-development-stack)
- [Project Structure](#-project-structure)
- [Development Workflow](#-development-workflow)
- [Quick Start](#-quick-start)
- [Usage Examples](#-usage-examples)

### 📋 Development Guides
- [Best Practices](#-best-practices)
- [Future Roadmap](#-future-roadmap)
- [Contact & Links](#-contact--links)

### 📑 Detailed Documentation
| Document | Description |
|----------|-------------|
| [Rover Description](docs/rover.txt) | Description of the rover simulation |
| [Changelog](docs/CHANGELOG.md) | History of changes and version updates |
| [Design Decisions](docs/DESIGN-DECISION.md) | Architecture and technical choices |
| [Test Strategy](docs/TEST-STRATEGY.md) | Testing approach and methodology |

---

## 🚀 Key Features
- Plateau grid system with boundary validation
- Rover movement commands (`L`/`R`/`M`)
- Direction handling (N/S/E/W) with enum logic
- Sequential processing of multiple rovers
- Graceful error handling & recovery

---

### Development Stack
[![Java](https://img.shields.io/badge/Java-17-orange?logo=java)](https://www.oracle.com/java/) - Core Language  
[![Maven](https://img.shields.io/badge/Maven-3.9-red?logo=apache-maven)](https://maven.apache.org/) - Build Tool  
[![JUnit](https://img.shields.io/badge/JUnit-5-green?logo=testlio)](https://junit.org/junit5/) - Testing Framework  
[![Jacoco](https://img.shields.io/badge/Coverage-70%2B-brightgreen)](https://www.jacoco.org/) - Test Coverage
[![Checkstyle](https://img.shields.io/badge/Checkstyle-Enabled-blue)](https://checkstyle.sourceforge.io/) - Code Style Enforcement
[![SpotBugs](https://img.shields.io/badge/SpotBugs-Enabled-blue)](https://spotbugs.github.io/) - Bug Detection
[![Spotless](https://img.shields.io/badge/Spotless-Enabled-blue)](https://github.com/diffplug/spotless) - Code Formatter

---

## 🛠 Quick Start

### Prerequisites
- Java 17+
- Maven 3.9+

### Installation
```bash
# 1. Clone repository
git clone https://github.com/aitdahmane/rover-exercice.git

# 2. Build and run
mvn clean package
java -jar target/rover.jar input.txt
```

## 📚 Usage Examples

### Input Format
```
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```

### Expected Output
```
1 3 N
5 1 E
```

## 🏗 Project Structure
```
rover-exercice/
├── docs/                          # Documentation
│   ├── CHANGELOG.md               # Historique des versions
│   ├── DESIGN-DECISION.md         # Décisions d'architecture
│   ├── TEST-STRATEGY.md           # Stratégie de tests
│   └── rover.txt                  # Description du rover
├── rover/                         # Code source du projet
│   ├── pom.xml                    # Configuration Maven
│   ├── src/
│       ├── main/
│       │   └── java/
│       │       └── com/
│       │           └── nasa/
│       │               └── rover/
│       │                   ├── model/
│       │                   │   └── impl/      # Implémentations du modèle
│       │                   │       ├── Direction.java
│       │                   │       ├── Plateau.java
│       │                   │       ├── Position.java
│       │                   │       └── Rover.java
│       │                   ├── service/
│       │                   │   └── impl/      # Services de l'application
│       │                   │       ├── InputFileService.java
│       │                   │       ├── MissionService.java
│       │                   │       └── RoverControlService.java
│       │                   └── RoverApplication.java # Point d'entrée
│       └── test/
│           ├── java/
│           │   └── com/
│           │       └── nasa/
│           │           └── rover/
│           │               ├── model/impl/     # Tests unitaires du modèle
│           │               ├── service/impl/   # Tests unitaires des services
│           │               └── RoverApplicationTest.java
│           └── resources/
│               └── edge-cases/                # Fichiers de test
│                   ├── edge-positions.txt
│                   ├── empty-file.txt
│                   ├── invalid-format.txt
│                   ├── large-plateau.txt
│                   ├── minimal-plateau.txt
│                   └── multiple-rovers.txt
└── README.md                      # Documentation principale
```

---

## 📝 Development Workflow

```mermaid
gitGraph
    commit id: "chore(init)"
    branch develop
    checkout develop
    commit id: "chore(application): Add main application class"
    branch feature/configuration-tools
    checkout feature/configuration-tools
    commit id: "chore(application): Add main application class"
    commit id: "chore(pom): Add Maven plugins"
    checkout develop
    merge feature/configuration-tools
    commit id: "feat(model): Add interfaces"
    branch feature/position-management
    checkout feature/position-management
    commit id: "test(position): Write failing test"
    commit id: "feat(position): Implement Position class"
    commit id: "refactor(position): Enhance Position class"
    checkout develop
    merge feature/position-management
    branch feature/direction-managment
    checkout feature/direction-managment
    commit id: "test(direction): Add first tests"
    commit id: "feat(direction): Implement Direction enum"
    commit id: "refactor(direction): simplify direction check"
    checkout develop
    merge feature/direction-managment
    branch feature/plateau-managment
    checkout feature/plateau-managment
    commit id: "test(plateau): Add unit tests"
    commit id: "feat(plateau): Implement Plateau class"
    checkout develop
    merge feature/plateau-managment
    branch feature/rover-management
    checkout feature/rover-management
    commit id: "test(rover): Add unit tests"
    commit id: "feat(rover): Implement Rover class"
    checkout develop
    merge feature/rover-management
    branch feature/rover-deplacement
    checkout feature/rover-deplacement
    commit id: "test(rover): Add RoverControlService tests"
    commit id: "feat(rover): Implement RoverControlService"
    commit id: "refactor(rover): Improve code readability"
    checkout develop
    merge feature/rover-deplacement
    branch feature/Input-processing
    checkout feature/Input-processing
    commit id: "test(input): Add unit tests"
    commit id: "feat(input): Implement InputFileService"
    commit id: "feat(rover): Add RoverCommand implementation"
    checkout develop
    merge feature/Input-processing
    branch feature/mission-management
    checkout feature/mission-management
    commit id: "feat(mission): Implement MissionService"
    checkout develop
    merge feature/mission-management
    branch feature/rover-application
    checkout feature/rover-application
    commit id: "feat(application): Enhance RoverApplication"
    checkout develop
    merge feature/rover-application
    branch feature/edge-cases
    checkout feature/edge-cases
    commit id: "feat(tests): Add edge case tests"
    checkout develop
    merge feature/edge-cases
    branch feature/refactoring-code
    checkout feature/refactoring-code
    commit id: "feat(application): Refactor application"
    checkout develop
    merge feature/refactoring-code
```

---

## 🔧 Best Practices
- SOLID Principles: Clean separation of concerns
- TDD Approach: Comprehensive test coverage
- KISS Philosophy: Minimal complexity
- DRY Code: Reusable validation logic
- Error Handling: Robust exception management

## ➡️ Future Roadmap
- Collision detection between rovers
- 3D movement support
- Interactive visualization
- Docker containerization
- REST API interface

## 📬 Contact & Links
- Maintainer: Ayoub AIT DAHMANE
- Email: ayoub@aitdahmane.com
- Repository: github.com/aitdahmane/rover-exercice
- Issues: github.com/aitdahmane/rover-exercice/issues
