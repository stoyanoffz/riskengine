Resources:

    https://dev.to/nziokidennis/choosing-the-right-database-for-your-project-1a2a
    https://www.confluent.io/blog/real-time-risk-management-with-kafka-and-event-streaming/?form=MG0AV3
    https://stackoverflow.com/questions/5279711/guidelines-for-implementing-a-rule-engine
    https://stackoverflow.com/questions/250403/rules-engine-pros-and-cons
    https://oscilar.com/blog/drools-business-rules-engine?form=MG0AV3
    https://www.oracle.com/technical-resources/articles/javase/javarule.html
    https://www.baeldung.com/java-rule-engines
    https://github.com/j-easy/easy-rules

Run the application in Docker:
    
    Open project in an IDE and execute gradle clean build. Open terminal and navigate to the directory of 
    the application: 
        cd path/to/project

    Execute the following command:
        docker compose up

To execute gatling tests (load tests):

    run the application from IDE (sorry, but couldnt figure out how to make it work from the container),
    execute following command "gradle gatling"

    After the test is done, you can navigate to build/reports/gatling and find the latest report. Inside, you can open
    one of the generated html reports and check the metrics from the test.

    You can change the configuration here:
    com.riskengine.service.impl.FraudulentTransactionServiceImpl -> constantUsersPerSec(100).during(60)

    constantUsersPerSec: how many users per second will make a request to the server
    during: duration of the test

    I strongly recommend to comment out fraudService.save(fraudulentTransactionEntity); in 
    com.riskengine.service.impl.FraudulentTransactionServiceImpl.isFraudulentTransaction 
    because the test will be next to pointless. I could not find a way to generate unique id for every request.

    If you want to see the server go on fire, change constantUsersPerSec to 500, then the real show begins.

Initial Data
    
    There is a script for adding few countries to the blacklisted countries. Feel free to modify it if needed.
    Also, there is a script for adding some initial transactions. You can change the loop size for gatling to have a 
    more realistic database, but keep in mind the real show i was talking about
    
