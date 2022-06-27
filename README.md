1. To change properties (browser, local or remote run) edit config.properties
2. To run SeleniumGrid go to your SeleniumGrid location and run command 
-jar selenium-server-{version}.jar hub and -jar selenium-server-{version}.jar node
3. To generate test-task allure report run command: mvn clean test allure:report.
4. To open report go to path test-task/target/site/index.html
5. Some tests give different results in Edge and Chrome browsers. To tests valid both for chrome and edge browsers 
use command: mvn clean test -Dgroups=chromeAndEdgeTests allure:report

