CWAC-CrossPort: Material Design Using Theme.Material
====================================================

This project offers a "crossport" of some classes from
[the Material Components for Android library](https://github.com/material-components/material-components-android),
revised to use `Theme.Material` instead of `Theme.AppCompat` as 
a basis, along with removing all other `appcompat-v7` references.
In particular, this library ports:

- `FloatingActionButton`
- `Snackbar`
- `TabLayout`
- `TextInputLayout` and `TextInputEditText`

among other classes from Material Components for Android. It also has a port
of `DrawerArrowDrawable`, for use with nav drawers.

These classes allow you to use these Material Design elements without incurring
the overhead of `appcompat-v7`.

This library will be updated sporadically, at least once per major
release of the Material Components for Android library.

This Android library project is 
available as an artifact for use with Gradle. To use that, add the following
blocks to your `build.gradle` file:

```groovy
repositories {
    maven {
        url "https://s3.amazonaws.com/repo.commonsware.com"
    }
}

dependencies {
    compile 'com.commonsware.cwac:crossport:0.1.0'
}
```

Or, if you cannot use SSL, use `http://repo.commonsware.com` for the repository
URL.

Usage
-----
Other than adding the repository, everything else should work more or less
as do the original classes. Follow the JavaDocs (e.g.,
[for `TabLayout`](https://developer.android.com/reference/android/support/design/widget/TabLayout.html)).

The package names have been revised to be in `com.commonsware.cwac.crossport`.
So, instead of `android.support.design.widget.TabLayout`, it is
`com.commonsware.cwac.crossport.design.widget.TabLayout`.

Because this library requires `Theme.Material`, this library has a `minSdkVersion` of 21.

Dependencies
------------
This project depends upon `support-annotations` and three pieces of the
former `support-v4`: `support-compat`, `support-core-ui`, and `support-core-utils`.
All will be pulled in via transitive dependencies, and you probably already are
using some or all of those dependencies anyway.

Version
-------
This is version v0.1.0 of this module, meaning it is pretty new.

Demo
----
In the `demo/` sub-project you will find a sample project demonstrating the use
a few of these classes. More demonstrations will be added over time.

License
-------
The code in this project is licensed under the Apache
Software License 2.0, per the terms of the included LICENSE
file. Most of the code's copyrights are held by The Android Open Source Project;
only the modifications' copyrights are held by CommonsWare, LLC. Both the AOSP
and CommonsWare are using the Apache Software License 2.0 for their portions
of this code.

Questions
---------
If you have questions regarding the use of this code, please post a question
on [Stack Overflow](http://stackoverflow.com/questions/ask) tagged with
`commonsware-cwac` and `android` after [searching to see if there already is an answer](https://stackoverflow.com/search?q=[commonsware-cwac]+streamprovider). Be sure to indicate
what CWAC module you are having issues with, and be sure to include source code 
and stack traces if you are encountering crashes.

If you have encountered what is clearly a bug, or if you have a feature request,
please post an [issue](https://github.com/commonsguy/cwac-crossport/issues).
Be certain to include complete steps for reproducing the issue.
The [contribution guidelines](CONTRIBUTING.md)
provide some suggestions for how to create a bug report that will get
the problem fixed the fastest.

Note that bugs might come from this crossport, or they might originate with
the original code that was ported. Try reproducing your bug both with
`appcompat-v7` and with this library (in separate projects, or at least
separate activities). If the bug appears in both, then the issue resides
with `appcompat-v7`, and [should be filed with Google](http://b.android.com).
If the bug is unique to the crossport, then file it with this project.

You are also welcome to join
[the CommonsWare Community](https://community.commonsware.com/)
and post questions
and ideas to [the CWAC category](https://community.commonsware.com/c/cwac).

Do not ask for help via social media.

Also, if you plan on hacking
on the code with an eye for contributing something back,
**please open an issue that we can use for discussing
implementation details**. This project is mostly trying to follow the
upstream originals.

Release Notes
-------------
- v0.1.0: original release, mirroring the original dump of code from the Material Components library (which, unfortunately, they did not tag, but should line up with 25.x.y of the Design Support library)

Who Made This?
--------------
<a href="http://commonsware.com">![CommonsWare](http://commonsware.com/images/logo.png)</a>

