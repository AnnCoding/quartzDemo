# quartzDemo
## Quartz Scheduler 开源框架 学习记录

```
 1.简介
 2.优点
 3.核心元素介绍
 4.线程视图
 5.数据存储
```

1. 简介

Quartz 是 OpenSymphony 开源组织在任务调度领域的一个开源项目，完全基于 Java 实现。该项目于 2009 年被 Terracotta 收购，目前是 Terracotta 旗下的一个项目。 http://www.quartz-scheduler.org/ 这里可以下载 Quartz 的发布版本及其源代码。

2. 优点

- 强大的调度功能，例如支持丰富多样的调度方法，可以满足各种常规及特殊需求；
- 灵活的应用方式，例如支持任务和调度的多种组合方式，支持调度数据的多种存储方式；
- 分布式和集群能力，Terracotta 收购后在原来功能基础上作了进一步提升。本文暂不讨论该部分内容
- 作为 Spring 默认的调度框架，Quartz 很容易与 Spring 集成实现灵活可配置的调度功能。

3. 核心元素介绍

- scheduler 任务调度器：可以理解为实际执行调度的控制器。<br>
在 Quartz 中， scheduler 由 scheduler 工厂创建：<br>
(1)DirectSchedulerFactory <br>
(2)StdSchedulerFactory。 <br>
**Scheduler 主要有三种：** <br> 
(1)RemoteMBeanScheduler <br> 
(2)RemoteScheduler <br>
(3)StdScheduler。最常用! <br>


- trigger 触发器：用于定义任务调度时间规则。<br>
Quartz 中主要提供了四种类型的 trigger：<br>
（1）SimpleTrigger；<br>
（2）CronTirgger；<br>
（3）DateIntervalTrigger；<br>
（4）NthIncludedDayTrigger；<br>



- job 任务。<br>
主要有两种类型的 job：<br>
（1）无状态的（stateless）<br>
（2）有状态的（stateful）<br>
> 对于同一个 trigger 来说，有状态的 job 不能被并行执行，只有上一次触发的任务被执行完之后，才能触发下一次执行。
<br>

4. 线程视图 <br>

5. 数据存储 <br>
qrtz_blob_triggers : 以Blob 类型存储的触发器。<br>
qrtz_calendars：存放日历信息，quartz可配置一个日历来指定一个时间范围。<br>
qrtz_cron_triggers：存放cron类型的触发器。<br>
qrtz_fired_triggers：存放已触发的触发器。<br>
qrtz_job_details：存放一个jobDetail信息。<br>
qrtz_job_listeners：job监听器。<br>
qrtz_locks： 存储程序的悲观锁的信息(假如使用了悲观锁)。<br>
qrtz_paused_trigger_graps：存放暂停掉的触发器。<br>
qrtz_scheduler_state：调度器状态。<br>
qrtz_simple_triggers：简单触发器的信息。<br>
qrtz_trigger_listeners：触发器监听器。<br>
qrtz_triggers：触发器的基本信息。<br>




