Gas Tracking App
============

A simple way to track your gas expenditure.

## Current Status

The app is functioning and even has some graphing implemented. I am currently working on switching between cars and, by extension, cleaning up and perhaps even redesigning the UI/UX. I honestly believe this will be the most important part of this system to get correct, as without this, there will be no point to what I am doing.

## Version 1
 - [X] Add Trips to the app
 - [X] Add Cars to the app
 - [X] Track your gas mileage on a vehicle
 - [ ] Track mileages for different vehicles
 - [ ] Track mileages across multiple devices
 - [ ] Compare your mileage against the cars rating
 - [ ] Add monetization scheme
 
## Version 2
 - [ ] Allow users to track across multiple devices

## Version 3
 - [ ] Give Trips different tags
 - [ ] Generate 'Reports' based on Trips & Tags
 - [ ] Generate Data based on user data

## Before Public Release
 - [ ] Create LLC
 - [ ] Create Website
 - [ ] Secure Code

## Why am I building this
I have wanted to track my gas and see how well I am driving... or more correctly, how efficiently am I driving. Too many other trackers are either too much or are not very well designed (aesthetically), so I figured I would through my hat in the ring. This project will hopefully be able to be expanded to iOS soon if all goes according to plan.

## Systems Used
[Graph View (for Android)](http://www.android-graphview.org/) - This is used to create the graphs within the app.

[Realm](http://realm.io/) - This is used for the back end so that things can be made simply and quickly. If I decide to make an iOS version of this app, this should speed up the process.

[Butter Knife](http://jakewharton.github.io/butterknife/) - This makes binding views to a class so much easier.

[Realm RecyclerView](https://github.com/thorbenprimke/realm-recyclerview) - This makes it super easy to use realm with list style displays.

~~[Swipe Menu List View](https://github.com/baoyongzhang/SwipeMenuListView) - This makes it easy to implement swipe to delete.~~  
*Removed to use Realm RecyclerView*
