# KatalonChapter2

Generate Custom Report with Extent Report

Used Extent Report : 4.0.9

Pre-Requisites
1. Download Gradle Build Tool from this link https://gradle.org/install/ and install.
2. Set path variable.  Setting this avoid specifying the complete path to run/complie gradle.
	GRADLE_HOME = C:\Gradle\gradle-5.4.1
	path = %GRADLE_HOME%\bin
 
2. Now add build.gradle under the project root folder
3. Add the dependencies 
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
4. If Katalon is running then close and open the CMD
5. Enter gradle katalonCopyDependencies. 
6. All the defined dependencies in build.gradle will be download from defined remote repositories into Gradle's local repository.
Default local repository location:  %UserProfile%/.gradle/caches/modules-2/files-2.1. You can also print the print the Gradle's local repository by adding the below line in build.gradle
task showMeCache << {
  configurations.compile.each { println it }
}
To change default local repository location add below task in build.gradle
task copyDepJars(type: Copy) {
  from configurations.compile
  into 'C:\\Gradle\\Dependencies'
}
7. Once download completed start the Katalon Studio. Check this link https://github.com/katalon-studio/gradle-plugin for more details.

