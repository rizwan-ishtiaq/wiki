# Mainfest
ios/ipados will only load mainfest file upon install not on brower open - if user offline that time will be problem. Preload assets to reduce install issues. Only chrome can see the changes after install. user need to reinstall app

## name
* if both name/short_name provided (short for icons and long for other places)
* Only name? it will be use all places

## theme color
* backup theme color if it is not defined in "theme-color" html meta tag
* how app look like in operating system
* title color (mac os)
* status bar color (android 5+ os)
* ios/ipados not supported (can use one of white|black|black-translucent). black-translucent is quite dynamic. Use it carefully

## Display Options
* Browser no PWA
* Standalone
* Fullscreen 100% screen (not for desktop and ios/ipad)
* Minimal-UI (back, reload, zoom button) (not for ios/ipad)

## orientation
* Default is any but you can use portrait or landscape
* Only supported by android, for others platform use media quries and ask user to change orientation

## Others
* description & categories for SEO
* lang for language example "en"
* no built-in support multi lang

#Html design
* title will be use as title in desktop apps
* use apple-mobile-web-app-status-bar-style for iso and black is default

# icons
* png image (desktop & android 1-7) support transparency
* ios ipad android 8+ (fill color transparent area)
* at least two sizes 192*192 and 512*512

## apple icons
* apple not use manisfest for icons. instead use "apple-touch-icon" link
* can specify different icons for different devices or
* use 180*180 square icon and apple will use it for other devices
* remember to preload icon

# splash
* android use manifest file to generate splash
* theme color will be header, in middle icon and background color for background, name prop to display name
* apple use startup image (png) exact size for the size of the screen (1 png for each screen)
* split view/landscape everything need its on image. Use media in link and media query to add images
* can use static generatore: pwa-asset-generator or use PWACompat client-side generator

# Mobile & Desktop user experence
* No URL, No tabs, No navigation buttons, No progress indicators, No reload action, No bookmarks
* On mobile standalone also No SSL certificate, No <title> usage, No Permissions Data

## tips
1. Provide a back button in your UI
2. Create a refresh button for unhandledexceptions
3. Use media queries for display mode
4. If security is important, use minimal-ui
5. Maximize web performance
6. Understand App's Lifecycle

## links out of scope
* when link is not same scope as PWA it will open in standalone browser

## native expenience mobile
* Pull to Refresh (android only can disable)
* App Translation (can disable Chromium-based browsers)
* System Fonts (On some platforms, only system fonts are available. But on other platforms all web
* fonts are usable, so we can fallback to system fonts if we want to)

## desktop friendly
* provide keyborad support where user expecting
* Support Mini-Window (when user decrease size)
