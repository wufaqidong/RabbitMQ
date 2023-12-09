[RabbitMQ](https://so.csdn.net/so/search?q=RabbitMQ&spm=1001.2101.3001.7020)<br />![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837248437-3e490561-6f76-4bef-bddd-ae02e3a1982a.png#averageHue=%23fafafa&clientId=ud104d685-f834-4&from=paste&id=u3db44600&originHeight=540&originWidth=893&originalType=url&ratio=1&rotation=0&showTitle=false&size=60132&status=done&style=none&taskId=u0a2ce192-c782-4197-994d-19730180b77&title=)
<a name="rg6y1"></a>
### 1 MQ引言
<a name="Avysy"></a>
#### 1.1 什么是MQ
:::info
MQ(Message Quene) :  翻译为消息队列,通过典型的生产者<br />和消费者模型,生产者不断向消息队列中生产消息，消费者<br />不断的从队列中获取消息。因为消息的生产和消费都是异<br />步的，而且只关心消息的发送和接收，没有业务逻辑的侵<br />入,轻松的实现系统间解耦。别名为 消息中间件通过利用<br />高效可靠的消息传递机制进行平台无关的数据交流，并<br />基于数据通信来进行分布式系统的集成。
:::
<a name="L3KTf"></a>
#### 1.2 MQ有哪些
> 当今市面上有很多主流的消息中间件，如老牌的ActiveMQ、<br />RabbitMQ，炙手可热的Kafka，阿里巴巴自主开发RocketMQ等。

<a name="XzREi"></a>
#### 1.3 不同MQ特点
> 1.ActiveMQ<br />		ActiveMQ 是Apache出品，最流行的，能力强劲的开源<br />		消息总线。它是一个完全支持JMS规范的的消息中间件。<br />		丰富的API,多种集群架构模式让ActiveMQ在业界成为老<br />		牌的消息中间件,在中小型企业 颇受欢迎!

2.Kafka<br />		Kafka是LinkedIn开源的分布式发布-订阅消息系统，目<br />		前归属于Apache顶级项目。Kafka主要特点是基于Pull<br />		的模式来处理消息消费，追求高吞吐量，一开始的目的<br />		就是用于日志收集和传输。0.8版本开始支持复制，不支<br />		持事务，对消息的重复、丢失、错误没有严格要求，<br />		适合产生大量数据的互联网服务的数据收集业务。

3.RocketMQ<br />		RocketMQ是阿里开源的消息中间件，它是纯Java开发，<br />		具有高吞吐量、高可用性、适合大规模分布式系统应用<br />		的特点。RocketMQ思路起源于Kafka，但并不是Kafka<br />		的一个Copy，它对消息的可靠传输及事务性做了优化，<br />		目前在阿里集团被广泛应用于交   易、充值、流计算、<br />		消息推送、日志流式处理、binglog分发等场景。

4.RabbitMQ<br />		RabbitMQ是使用Erlang语言开发的开源消息队列系统，<br />		基于AMQP协议来实现。AMQP的主要特征是面向消息、<br />		队列、路由（包括点对点和发布/订阅）、可靠性、安全。<br />		AMQP协议更多用在企业系统内对数据一致性、稳定性和<br />		可靠性要求很高的场景，对性能和吞吐量的要求还在<br />		其次。

RabbitMQ比Kafka可靠，Kafka更适合IO高吞吐的处理，一般应用<br />在大数据日志处理或对实时性（少量延迟），可靠性（少量丢数据）<br />要求稍低的场景使用，比如ELK日志收集。
> ![](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701862749202-6c58bbcb-0808-43a0-9e9a-77ff32c00a20.png#averageHue=%23efeeee&clientId=ue5f1ce60-2f31-4&from=paste&id=u83b79fa1&originHeight=720&originWidth=793&originalType=url&ratio=1.5&rotation=0&showTitle=false&status=done&style=none&taskId=u653e9948-84cf-4a86-b9fe-b38bf079a88&title=)
> ![](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701862762726-162cc443-c887-4fd1-a707-0296a961bc21.png#averageHue=%23cddcee&clientId=ue5f1ce60-2f31-4&from=paste&id=u8f0b439a&originHeight=698&originWidth=1430&originalType=url&ratio=1.5&rotation=0&showTitle=false&status=done&style=none&taskId=ua76e7746-6658-42c2-84da-06c3671d62f&title=)

<a name="v5Jkm"></a>
### 2 RabbitMQ 的引言
<a name="MsBfP"></a>
#### 2.1 RabbitMQ
:::info
基于**AMQP协议，erlang语言**开发，是部署最广泛的开源<br />消息中间件,是最受欢迎的开源消息中间件之一。
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837249419-fabf28b4-7f16-427a-9167-fb7767ad1422.png#averageHue=%23a2a3a1&clientId=ud104d685-f834-4&from=paste&id=ub5301e64&originHeight=1476&originWidth=2586&originalType=url&ratio=1&rotation=0&showTitle=false&size=371581&status=done&style=none&taskId=u07ace1d0-d569-4e34-a487-dbf41bf454c&title=)
> AMQP 协议<br /> 	AMQP（advanced message queuing protocol）在2003年<br /> 	时被提出，最早用于解决金融领不同平台之间的消息传递<br /> 	交互问题。顾名思义，AMQP是一种协议，更准确的说是<br /> 	一种binary wire-level protocol（链接协议）。这是其和<br /> 	JMS的本质差别，AMQP不从API层进行限定，而是直接<br /> 	定义网络交换的数据格式。这使得实现了AMQP的<br /> 	provider天然性就是跨平台的。以下是AMQP协议模型:

![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837248520-021a2986-cb6f-4d41-a707-483d14d1c0e0.png#averageHue=%23e3e6f8&clientId=ud104d685-f834-4&from=paste&id=u8875578a&originHeight=493&originWidth=1217&originalType=url&ratio=1&rotation=0&showTitle=false&size=146976&status=done&style=none&taskId=uf31ceed6-2229-4c55-931b-6ba6efb16b6&title=)
<a name="D61IT"></a>
#### 2.2 RabbitMQ 的安装
> 最新版的3.8版本的可以看我这篇文章<br />https://blog.csdn.net/unique_perfect/article/details/108643804

<a name="r61HU"></a>
##### 2.2.1 下载
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837249530-6ec3c537-45a7-485e-84aa-15524cc03953.png#averageHue=%23f5f0ef&clientId=ud104d685-f834-4&from=paste&id=u59949b1b&originHeight=792&originWidth=1912&originalType=url&ratio=1&rotation=0&showTitle=false&size=206487&status=done&style=none&taskId=u0122474a-dcdc-49be-be49-44a7328f8c4&title=)
<a name="oDvsQ"></a>
##### 2.2.2 下载的安装包
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837249095-dcd291ae-63d2-4c9a-88eb-bebeed4501c8.png#averageHue=%23e3e1db&clientId=ud104d685-f834-4&from=paste&id=ua1b5ada7&originHeight=164&originWidth=1568&originalType=url&ratio=1&rotation=0&showTitle=false&size=56474&status=done&style=none&taskId=u6554388b-e85c-40ba-a3bc-38d68e1cd51&title=)
> 注意:这里的安装包是centos7安装的包

<a name="B1IBs"></a>
##### 2.2.3 安装步骤
> # 1.将rabbitmq安装包上传到linux系统中<br />	erlang-22.0.7-1.el7.x86_64.rpm  #l7表示是Centosl7,Centosl8表示Centos8<br />	rabbitmq-server-3.7.18-1.el7.noarch.rpm

# 2.安装Erlang依赖包<br />	rpm -ivh erlang-22.0.7-1.el7.x86_64.rpm

# 3.安装RabbitMQ安装包(需要联网)<br />	yum install -y rabbitmq-server-3.7.18-1.el7.noarch.rpm<br />	注意:默认安装完成后配置文件模板在:/usr/share/doc/rabbitmq-server-3.7.18/rabbitmq.config.example目录中,需要	<br />	将配置文件复制到/etc/rabbitmq/目录中,并修改名称为rabbitmq.config<br /># 4.复制配置文件<br />	cp /usr/share/doc/rabbitmq-server-3.7.18/rabbitmq.config.example /etc/rabbitmq/rabbitmq.config

# 5.查看配置文件位置<br />	ls /etc/rabbitmq/rabbitmq.config

# 6.修改配置文件(参见下图:)<br />	vim /etc/rabbitmq/rabbitmq.config

![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837249610-4bf4e8f7-3a8c-4559-8100-93562dedc318.png#averageHue=%23030100&clientId=ud104d685-f834-4&from=paste&id=uf560ff80&originHeight=434&originWidth=2120&originalType=url&ratio=1&rotation=0&showTitle=false&size=230890&status=done&style=none&taskId=u0d2dabe6-42b0-41a9-b11f-9a177db8dde&title=)
> 将上图中配置文件中红色部分去掉`%%`,以及最后的`,`逗号 <br />修改为下图:

![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837249538-be68c7f0-88eb-464c-be51-cc9e8455749a.png#averageHue=%23000000&clientId=ud104d685-f834-4&from=paste&id=u89ad786b&originHeight=296&originWidth=2122&originalType=url&ratio=1&rotation=0&showTitle=false&size=108985&status=done&style=none&taskId=u1effd90b-258b-4fbe-9181-9b0646f9956&title=)
> # 7.执行如下命令,启动rabbitmq中的插件管理<br />	rabbitmq-plugins enable rabbitmq_management<br />	<br />	出现如下说明:<br />		Enabling plugins on node rabbit@localhost:<br />    rabbitmq_management<br />    The following plugins have been configured:<br />      rabbitmq_management<br />      rabbitmq_management_agent<br />      rabbitmq_web_dispatch<br />    Applying plugin configuration to rabbit@localhost...<br />    The following plugins have been enabled:<br />      rabbitmq_management<br />      rabbitmq_management_agent<br />      rabbitmq_web_dispatch

    set 3 plugins.<br />    Offline change; changes will take effect at broker restart.

# 8.启动RabbitMQ的服务<br />	systemctl start rabbitmq-server<br />	systemctl restart rabbitmq-server<br />	systemctl stop rabbitmq-server<br />	

# 9.查看服务状态(见下图:)<br />	systemctl status rabbitmq-server<br />  ● rabbitmq-server.service - RabbitMQ broker<br />     Loaded: loaded (/usr/lib/systemd/system/rabbitmq-server.service; disabled; vendor preset: disabled)<br />     Active: active (running) since 三 2019-09-25 22:26:35 CST; 7s ago<br />   Main PID: 2904 (beam.smp)<br />     Status: "Initialized"<br />     CGroup: /system.slice/rabbitmq-server.service<br />             ├─2904 /usr/lib64/erlang/erts-10.4.4/bin/beam.smp -W w -A 64 -MBas ageffcbf -MHas ageffcbf -<br />             MBlmbcs...<br />             ├─3220 erl_child_setup 32768<br />             ├─3243 inet_gethost 4<br />             └─3244 inet_gethost 4<br />      .........

![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837251289-4c205985-b917-401a-9c1f-6723b2aab93a.png#averageHue=%23070707&clientId=ud104d685-f834-4&from=paste&id=uc9c8b929&originHeight=1968&originWidth=3246&originalType=url&ratio=1&rotation=0&showTitle=false&size=1463996&status=done&style=none&taskId=udf7eda32-52e7-41b7-af8e-185f006c73f&title=)
> # 10.关闭防火墙服务<br />	systemctl disable firewalld  # 需要关闭防火墙，否则访问不了<br />    Removed symlink /etc/systemd/system/multi-user.target.wants/firewalld.service.<br />    Removed symlink /etc/systemd/system/dbus-org.fedoraproject.FirewallD1.service.<br />	systemctl stop firewalld   

# 11.访问web管理界面<br />	http://10.15.0.8:15672/

![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837249918-bb17a269-7ab0-48cb-8cf7-389ddd007baf.png#averageHue=%23fdfcfb&clientId=ud104d685-f834-4&from=paste&id=ud42203fd&originHeight=592&originWidth=2626&originalType=url&ratio=1&rotation=0&showTitle=false&size=127636&status=done&style=none&taskId=u64fc7587-7d55-4fcd-a07b-e55e9133ebb&title=)
:::info
# 12.登录管理界面<br />	username:  guest<br />	password:  guest
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837250111-02b31d41-1b61-4311-a61d-c0fcf6ab2a5f.png#averageHue=%23f6f6f6&clientId=ud104d685-f834-4&from=paste&id=u1ac0442e&originHeight=1388&originWidth=2730&originalType=url&ratio=1&rotation=0&showTitle=false&size=337941&status=done&style=none&taskId=u0f15e2af-6081-4efd-aaf9-ae3836f68f6&title=)
<a name="Zlfj7"></a>
### 3 RabiitMQ 配置
<a name="Bodmv"></a>
#### 3.1 RabbitMQ 管理命令行
:::info
# 1.服务启动相关<br />	systemctl start|restart|stop|status rabbitmq-server

# 2.管理命令行  用来在不使用web管理界面情况下命令操作RabbitMQ<br />	rabbitmqctl  help  可以查看更多命令

# 3.插件管理命令行<br />	rabbitmq-plugins enable|list|disable
:::
<a name="yIukx"></a>
#### 3.2 web管理界面介绍
<a name="b7X6F"></a>
##### 3.2.1 overview概览
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837250063-c39a9850-51dd-4c46-9a4e-a5104f57a927.png#averageHue=%23fafaf9&clientId=ud104d685-f834-4&from=paste&id=uae89536d&originHeight=1187&originWidth=2657&originalType=url&ratio=1&rotation=0&showTitle=false&size=229182&status=done&style=none&taskId=u1f6b8ed1-ff71-45ce-9aed-77dd8490fc5&title=)
:::info
connections：无论生产者还是消费者，都需要与RabbitMQ建立连接后才<br />可以完成消息的生产和消费，在这里可以查看连接情况`

channels：通道，建立连接后，会形成通道，消息的投递获取依赖通道。

Exchanges：交换机，用来实现消息的路由

Queues：队列，即消息队列，消息存放在队列中，等待消费，<br />消费后被移除队列。
:::
<a name="PDMJE"></a>
##### 3.2.2 Admin用户和虚拟主机管理
<a name="KoWO2"></a>
###### 3.2.2.1 添加用户
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837250414-24ee9521-d112-419d-b51e-86602aae2fc0.png#averageHue=%23f8f4f3&clientId=ud104d685-f834-4&from=paste&id=u72693d42&originHeight=548&originWidth=1028&originalType=url&ratio=1&rotation=0&showTitle=false&size=110590&status=done&style=none&taskId=u4375e87b-066a-4a95-b6f1-33a797c386b&title=)
:::info
上面的Tags选项，其实是指定用户的角色，可选的有以下几个：

超级管理员(administrator)

可登陆管理控制台，可查看所有的信息，并且可以对用户，<br />策略(policy)进行操作。

监控者(monitoring)

可登陆管理控制台，同时可以查看rabbitmq节点的相关信息<br />(进程数，内存使用情况，磁盘使用情况等)

策略制定者(policymaker)

可登陆管理控制台, 同时可以对policy进行管理。但无法查看节点的<br />相关信息(上图红框标识的部分)。

普通管理者(management)

仅可登陆管理控制台，无法看到节点信息，也无法对策略进行管理。

其他

无法登陆管理控制台，通常就是普通的生产者和消费者。
:::
<a name="UC9MO"></a>
###### 3.2.2.2 创建虚拟主机
:::info
虚拟主机<br />为了让各个用户可以互不干扰的工作，RabbitMQ添加了<br />虚拟主机（Virtual Hosts）的概念。其实就是一个独立的<br />访问路径，不同用户使用不同路径，各自有自己的<br />队列、交换机，互相不会影响。相当于关系型中的数据库
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837250496-be5aa761-118f-473b-aacc-a26c481d1bcc.png#averageHue=%23f8f5f4&clientId=ud104d685-f834-4&from=paste&id=u905f03f5&originHeight=356&originWidth=1346&originalType=url&ratio=1&rotation=0&showTitle=false&size=88780&status=done&style=none&taskId=uf0e67399-b723-49cf-bc35-82204ff25fa&title=)
<a name="isoFV"></a>
###### 3.2.2.3 绑定虚拟主机和用户
:::info
创建好虚拟主机，我们还要给用户添加访问权限：

点击添加好的虚拟主机：
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837250550-c2d97038-c97a-412a-9932-13edbf964980.png#averageHue=%23f5f3f2&clientId=ud104d685-f834-4&from=paste&id=u5369c7bb&originHeight=348&originWidth=1130&originalType=url&ratio=1&rotation=0&showTitle=false&size=94993&status=done&style=none&taskId=u001b9440-665e-4cfa-ba04-a02fa1a231b&title=)
:::info
进入虚拟机设置界面
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837251175-11fd8c2d-06b2-44e1-a0d4-5fa70f627656.png#averageHue=%23f0f0f0&clientId=ud104d685-f834-4&from=paste&id=u76fc6156&originHeight=705&originWidth=567&originalType=url&ratio=1&rotation=0&showTitle=false&size=105629&status=done&style=none&taskId=u4e20a7b3-101b-48ed-b7c6-f5734e5275d&title=)
<a name="DMAjn"></a>
### 4 RabbitMQ 的第一个程序
<a name="kk0fR"></a>
#### 4.1 AMQP协议的回顾
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837251328-3a7cccd5-4de8-4548-a1cf-3121272bb30e.png#averageHue=%23f1f1f1&clientId=ud104d685-f834-4&from=paste&id=u039b977f&originHeight=467&originWidth=1064&originalType=url&ratio=1&rotation=0&showTitle=false&size=62045&status=done&style=none&taskId=u5fd18a2c-9a68-471b-b65a-cc6121ec5e0&title=)
<a name="tkW9J"></a>
#### 4.2 RabbitMQ支持的消息模型
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837252081-9eceb6c7-4770-4ad6-b183-4143433c98a4.png#averageHue=%23fdf9f7&clientId=ud104d685-f834-4&from=paste&id=u7d91f7a9&originHeight=1560&originWidth=3146&originalType=url&ratio=1&rotation=0&showTitle=false&size=271825&status=done&style=none&taskId=u05e84ded-c54f-4e31-8bd7-f80df2f2b44&title=)<br />![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837252135-6a70a05f-00a8-497b-b30c-bf5e12e83d7f.png#averageHue=%23fdfaf9&clientId=ud104d685-f834-4&from=paste&id=u4103535c&originHeight=1540&originWidth=3110&originalType=url&ratio=1&rotation=0&showTitle=false&size=265108&status=done&style=none&taskId=uc99e1c94-8d87-4db0-acef-3d57cfb05f8&title=)
<a name="uTfFq"></a>
#### 4.3 引入依赖
:::info
<dependency><br />    <groupId>com.rabbitmq</groupId><br />    <artifactId>amqp-client</artifactId><br />    <version>5.7.2</version><br /></dependency>
:::
<a name="Z2EwX"></a>
#### 4.4 第一种模型(直连)
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837251981-e77d06c8-740b-4248-aa0e-d9a01341d65a.png#averageHue=%23fdf7f7&clientId=ud104d685-f834-4&from=paste&id=u5da84d0e&originHeight=151&originWidth=1018&originalType=url&ratio=1&rotation=0&showTitle=false&size=13828&status=done&style=none&taskId=ub87c60ea-26cc-43a4-a0a0-19df9a6561b&title=)
:::info
在上图的模型中，有以下概念：

P：生产者，也就是要发送消息的程序<br />C：消费者：消息的接受者，会一直等待消息到来。<br />queue：消息队列，图中红色部分。类似一个邮箱，<br />可以缓存消息；生产者向其中投递消息，消费者从其中取出消息。
:::
<a name="sDz9H"></a>
##### 4.4.1 开发生产者
:::info
package helloword;

import com.rabbitmq.client.Channel;<br />import com.rabbitmq.client.Connection;<br />import com.rabbitmq.client.MessageProperties;<br />import org.junit.Test;<br />import utils.RabbitMQUtils;

import java.io.IOException;<br />import java.util.concurrent.TimeoutException;

public class Provider {

    //生产消息<br />    @Test<br />    public void testSendMessage() throws IOException, TimeoutException {<br />/*<br />        //创建连接mq的连接工厂对象<br />        ConnectionFactory connectionFactory = new ConnectionFactory();<br />        //设置连接rabbitmq主机<br />        connectionFactory.setHost("192.168.11.143");<br />        //设置端口号<br />        connectionFactory.setPort(5672);<br />        //设置连接那个虚拟主机<br />        connectionFactory.setVirtualHost("/ems");<br />        //设置访问虚拟主机的用户名和密码<br />        connectionFactory.setUsername("ems");<br />        connectionFactory.setPassword("123");

        //获取连接对象<br />        Connection connection = connectionFactory.newConnection();*/

        //通过工具类获取连接对象<br />        Connection connection = RabbitMQUtils.getConnection();

        //获取连接中通道<br />        Channel channel = connection.createChannel();

        //通道绑定对应消息队列<br />        //参数1:  队列名称 如果队列不存在自动创建<br />        //参数2:  用来定义队列特性是否要持久化 true 持久化队列   false 不持久化<br />        //参数3:  exclusive 是否独占队列  true 独占队列   false  不独占<br />        //参数4:  autoDelete: 是否在消费完成后自动删除队列  true 自动删除  false 不自动删除<br />        //参数5:  额外附加参数<br />        channel.queueDeclare("hello",true,false,false,null);

        //发布消息

        //参数1: 交换机名称 参数2:队列名称  参数3:传递消息额外设置  参数4:消息的具体内容<br />        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello rabbitmq".getBytes());

        /*channel.close();<br />        connection.close();*/

        //调用工具类<br />        RabbitMQUtils.closeConnectionAndChanel(channel,connection);

    }<br />}
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837251984-2f6d80a1-b69b-4a59-86a7-f652dbdcc30d.png#averageHue=%23f9f8f7&clientId=ud104d685-f834-4&from=paste&id=ud1bdbc33&originHeight=224&originWidth=1006&originalType=url&ratio=1&rotation=0&showTitle=false&size=48204&status=done&style=none&taskId=u932a63f9-3e06-4cf6-94fa-fbc65224d7c&title=)
<a name="iOmJX"></a>
##### 4.4.2 开发消费者
```java
package helloworld;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.junit.jupiter.api.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: RabbitMQ
 * @description:
 * @author:
 * @create: 2023-12-07 14:54
 **/

public class Customer {
    // 消费消息
    @Test
    public static void main(String[] args) throws IOException, TimeoutException {

        //        // RabbitMQ 生产者的代码
        //        // 1. 创建连接工厂
        //        ConnectionFactory factory = new ConnectionFactory();
        //        // 2. 设置连接地址
        //        factory.setHost("localhost");
        //        factory.setPort(5672);
        //        // 3. 设置虚拟主机
        //        factory.setVirtualHost("ems");
        //        factory.setUsername("ems");
        //        factory.setPassword("123");
        //
        //        //  3. 创建连接
        //        Connection connection = factory.newConnection();
        //通过工具类获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        // 4. 创建通道
        Channel channel = connection.createChannel();
        // 5. 通道绑定队列
        // 参数1：队列名称
        // 参数2：是否持久化,true 表示持久化，false 表示不持久化
        // 参数3：是否独占队列,true 表示这个队列只有一个消费者，false 表示这个队列可以多个消费者消费
        //  参数4：是否自动删除,true 表示如果最后一个消费者断开连接，队列则自动删除
        channel.queueDeclare("hello", false, false, false, null);
        // 6. 消费消息
        // 参数1：队列名称
        // 参数2：是否自动确认,true 表示自动确认，false 表示手动确认
        // 参数3：消费者
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者收到消息：" + new String(body));
            }
        });

    }
}
```
<a name="N7nem"></a>
##### 4.4.3 参数的说明
:::info
channel.queueDeclare("hello",true,false,false,null);<br />'参数1':用来声明通道对应的队列<br />'参数2':用来指定是否持久化队列<br />'参数3':用来指定是否独占队列<br />'参数4':用来指定是否自动删除队列<br />'参数5':对队列的额外配置
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837252224-7431a749-7ff7-4afd-a860-d481aad1fc76.png#averageHue=%23f9f8f8&clientId=ud104d685-f834-4&from=paste&id=u433e1fc9&originHeight=264&originWidth=1001&originalType=url&ratio=1&rotation=0&showTitle=false&size=43667&status=done&style=none&taskId=u43a5b120-6b88-4be4-aba3-fb4efbd6097&title=)<br />![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837252253-a58e04b6-7e55-4bce-9bff-a15b5b61dfe8.png#averageHue=%23fdfcfc&clientId=ud104d685-f834-4&from=paste&id=u9beb20f6&originHeight=34&originWidth=537&originalType=url&ratio=1&rotation=0&showTitle=false&size=2044&status=done&style=none&taskId=u606038db-e6d8-4958-a803-5e3cd73bff6&title=)
<a name="a05Xq"></a>
##### 4.4.4 工具类的包装
:::info
package utils;

import com.rabbitmq.client.Channel;<br />import com.rabbitmq.client.Connection;<br />import com.rabbitmq.client.ConnectionFactory;

import java.util.Properties;

public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory;<br />    private static Properties properties;<br />    static{<br />        //重量级资源  类加载执行之执行一次<br />        connectionFactory = new ConnectionFactory();<br />        connectionFactory.setHost("192.168.42.134");<br />        connectionFactory.setPort(5672);<br />        connectionFactory.setVirtualHost("/");<br />        connectionFactory.setUsername("admin");<br />        connectionFactory.setPassword("admin");

    }

    //定义提供连接对象的方法<br />    public static Connection getConnection() {<br />        try {<br />            return connectionFactory.newConnection();<br />        } catch (Exception e) {<br />            e.printStackTrace();<br />        }<br />        return null;<br />    }

    //关闭通道和关闭连接工具方法<br />    public static void closeConnectionAndChanel(Channel channel, Connection conn) {<br />        try {<br />            if(channel!=null) channel.close();<br />            if(conn!=null)   conn.close();<br />        } catch (Exception e) {<br />            e.printStackTrace();

        }<br />    }

    public static void main(String[] args) {<br />        //System.out.println("RabbitMQUtils.getConnection() = " + RabbitMQUtils.getConnection());<br />    }<br />}
:::
<a name="ESmh2"></a>
#### 4.5 第二种模型(work quene任务模型)
:::info
Work queues，也被称为（Task queues），任务模型。<br />当消息处理比较耗时的时候，可能生产消息的速度会<br />远远大于消息的消费速度。长此以往，消息就会堆积<br />越来越多，无法及时处理。此时就可以使用work 模型：<br />让多个消费者绑定到一个队列，共同消费队列中的消息。<br />队列中的消息一旦消费，就会消失，因此任务是不会被重复执行的。
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837252366-e6eca4a2-61a1-4cd2-ab2f-7010d46ff845.png#averageHue=%23fdfcfc&clientId=ud104d685-f834-4&from=paste&id=u15077d0b&originHeight=252&originWidth=1814&originalType=url&ratio=1&rotation=0&showTitle=false&size=24125&status=done&style=none&taskId=ud10dc8fe-9e78-4451-ab5b-7aca5f8db5a&title=)
:::info
角色：

P：生产者：任务的发布者<br />C1：消费者-1，领取任务并且完成任务，假设完成速度较慢<br />C2：消费者-2：领取任务并完成任务，假设完成速度快
:::
<a name="oCGx3"></a>
##### 4.5.1 开发生产者
:::info
channel.queueDeclare("hello", true, false, false, null);<br />for (int i = 0; i < 10; i++) {<br />  channel.basicPublish("", "hello", null, (i+"====>:我是消息").getBytes());<br />}
:::
<a name="jf8Zz"></a>
##### 4.5.2 开发消费者-1
:::info
channel.queueDeclare("hello",true,false,false,null);<br />channel.basicConsume("hello",true,new DefaultConsumer(channel){<br />  @Override<br />  public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {<br />    System.out.println("消费者1: "+new String(body));<br />  }<br />});
:::
<a name="sWls0"></a>
##### 4.5.3 开发消费者-2
:::info
channel.queueDeclare("hello",true,false,false,null);<br />channel.basicConsume("hello",true,new DefaultConsumer(channel){<br />  @Override<br />  public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {<br />    try {<br />      Thread.sleep(1000); //处理消息比较慢 一秒处理一个消息<br />    } catch (InterruptedException e) {<br />      e.printStackTrace();<br />    }<br />    System.out.println("消费者2: "+new String(body));  <br />  }<br />});
:::
<a name="XIurK"></a>
##### 4.5.4 测试结果
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837252428-803bf268-fc38-42a6-afef-f2e7512fb40d.png#averageHue=%23333333&clientId=ud104d685-f834-4&from=paste&id=u307fb262&originHeight=178&originWidth=1924&originalType=url&ratio=1&rotation=0&showTitle=false&size=52966&status=done&style=none&taskId=u216b8c33-69f6-4b85-9e8c-8c39121f767&title=)<br />![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837253243-e68f714e-7258-46fa-9b16-73efd927e6dd.png#averageHue=%23323232&clientId=ud104d685-f834-4&from=paste&id=ub2995398&originHeight=179&originWidth=2161&originalType=url&ratio=1&rotation=0&showTitle=false&size=57130&status=done&style=none&taskId=u4a777cf7-a1d8-4d78-b759-7551a94b435&title=)
:::info
总结:默认情况下，RabbitMQ将按顺序将每个消息发送给<br />下一个使用者。平均而言，每个消费者都会收到相同数量<br />的消息。这种分发消息的方式称为循环。
:::
<a name="ExG3C"></a>
##### 4.5.5 消息自动确认机制
:::info
Doing a task can take a few seconds. You may<br />wonder what happens if one of the consumers <br />starts a long task and dies with it only partly <br />done.With our current code,once RabbitMQ <br />delivers a message to the consumer it immediately<br />marks it for deletion. In this case, if you kill a <br />worker we will lose the message it was just<br />processing. We'llalso lose all the messages that  <br />were dispatched to this particular worker but <br />were not yet handled.

But we don't want to lose any tasks. If a worker <br />dies,we'd like the task to be delivered to <br />another worker.

完成一项任务可能需要几秒钟。您可能想知道，如果其中一个消费者<br />开始了一项长期任务，但只完成了一部分就死了，会发生什么情况。<br />在我们当前的代码中，一旦RabbitMQ将消息传递给使用者，<br />它就会立即将其标记为删除。在这种情况下，<br />如果您杀死一个worker，我们将丢失它刚刚处理的消息。<br />我们还将丢失发送给该特定工作进程但尚未处理的所有消息。

但我们不想失去任何任务。如果一个worker死了，我们希望把<br />任务交给另一个工人。
:::
<a name="jAGEy"></a>
###### 4.5.5.1 开发生产者
:::info
package workquene;

import com.rabbitmq.client.Channel;<br />import com.rabbitmq.client.Connection;<br />import utils.RabbitMQUtils;

import java.io.IOException;

public class Provider {<br />    public static void main(String[] args) throws IOException {<br />        //获取连接对象<br />        Connection connection = RabbitMQUtils.getConnection();<br />        //获取通道对象<br />        Channel channel = connection.createChannel();

        //通过通道声明队列<br />        channel.queueDeclare("work", true, false, false, null);

        for (int i = 1; i <=20; i++) {<br />            //生产消息<br />            channel.basicPublish("", "work", null, (i + "hello work quene").getBytes());<br />        }

        //关闭资源<br />        RabbitMQUtils.closeConnectionAndChanel(channel, connection);

    }<br />}
:::
<a name="AMPcv"></a>
###### 4.5.5.2 开发消费者-1
:::info
消费之1<br />package workquene;

import com.rabbitmq.client.*;<br />import utils.RabbitMQUtils;

import java.io.IOException;

public class Customer1 {<br />    public static void main(String[] args) throws IOException {

        //获取连接<br />        Connection connection = RabbitMQUtils.getConnection();<br />        final Channel channel = connection.createChannel();<br />        channel.basicQos(1);//每一次只能消费一个消息<br />        channel.queueDeclare("work",true,false,false,null);<br />        //参数1:队列名称  参数2:消息自动确认 true  消费者自动向rabbitmq确认消息消费  false 不会自动确认消息<br />        channel.basicConsume("work",false,new DefaultConsumer(channel){<br />            @Override<br />            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {<br />                try{<br />                    Thread.sleep(2000);<br />                }catch (Exception e){<br />                    e.printStackTrace();<br />                }<br />                System.out.println("消费者-1: "+new String(body));<br />                // 参数1:确认队列中那个具体消息 参数2:是否开启多个消息同时确实<br />                channel.basicAck(envelope.getDeliveryTag(),false);<br />            }<br />        });

    }<br />}
:::
<a name="z3NUq"></a>
###### 4.5.5.3 开发消费者-2
:::info
package workquene;

import com.rabbitmq.client.*;<br />import utils.RabbitMQUtils;

import java.io.IOException;

public class Customer2 {<br />    public static void main(String[] args) throws IOException {

        //获取连接<br />        Connection connection = RabbitMQUtils.getConnection();<br />        final Channel channel = connection.createChannel();

        channel.basicQos(1);<br />        channel.queueDeclare("work",true,false,false,null);

        channel.basicConsume("work",false,new DefaultConsumer(channel){<br />            @Override<br />            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {<br />                System.out.println("消费者-2: "+new String(body));<br />                //手动确认  参数1:手动确认消息标识  参数2:false 每次确认一个<br />                channel.basicAck(envelope.getDeliveryTag(), false);<br />            }<br />        });

        

    }<br />}
:::
:::info
设置通道一次只能消费一个消息<br />关闭消息的自动确认,开启手动确认消息
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837253230-eec49c43-b459-445b-8232-1f7e05f4e03e.png#averageHue=%23d9a97c&clientId=ud104d685-f834-4&from=paste&id=u7a228a8a&originHeight=174&originWidth=727&originalType=url&ratio=1&rotation=0&showTitle=false&size=24308&status=done&style=none&taskId=u4054fe8e-a434-4146-b0ae-a8686e3b783&title=)<br />![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837253626-37f71e1f-d06f-4fa9-8313-02bc87c4994d.png#averageHue=%23faf9f7&clientId=ud104d685-f834-4&from=paste&id=u3f14aad3&originHeight=536&originWidth=857&originalType=url&ratio=1&rotation=0&showTitle=false&size=99830&status=done&style=none&taskId=uc6ef94c5-0202-4496-950e-6139e40e1be&title=)
<a name="O55Hv"></a>
#### 4.5 第三种模型(fanout广播模型)
:::info
fanout 扇出 也称为广播
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837253651-7061e5d7-47e2-40e5-a07e-5c5f62a86b68.png#averageHue=%23fbf7f6&clientId=ud104d685-f834-4&from=paste&id=u39ef4959&originHeight=199&originWidth=852&originalType=url&ratio=1&rotation=0&showTitle=false&size=21866&status=done&style=none&taskId=uaaf9b655-e31a-4df3-8405-fffcf604db7&title=)
:::info
在广播模式下，消息发送流程是这样的：

可以有多个消费者<br />每个消费者有自己的queue（队列）<br />每个队列都要绑定到Exchange（交换机）<br />生产者发送的消息，只能发送到交换机，<br />交换机来决定要发给哪个队列，生产者无法决定。<br />交换机把消息发送给绑定过的所有队列<br />队列的消费者都能拿到消息。实现一条消息被多个消费者消费
:::
<a name="LSN2W"></a>
##### 4.5.1 开发生产者
:::info
package fanout;

import com.rabbitmq.client.Channel;<br />import com.rabbitmq.client.Connection;<br />import utils.RabbitMQUtils;

import java.io.IOException;

public class Provider {

    public static void main(String[] args) throws IOException {<br />        //获取连接对象<br />        Connection connection = RabbitMQUtils.getConnection();<br />        Channel channel = connection.createChannel();

        //将通道声明指定交换机   //参数1: 交换机名称    参数2: 交换机类型  fanout 广播类型<br />        channel.exchangeDeclare("logs","fanout");

        //发送消息<br />        channel.basicPublish("logs","",null,"fanout type message".getBytes());

        //释放资源<br />        RabbitMQUtils.closeConnectionAndChanel(channel,connection);

    }<br />}
:::
<a name="LbX07"></a>
##### 4.5.2 开发消费者-1
:::info
package fanout;

import com.rabbitmq.client.*;<br />import utils.RabbitMQUtils;

import java.io.IOException;

public class Customer1 {<br />    public static void main(String[] args) throws IOException {<br />        //获取连接对象<br />        Connection connection = RabbitMQUtils.getConnection();<br />        Channel channel = connection.createChannel();

        //通道绑定交换机<br />        channel.exchangeDeclare("logs","fanout");

        //临时队列<br />        String queueName = channel.queueDeclare().getQueue();

        //绑定交换机和队列<br />        channel.queueBind(queueName,"logs","");

        //消费消息<br />        channel.basicConsume(queueName,true,new DefaultConsumer(channel){<br />            @Override<br />            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {<br />                System.out.println("消费者1: "+new String(body));<br />            }<br />        });

    }<br />}
:::
<a name="QGHb6"></a>
##### 4.5.3 开发消费者-2
:::info
package fanout;

import com.rabbitmq.client.*;<br />import utils.RabbitMQUtils;

import java.io.IOException;

public class Customer2 {<br />    public static void main(String[] args) throws IOException {<br />        //获取连接对象<br />        Connection connection = RabbitMQUtils.getConnection();<br />        Channel channel = connection.createChannel();

        //通道绑定交换机<br />        channel.exchangeDeclare("logs","fanout");

        //临时队列<br />        String queueName = channel.queueDeclare().getQueue();

        //绑定交换机和队列<br />        channel.queueBind(queueName,"logs","");

        //消费消息<br />        channel.basicConsume(queueName,true,new DefaultConsumer(channel){<br />            @Override<br />            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {<br />                System.out.println("消费者2: "+new String(body));<br />            }<br />        });

    }<br />}
:::
<a name="lMpdv"></a>
##### 4.5.4 开发消费者-3
:::info
package fanout;

import com.rabbitmq.client.*;<br />import utils.RabbitMQUtils;

import java.io.IOException;

public class Customer3 {<br />    public static void main(String[] args) throws IOException {<br />        //获取连接对象<br />        Connection connection = RabbitMQUtils.getConnection();<br />        Channel channel = connection.createChannel();

        //通道绑定交换机<br />        channel.exchangeDeclare("logs","fanout");

        //临时队列<br />        String queueName = channel.queueDeclare().getQueue();

        //绑定交换机和队列<br />        channel.queueBind(queueName,"logs","");

        //消费消息<br />        channel.basicConsume(queueName,true,new DefaultConsumer(channel){<br />            @Override<br />            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {<br />                System.out.println("消费者3: "+new String(body));<br />            }<br />        });

    }<br />}
:::
<a name="Crhin"></a>
##### 4.5.5 测试结果
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837253727-921963ea-0c87-436a-b4fc-39448e87f69c.png#averageHue=%23f8f7f6&clientId=ud104d685-f834-4&from=paste&id=u1c13d699&originHeight=29&originWidth=435&originalType=url&ratio=1&rotation=0&showTitle=false&size=2676&status=done&style=none&taskId=ua5383468-db5f-4cab-8971-75bd067116d&title=)<br />![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837253691-4c09d836-c44e-420c-b42f-1ecd88f1671e.png#averageHue=%23f3f1ee&clientId=ud104d685-f834-4&from=paste&id=u6c9dc54f&originHeight=23&originWidth=365&originalType=url&ratio=1&rotation=0&showTitle=false&size=2646&status=done&style=none&taskId=u72b03e7f-3234-48c1-a839-29aaf9b5f1d&title=)<br />![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837253811-2a1733b9-eeac-4788-9cca-78ce71db6758.png#averageHue=%23f5f4f2&clientId=ud104d685-f834-4&from=paste&id=udd93c860&originHeight=25&originWidth=355&originalType=url&ratio=1&rotation=0&showTitle=false&size=2649&status=done&style=none&taskId=ud82cc664-90d3-474e-868e-52d937c68d5&title=)
<a name="EM4Wi"></a>
#### 4.6 第四种模型(Routing)
<a name="JODLK"></a>
##### 4.6.1 Routing 之订阅模型-Direct(直连)
:::info
在Fanout模式中，一条消息，会被所有订阅的队列都消费。<br />但是，在某些场景下，我们希望不同的消息被不同的队列消费。<br />这时就要用到Direct类型的Exchange。
:::
:::info
在Direct模型下：队列与交换机的绑定，不能是任意绑定了，<br />而是要指定一个RoutingKey（路由key）<br />消息的发送方在 向 Exchange发送消息时，<br />也必须指定消息的 RoutingKey。

Exchange不再把消息交给每一个绑定的队列，<br />而是根据消息的Routing Key进行判断，<br />只有队列的Routingkey与消息的 Routing key完全一致，<br />才会接收到消息
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837253876-0622ff91-83db-4809-9938-6d8469324c49.png#averageHue=%23faf6f5&clientId=ud104d685-f834-4&from=paste&id=u689e3c6d&originHeight=207&originWidth=853&originalType=url&ratio=1&rotation=0&showTitle=false&size=29127&status=done&style=none&taskId=u57f167fb-53ee-4aeb-b188-e207f076ae4&title=)
:::info
P：生产者，向Exchange发送消息，发送消息时，<br />会指定一个routing key。<br />X：Exchange（交换机），接收生产者的消息，<br />然后把消息递交给 与routing key完全匹配的队列<br />C1：消费者，其所在队列指定了需要routing key 为 error 的消息<br />C2：消费者，其所在队列指定了需要routing key 为 info、<br />error、warning 的消息
:::
<a name="otydV"></a>
###### 4.6.1.1 开发生产者
:::info
package direct;

import com.rabbitmq.client.Channel;<br />import com.rabbitmq.client.Connection;<br />import utils.RabbitMQUtils;

import java.io.IOException;

public class Provider {<br />    public static void main(String[] args) throws IOException {<br />        //获取连接对象<br />        Connection connection = RabbitMQUtils.getConnection();<br />        //获取连接通道对象<br />        Channel channel = connection.createChannel();<br />        String exchangeName = "logs_direct";<br />        //通过通道声明交换机  参数1:交换机名称  参数2:direct  路由模式<br />        channel.exchangeDeclare(exchangeName,"direct");<br />        //发送消息<br />        String routingkey = "info";<br />        channel.basicPublish(exchangeName,routingkey,null,("这是direct模型发布的基于route key: ["+routingkey+"] 发送的消息").getBytes());

        //关闭资源<br />        RabbitMQUtils.closeConnectionAndChanel(channel,connection);<br />    }<br />}
:::
<a name="mAYkr"></a>
###### 4.6.1.2 开发消费者-1
:::info
package direct;

import com.rabbitmq.client.*;<br />import utils.RabbitMQUtils;

import java.io.IOException;

public class Customer1 {<br />    public static void main(String[] args) throws IOException {<br />        Connection connection = RabbitMQUtils.getConnection();<br />        Channel channel = connection.createChannel();

        String exchangeName = "logs_direct";

        //通道声明交换机以及交换的类型<br />        channel.exchangeDeclare(exchangeName,"direct");

        //创建一个临时队列<br />        String queue = channel.queueDeclare().getQueue();

        //基于route key绑定队列和交换机<br />        channel.queueBind(queue,exchangeName,"error");

        //获取消费的消息<br />        channel.basicConsume(queue,true,new DefaultConsumer(channel){<br />            @Override<br />            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {<br />                System.out.println("消费者1: "+ new String(body));<br />            }<br />        });

    }<br />}
:::
<a name="qYeN7"></a>
###### 4.6.1.3 开发消费者-2
:::info
package direct;

import com.rabbitmq.client.*;<br />import utils.RabbitMQUtils;

import java.io.IOException;

public class Customer2 {<br />    public static void main(String[] args) throws IOException {<br />        Connection connection = RabbitMQUtils.getConnection();<br />        Channel channel = connection.createChannel();

        String exchangeName = "logs_direct";

        //声明交换机 以及交换机类型 direct<br />        channel.exchangeDeclare(exchangeName,"direct");

        //创建一个临时队列<br />        String queue = channel.queueDeclare().getQueue();

        //临时队列和交换机绑定<br />        channel.queueBind(queue,exchangeName,"info");<br />        channel.queueBind(queue,exchangeName,"error");<br />        channel.queueBind(queue,exchangeName,"warning");

        //消费消息<br />        channel.basicConsume(queue,true,new DefaultConsumer(channel){<br />            @Override<br />            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {<br />                System.out.println("消费者2: "+new String(body));<br />            }<br />        });

    }<br />}
:::
<a name="FMzHf"></a>
###### 4.6.1.4 测试生产者发送Route key为error的消息时
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837253967-968ed37a-89aa-449e-86d1-0a793297eebc.png#averageHue=%23fcfbfb&clientId=ud104d685-f834-4&from=paste&id=u3437adb9&originHeight=152&originWidth=678&originalType=url&ratio=1&rotation=0&showTitle=false&size=7410&status=done&style=none&taskId=u7908ad9b-a2b7-4a8e-85b8-77bca046b94&title=)<br />![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837254009-03de3257-1a1a-44ca-a630-7e595b4535ec.png#averageHue=%23fafaf9&clientId=ud104d685-f834-4&from=paste&id=udd43cdb2&originHeight=155&originWidth=615&originalType=url&ratio=1&rotation=0&showTitle=false&size=9771&status=done&style=none&taskId=u9e4cae36-3f63-49ad-bd4c-0a0b4eb5bf8&title=)
<a name="XpXdl"></a>
###### 4.6.1.5 测试生产者发送Route key为info的消息时
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837254125-6865a8cd-312a-43f1-b36e-d6099d9cffcf.png#averageHue=%23fdfdfd&clientId=ud104d685-f834-4&from=paste&id=uf525c697&originHeight=175&originWidth=713&originalType=url&ratio=1&rotation=0&showTitle=false&size=6928&status=done&style=none&taskId=u80cab7c1-3669-41ef-abfd-d778c633673&title=)<br />![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837254157-fd010be6-b193-4902-8d77-83f4f1d7e4b1.png#averageHue=%23f9f8f8&clientId=ud104d685-f834-4&from=paste&id=u35c899f5&originHeight=138&originWidth=730&originalType=url&ratio=1&rotation=0&showTitle=false&size=9984&status=done&style=none&taskId=uedf242a8-085a-4836-8561-49f3c2e31a9&title=)
<a name="PLnXq"></a>
##### 4.6.2 Routing 之订阅模型-Topic
:::info
Topic类型的Exchange与Direct相比，都是可以根据RoutingKey<br />把消息路由到不同的队列。只不过Topic类型Exchange可以让<br />队列在绑定Routing key的时候使用通配符！这种模型Routingkey <br />一般都是由一个或多个单词组成，多个单词之间以”.”分割，<br />例如： item.insert
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837254756-9b429f21-568d-4be9-879f-e1ac18d4766d.png#averageHue=%23fcf4f4&clientId=ud104d685-f834-4&from=paste&id=u1d84659d&originHeight=520&originWidth=2533&originalType=url&ratio=1&rotation=0&showTitle=false&size=112198&status=done&style=none&taskId=uc787562f-8008-42f6-82c3-444a5391ef2&title=)
:::info
# 统配符<br />		* (star) can substitute for exactly one word.    匹配不多不少恰好1个词<br />		# (hash) can substitute for zero or more words.  匹配零个、一个或多个词<br /># 如:<br />		audit.#    匹配audit、audit.irs 、或者audit.irs.corporate等<br />    audit.*   只能匹配 audit.irs
:::
<a name="oHx59"></a>
###### 4.6.2.1 开发生产者
:::info
package topic;

import com.rabbitmq.client.Channel;<br />import com.rabbitmq.client.Connection;<br />import utils.RabbitMQUtils;

import java.io.IOException;

public class Provider {<br />    public static void main(String[] args) throws IOException {<br />        //获取连接对象<br />        Connection connection = RabbitMQUtils.getConnection();<br />        Channel channel = connection.createChannel();

        //声明交换机以及交换机类型 topic<br />        channel.exchangeDeclare("topics","topic");

        //发布消息<br />        String routekey = "save.user.delete";

        channel.basicPublish("topics",routekey,null,("这里是topic动态路由模型,routekey: ["+routekey+"]").getBytes());

        //关闭资源<br />        RabbitMQUtils.closeConnectionAndChanel(channel,connection);

    }<br />}
:::
<a name="XGJ7o"></a>
###### 4.6.2.2 开发消费者-1
:::info
Routing Key中使用*通配符方式
:::
:::info
package topic;

import com.rabbitmq.client.*;<br />import utils.RabbitMQUtils;

import java.io.IOException;

public class Customer1 {<br />    public static void main(String[] args) throws IOException {

        //获取连接<br />        Connection connection = RabbitMQUtils.getConnection();<br />        Channel channel = connection.createChannel();

        //声明交换机以及交换机类型<br />        channel.exchangeDeclare("topics","topic");<br />        //创建一个临时队列<br />        String queue = channel.queueDeclare().getQueue();<br />        //绑定队列和交换机  动态统配符形式route key<br />        channel.queueBind(queue,"topics","*.user.*");

        //消费消息<br />        channel.basicConsume(queue,true,new DefaultConsumer(channel){<br />            @Override<br />            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {<br />                System.out.println("消费者1: "+ new String(body));<br />            }<br />        });<br />    }<br />}
:::
<a name="q9TDp"></a>
###### 4.6.2.3 开发消费者-2
:::info
Routing Key中使用#通配符方式
:::
:::info
package topic;

import com.rabbitmq.client.*;<br />import utils.RabbitMQUtils;

import java.io.IOException;

public class Customer2 {<br />    public static void main(String[] args) throws IOException {

        //获取连接<br />        Connection connection = RabbitMQUtils.getConnection();<br />        Channel channel = connection.createChannel();

        //声明交换机以及交换机类型<br />        channel.exchangeDeclare("topics","topic");<br />        //创建一个临时队列<br />        String queue = channel.queueDeclare().getQueue();<br />        //绑定队列和交换机  动态统配符形式route key<br />        channel.queueBind(queue,"topics","*.user.#");

        //消费消息<br />        channel.basicConsume(queue,true,new DefaultConsumer(channel){<br />            @Override<br />            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {<br />                System.out.println("消费者2: "+ new String(body));<br />            }<br />        });<br />    }<br />}
:::
<a name="dqH3d"></a>
###### 4.6.2.4 测试结果
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837254709-5edc4ab0-00c5-47c6-bcac-6ab5967b39fc.png#averageHue=%23fbf9f8&clientId=ud104d685-f834-4&from=paste&id=u6af606be&originHeight=219&originWidth=868&originalType=url&ratio=1&rotation=0&showTitle=false&size=49364&status=done&style=none&taskId=uc02fa230-a832-49b0-85f1-055527db067&title=)<br />![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837254757-0887ea10-da34-487a-b4d1-552282c740c8.png#averageHue=%23fbfaf9&clientId=ud104d685-f834-4&from=paste&id=ua6faf973&originHeight=291&originWidth=940&originalType=url&ratio=1&rotation=0&showTitle=false&size=50995&status=done&style=none&taskId=u6628b798-c441-4d5a-9fa8-8408e2bd5a3&title=)
<a name="lTSdf"></a>
### 5 SpringBoot中使用RabbitMQ
<a name="TmIFG"></a>
#### 5.1 搭建初始环境
<a name="auHBy"></a>
##### 5.1.1 引入依赖
:::info
<dependency><br />  <groupId>org.springframework.boot</groupId><br />  <artifactId>spring-boot-starter-amqp</artifactId><br /></dependency>
:::
<a name="ydHYD"></a>
##### 5.1.2 配置配置文件
:::info
spring:<br />  application:<br />    name: springboot_rabbitmq<br />  rabbitmq:<br />    host: 10.15.0.9<br />    port: 5672<br />    username: ems<br />    password: 123<br />    virtual-host: /ems
:::
:::info
RabbitTemplate用来简化操作    使用时候直接在项目中注入即可使用
:::
<a name="Lg2xX"></a>
#### 5.2 第一种hello world模型使用
:::info
开发生产者
:::
:::info
package com.example;

import org.junit.jupiter.api.Test;<br />import org.springframework.amqp.rabbit.core.RabbitTemplate;<br />import org.springframework.beans.factory.annotation.Autowired;<br />import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest<br />class DemoApplicationTests {

    @Autowired<br />    private RabbitTemplate rabbitTemplate;<br />    @Test<br />    void contextLoads() {<br />        rabbitTemplate.convertAndSend("hello","hello world"); <br />// 生产端没有指定交换机只有routingKey和Object。<br />//消费方产生hello队列，放在默认的交换机(AMQP default)上。<br />//而默认的交换机有一个特点，只要你的routerKey的名字与这个<br />//交换机的队列有相同的名字，他就会自动路由上。 <br />//生产端routingKey 叫hello ，消费端生产hello队列。<br />//他们就路由上了<br />    }

}
:::
:::info
开发消费者
:::
:::info
import org.springframework.amqp.rabbit.annotation.Queue;<br />import org.springframework.amqp.rabbit.annotation.RabbitHandler;<br />import org.springframework.amqp.rabbit.annotation.RabbitListener;<br />import org.springframework.stereotype.Component;

@Component<br />// 生产端没有指定交换机只有routingKey和Object。<br />//消费方产生hello队列，放在默认的交换机(AMQP default)上。<br />//而默认的交换机有一个特点，只要你的routerKey的名字与这个<br />//交换机的队列有相同的名字，他就会自动路由上。 <br />//生产端routingKey 叫hello ，消费端生产hello队列。<br />//他们就路由上了<br />@RabbitListener(queuesToDeclare = @Queue(value = "hello"))<br />public class HelloCustomer {

    @RabbitHandler<br />    public void receive1(String message){<br />        System.out.println("message = " + message);<br />    }

}
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837254736-8d1972a0-bb03-4f94-abb3-743355147fda.png#averageHue=%23fcfaf9&clientId=ud104d685-f834-4&from=paste&id=u06b7bdac&originHeight=29&originWidth=366&originalType=url&ratio=1&rotation=0&showTitle=false&size=2231&status=done&style=none&taskId=ub8932e3e-21a5-421d-8dcb-26b246b95b0&title=)
<a name="gAmgW"></a>
#### 5.3 第二种work模型使用
:::info
开发生产者
:::
:::info
package com.example;

import org.junit.jupiter.api.Test;<br />import org.springframework.amqp.rabbit.core.RabbitTemplate;<br />import org.springframework.beans.factory.annotation.Autowired;<br />import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest<br />class DemoApplicationTests {

    @Autowired<br />    private RabbitTemplate rabbitTemplate;<br />    @Test<br />    void contextLoads() {<br />        for (int i = 0; i < 10; i++) {<br />            rabbitTemplate.convertAndSend("work","hello work!"); <br />			// 生产端没有指定交换机只有routingKey和Object。<br />			//消费方产生work队列，放在默认的交换机(AMQP default)上。<br />			//而默认的交换机有一个特点，只要你的routerKey的名字与这个<br />			//交换机的队列有相同的名字，他就会自动路由上。 <br />			//生产端routingKey 叫work ，消费端生产work队列。<br />			//他们就路由上了	             <br />        }<br />    }

}
:::
:::info
开发消费者
:::
:::info
package com.example;

import org.springframework.amqp.rabbit.annotation.Queue;<br />import org.springframework.amqp.rabbit.annotation.RabbitListener;<br />import org.springframework.stereotype.Component;

@Component<br />public class WorkCustomer {<br />	// 生产端没有指定交换机只有routingKey和Object。<br />	//消费方产生work队列，放在默认的交换机(AMQP default)上。<br />	//而默认的交换机有一个特点，只要你的routerKey的名字与这个<br />	//交换机的队列有相同的名字，他就会自动路由上。 <br />	//生产端routingKey 叫work ，消费端生产work队列。<br />	//他们就路由上了	             <br />    @RabbitListener(queuesToDeclare = @Queue("work"))<br />    public void receive1(String message){<br />        System.out.println("work message1 = " + message);<br />    }

    @RabbitListener(queuesToDeclare = @Queue("work"))<br />    public void receive2(String message){<br />        System.out.println("work message2 = " + message);<br />    }<br />}
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837255332-7fe842b1-b191-45ac-b6d6-89d6d99274bf.png#averageHue=%23fcfaf9&clientId=ud104d685-f834-4&from=paste&id=u259fadad&originHeight=221&originWidth=614&originalType=url&ratio=1&rotation=0&showTitle=false&size=40631&status=done&style=none&taskId=ub9fef88d-b0a0-4255-a621-ac724665f7e&title=)
:::info
说明:默认在Spring AMQP实现中Work这种方式就是公平调度,如果需要实现能者多劳需要外配置
:::
<a name="RAM8Z"></a>
#### 5.4 Fanout 广播模型
:::info
开发生产者
:::
:::info
package com.example;

import org.junit.jupiter.api.Test;<br />import org.springframework.amqp.rabbit.core.RabbitTemplate;<br />import org.springframework.beans.factory.annotation.Autowired;<br />import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest<br />class DemoApplicationTests {

    @Autowired<br />    private RabbitTemplate rabbitTemplate;<br />    @Test<br />    void contextLoads() {<br />        rabbitTemplate.convertAndSend("logs","","这是日志广播"); // 参数1为交换机，参数2为路由key，“”表示为任意路由，参数3为消息内容<br />    }

}
:::
:::info
开发消费者
:::
:::info
package com.example;

import org.springframework.amqp.rabbit.annotation.Exchange;<br />import org.springframework.amqp.rabbit.annotation.Queue;<br />import org.springframework.amqp.rabbit.annotation.QueueBinding;<br />import org.springframework.amqp.rabbit.annotation.RabbitListener;<br />import org.springframework.stereotype.Component;

@Component<br />public class WorkCustomer {<br />    @RabbitListener(bindings = @QueueBinding(<br />            value = @Queue, // 创建临时队列<br />            exchange = @Exchange(name = "logs", type = "fanout")<br />    ))<br />    public void receive1(String message) {<br />        System.out.println("message1 = " + message);<br />    }

    @RabbitListener(bindings = @QueueBinding(<br />            value = @Queue, //创建临时队列<br />            exchange = @Exchange(name = "logs", type = "fanout") //绑定交换机类型<br />    ))<br />    public void receive2(String message) {<br />        System.out.println("message2 = " + message);<br />    }<br />}
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837255325-1806fee0-7c4b-4a9b-a099-8153328e6958.png#averageHue=%23f5f4f3&clientId=ud104d685-f834-4&from=paste&id=uf0534913&originHeight=48&originWidth=392&originalType=url&ratio=1&rotation=0&showTitle=false&size=3516&status=done&style=none&taskId=u99f16938-9051-4985-9774-a0b3c31f6bf&title=)
<a name="N04vX"></a>
#### 5.5 Route 路由模型
:::info
开发生产者
:::
:::info
package com.example;

import org.junit.jupiter.api.Test;<br />import org.springframework.amqp.rabbit.core.RabbitTemplate;<br />import org.springframework.beans.factory.annotation.Autowired;<br />import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest<br />class DemoApplicationTests {

    @Autowired<br />    private RabbitTemplate rabbitTemplate;<br />    @Test<br />    void contextLoads() {<br />        rabbitTemplate.convertAndSend("directs","error","error 的日志信息");<br />    }

}
:::
:::info
开发消费者
:::
:::info
package com.example;

import org.springframework.amqp.rabbit.annotation.Exchange;<br />import org.springframework.amqp.rabbit.annotation.Queue;<br />import org.springframework.amqp.rabbit.annotation.QueueBinding;<br />import org.springframework.amqp.rabbit.annotation.RabbitListener;<br />import org.springframework.stereotype.Component;

@Component<br />public class WorkCustomer {<br />    @RabbitListener(bindings = {<br />            @QueueBinding(<br />                    value = @Queue, // 创建临时队列<br />                    key = {"info", "error"}, // 路由key<br />                    exchange = @Exchange(type = "direct", name = "directs")<br />            )})<br />    public void receive1(String message) {<br />        System.out.println("message1 = " + message);<br />    }

    @RabbitListener(bindings = {<br />            @QueueBinding(<br />                    value = @Queue,<br />                    key = {"error"},<br />                    exchange = @Exchange(type = "direct", name = "directs")<br />            )})<br />    public void receive2(String message) {<br />        System.out.println("message2 = " + message);<br />    }<br />}
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837255323-52d0cb69-2ab4-4e90-8141-268b75a7d717.png#averageHue=%23f5f4f3&clientId=ud104d685-f834-4&from=paste&id=u6aa16f59&originHeight=54&originWidth=360&originalType=url&ratio=1&rotation=0&showTitle=false&size=2992&status=done&style=none&taskId=ue7e8fec1-1631-4457-bf04-b2ce3cff666&title=)
<a name="cY7Yg"></a>
#### 5.6 Topic 订阅模型(动态路由模型)
:::info
开发生产者
:::
:::info
package com.example;

import org.junit.jupiter.api.Test;<br />import org.springframework.amqp.rabbit.core.RabbitTemplate;<br />import org.springframework.beans.factory.annotation.Autowired;<br />import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest<br />class DemoApplicationTests {

    @Autowired<br />    private RabbitTemplate rabbitTemplate;<br />    @Test<br />    void contextLoads() {<br />        rabbitTemplate.convertAndSend("topics","user.save.findAll","user.save.findAll 的消息");<br />    }

}
:::
:::info
开发消费者
:::
:::info
package com.example;

import org.springframework.amqp.rabbit.annotation.Exchange;<br />import org.springframework.amqp.rabbit.annotation.Queue;<br />import org.springframework.amqp.rabbit.annotation.QueueBinding;<br />import org.springframework.amqp.rabbit.annotation.RabbitListener;<br />import org.springframework.stereotype.Component;

@Component<br />public class WorkCustomer {<br />    @RabbitListener(bindings = {<br />            @QueueBinding(<br />                    value = @Queue,<br />                    key = {"user.*"},<br />                    exchange = @Exchange(type = "topic",name = "topics")<br />            )<br />    })<br />    public void receive1(String message){<br />        System.out.println("message1 = " + message);<br />    }

    @RabbitListener(bindings = {<br />            @QueueBinding(<br />                    value = @Queue,<br />                    key = {"user.#"},<br />                    exchange = @Exchange(type = "topic",name = "topics")<br />            )<br />    })<br />    public void receive2(String message){<br />        System.out.println("message2 = " + message);<br />    }<br />}
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837255325-9ce5d352-f870-4b0f-a97c-fb44bbfc8704.png#averageHue=%23f9f8f7&clientId=ud104d685-f834-4&from=paste&id=u5ed05382&originHeight=36&originWidth=447&originalType=url&ratio=1&rotation=0&showTitle=false&size=3198&status=done&style=none&taskId=u2b0ff6a2-a8c8-433b-94ef-aff2d7c959b&title=)
<a name="fAB44"></a>
### 6 MQ的应用场景
<a name="pi0Me"></a>
#### 6.1 异步处理
:::info
场景说明：用户注册后，需要发注册邮件和注册短信,传统的做法有两种 <br />1.串行的方式 2.并行的方式
:::
:::info
串行方式: 将注册信息写入数据库后,发送注册邮件,再发送注册短信,<br />以上三个任务全部完成后才返回给客户端。 这有一个问题是,邮件,<br />短信并不是必须的,它只是一个通知,而这种做法让客户端等待没有<br />必要等待的东西.
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837255566-8e27ce1d-22ba-4ab7-aaae-b8b1b7cdf5a5.png#averageHue=%23010101&clientId=ud104d685-f834-4&from=paste&id=u046bcee6&originHeight=225&originWidth=980&originalType=url&ratio=1&rotation=0&showTitle=false&size=29980&status=done&style=none&taskId=uc69ae339-6080-4f53-aa1f-0c1586a4467&title=)
:::info
并行方式: 将注册信息写入数据库后,发送邮件的同时,发送短信,<br />以上三个任务完成后,返回给客户端,并行的方式能提高处理的时间。
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837255608-6b31c748-9c3c-4147-b3a9-7876c4c8f78c.png#averageHue=%23010101&clientId=ud104d685-f834-4&from=paste&id=uf8b00644&originHeight=383&originWidth=827&originalType=url&ratio=1&rotation=0&showTitle=false&size=34684&status=done&style=none&taskId=uc02dd286-b967-40dd-b7e5-b30eb608ed2&title=)
:::info
消息队列:假设三个业务节点分别使用50ms,串行方式使用时间150ms,<br />并行使用时间100ms。虽然并行已经提高的处理时间,但是,前面说过,<br />邮件和短信对我正常的使用网站没有任何影响，客户端没有必要等<br />着其发送完成才显示注册成功,应该是写入数据库后就返回. 消息队<br />列: 引入消息队列后，把发送邮件,短信不是必须的业务逻辑异步处理
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/35031166/1701837255760-ae462d94-becd-48d9-b146-7944626c2395.jpeg#averageHue=%23f9f9ec&clientId=ud104d685-f834-4&from=paste&id=u318ff772&originHeight=306&originWidth=1070&originalType=url&ratio=1&rotation=0&showTitle=false&size=24939&status=done&style=none&taskId=ue79389c6-f349-42ba-9d63-ba34548726e&title=)
:::info
由此可以看出,引入消息队列后，用户的响应时间就等于写入数据库的<br />时间+写入消息队列的时间(可以忽略不计),引入消息队列后处理后,响<br />应时间是串行的3倍,是并行的2倍。
:::
<a name="tdRSF"></a>
#### 6.2 应用解耦
:::info
场景：双11是购物狂节,用户下单后,订单系统需要通知库存系统,传统<br />的做法就是订单系统调用库存系统的接口.
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837255767-eaa6426b-e065-4453-95bd-833fbb294381.png#averageHue=%2342421d&clientId=ud104d685-f834-4&from=paste&id=ua36a4381&originHeight=178&originWidth=529&originalType=url&ratio=1&rotation=0&showTitle=false&size=10398&status=done&style=none&taskId=u4e8cc0f4-d42d-4fb6-bcbe-744eb948f0c&title=)
:::info
这种做法有一个缺点:<br />当库存系统出现故障时,订单就会失败。 订单系统和库存系统高耦合.  引入消息队列
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837256260-85bf69f6-b886-4e38-a76c-097278d78244.png#averageHue=%23010101&clientId=ud104d685-f834-4&from=paste&id=u7f31349e&originHeight=342&originWidth=697&originalType=url&ratio=1&rotation=0&showTitle=false&size=26884&status=done&style=none&taskId=u6089357d-ef45-4ac7-9e6e-4b147a88e00&title=)
:::info
订单系统:用户下单后,订单系统完成持久化处理,将消息写入消息队列,<br />返回用户订单下单成功。

库存系统:订阅下单的消息,获取下单消息,进行库操作。<br />就算库存系统出现故障,消息队列也能保证消息的可靠投递,<br />不会导致消息丢失.
:::
<a name="W2u1m"></a>
#### 6.3 流量削峰
:::info
场景: 秒杀活动，一般会因为流量过大，导致应用挂掉,为了解决这个<br />问题，一般在应用前端加入消息队列。
:::
:::info
作用:

1.可以控制活动人数，超过此一定阀值的订单直接丢弃(我为什么<br />秒杀一次都没有成功过呢^^) 

2.可以缓解短时间的高流量压垮应用(应用程序按自己的最大处理能力<br />获取订单)
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837256250-817e1e62-40cd-4e10-9f21-f6a5895834f6.png#averageHue=%232b2b14&clientId=ud104d685-f834-4&from=paste&id=u03985567&originHeight=205&originWidth=776&originalType=url&ratio=1&rotation=0&showTitle=false&size=28373&status=done&style=none&taskId=ue51d92e3-baa6-4376-a98f-9a6927e685f&title=)
:::info
1.用户的请求,服务器收到之后,首先写入消息队列,加入消息队列长度<br />超过最大值,则直接抛弃用户请求或跳转到错误页面.  

2.秒杀业务根据消息队列中的请求信息，再做后续处理.
:::
<a name="kd2Qn"></a>
### 7 RabbitMQ的集群
:::info
最新版的3.8版本的可以看我这篇文章<br />https://blog.csdn.net/unique_perfect/article/details/108643804
:::
<a name="tqW85"></a>
#### 7.1 集群架构
<a name="oZBKX"></a>
##### 7.1.1 普通集群(副本集群)
:::info
All data/state required for the operation of a <br />RabbitMQ broker is replicated across all nodes. <br />An exception to this are message queues, which<br />by default reside on one node, though they are <br />visible and reachable from all nodes. To <br />replicate queues across nodes in a cluster   <br />--摘自官网<br />默认情况下:RabbitMQ代理操作所需的所有数据/状态都将跨<br />所有节点复制。这方面的一个例外是消息队列，默认情况下，<br />消息队列位于一个节点上，尽管它们可以从所有节点看到和访问
:::
:::info
架构图
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837256312-7afaa428-00f9-4fe0-a933-d1c427c0e38a.png#averageHue=%23fcfaf8&clientId=ud104d685-f834-4&from=paste&id=ubd6b2d2c&originHeight=332&originWidth=1091&originalType=url&ratio=1&rotation=0&showTitle=false&size=41435&status=done&style=none&taskId=u3b4b86af-dad1-4be4-8c70-88f3c8909ba&title=)
:::info
核心解决问题:  当集群中某一时刻master节点宕机,<br />可以对Quene中信息,进行备份
:::
:::info
集群搭建
:::
:::info
# 0.集群规划<br />	node1: 10.15.0.3  mq1  master 主节点<br />	node2: 10.15.0.4  mq2  repl1  副本节点<br />	node3: 10.15.0.5  mq3  repl2  副本节点

# 1.克隆三台机器主机名和ip映射<br />	vim /etc/hosts加入:<br />		 10.15.0.3 mq1<br />    	10.15.0.4 mq2<br />    	10.15.0.5 mq3<br />	node1: vim /etc/hostname 加入:  mq1<br />	node2: vim /etc/hostname 加入:  mq2<br />	node3: vim /etc/hostname 加入:  mq3

# 2.三个机器安装rabbitmq,并同步cookie文件,在node1上执行:<br />	scp /var/lib/rabbitmq/.erlang.cookie root@mq2:/var/lib/rabbitmq/<br />	scp /var/lib/rabbitmq/.erlang.cookie root@mq3:/var/lib/rabbitmq/

# 3.查看cookie是否一致:<br />	node1: cat /var/lib/rabbitmq/.erlang.cookie <br />	node2: cat /var/lib/rabbitmq/.erlang.cookie <br />	node3: cat /var/lib/rabbitmq/.erlang.cookie 

# 4.后台启动rabbitmq所有节点执行如下命令,启动成功访问管理界面:<br />	rabbitmq-server -detached 

# 5.在node2和node3执行加入集群命令:<br />	1.关闭       rabbitmqctl stop_app<br />	2.加入集群    rabbitmqctl join_cluster rabbit@mq1<br />	3.启动服务    rabbitmqctl start_app

# 6.查看集群状态,任意节点执行:<br />	rabbitmqctl cluster_status

# 7.如果出现如下显示,集群搭建成功:<br />	Cluster status of node rabbit@mq3 ...<br />	[{nodes,[{disc,[rabbit@mq1,rabbit@mq2,rabbit@mq3]}]},<br />	{running_nodes,[rabbit@mq1,rabbit@mq2,rabbit@mq3]},<br />	{cluster_name,<<"rabbit@mq1">>},<br />	{partitions,[]},<br />	{alarms,[{rabbit@mq1,[]},{rabbit@mq2,[]},{rabbit@mq3,[]}]}]

# 8.登录管理界面,展示如下状态:
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837256694-8111a3bb-39f1-42e4-b7cd-c60f19ec28ad.png#averageHue=%23e9e9e9&clientId=ud104d685-f834-4&from=paste&id=u9c96744e&originHeight=473&originWidth=2481&originalType=url&ratio=1&rotation=0&showTitle=false&size=267148&status=done&style=none&taskId=u0705593b-c493-44e5-ab85-2812b928cd4&title=)
:::info
# 9.测试集群在node1上,创建队列
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837256658-961473df-cf47-48ec-848e-6b36ced9c76e.png#averageHue=%23f6f5f5&clientId=ud104d685-f834-4&from=paste&id=u4a340739&originHeight=444&originWidth=998&originalType=url&ratio=1&rotation=0&showTitle=false&size=86607&status=done&style=none&taskId=u54f7bbec-8833-4baa-80b5-cdeded450e2&title=)
:::info
# 10.查看node2和node3节点:
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837256667-cd111a24-cc23-4cd5-85f8-f1921d2ef685.png#averageHue=%23f3f3f2&clientId=ud104d685-f834-4&from=paste&id=u91582a9a&originHeight=488&originWidth=1072&originalType=url&ratio=1&rotation=0&showTitle=false&size=100398&status=done&style=none&taskId=u501ad9c2-b90b-44f4-a3e1-1686325260a&title=)<br />![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837256664-d701a475-140d-4890-b3b0-9f7facf06c2d.png#averageHue=%23f5f4f3&clientId=ud104d685-f834-4&from=paste&id=ud47a5822&originHeight=424&originWidth=1008&originalType=url&ratio=1&rotation=0&showTitle=false&size=87011&status=done&style=none&taskId=uc38673f7-f010-453f-8eb6-f1873cfe2bc&title=)
:::info
# 11.关闭node1节点,执行如下命令,查看node2和node3:<br />rabbitmqctl stop_app
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837257014-0175cbc3-e0cb-4f72-b834-5ad8bd00a9a0.png#averageHue=%23f5f5f4&clientId=ud104d685-f834-4&from=paste&id=u301e4b4e&originHeight=424&originWidth=967&originalType=url&ratio=1&rotation=0&showTitle=false&size=84411&status=done&style=none&taskId=u24fb6f78-2318-4b7a-af5b-3799fe1423c&title=)<br />![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837257033-7cb40482-8c20-4a35-99b9-f9ea27fd36ea.png#averageHue=%23f5f5f4&clientId=ud104d685-f834-4&from=paste&id=u9502a593&originHeight=437&originWidth=980&originalType=url&ratio=1&rotation=0&showTitle=false&size=87206&status=done&style=none&taskId=u31aade48-6444-445c-9d29-5e3d047aaa8&title=)
<a name="Vibim"></a>
##### 7.1.2 镜像集群
:::info
This guide covers mirroring (queue contents replication) <br />of classic queues  --摘自官网

By default, contents of a queue within a RabbitMQ <br />cluster are located on a single node (the node on <br />which the queue was declared). This is in contrast <br />to exchanges and bindings, which can always be<br />considered to be on all nodes. Queues can <br />optionally be made *mirrored* across multiple nodes. <br />--摘自官网<br />镜像队列机制就是将队列在三个节点之间设置主从关系，<br />消息会在三个节点之间进行自动同步，且如果其中一个<br />节点不可用，并不会导致消息丢失或服务不可用的情况，<br />提升MQ集群的整体高可用性。
:::
:::info
集群架构图
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837257200-65d546bf-c419-44f0-b025-7f5ee5684848.png#averageHue=%23ebe7c9&clientId=ud104d685-f834-4&from=paste&id=ua77aad80&originHeight=529&originWidth=1632&originalType=url&ratio=1&rotation=0&showTitle=false&size=147308&status=done&style=none&taskId=ub82141b3-4d4c-41f5-a497-1ecc7f6de0f&title=)
:::info
配置集群架构
:::
:::info
# 0.策略说明<br />rabbitmqctl set_policy [-p <vhost>] [--priority <priority>] [--apply-to <apply-to>] <name> <pattern>  <definition><br />-p Vhost： 可选参数，针对指定vhost下的queue进行设置<br />Name:     policy的名称<br />Pattern: queue的匹配模式(正则表达式)<br />Definition：镜像定义，包括三个部分ha-mode, ha-params, ha-sync-mode<br />ha-mode:指明镜像队列的模式，有效值为 all/exactly/nodes<br />all：表示在集群中所有的节点上进行镜像<br />exactly：表示在指定个数的节点上进行镜像，节点的个数由ha-params指定<br />nodes：表示在指定的节点上进行镜像，节点名称通过ha-params指定<br />ha-params：ha-mode模式需要用到的参数<br />ha-sync-mode：进行队列中消息的同步方式，有效值为automatic和manual<br />              priority：可选参数，policy的优先级<br />              <br />               <br /># 1.查看当前策略<br />rabbitmqctl list_policies

# 2.添加策略<br />rabbitmqctl set_policy ha-all '^hello' '{"ha-mode":"all","ha-sync-mode":"automatic"}' <br />说明:策略正则表达式为 “^” 表示所有匹配所有队列名称  ^hello:匹配hello开头队列

# 3.删除策略<br />rabbitmqctl clear_policy ha-all

# 4.测试集群
:::
![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837257079-af96a89b-f3eb-4769-9b98-8e271ef71b52.png#averageHue=%23f9f8f7&clientId=ud104d685-f834-4&from=paste&id=ua73a393e&originHeight=391&originWidth=1035&originalType=url&ratio=1&rotation=0&showTitle=false&size=58018&status=done&style=none&taskId=ubcbc4565-550c-40e6-8db3-8268cd4854a&title=)<br />![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837257275-d307b79c-55dc-4036-8d60-3f90328674f1.png#averageHue=%23f8f7f7&clientId=ud104d685-f834-4&from=paste&id=u38aa70cc&originHeight=333&originWidth=972&originalType=url&ratio=1&rotation=0&showTitle=false&size=50096&status=done&style=none&taskId=ub4e25861-f141-487c-a577-d4fea951e66&title=)<br />![image.jpg](https://cdn.nlark.com/yuque/0/2023/png/35031166/1701837257342-7f52d655-1060-4de7-a28c-9edaee5a233e.png#averageHue=%23f8f7f7&clientId=ud104d685-f834-4&from=paste&id=ua7d16143&originHeight=533&originWidth=1055&originalType=url&ratio=1&rotation=0&showTitle=false&size=81566&status=done&style=none&taskId=u46c74e9f-5309-422a-b3ed-6a07cabfa17&title=)
```markdown
想要获取该该课程markdown笔记（脑图+笔记）。可以扫描以下
微信公众号二维码。或者搜索微信公众号-Java大世界。回复
RabbitMQ即可获取笔记获取方式。
```
![image.jpg](https://cdn.nlark.com/yuque/0/2023/bmp/35031166/1701837269215-8f53ae15-b122-4e72-a324-c99c104f606c.bmp#averageHue=%23e8e8e8&clientId=ud104d685-f834-4&from=paste&id=u3b4abcf0&originHeight=624&originWidth=1816&originalType=url&ratio=1&rotation=0&showTitle=false&size=3399606&status=done&style=none&taskId=u2a561cd1-05c8-485d-bf8c-5e1bc58e1a5&title=)

> 来自: [从前慢-RabbitMQ_unique_perfect的博客-CSDN博客](https://blog.csdn.net/unique_perfect/article/details/109380996)

