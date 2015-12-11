
######## LOGGING IN ############

In order to run the app you must place a passwords.txt file in the following
location of the phone:

/data/data/csc207.flightapp/files

The passwords.txt must be in the following format per line

EMAIL,PASSWORD,FLAG

where FLAG is "A" (for administrator) or "C" (for client)

a correct passwords.txt file has been provided at the root of the pIII
folder, you may place it in the phone if you desire

Once you have started the app, enter one of the emails, and passwords from the
passwords.txt in order to login. Once you have logged in, you will be shown
a screen based on what you logged in as.

Admins will be given a screen that allows them to select what task the user
wishes to do. Users can view and add flights. In addition admins can also edit
client information by providing the client's email.

##### Adding CSV information #######

In order to add flights or client from CSV, you must put the CSV file in the
following directory of the phone:
/data/data/csc207.flightapp/files

And then login as an Admin and choose the Upload button and specify in
csv filename in the app

####### GENERAL USAGE ###########
Clients will be given a screen that allows them to quicky search flights and
book itineraries. By providing the origin, destination, date of deprature
in the format YYYY-MM-DD, they can search for itineraries or flights. When
searching for itineraries clients can press the view button to view the list of
individual flights in the itinerary. Once the user has found the itinerary that
they want to book, they can press the book button located beside the view
button.
