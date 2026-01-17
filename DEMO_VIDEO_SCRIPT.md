# TestMyMaths Android App Demo Video Script

**Duration: 6 minutes (+/-10%)**  
**Assessment: TECH4300 Mobile Development - Assessment 2**  
**Student:** [Your Name]  
**Date:** January 2026

---

## Video Structure Overview

### Part 1: Introduction & Data Passing Comparison (2 minutes)
### Part 2: App Functionality Demonstration (2.5 minutes)  
### Part 3: Testing Demonstration (1.5 minutes)

---

## 🎬 PART 1: INTRODUCTION & DATA PASSING COMPARISON (2:00)

### Opening (0:00 - 0:15)
**[Screen: Android Studio with project open]**

> "Hello, I'm [Your Name], and this is my demonstration of the TestMyMaths Android application developed for TECH4300 Mobile Development Assessment 2. This app is designed to test children's math skills through a two-activity quiz system."

### Data Passing Approaches Comparison (0:15 - 2:00)
**[Screen: Split view showing code snippets]**

> "First, let me discuss the various data passing approaches I evaluated for this project and justify my chosen implementation."

**[Show code: Intent with putExtra]**
> "**Approach 1: Intent with putStringArrayListExtra**
> - This is the method I implemented in my app
> - Advantages: Simple, direct, built-in Android mechanism
> - Perfect for small to medium datasets like our quiz responses
> - Type-safe when using specific methods like putStringArrayListExtra
> - Automatically handles serialization"

**[Show alternative code examples]**
> "**Approach 2: Parcelable Objects**
> - More efficient for complex objects
> - Requires implementing Parcelable interface
> - Better performance but more complex for simple string data
> - Overkill for our use case"

> "**Approach 3: Shared Preferences**
> - Suitable for persistent data
> - Not ideal for temporary quiz session data
> - Would require cleanup after use"

> "**Approach 4: Static Variables or Singleton Pattern**
> - Risk of memory leaks
> - Data can be lost on configuration changes
> - Not recommended for Activity communication"

**[Highlight chosen approach in code]**
> "I chose **Intent with putStringArrayListExtra** because:
> 1. It's the standard Android approach for Activity communication
> 2. Perfect size for our 5 quiz responses
> 3. Automatic lifecycle management
> 4. Simple and maintainable code
> 5. No memory leak risks"

---

## 🎬 PART 2: APP FUNCTIONALITY DEMONSTRATION (2:30)

### Activity 1: Math Quiz Interface (0:00 - 1:20)
**[Screen: Show app launch - MainActivity/MathExamActivity]**

> "Now let me demonstrate the app's core functionality. The first activity presents 5 math questions with different input types."

**[Show each question type]**
> "**Question 1**: 'What is the result of 42 + 7?'
> - Uses EditText for numerical input
> - Answer: 49"

**[Type: 49]**

> "**Question 2**: '15 × 3 = 50, is this equation correct?'
> - Uses RadioButton group for True/False selection
> - Answer: Incorrect/False"

**[Select: Incorrect radio button]**

> "**Question 3**: 'Calculate: 81 ÷ 3 + 27'
> - Another EditText for numerical calculation
> - Answer: 54"

**[Type: 54]**

> "**Question 4**: '8 × 6 = 48, is this correct?'
> - RadioButton group for verification
> - Answer: Correct/True"

**[Select: Correct radio button]**

> "**Question 5**: 'What is 9 × 5?'
> - EditText for multiplication result
> - Answer: 45"

**[Type: 45]**

### Reset Functionality Demo (1:20 - 1:35)
**[Click Reset button]**
> "The Reset button clears all user inputs and resets the exam state. As you can see, all fields are now empty and radio buttons are deselected."

**[Quickly refill answers]**
> "Let me quickly refill the answers to proceed to the results."

### Input Validation Demo (1:35 - 1:50)
**[Leave one field empty and click Show Result]**
> "The app includes input validation. If any field is empty, users see a Toast message: 'Please fill in all answers before proceeding.'"

**[Fill the empty field]**
> "Once all fields are completed, we can proceed to the results."

### Navigation to Results (1:50 - 2:00)
**[Click Show Result button]**
> "Clicking 'Show Result' passes all responses to the second activity using Intent.putStringArrayListExtra and launches the ScoreDisplayActivity."

---

### Activity 2: Results Display (2:00 - 2:30)
**[Screen: ScoreDisplayActivity with results]**

> "The second activity displays a comprehensive results analysis:"

**[Scroll through results]**
> "**Overall Score**: Displayed at the top showing '5/5 Correct • 100%'

> **Individual Question Analysis**: Each question is shown in a color-coded card:
> - **Green cards** represent correct answers
> - **Red cards** represent incorrect answers
> - Each card shows: Question text, Student answer, Expected answer, and accuracy status"

