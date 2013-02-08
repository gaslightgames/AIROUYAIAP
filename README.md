AIROUYAIAP
=================

AIROUYAIAP is an unofficial Native Extension for Adobe AIR designed to expose the functionalities of the OUYA IAP (In-App Purchases) to ActionScript 3.

This Extension is in HEAVY development, is constantly being updated and modified.  It is not exactly "stable" nor ready for commercial use.  Use with caution and be aware of problems, changes etc!

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

<BR>Initialization works (Using Developers UUID)
<BR>Requests Product Info
<BR>Requests Receipts
<BR>Requests Gamer UUID (NB: These are unique for each gamer, on each game - even if the game is by the same developer!)
<BR>Make a Purchase Request
<BR>Put the Purchase into Test Mode

To-Do
=====

* Build ANE Projects (Java; AS3 Library; Demo Project) [DONE]
* Include OUYA Library (ODK) [DONE]
* Exctract .class files from ODK and embed into ANE (at JAR level) [DONE]
* Send initialize details (Developer UUID) from AS3 to Java [DONE]
* Attempt initialization with OUYA Servers (in Java) [DONE]
* Creates classes for and Initialize:
** Product Request Listener [DONE]
** Receipt Request Listener (Currently only supports the JSON receipt parser, as Encrypted receipts are not yet available) [DONE]
** Gamer UUID Request Listener [DONE]
** Make Purchase Request Listener [DONE]
* Parse results in Java [DONE]
* Pass results to AS3 [DONE]
* Parse results from Java into usable AS3 classes [DONE]
* Create and issue events in AS3 [DONE]
* Support encrypted receipts [TBC]