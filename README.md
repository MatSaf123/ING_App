# Android-Kotlin-MVVM Profile Display App

## SCREENS:
<img src = "https://github.com/MatSaf123/ING_App/blob/master/app_gif_2.gif"
data-canonical-src = "https://github.com/MatSaf123/ING_App/blob/master/app_gif_2.gif" width="331" height="700" />
   
<img src = "https://i.imgur.com/H73EZTX.jpg"
data-canonical-src = "https://i.imgur.com/H73EZTX.jpg" width="331" height="700" />
<img src = "https://i.imgur.com/8yjspc4.jpg"
data-canonical-src = "https://i.imgur.com/8yjspc4.jpg" width="331" height="700" />
<img src = "https://i.imgur.com/sTNj3iN.jpg"
data-canonical-src = "https://i.imgur.com/sTNj3iN.jpg" width="331" height="700" /> 
<img src = "https://i.imgur.com/qKOUW4Q.jpg"
data-canonical-src = "https://i.imgur.com/qKOUW4Q.jpg" width="331" height="700" />
<img src = "https://i.imgur.com/Tnntv9s.jpg"
data-canonical-src = "https://i.imgur.com/Tnntv9s.jpg" width="331" height="700" />
   

### APPLICATION SUMMARY:

Application requests data from fake REST API (https://jsonplaceholder.typicode.com) and presents it to the user in form of a scrollable list. Elements of the list - Posts - contain author's name, post title, body and number of comments attached to the post. Clicking in author's name presents user-detail screen with info about him, his personal data, his geolocation and a button navigating to user's galery of photos. Clicking in comments count navigates to a scrollable list of comments relating to the post.
In case of no internet connection or any other exception, an error-screen will show up.

TODO:
- [x] Setting an onClickListener for the author's name
- [x] Implementing user-detail fragment
- [x] Mapping geo data and presenting it in form of a Google Map
- [x] Implementing comments fragment
- [x] Implementing user's photos-fragment accessible from his profile
- [x] Implementing app-authors info button 
- [x] Pagination*
- [x] Frontend improvement
- [ ] Adding tests

* because of long awaiting times during REST API requests, the pagination has been simplified and implemented only for training purposes.

<br /><hr /><br />

Project consisting of displaying various information from API - https://jsonplaceholder.typicode.com/

Project designed for mobile devices, written in Kotlin.
We use Retrofit for making API calls, Moshi for deserializing JSON objects to Kotlin data objects and data binding.

Contributors:
1. MatSaf123
2. TymJannek
3. Shepard701
