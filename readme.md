# gradle 工作流程
1. 初始化 执行settings.gradle
2. 解析工程的gradle脚本 将每个工程里面的gradle的脚本的task添加到一个有向图里面
3. 执行task，如果键入 gradle xxx ，那么gradle会把xxx task链上的所有的task执行完

Gradle 基于 Groovy， Groovy 又基于 Java。所以， Gradle 执行的时候和 Groovy 一样，
会把脚本转换成 Java 对象。 Gradle 主要有三种对象，这三种对象和三种不同的脚本文件对
应，在 gradle 执行的时候，会将脚本转换成对应的对端：

1. Gradle 对象：当我们执行 gradle xxx 或者什么的时候， gradle 会从默认的配置脚本中构造出一个 Gradle 对象。在整个执行过程中，只有这么一个对象。 Gradle
对象的数据类型就是 Gradle。

2. Project 对象：每一个 build.gradle 会转换成一个 Project 对像。
3. Settings 对象：显然，每一个 settings.gradle 都会转换成一个 Settings 对象。

注意：对于其他 gradle 文件，除非定义了 class，否则会转换成一个实现了 Script 接口的对
象。


每一个 build.gradle 文件都会转换成一个 Project 对象。在 Gradle 术语中， Project 对象对
应的是 Build Script。

Project 包含若干 Tasks。 另外， 由于 Project 对应具体的工程，所以需要为 Project 加载
所需要的插件，比如为 Java 工程加载 Java 插件。 其实，一个 Project 包含多少 Task 往往是
插件决定的。
所以，在 Project 中，我们要：

1. 加载插件。

2. 不同插件有不同的行话，即不同的配置。我们要在 Project 中配置好，这样插件就知道从哪里读取源文件等
3.  设置属性。

apply plugin: 'com.android.application'
除了加载二进制的插件（上面的插件其实都是下载了对应的 jar 包，这也是通常意义上我们所理解的插件），还可以加载一个 gradle 文件。为什么要加载 gradle 文件呢？
其实这和代码的模块划分有关。一般而言，我会把一些通用的函数放到一个名叫utils.gradle 文件里。然后在其他工程的 build.gradle 来加载这个 utils.gradle。这样，通过一些处理，我就可以调用 utils.gradle 中定义的函数了。