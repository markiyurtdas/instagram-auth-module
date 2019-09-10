# instagram-auth-module
Instagram authicentation module



--------------------------GRADLE WAY----------------------------------------------------
Gradle Way:

This way is the recommended by me. Too easy and comfortable.
1-) In the gradle(Project level) there is a code snippet  like: 
Then add "maven { url 'https://jitpack.io' }" line to en od repositories area. It should be like this:

![maven](https://user-images.githubusercontent.com/36734013/64606855-e4b0e500-d3cf-11e9-8627-3622755b2689.png)




2-) After adding maven repository then add a line to gradle(app level) dependencies. 
implementation 'com.github.markiyurtdas:instagram-auth-module:1.0.1'

It loke like this:

![dependencies](https://user-images.githubusercontent.com/36734013/64606934-1b86fb00-d3d0-11e9-9014-6b6edc27e488.png)


3-) Finally call startActivityForResult(new ıntent(YourActivity.this,InstaAccess.class),YOUR_REQUEST_CODE)
and override onActivityResult method like below:
![override](https://user-images.githubusercontent.com/36734013/64613509-f9e14000-d3de-11e9-81bb-411fc3a166ca.png)



--------------------------MODULE WAY----------------------------------------------------
Module Way:

1-) First install this project. Then import module called name instaaccess. 
For importing module to your project please follow this link: https://developer.android.com/studio/projects/add-app-module
and select instaaccess module to import your project.

2-) Then look at Mainactivity.java file there will be sample code to get access token.
