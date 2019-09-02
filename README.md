# Custom Report with Extent Report

Used Extent Report : 4.0.9

Pre-requisites:

1. Download Gradle Build Tool from this link https://gradle.org/install/ and install.
2. Set path variable.  Setting this avoid specifying the complete path to run/complie gradle.
	```
	GRADLE_HOME = C:\Gradle\gradle-5.4.1
	path = %GRADLE_HOME%\bin
	```
 
3. Now add <b>build.gradle</b> under the project root folder
4. Add the dependencies 
	```
	plugins {
	  id 'java'
	  id "com.katalon.gradle-plugin" version "0.0.7"
	}
	repositories {
	  mavenCentral()
	}
	dependencies {
	// https://mvnrepository.com/artifact/com.aventstack/extentreports
	compile group: 'com.aventstack', name: 'extentreports', version: '4.0.9'
	}
	```
	
5. If Katalon is running then close and open the cmd.
6. Enter command <b>gradle katalonCopyDependencies</b> 
<ul> 
7. All the defined dependencies in build.gradle will be download from defined remote repositories into Gradle's local repository.
<li>
 Default local repository location:  </b>%UserProfile%/.gradle/caches/modules-2/files-2.1</b>. You can also print the print the Gradle's local repository by adding the below line in <b>build.gradle</b></li>
	```
	task showMeCache << {
  		configurations.compile.each { println it }
	}
	```
<li> To change default local repository location add below task in <b>build.gradle</b></li>
	```
	task copyDepJars(type: Copy) {
  	from configurations.compile
 	 into 'C:\\Gradle\\Dependencies'
	}
	```
</ul> 
8. Once download completed start the Katalon Studio. Check this link https://github.com/katalon-studio/gradle-plugin for more details.