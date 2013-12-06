AIROUYAIAP
=================

DEPRECATED!  Use the Facade Extension instead: https://github.com/gaslightgames/AIROUYAFacade

AIROUYAIAP is an unofficial Native Extension for Adobe AIR designed to expose the functionalities of the OUYA IAP (In-App Purchases) to ActionScript 3.

Updated to ODK 1.0.5 and currently being used in several OUYA titles!

Notes
================

You will need to register your App on OUYAs servers in order to be able to download a key.der file.  In the demo project, you will need to put this in your bin/ directory.
<BR>Your package name in your Manifest.xml file might be something like this:
- com.yourdomain.appname
<BR>BUT when you register on OUYA, you MUST use a format like this:
- air.com.yourdomain.appname
<BR>If you don't, then you'll receive the 2005 Error that the Application UUID is invalid (Check DDMS in case you aren't getting any purchase responses in AIR Console)

<P>Be careful when using the APK in the test application!  This has actual purchasing information with our developer id and application key, etc.  If you make a purchase in this application you may inadvertantly buy me a beer - as in, YOU MAY BE CHARGED!
<BR>Instead, use the project, enter your developer ID, register your app and generate a key.der, download and add to your bin/ directory and THEN deploy/run the application.  At this point you'll be using YOUR developer credentials and wont be charged.
<BR>YE HAVE BEEN WARRRRNED!

Folder Structure
================

There are 4 folders within the project:
<BR><B>1) AIROUYAIAPANEJava</B>
<BR>The Native Java code.
<BR><B>2) AIROUYAIAPANELib</B>
<BR>The ActionScript 3 Interface code.  Generates a SWC.
<BR><B>3) AIROUYAIAPANETest</B>
<BR>A simple application that implements the ANE.  This performs your initialization, makes various requests and traces the results.
<BR><B>4) AIROUYAIAPANEDefaultLib</B>
<BR>The default implementation that allows the ANE to be attached to any platforms project and still compile.

Current Progress
================
<BR<Update to ODK 1.0.5 (Complete!)
<BR>Update to ODK 1.0.3 (Complete!)
<BR>Initialization works (Using Developers UUID and Application Key)
<BR>Requests Product Info
<BR>Requests Receipts (Broken! Currently updating)
<BR>Requests Gamer UUID (NB: These are unique for each gamer, on each game - even if the game is by the same developer!)
<BR>Make a Purchase Request (Working with new ODK)
<BR>Put the Purchase into Test Mode (Use new method - send boolean in first call to getInstance)

To-Do
=====

* Update external class files added to JAR, to avoid collisions with Controller ANE [DONE]
* Add "Testing" Boolean (Affects new way of signalling testing in ODK)
* Update to ODK 1.0.3 [DONE]
* Send Application Key [DONE]
* Support Encrypted Purchase Requests [DONE]
* Support Encrypted Purchase Responses [DONE]
* Support Encrypted Receipt Requests [DONE]
* Support Encrypted Receipt Responses [DONE]
* Create and issue events in AS3 [DONE]
* Parse results from Java into usable AS3 classes [DONE]
* Pass results to AS3 [DONE]
* Parse results in Java [DONE]
* Creates classes for and Initialize:
** Product Request Listener [DONE]
** Receipt Request Listener (Currently only supports the JSON receipt parser, as Encrypted receipts are not yet available) [DONE]
** Gamer UUID Request Listener [DONE]
** Make Purchase Request Listener [DONE]
* Attempt initialization with OUYA Servers (in Java) [DONE]
* Send initialize details (Developer UUID) from AS3 to Java [DONE]
* Exctract .class files from ODK and embed into ANE (at JAR level) [DONE]
* Include OUYA Library (ODK) [DONE]
* Build ANE Projects (Java; AS3 Library; Demo Project) [DONE]

License
================

The code, packages and anything in this project are provided "as is".  You are free to do what you like, modify, update etc - if you like, send a pull/push request if you have any fixes or suggestions.
And if in doubt, follow Whetons Law: http://knowyourmeme.com/memes/wheatons-law