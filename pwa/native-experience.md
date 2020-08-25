experiments shows use don't consider technology but following three to tell is it native or PWA

# User interface
.inoic or  mdc (matrial design webcomponents) have goals to provide native interfaces

# User experience & performance
## design & build an app shell
  * content flows inside of the app shell
## build an advancee app shell
  * navigation bar (below of screen) for main pages
  * transform/opacity for animations
  * avoid expensive work on main thread while animating
  * thest on serveral devices
## Reduce javascript footprint
  * use js module and nomodule attributes
  * don't bundule all code in single code instead use code spliting
    * show loader while loading js
    * after 3 sec change msg from loading to still loading (user know app is working)
    * something failed handle it properly (catch)
## React with fast interactions
  * every action needs a reaction - 90 milisecond
  * request idle callback (api)
    * requires a polyfill for safari
    * measure before/after using it
    * defer non-critical work
    * 5 - 15 milli seconds
    * defer small work of chenks
    * browser will render it after frame load
 ## Make it work offline
  * network & fallback to cache (liewife 5 second timeout)
  * be mindful of storage (alwasy set cache expire)
## Increase "Add to Homescreen" conversion
  * delay untill user check some feature so you know user like it
  * send accept/reject results to analytics service to check what users are doing etc
# Reliability
* Build a smooth updates process
