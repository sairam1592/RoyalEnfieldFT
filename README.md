# RoyalEnfieldFT
An Android app to view about Royal Enfield and calculate total amount &amp; fuel required to travel from one place to another.

“Royal Enfield Fuel Tracker” purpose is to calculate liter & price needed to travel a certain distance based on the user input. 
Google maps api’s used to provide maps info based on To/From entered, SQLite for performing database operations.
         
Technology used

•	Java in Android
•	SqLite Database for Android Client.
•	Intents – to move from one activity to another in android
•	Fragments – Supporting multiple screen, effective UI inside activity. 
•	XML to create user interface using layouts
•	Viewpager for swipe views, ViewFlipper for auto swipe based on handler.
•	Listview to display details.
•	Navigation drawer for effective UI
•	Google maps distance matrix API to fetch distance,duration, from, to details.
•	Google maps nearby places search API to fetch the nearby gas station based on current user location and range (in Kms) specified by user.
•	Json parsing and retrieving needed details from Google Maps API result. 

Project is currently under Alpha testing in Google Play Store, please find the link below for testing the same:
https://play.google.com/apps/testing/com.reft.admin.royalenfield

Future additions to the project: 
A server architecture to support GCM for frequent notification updates and save the notifications in client end SqLite DB and populate in Listview for user viewing.

PS: Interested people are welcome to contribute to expand the Apps scope.
Also budding developers can refer this app to get a basic understanding of Android architecture and concepts.

In case of feedback,other details, reach me at sairam.a1592@gmail.com
