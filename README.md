# Data Formt APIs and Examples


## Json Parsing
Using Javax  to parse json log files


Java Code Examples for javax.json.JsonReader

The following are my examples for showing how to use javax.json.JsonReader. 

These examples will be used in our system to product more good examples. 

## Currency type to use for Money
 
[what is the best Java data type to use for currency?](http://stackoverflow.com/questions/8148684/what-is-the-best-data-type-to-use-for-money-in-java-app)

You can use Money and Currency API (JSR 354). This API is expected to be part of Java 9.
You can use this API in Java 7 and Java 8, provided you add appropriate dependencies to your project.
```xml
<dependency>
    <groupId>org.javamoney</groupId>
    <artifactId>moneta</artifactId>
    <version>1.0</version>
</dependency>
```
