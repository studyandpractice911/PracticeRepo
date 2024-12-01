
### To run testng test in command line follow below steps

1) `./gradlew clean` (optional step)
2) `./gradlew test "-Dproperty.filename=default" --tests "com.practice.repo.tests.DummyTest.testA" --info`

### To run cucumber feature file in command line follow below steps

1) `./gradlew clean` (optional step)
2) `./gradlew cucumber "-Dproperty.filename=default" "-Dcucumber.filter.tags=@UI" --info`

### To run testNg test in IDE follow below steps

1) Run _clean_ task (optional step)
2) Define property file name in _[SpringComponentConfiguration.java](src/main/java/com/practice/repo/SpringComponentConfiguration.java)_
3) Run your test class [DummyTest.java](src/test/java/com/practice/repo/tests/DummyTest.java)

### To run cucumber feature file in IDE follow below steps

1) Run _clean_ task (optional step)
2) Define property file name in _[SpringComponentConfiguration.java](src/main/java/com/practice/repo/SpringComponentConfiguration.java)_
3) Run your feature file [DummyTest.java](src/test/resources/features/DummyTest.feature)
