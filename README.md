screenSharer
============

Java application that takes screen shot, and uploads it to imgur. For making things easy there is perl script added, that will show notifications about what is going on. 

Steps of taking screenshot
--------------------------

1. Program takes screen shot and stores it as "tempScreen.png"
2. Another part of it starts uploading file to imgur
3. Finally script part adds link to clipboard, and shows notifications about what is happening.

![Test screen shot](http://i.imgur.com/sNLJkT8.png)

Assigning app to keyboard shortcut
----------------------------------

So here steps are easy, I am using Ubuntu 13.04 but that won't change a thing. Just follow steps.

1. So go to **System Settings** or whatever it is, and move on to **Keyboard** -> **Shortcuts**
![What it looks like](http://i.imgur.com/KtsdwYF.png)
2. After that add new *shortcut*, where put name whatever you want, and command to `bash -c 'cd directory/where/app/is/ && perl takeScreenShot.pl'`
3. Assign a shortcut to that, and you are ready to go!
