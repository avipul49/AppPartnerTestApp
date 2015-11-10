=================
Details
=================

Header Text (Chat, Login, Animation) font is supposed to be Machinato Extra Light. However, page header text in Mockups have different font. I have applied Machinato Extra Light with 22sp size.

Main Page
===========
Size of CODING TASKS text is 24sp.

Login Page
===========
Login placeholder colour is supposed to be #ffffff(White). However, as the background is also white text won’t be visible. Therefore, I used #000000(Black).
I have added a loading indicator on the right corner of toolbar.
There is a toast message displayed in case of any connectivity error.

Animation Page
===============
The font size for Animation font is set to 16sp and for Animation Bonus its 19sp. According to requirement it is supposed to be 36pt. Also the font mentioned requirement is Machinato Semi Bold Italic but there is a different font in the mockups. I used Machinato Semi Bold Italic.

App Partner Logo fades from 100% to 0% when activity is launched. Fade button can be used to bring the icon back from 0% alpha to 100% alpha. Fade button acts as toggle and would make the icon appear and disappear.

The logo can be dragged on the screen at any place by using one finger touch. 

***Extra Feature
By using two finger the logo can be rotated clockwise or anticlockwise.

By double tapping at any place on the screen logo can be restored to its original position. 


Chat Page
==========
Made use of Universal Image loader for lazy loading of images.


================================================================
Libraries Used
================================================================
1. Used Retrofit from Square for http communication.
Details can be fount at: http://square.github.io/retrofit/
This library allows us to use annotation in service interface for different end points and their request and response. 

2. Two finger rotation: Made use of code mention on the following stack overflow post:
http://stackoverflow.com/questions/10682019/android-two-finger-rotation

3. Dragging image: Followed following tutorial for implementing drag feature:
http://code.tutsplus.com/tutorials/android-gesture--mobile-2239



