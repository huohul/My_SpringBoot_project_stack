docker简易搭建kafka  
		https://www.jianshu.com/p/adc4b8350fef
		
实战一 ： https://mp.weixin.qq.com/s/tosm_R9-qlipLE2Z50MQ4A

实战二： https://mp.weixin.qq.com/s/HJ1OPiXF8jbC-qdn1U4z7g


  ###拉取镜像
  
        docker pull wurstmeister/zookeeper
        docker pull wurstmeister/kafka
        docker pull sheepkiller/kafka-manager

  ###ookeeper

    docker run -d --name zookeeper --publish 2181:2181 \
      --volume /etc/localtime:/etc/localtime \
      --restart=always \
      wurstmeister/zookeeper
 
  ###run kafka

      docker run --name kafka \
      -p 9092:9092 \
      --link zookeeper:zookeeper \
      -e KAFKA_ADVERTISED_HOST_NAME=192.168.2.128 \
      -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 \
      -d  wurstmeister/kafka  
  
  ###run kafka manager

      docker run -d \
      --link zookeeper:zookeeper \
      -p 9000:9000  \
      -e ZK_HOSTS="zookeeper:2181" \
      hlebalbau/kafka-manager:stable \
      -Dpidfile.path=/dev/null