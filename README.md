## Ancient Trees
**LATEST OFFICIAL VERSION**: [Ancient Trees 0.1.0 for MC 1.7.10][release]
[release]: https://github.com/MinecraftModArchive/Dendrology/releases/download/v0.1.0/dendrology_1.7.10-0.1.0.jar

[Contributing](#contributing)

[Support](#support)

[Maven](#maven)

[Licensing](#licensing)

### Contributing

Please see [CONTRIBUTING.md](CONTRIBUTING.md).

### Support
Something not quite right?  Have a suggestion?  Found a bug?  Create an issue now!

1. Make sure your issue hasn't already been answered or fixed.  Also think about whether your issue is a valid one before submitting it.
2. Go to [the issues page][issues].
3. Click [`New Issue`][new] right below `Star` and `Fork`.
4. Enter your Issue's title (something that summarizes your issue), and then create a detailed description ("Hey, could you add/change xxx?" or "Hey, I found an exploit.", etc.).
5. Click `Submit new issue`, and wait for feedback!

[issues]: /MinecraftModArchive/Dendrology/issues
[new]: /MinecraftModArchive/Dendrology/issues/new

### Maven
If you are developing a mod that has a compile dependency to this mod, add the following to your `build.gradle` file:

    repositories {
        mavenRepo urls: 'https://raw.githubusercontent.com/MinecraftModArchive/Dendrology/maven/'
    }

    dependencies {
        compile 'com.scottkillen.mod:ancienttrees_1.7.10-VERSION-dev:VERSION'
    }

replacing `VERSION` with the release you wish to use, eg. `ancienttrees_1.7.10-0.1.0-dev:0.1.0`

* * *

#### Licensing

This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or distribute this software, either in source code form or as a compiled binary, for any purpose, commercial or non-commercial, and by any means.

In jurisdictions that recognize copyright laws, the author or authors of this software dedicate any and all copyright interest in the software to the public domain. We make this dedication for the benefit of the public at large and to the detriment of our heirs and successors. We intend this dedication to be an overt act of relinquishment in perpetuity of all present and future rights to this software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For more information, please refer to [unlicense.org](http://unlicense.org/).

![Public Domain](https://raw.githubusercontent.com/MinecraftModArchive/assets/master/pd-icon.png)
