## MazeBug
该项目为走迷宫，使用深度搜索优先算法为一个Bug寻找迷宫出口，最后打印走过的步数（在eclipse下进行开发）。
1. MazeBug.java文件，该文件实现了寻找迷宫出口的算法。
2. 在运行前必须要引用一个包含MazeBug.java编译过后的jar包，把编写好的MazeBug.java放在名为maze的文件夹下，放入GridWorldCode目录下的framework的子目录gridworld里，重新用命令行输入“ant build-jar”即可在dist文件里生成一个gridworld.jar包，在项目里引用这个包即可。
3. 运行后，窗口上面有四个选项，分别是：
    1. World，该选项选择用到的Grid，测试的地图大部分是选用UnboundedGrid。
    2. Map，点击该选项可以选择读入已写好的迷宫地图（在已提供的源文件内有）。
    3. Location，点击可以修改所选的单元格的位置（通过up、down等），edit可修改所选单元格的属性，delete课删除所选单元格的物体，Zoom in为放大地图（显示界面），Zoom out为缩小地图。
    4. Help，点击其会有该项目相关的信息。
4. 选好World和Map后，点击Run即可自动运行直至到达出口，拉动Slow-Fast速度条可以改变Bug移动速度。Step为手动运行，此时Bug将一步步进行移动。
