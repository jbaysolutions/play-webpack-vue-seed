# Play Framework seed project with webpack and vue.js

## Overview

This is a seed project using Play and webpack to build the fronted with vue.js.
When activator is launched, an instance of webpack is also started. webpack will watch for changes in the fronted js/vue files and bundle them on a single javascript file placed in the public/javascripts folder of the play app.

When running `activator dist`, webpack will minify and uglify the javascript file.


## Requirements

* JDK >= 1.8
* [Play! Framework 2.5.4](http://www.playframework.com/)
* [Node.js](https://nodejs.org/en/)


## Setup

    npm install
    activator run

Open browser on http://localhost:9000