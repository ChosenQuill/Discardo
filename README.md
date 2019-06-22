# The Discardo Competition

The discardo competition is a competition based of a Java lab (aka mini-project) created by CompSci teacher Mr. Bunn and [Dave Feinberg](https://sites.google.com/site/feinbergcompsci/home/hcs1/labs/discardolab).

Discardo itself is a simple single player card game. The goal of the origional Java lab is for students to have fun creating a simple to make yet thought-provoking to beat game. While its enjoyable for students to create their own game, students can also compete to see who can make the most efficient java discardo bot that can beat the game in the least amount of turns. This is the purpose of this repository/project. To make a fun and enjoyable competition while streamlining the process of providing information about the competition, submitting code, and viewing who is on top to a single multipage site.

While this repository/project is made primarily for Java teachers who want to provide their students with a fun competition, anyone can use this guide to host their own competition.

## How to host a competition
First, head over to the [releases page](https://github.com/SureDroid/Discardo/releases) and download both the backend jar file and web-files zip folder. The Jar file is the backend that processes all the code submissions and stores leaderboard information. The front-end is the website and is what everyone sees. The nice part is that we can host the jar and website on two seperate server, making it way easier to host (you don't have to setup things yourself).

### First, hosting the jar. 
You want to make sure that you have java 11 or higher installed. You can test this by using ``java -version``. Then you want to start the jar by running ``java -jar discardo-back-end-x.jar`` in a command prompt window, and replace the x with the current version number. I should mention that if you close the window or stop the java application, the leaderboard is completely reset and there is no way to restore it. 

Now that you got have it running, the guide now splits into two lanes. The first being if you want to host it only on your wifi network, and the other being if you want it to be accessable from anywhere. I reccomend to set it up to be accessable from everywhere, but it will require an extra step.

If you are making it accessable from outside your local network, the next step is to port forward your machine. This requires some finicking with your router and I can't guide you through this process. The best way if you don't know how to is search up your model number + how to port forward on google. The port you will be forwarding is port 5000. Once you are done [search up "ip" on google](https://www.google.com/search?q=ip) and write down the number google gives you.

If you don't want it accessable outside your network, you just need to grab your ipv4 address. You can do that by opening a command prompt and typing ipconfig. Find ipv4 and write that number down.

### Now on to the website.
There are a number of ways that you can host a website, but I will be explaining the easiest way. There is a drawback to this method (and I will tell you later), but it should simplify a lot of the steps of hosting a website.

The first thing you want to do is click on the fork button on the top of this page.

![Fork Button](https://image.prntscr.com/image/bfh_MmDPRxKRowxCkKmm-Q.png)

Then, you want to open the gh pages branch.

![Gh-Pages Branch](https://image.prntscr.com/image/nbxpa2pgTc_gmI5zFUNrQw.png)

Then, click and open the Config.js file and click on the tiny pencil (edit button) on the right of the page.

Now replace localhost with the number that you wrote down earlier. Make sure you have the protocol (http://), port 5000 (:5000), and the ending slash (/). Click Commit Changes.

Now you want to go to settings, which is right below the fork button and has a gear cog icon, and scroll down to github pages.
Then change the Source from None, to Gh-Pages.

![GH-Pages Settings](https://image.prntscr.com/image/IagrG1ZJTS_wzlBaCq4A2w.png)

Now you should be set. The site should be published on the link provided.

#### The Main Drawback
Now I mentioned that there is an issue with this method earlier, and here it is.
You cannot give links to subfolders (pages other than the root page). What I mean by that is putting you.github.io/discardo/leaderboard in your url bar or sending that link to someone else won't work. You have to go to you.github.io/discardo (the root site), then click on the leaderboard button to go to the leaderboard, and only then you can view the leaderboard. This is due to the way that github directs queries to access files. To avoid this issue, I used a custom nginx config file which you can find (here)[https://github.com/SureDroid/Discardo/blob/master/discardo.conf]. 
