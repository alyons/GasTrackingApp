Gas Tracking App
============

A simple way to track your gas expenditure.

## Current Status

The app is currently unable to run. I am getting an error related to setting a child with a parent and needing to remove the existing child. The log can be found [here](https://github.com/alyons/GasTrackingApp/blob/master/Documentation/Error%2028-10-2015.md).

## Progress
 - [X] Add Trips to the app
 - [ ] Add Cars to the app
 - [ ] Track your gas mileage on a vehicle
 - [ ] Track mileages for different vehicles
 - [ ] Track mileages across multiple devices
 - [ ] Compare your mileage against the cars rating

## Why am I building this
I have wanted to track my gas and see how well I am driving... or more correctly, how efficiently am I driving. Too many other trackers are either too much or are not very well designed (aesthetically), so I figured I would through my hat in the ring. This project will hopefully be able to be expanded to iOS soon if all goes according to plan.

## Systems Used
[Graph View (for Android)](http://www.android-graphview.org/) - This is used to create the graphs within the app.

[Realm](http://realm.io/) - This is used for the back end so that things can be made simply and quickly. If I decide to make an iOS version of this app, this should speed up the process.

[Butter Knife](http://jakewharton.github.io/butterknife/) - This makes binding views to a class so much easier.

[Swipe Menu List View](https://github.com/baoyongzhang/SwipeMenuListView) - This makes it easy to implement swipe to delete.
