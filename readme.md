
### To run in command line

./gradlew clean
./gradlew test --tests "com.practice.repo.tests.DummyTest" --info
./gradlew allureServe

### To pass config at runtime
@PropertySource("classpath:${test.config}.properties") - SpringComponentConfiguration.java
-Dtest.config=default

