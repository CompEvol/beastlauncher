# BeastLauncher

A flexible, generic launcher for BEAST2-based applications that ensures proper package initialization.

## Overview

BeastLauncher extends the standard BEAST2 launcher to provide a configurable entry point for running any BEAST2-based application. It ensures proper initialization of the BEAST2 environment, including package management, before launching the specified main class.

The main benefits are:

- Proper initialization of BEAST2 packages
- Setting of important environment variables and system properties
- Configurable main class through command-line arguments
- Seamless integration with the BEAST2 ecosystem

## Usage

### Basic Usage

```
./beast-generic --main your.package.MainClass [other args]
```

If no main class is specified, it defaults to the standard BEAST2 main class (`beastfx.app.beast.BeastMain`).

### Example: Running Beast2Lang

```
./beast-generic --main org.beast2.modelLanguage.Beast2Lang -i model.b2l -o model.xml
```

### Example: Running Standard BEAST2

```
./beast-generic input.xml
```

## Building from Source

1. Clone the repository:
   ```
   git clone https://github.com/your-username/beastlauncher.git
   cd beastlauncher
   ```

2. Build using Maven:
   ```
   mvn clean package
   ```

3. The compiled JAR and executable script will be in the `target` directory:
   ```
   cd target
   ./beast-generic --help
   ```

## Requirements

- Java 11 or later
- BEAST 2.7.x installation (or will use bundled dependencies)
- Maven (for building from source)

## Project Structure

- `src/main/java/beast/app/launcher/BeastGenericLauncher.java`: The main launcher class
- `beast-generic`: Shell script to run the launcher
- `pom.xml`: Maven configuration file

## License

This project is licensed under the same license as BEAST2 (LGPL v3).

## Acknowledgments

BeastLauncher extends the original BeastLauncher class from the BEAST2 project.
