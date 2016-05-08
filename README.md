# Data Formt APIs and Examples

## Date Formatter
Reformat date format   
20th Oct 2052  
6th Jun 1933  
26th May 1960  
20th Sep 1958  
16th Mar 2068  
25th May 1912  
16th Dec 2018  
26th Dec 2061  
4th Nov 2030  
28th Jul 1963  

change to   
2052-10-20  
1933-06-06  
1960-05-26  
1958-09-20  
2068-03-16  
1912-05-25  
2018-12-16  
2061-12-26  
2030-11-04  
1963-07-28  



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
