In order to run the app you must place a passwords.txt, file in the following 
location of the phone:
/data/data/csc207.flightapp/files
 
The passwords.txt must be in the following format: 
EMAIL,PASSWORD,A/C
A - represents an administrator 
C - Represents a client

Once you have started the app, enter one of the emails, and passwords from the 
passwords.txt inorder to login. Once you have logged in, you will be shown 
a screen based on what you logged in as.

Admins will be given a screen that allows them to select what task the user 
wishs to do. Users can view and add flights. In addition admins can also edit 
client information by providing the client's email. In order to add flights or 
client info, the user must be a CSV file located in the following directory: 
/data/data/csc207.flightapp/files

Clients will be given a screen that allows them to quicky search flights and 
book itineraries. By providing the origin, destination, date of deprature in 
the format YYYY-MM-DD, they can search for itineraries or flights. In addition 
the search can be sort by duration or cost, by pressing on their respstive tabs 
before the search. When searching for itineraries clients can press the view 
button to view the list of individual flights in the itinerary. Once the user 
has found the itinerary that they want to book, they can press the book button 
located beside the view button. 
