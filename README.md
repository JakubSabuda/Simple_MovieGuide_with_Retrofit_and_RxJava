# MovieGuide created with Retrofit and RxJava

This application is done very simply just to see how to use Retrofit and Rxjava.

App is taking all the infromation from TMDB database using Retrofit.If you want to use genereate your own api_key.

Movies taken from TMDB are only top 20 movies.(there are going to be updates in the future)


## Main layout

Recyclerview
Layout is done by using StaggeredGridLayout.

ALl of the photos are loaded by using Glide Library.

Also I used SwipeToRefresh library.When you will swipe Main View the app will download all new data from TMDB.Then adapter will be notified about changes and the view will be updated.

![screen_mainActivity (1)](https://user-images.githubusercontent.com/66402503/85531094-c0489100-b60e-11ea-8e08-54f117fd242c.png)

 
 
 
 To not overcomplicate each movie is put to cardView.
 
 The cardView consist only of the poster. Then you can see created star using VectorImage and rating of the movie.
 
 Last object in each cardView is movie tilte.Number of Lines is not limited the cardView will stretch.That's why StaggeredGridLayout comes in handy.
 
![Movie detail](https://user-images.githubusercontent.com/66402503/85530764-7a8bc880-b60e-11ea-9d8f-c1be47714051.png)


## Movie details activity


## Icon
Icon is generated by using [Android Asset Studio](https://romannurik.github.io/AndroidAssetStudio)

Generated icon is clean and simple.Shape is circle(only).Padding is 35%, to make it look better I used Cast Shadow.Color is the same as colorPrimaryDark in app.

![ic_launcher_movie](https://user-images.githubusercontent.com/66402503/85525687-96d93680-b609-11ea-8638-368cad867030.png)
