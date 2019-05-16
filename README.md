# Pravega
Pravega is high speed in Sanskrit and is a Kotlin library intended to help you develop Android Applications easier and faster. 

## Design Considerations
Certain patterns like reactive and functional programming are chosen over others as a matter of preference and my understanding that they work better. I'm not claiming superiority over other approaches.
<br/>

I am learning Reactive programming with RxJava and this library will use Rx where deemed appropriate. This also prefers the Java 8 Streams approach to development instead of conventional imperative programming. Streams(for Sync) and Rx(for Async) are approaches which increase code readability as well as functionality.
<br/>
I will include some libraries that solve common problems in the Android Framework and are widely used by the community like (Rx), Glide, IcePick, etc.
<br/>
This library will also be integrated with Firebase because almost any non-trivial app today needs a backend and Firebase is widely supported. This also aims to include Heroku in the library, as it has additional features offering what Firebase doesn't or can't.
<br/>

This library will also have an App Creator which will create the neccessary Scaffold for a new App. It will have 
 <br/>
 
 /app (Android) 
 <br/>
 
 /website (React, Firebase Hosting) 
 <br/>
 
 /functions ( Firebase Cloud Functions )
 <br/>
 
 /server (Express, Heroku)
 <br/>
 
 /AI (Flask, Heroku, Scikit-Learn) 
 <br/>
 
 /docs (Jekyll, GithubPages) 
 <br/>

I used this wonderfully simple article to publish this library
<br/>
https://medium.com/@anujguptawork/how-to-create-your-own-android-library-and-publish-it-750e0f7481bf
