### Pre-requirements
1. Have installed browser, e.g. Chrome
2. Have installed Java 11
    * If error when installing dependencies then remove current java version and install again.
    * Suggested source Amazon Correto: https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html
3. Have installed IntelliJ IDE:  https://www.jetbrains.com/idea/download/#section=windows
4. Configure Java 11 from IntelliJ IDE:
    * File > Project Structure > Language Level  

### How to Run
1. Clone repo:   
```git clone https://github.com/jccolina/unosquare-qa.git```
2. Open project with IntelliJ  
3. Right click on pom.xml > Maven > Reload Project (will download dependencies)
4. Right click on testng.xml file (in root) and then ``Run ..\testng.xml``
5. Once all tests are executed you'll find results in ``reports/cucumber-html-reports``  
Open ``overview-features.html`` to see html report.

## Web UI
### Frameworks/Libraries
1. Selenium  4.4.0
2. Java 11
3. Cucumber 7.4.0
4. Test NG 7.4.0
6. cucumber-reporting-plugin 7.3.0
7. webdrivermanager 4.4.3
8. json-simple 1.1.1
9. json-path 2.6.0

### Structure
1. Cucumber ui tests in ``src/test/resources/scenarios/searchValidation``  
2. Step definitions for cucumber in ``src/test/java/cucumber/steps``  
3. Runner specifying scenarios folder and plugin report in ``src/test/java/cucumber/runner``  
4. Page objects for webui in ``src/main/java/core/ui/pages``  
5. Screenshot taken when test failed  
6. Driver Manager in ``src/main/java/core/ui/driver`` create webdriver and make sure to have only a driver during whole execution. 
If tests are executed in parallel implemented thread safe to not mix webdrivers.
7. WebDriverManager will automatically download the required driver for current browser so no need to do it manually.  
8. To change browser modify the file environment.json > "browser": "chrome"  
   Values allowed (non case sensitive):  
   * CHROME,
   * FIREFOX
    
## API
### Frameworks/Libraries
1. rest-assured 5.1.1
2. Java 11
3. Cucumber 7.4.0
4. Test NG 7.4.0
5. cucumber-reporting-plugin 7.3.0

### Structure
1. Cucumber api tests in ``src/test/resources/scenarios/apiTest``
2. Step definitions for cucumber in ``src/test/java/cucumber/steps/ApiTests``
3. Runner specifying scenarios folder and plugin report in ``src/test/java/cucumber/runner``
4. Schema validation implemented for API response, schemas are stored in ``src/test/resourcs/schemas``
5. API request handler in ``src/main/java/core/api/RequestManager``
### Additional features
1. Parallel execution is enabled in testng runner
2. Retry execution on failure is enabled through testng listeners ``Retry`` and ``AnnotationTransformer``
3. ``Environment.json`` stores data test like search keyword, baserUrl and browser type.
   Each time the tests run the file automatically loads by ``EnvReader`` class and all value are available since is a singleton object accessible from other objects.  
4. ``testng.xml`` will call runner sending the parameter for the amount of tests in parallel threadcount.  