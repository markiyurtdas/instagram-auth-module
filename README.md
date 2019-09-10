# instagram-auth-module
Instagram authicentation module



--------------------------GRADLE WAY----------------------------------------------------
Gradle Way:
This way is the recommended by me. Too easy and comfortable.
1-) In the gradle(Project level) there is a code snippet  like: 

allprojects {
    repositories {
        google()
        jcenter()
    }
}

Then add "maven { url 'https://jitpack.io' }" line to en od repositories area. It should be like this:

allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}

2-) After adding maven repository then add a line to gradle(app level) dependencies. 
implementation 'com.github.markiyurtdas:instagram-auth-module:1.0.1'

It loke like this:
dependencies {
    ...
    ...
    ...
    ...
    
    implementation 'com.github.markiyurtdas:instagram-auth-module:1.0.1'
}




--------------------------MODULE WAY----------------------------------------------------
Module Way:

1-) First install this project. Then import module called name instaaccess. 
For importing module to your project please follow this link: https://developer.android.com/studio/projects/add-app-module
and select instaaccess module to import your project.

2-) Then in Mainactivity.java file there will be sample code to get access token.
