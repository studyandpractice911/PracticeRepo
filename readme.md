
### To run test in command line follow below steps

1) `./gradlew clean` (optional step)
2) `./gradlew "-Dproperty.filename=default" test --tests "com.practice.repo.tests.DummyTest" --info`

### To run test in IDE follow below steps

1) Run _clean_ task (optional step)
2) Define property file name in _[build.gradle](build.gradle)_
3) Run your test class [DummyTest.java](src/test/java/com/practice/repo/tests/DummyTest.java)
