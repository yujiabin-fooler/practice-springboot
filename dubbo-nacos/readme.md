
window下安装nacos
https://blog.csdn.net/gzmyh/article/details/129836470
启动命令(standalone代表着单机模式运行，非集群模式):
startup.cmd -m standalone
另外也可以修改startup.cmd
#set MODE="cluster" 把集群模式改成单机模式
set MODE="standalone"
改完后直接双击startup.cmd启动服务
原文链接：https://blog.csdn.net/gzmyh/article/details/129836470
浏览器访问http://127.0.0.1:8848/nacos，输入用户名密码nacos/nacos(默认)