https://mp.weixin.qq.com/s/LhfpxqiJZMEcnKDiOTwspg

#### 1.strategypattern 策略模式, 用于实现 同一个接口多个实现类
##### 1.静态  本 同 softmbh api  天津 sing 方法
##### 2.基于spring bean 注入形式工厂 可参见 infrared 

#### 2 责任链模式 实现效果同， 实现方法较绕 
#####  步骤逻辑：
    直接new 责任链实现类  触发 static 初始化 add 业务实现impl 对象
    后 调用本类方法 循环 调用业务接口 (除参数外， 且将本类this 传递到下文），实现层进行判断 
    符合进行业务处理 不符合 基于上网传入的 责任链实现类对象 回到上层调用 