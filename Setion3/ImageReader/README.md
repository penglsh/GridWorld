## ImageReader 
1. 该部分包括四个文件：
    1. ImplementImageIO.java,该文件实现位图的读写操作，即转化为二进制。
    2. ImplementImageProcessor.java，该文件实现了颜色通道的提取和灰度图的生成。
    3. ImageProcessorTest.java，junit测试，用于颜色通道提取和灰度图生成时与原图的比较。
    4. ImageReaderRunner.java，运行文件。
2. 运行后（不是运行ImageProcessorTest.java），会有一个小弹框，有“File”、“Process”、“Help”三个选项，点击“File”可选择一个为“bmp”的图像，然后点击“Process"可以对刚才选择的图形进行操作，如红、绿、蓝颜色通道提取以及灰度图的生成，点击“Help”会弹出“about”，点击其会出现一些关于这个项目的信息。
3. junit测试（使用eclipse开发），右击ImageProcessorTest.java，随后点击“Run as”下的Junit Test，即可对写好的测试样例进行测试，测试信息会在eclipse左栏目下出现。