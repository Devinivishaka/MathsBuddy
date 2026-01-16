# MathBuddy

Small Android project containing unit tests and instrumentation (Espresso) tests.

## Overview

This repository is an Android app named MathBuddy. It includes local JVM unit tests under `app/src/test` and instrumented Android tests under `app/src/androidTest`.

This README explains how to build the project and run tests from PowerShell on Windows (the developer's environment), including the PowerShell-specific quoting/escaping for Gradle properties that contain `#`.

---

## Requirements

- JDK 11+ (as required by the project's Gradle config)
- Android SDK (installed and referenced by `local.properties` in the project root)
- Android device or emulator for instrumentation tests
- PowerShell (Windows) — examples below use `powershell.exe`/PowerShell syntax


## Common tasks

Open the project in Android Studio for interactive development, or use the command-line Gradle wrapper (`gradlew.bat`) from the project root.

### Build

From the project root in PowerShell:

```powershell
# Assemble the debug APK
.\gradlew.bat assembleDebug
```

### Run local JVM unit tests

Unit tests live in `app/src/test/java` (for example `QuizUtilsTest.kt`). Run all unit tests with:

```powershell
.\gradlew.bat test
```

HTML reports for unit tests are usually generated under:

- `app/build/reports/tests/testDebugUnitTest/index.html` (or similarly named folder depending on the variant)

### Run instrumentation (Android) tests

Instrumentation tests must run on a connected device or emulator.

Start an emulator or connect a device with USB debugging, then run:

```powershell
# Run all instrumentation tests for the debug variant
.\gradlew.bat connectedDebugAndroidTest
```

PowerShell note: `#` starts a comment, so when passing a test class/method via Gradle's `-Pandroid.testInstrumentationRunnerArguments.class=` property you must quote or escape the value. Examples:

```powershell
# Run a single test class (quote the whole -P argument)
.\gradlew.bat connectedDebugAndroidTest "-Pandroid.testInstrumentationRunnerArguments.class=com.kaplan.mathbuddy.MainActivityTest"

# Run a single test method (quote the whole -P argument)
.\gradlew.bat connectedDebugAndroidTest "-Pandroid.testInstrumentationRunnerArguments.class=com.kaplan.mathbuddy.MainActivityTest#fullFlow_displaysPercentage"

# Or escape the # with PowerShell's backtick (less readable)
.\gradlew.bat connectedDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.kaplan.mathbuddy.MainActivityTest`#fullFlow_displaysPercentage

# Alternative: use single quotes around the property value
.\gradlew.bat connectedDebugAndroidTest '-Pandroid.testInstrumentationRunnerArguments.class=com.kaplan.mathbuddy.MainActivityTest#fullFlow_displaysPercentage'
```

Instrumented test reports are written to:

- `app/build/reports/androidTests/connected/index.html`

And device logs and Gradle output will show test run details.

---

## File references (useful tests)

- Unit test example: `app/src/test/java/com/kaplan/mathbuddy/QuizUtilsTest.kt`
- Instrumented test example: `app/src/androidTest/java/...` (search that folder for `*Test.kt` classes)

## Troubleshooting

- If Gradle can't find the SDK, ensure `local.properties` contains the proper `sdk.dir` path.
- If a `Task not found` error appears when using `-Pandroid.testInstrumentationRunnerArguments.class=...`, it usually means PowerShell stripped the `#` portion — see the quoting examples above.
- If instrumentation tests can't run, make sure an emulator is running (use Android Studio AVD Manager) or a device is connected and `adb devices` shows it.
- To inspect available Gradle tasks: `.
\gradlew.bat tasks` (run from the project root).

## Notes

- These instructions assume you are using the project's Gradle wrapper (`gradlew.bat`) located at the repository root—this ensures you use the Gradle version the project expects.
- If you want, I can add a small script or helper Gradle task to simplify running a single instrumentation test from PowerShell.

---

Requirements coverage:
- Provide commands for running unit tests: Done
- Provide commands for running instrumentation tests and PowerShell quoting fixes: Done
- Point to test files and reports: Done

If you'd like, I can also:
- Add a helper PowerShell script (e.g., `run-test.ps1`) to run a single instrumentation test with correct quoting, or
- Add a sample GitHub Actions workflow to run unit tests on CI.

