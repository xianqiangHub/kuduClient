# kuduClient
kudu没有交互式窗口，需要kuduPlus或者impala有时不方便
使用spark读取kudu方式，打包，执行可运行jar包，依次输入参数
kudumaster地址，kudu表的名字，视图的名字（自己定义，因为有些kudu表的名字spark试图解析报错），执行的sql（表名为视图名）
