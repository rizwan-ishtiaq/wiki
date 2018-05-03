### Test Phases
TDD | BDD
--- | ---
Arrange | Given
Act | When
Assert | Then
Annililation |

*In arrange phase we can have three type of test fixtures (transient fresh, persistence fresh, presistence shared)

### Lifecycle Annotations
Annotation | Description
---------- | -----------
@BeforeEach | Execute a method before the execucation of each test (Once per method)
@AfterEach | Execute a method after the execucation of each test (Once per method)
@BeforeAll | Execute a method before the all test execucated (Once per class)
@AfterAll | Execute a method after the all test execucated (Once per class)
@TestInstance (TestInstance.Lifecycle.PER_METHOD) | By default junit create a new instance of each test class before executing each test method or -Djunit.jupiter.testinstance.lifecycle.default=per_method
@TestInstance (TestInstance.Lifecycle.PER_CLASS) | once instance of class will use to run all the test (annotation put on class) or -Djunit.jupiter.testinstance.lifecycle.default=per_class or create a file on root of class path with name junit-platform.properties and put these properties there to change the behaviour
@DisplayName ("") | can be applied on method or class, increase readability, can have emojo are special characters, to be displayed by test runners
@Disabled ("reason") | on test method it is disable single test and on class it will ignore all tests in class

* with per_method @Before/After(All) methods need to be static
* with per_class @Before/After(All) can be non-static methods

### Test Hierarchies
* Non-static inner classes with @Nested annotation can be use to create a test hierarchy and express the realtionship among servaral group of tests
* @Before/After (Each) will work fine in nested classes
* @Before/After(All) will not work for per_method (default) but will work fine with per_class in nested class {static method in inner class are not allowed}

### Assertions
Method | Params | Description
------ | ------ | -----------
assertAll | stringHeading , lambdas | if one assertion fails, it continue to eveluate others
assertThrows | exception, lambda | expacts excption
assertTimeout | timeDuration, lambda | method under test completed within time, but complete method call. Run method in same thread
assertTimeoutPreemptively | timeDuration, lambda | terminate execution of method if time exceeds, run method in separate thread
