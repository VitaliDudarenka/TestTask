# TestTask
The application shows the list of GitHub users, users details (by clicking on item). Also there is implemented pull-to-refresh and pagination. The app uses GitHub API. There is used clean architecture, consists of 3 modules: domain, data, presentation.

# Domain module
Independent layer, it contains only business logic - no dependencies from Android SDK, other modules or libs , except RxJava. Here we have interfaces that should be implemented in other modules.

# Data module
It contains functions for getting entities of users. This module implements the interfaces located in the domain module and does not contain business logic. For working out it uses such libraries as: RxJava, Retrofit, OkHttp.

# Presentation module
This module contains the logic to configure the application user interface and all business logic. This module uses the MVVM pattern for organizing work with the user interface.

# Used liblrares
RxJava Retrofit OkHttp Glide Dagger2 ConstraintLayout RecyclerView 

# Download
To download this project click "Cone or download" button, then click "downloadZip" or copy the project link. If you have installed GitHub client you may click "Open in Desctop".
