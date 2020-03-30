# Design Pattern Code Generator

## Description:
This is an object-oriented design and implementation of a Design Pattern Code Generator. 
This program essentially generates the skeleton code for the supported software designs. 
For non-trivial designs the user is allow to enhance the design by adding what they believe 
will be necessary for their use of the design. The user is prompt for modifications if any.
All the code will be imported into a name specified package the src/main content folder 
(containing the code to be compiled for the project). 


## Prerequisites:
You will need to have the gradle plugin in your intellij environment.


## Getting Started:
You will be able to get this project up and running by importing it into intellij as a gradle 
project. If you do not have intellij install, you will still be able to run this project with 
the appropriate gradle commands.


## Gradle Dependencies:
dependencies {

testCompile group: 'junit', name: 'junit', version: '4.12'                                   //junit testing
compile group: 'org.slf4j', name: 'slf4j-api', version: '1.7+'                               //slf4j logback
compile group: 'ch.qos.logback', name: 'logback-classic', version: '1.+'                     //slf4j logback
compile group: 'com.typesafe', name: 'config', version: '1.0.2'                              //slf4j logback
compile group: 'com.github.javaparser',name:'javaparser-symbol-solver-core',version:'3.13.3' //javaparser
}


## JavaParser Library
The JavaParser library provides you with an Abstract Syntax Tree of your Java code. However, 
there are many uses of the library to generate new code during runtime. In this project it is 
done so. A new Abstract Syntax Tree is created during runtime, that throughout execution generation 
the software design pattern selected.


## Preventing clashes
Before the program generates the code, it creates an the excepted directory (The package in a source location). 
This is done to check if there already exist a package with the same name. The program also checks for proper
class names and method declarations. 


