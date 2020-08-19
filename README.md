# ShaadiDotCom
Assignment task from Shaadi.com

## Architecture Overview
adapter - holds adapters for recycler view
application - holds application base class and app components
model - holds model pojo classes
repository - holds classes for data repository from network and DB
utils - holds String utils and connectivity utils
view - holds view classes of activity, fragment, view holder
viewholder - holds view model classes

## Used libraries
Glide - for network image loading
Retrofit - for Network Api calling
RoomDB - for DB

## Behaviour of app
As user launches the app, if it has network connection it calls network api and gets members list by 10 per page.
As user scrolls/swipes, more members will be loaded.
If network connection gets lost in between, new members list will not be loaded.
When network connection is available again, list will be loaded from where it was stopped.
User can perform Accept/Decline on members and the state is stored in DB.

## Limitations
Right now, when app gets relaunched with network connection, DB gets cleared.