**[Point to specific elements]**
> "The color scheme clearly differentiates performance:
> - Green (#4CAF50) for correct responses
> - Red (#F44336) for incorrect responses
> - White text for good contrast and readability"

---

## 🎬 PART 3: TESTING DEMONSTRATION (1:30)

### Unit Testing (0:00 - 0:45)
**[Screen: Android Studio showing QuizUtilsTest.kt]**

> "Now I'll demonstrate the comprehensive testing implemented for this app."

**[Run unit tests]**
> "**Unit Tests** validate the core calculation logic:
> - `verifyCompleteness_allFilled_returnsTrue`: Tests input validation with complete data
> - `verifyCompleteness_missing_returnsFalse`: Tests input validation with missing data  
> - `evaluateResponses_scoreCalculation`: Validates score calculation accuracy
> - `calculateSuccessRate_calculation`: Tests percentage calculation"

**[Show test results - all passing]**
> "All unit tests pass, confirming our business logic is working correctly."

### UI/Instrumentation Testing (0:45 - 1:20)
**[Screen: MainActivityTest.kt]**

> "**UI Tests** validate the complete user workflow:
> - The test fills all question fields with correct answers
> - Clicks the Show Result button
> - Verifies the results activity displays the correct percentage (100%)
> - Tests the full data passing workflow between activities"

**[Run UI test]**
> "This test confirms that:
> 1. All UI components accept input correctly
> 2. Data passing between activities works
> 3. Results calculation and display are accurate
> 4. The complete user journey functions as expected"

### Test Coverage Summary (1:20 - 1:30)
**[Screen: Test summary/coverage report if available]**

> "The testing suite covers:
> - **Input validation**: Empty fields, valid inputs
> - **Business logic**: Score calculations, percentage computation  
> - **UI interactions**: Button clicks, text input, radio selection
> - **Activity navigation**: Intent passing, data retrieval
> - **Edge cases**: Boundary value testing
> This comprehensive testing ensures application robustness across all scenarios."

---

## 🎬 CLOSING (0:00 - 0:15)

**[Screen: Final app view or Android Studio project]**

> "This TestMyMaths application successfully demonstrates:
> - Modern Android development with Kotlin
> - Effective activity communication using Intents
> - Intuitive UI design with Material Design components
> - Comprehensive input validation
> - Clear visual feedback through color coding
> - Thorough testing coverage with both unit and UI tests
> 
> The app meets all assessment requirements and provides an engaging math quiz experience for children. Thank you for watching!"

---

## 📋 DEMO CHECKLIST

### Before Recording:
- [ ] Ensure app is compiled and runs without errors
- [ ] Prepare test device/emulator with clean state
- [ ] Have Android Studio open with relevant files
- [ ] Test all functionality once before recording
- [ ] Ensure good lighting and screen visibility
- [ ] Test microphone audio quality

### During Recording:
- [ ] Speak clearly and at appropriate pace
- [ ] Show code snippets for data passing comparison
- [ ] Demonstrate both successful and validation scenarios
- [ ] Run both unit and UI tests
- [ ] Highlight color coding in results
- [ ] Show reset functionality
- [ ] Keep within 6-minute time limit

### Key Points to Emphasize:
- [ ] Data passing approach justification
- [ ] Complete user workflow
- [ ] Input validation
- [ ] Visual feedback (colors)
- [ ] Testing comprehensiveness
- [ ] Material Design implementation

### Technical Details to Mention:
- [ ] putStringArrayListExtra for data passing
- [ ] Toast messages for validation
- [ ] MaterialCardView for results display
- [ ] Color resources for correct/incorrect states
- [ ] Espresso testing framework usage
- [ ] JUnit testing implementation

---

## 🎯 ASSESSMENT CRITERIA ALIGNMENT

This demo script addresses all marking criteria:

**Functionality of Activity 1 (8 marks)**: Demonstrates all 5 questions, different input types, Reset and Show Result buttons

**Functionality of Activity 2 (8 marks)**: Shows detailed results, color coding, percentage calculation, individual question analysis

**Testing (8 marks)**: Comprehensive unit and UI tests covering various scenarios

**Code Quality (4 marks)**: Clean, well-structured code following Android conventions

**Video Data Passing Comparison (4 marks)**: Detailed comparison of 4 different approaches with clear justification

**Video App Functionalities (4 marks)**: Complete demonstration covering all features and scenarios

**Video App Testing (4 marks)**: Shows both unit and UI tests with explanation of coverage

---

*Total Script Length: Approximately 6 minutes*  
*File: DEMO_VIDEO_SCRIPT.md*
