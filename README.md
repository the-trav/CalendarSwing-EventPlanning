# CalendarSwing-EventPlanning
#Introduction
This application is an event based calendar and a reminder of those events. The application is able to keep track of everything you do in life. Whether you want to stay on top of your bills, be reminded of your family members birthday and/or don't want to forget about your next test. Then this self contained Java Application is for you! <b>(No Internet access required)</b>

#Why use this Calendar?
A person who chooses to use this application might agree with the following points
<ul>
<li>I use a event based calendar reminder to automate my daily reoccuring tasks such as but not limited to: paying  bills, staying on top of my readings, birthday reminders, appointments.</li>
<li>I do not want my application being accessed via the internet/server</li>
<li>I would rather have a stand alone calendar application that is able to run on micro-controlers such as all models of the infamous <b>Raspberry Pi & Audrino</b></li>
<li>Platform independent, therefore if you are on any distribution of Linux, Windows, Mac, Solaris </li>
<li>If you prefer open-source projects to which you're able to fully re-design/engineer the application</li>
</ul>

#How to use
Luckily for you this application runs on Java which runs on 90%+ of modern day computers.
<br>However if you do not have Java installed on your operating system please visit this website 
http://www.oracle.com/technetwork/java/javase/downloads/index.html
<br>
<b>IMPORTANT IF YOU WANT TEXT REMINDERS</b><br>
You will need to edit a few locations
<br><br>
1st) inside of src/mypi/service/SendText.java<br>
-edit the variable EMAIL<br>
-edit the variable EMAIL_PASSWORD<br>

2nd) inside of src/mypi/service/SendTextOnDayOfEvent.java<br>
-Edit the variable VERIZON_NUMBER <br>
Important to take a look at this website http://www.emailtextmessages.com/<br>
This website will provide your @ extension to add after your 10 digit phone number<br>
For example<br>
if your carrier is sprint your extenstion would be your10digitnumber@messaging.sprintpcs.com<br>
if your carrier is ATT your extension would be your10digitnumber@mobile.att.net<br>
if your carrier is verizon your extension would be your10digitnumber@vtext.com<br>
