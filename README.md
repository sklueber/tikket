![custom logo](app/src/main/res/drawable/grosseslogo.png)

# tikket
tikket (working title). An event ticketing system  (and school project) by Simon Klüber and Max Stockhausen 

# Features

_tikket. (working title)_ allows the user to generate, manage and check tickets for events using a SQLite database stored on a local server.
Every ticket has an individual randomly generated ID represented by a barcode to prevent fraud. Tickets may be scanned using the Android app _tikket. Scan_ or with an external scanning device connected to a Windows PC. 

* Generating in presale and right at the box office
* checking and invalidating tickets via Android and Windows
* e-tickets sent by email
* printing tickets
* database management of multiple events
* overviewing attendance at a glance



# Using _tikket._ Scan
1. enter server IP
2. toggle IP editing mode
3. start client
4. Scan!

# Using _tikket._ Client
1. run ClientStarten.java
2. enter server IP and port
3. Client ready for work

# Using _tikket._ Server
1. run ServerStarten.java
2. enter port to use
3. _tikket._ Server is up and running

# Open Source Licenses
* Java Database Connectivity (JDBC): https://www.apache.org/licenses/LICENSE-2.0.txt
* ZXing ("Zebra Crossing"): https://www.apache.org/licenses/LICENSE-2.0.txt
* Java Mail API: https://oss.oracle.com/licenses/CDDL+GPL-1.1
* Java SE: https://javaee.github.io/glassfish/LICENSE
* Logo created using https://materialdesingicons.com and _Oswald_ ([Open Font License](http://scripts.sil.org/cms/scripts/page.php?site_id=nrsi&id=OFL_web))
