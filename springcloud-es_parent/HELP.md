# 项目说明书

### 项目基础说明
 本项目为springcloud为服务项目 由黑马架构师生成基础架构 为本地单库多表结构，使用eureka为注册中心 
 实现feign 远程调用 和 整合springboot-elasticsearch 的简单demo
 
 #### 项目结构说明：
     springcloud-es_parent -|
                  |- springcloud-es_common                  工具类 返回值 都放在这里
                  |- springcloud-es_common_db               空
                  |- springcloud-es_eureka                  注册中心 
                  |- springcloud-es_gateway                 服务网关 未进行配置与操作
                  |- springcloud-es_service                 业务层服务
                       |- springboot2-elasticsearch-service     boot2+Es 参照 G:\IDEAGIt\xuwujing\springBoot-study\springboot2-elasticsearch
                                                               问题较多 不能用当前实体类 查询也是查询不到 慢慢研究吧 内附url 教程
                       |- springboot-search-service            参照 changgou项目 进行 mysql 数据--》 es 的数据导入 
                       |- springcloud-es_service_system          MVC&CRUD  接口实现类 就构建查询对象作说明 模糊查询 根据自己业务需求进行修改 注意 【不要有空格】
                  |- springcloud-es_service_api                 pojo  & feign 结口
                       |-springboot2-elasticsearch-service-api        boot2+Es  所需pojo 
                       |-springcloud-es_service_system_api            当前库的pojo & feign接口  
                  |- springcloud-es_web                         web 层服务
                       |-springcloud-stu-es                     基于feign 进行远程调用 bug 见启动类 
                       
                   
                   
   ##  技术收获 ：
            feign 远程调用 
            List<pojo>  转 List<Map>   
                  //获取数据 进行类型转换
                              List<Student> skuList = (List<Student>) list.getData();
                              //将获取到的数据转为json格式字符串
                              String skulistJsonStr = JSON.toJSONString(skuList);
                              //将jsong格式字符串转换成需要的数据
                              List<Map> maps = JSON.parseArray(skulistJsonStr, Map.class);