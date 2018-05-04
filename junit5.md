### Test Phases
TDD | BDD | Description
--- | --- | -----------
Arrange | Given | Test fixtures setup
Act | When | operation on test perform
Assert | Then | check result of operation
Annililation | | clean up/reset state

*In arrange phase we can have three type of test fixtures (transient fresh, persistence fresh, persistence shared)

### Lifecycle Annotations
Annotation | Description
---------- | -----------
@BeforeEach | Execute a method before the execution of each test (Once per method)
@AfterEach | Execute a method after the execution of each test (Once per method)
@BeforeAll | Execute a method before the all test executed (Once per class)
@AfterAll | Execute a method after the all test executed (Once per class)
@TestInstance (TestInstance.Lifecycle.PER_METHOD) | By default junit create a new instance of each test class before executing each test method or -Djunit.jupiter.testinstance.lifecycle.default=per_method
@TestInstance (TestInstance.Lifecycle.PER_CLASS) | once instance of class will use to run all the test (annotation put on class) or -Djunit.jupiter.testinstance.lifecycle.default=per_class or create a file on root of class path with name junit-platform.properties and put these properties there to change the behaviour
@DisplayName ("") | can be applied on method or class, increase readability, can have emoji and special characters, to be displayed by test runners
@Disabled ("reason") | on test method it is disable single test and on class it will ignore all tests in class
@RepeatedTest (no) | each invocation of repeated test behave like a regular test with full life cycle support. No need to put @Test with this annotation, else junit will execute repeated no + 1 times. RepetitionInfo can be injected by junit in @Before/After (each) or in test method
@Nested | see below
@TestFactory | On method (not private or static). Generates dynamic test to run
@ParameterizedTest | run test with different arguments, @Test will not use with it. if test fails with specific argument then remaining test will continue to evaluate
@ValueSource | allow to specify array of primitive / string type data and can only be use with test with single parameter
@EnumSource | run a test with values of enum, can only be use with single param test. Provide different filter to get specific enums from Enum Source
@MethodSource | specify the name of one or more methods which will provide the argument for the test. For single param test, methods can return stream of primitive/String. For multiple parameters method should return stream or iterable or array or iterator of type Arguments. Methods must be static (can be private) or use per_class
@CsvSource | comma separated string literals, uses single quote as quote character
@CsvFileSource | takes one or more csv file from class path. each line of file result as one invocation of test uses double quote as quote character. Use / to get file from  root of class path
@ArgumentsSource | custom sources by providing a class which implement ArgumentsProvider
@ConvertWith | applied on parameters which you want to convert, passing a class as parameter which extends SimpleArgumentConverter or implement ArgumentConverter
@ExtendWith | 
@TestTemplate | 
@Tag | 

* with per_method @Before/After(All) methods need to be static
* with per_class @Before/After(All) can be non-static methods

### Test Hierarchies
* Non-static inner classes with @Nested annotation can be use to create a test hierarchy and express the relationship among several group of tests
* @Before/After (Each) will work fine in nested classes
* @Before/After(All) will not work for per_method (default) but will work fine with per_class in nested class {static method in inner class are not allowed}

### Assertions
Method | Params | Description
------ | ------ | -----------
assertAll | stringHeading , lambdas | if one assertion fails, it continue to evaluate others
assertThrows | exception, lambda | expects exception
assertTimeout | timeDuration, lambda | method under test completed within time, but complete method call. Run method in same thread
assertTimeoutPreemptively | timeDuration, lambda | terminate execution of method if time exceeds, run method in separate thread

### Assumptions
* Conditionally stop the execution of test
* Don't result in test failure (but test will be like disable)
1. assumeTrue stops the execution
1. assumeFalse stops the execution
1. assumingThat if condition is true execute the lambda expression else don't abort the execution of test

### Dynamic Test
* @Before/After (Each) are not executed for each dynamic test (run only once for whole factory)
* If one test fails other will continue
* @TestFactory can return Collection or Iterable or Stream or Iterator of type DynamicNode (which have sub-type DynamicTest and DynamicContainer)
* we have a DynamicTest.dynamicTest static method to create instance of DynamicTest which take display name and lambda (to execute) as parameters
* DynamicTest.stream static method can be use to generate stream of test which take inputGeneratorIterator, displayNameGeneratorFunction and testExecutorThowingConsumer as parameter
* DynamicContainer.dynamicContainer static method can use to create object. It take display name and stream of dynamic nodes. Help to nested test

### Parameterized Test
* Have support for life cycle methods
* Need junit-jupiter-params dependency
* provide at least one source of parameters
* parameters can't be injected into life cycle methods
* each source need to provide values for all parameters, means you can't have one source for 1st param and 2nd source for second param
* Arguments.of() can be use to create an instance of Arguments
* strings params can be convert implicitly to primitive types, enum or java.time, see documentation

### Remember
1. We can use test interfaces with default/static methods in junit5 due to java 8
1. Annotation on interface will change the behaviour of implementation class as well
1. TestInfo and TestReporter can be injected into life-cycle and test methods and after parameters in @ParameterizedTest
