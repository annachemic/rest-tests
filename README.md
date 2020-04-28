# rest-tests

There is a tiny framework with autotests for https://reqres.in/ service.

Used technologies:
- Java 11 (might be executed with java 8)
- TestNG as testing framework
- Retrofit 2.0 as HTTP client and API testing framework
- Gson as json converter
- Hamcrest lib for assertions
- Slf4j + logback for logging
- Surefire plugin for short test reports

### Prerequisites
- Lombok plugin required
File | Settings | Plugins | Marketplace | find "lombok" | install

- Annotation processing required (check option): <br>
File | Settings | Build, Execution, Deployment | Compiler | Annotation Processors |Enable annotation processing

## Running the tests  
  - execute ```maven clean test``` or any latest goal
  
## Reporting
You can view a short report after tests execution via Surefire.
Destination: target -> surefire-reports.

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.