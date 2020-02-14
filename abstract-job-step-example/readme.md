# Description

In this example, we will show you how to create a super class for all of your jobs/steps classes.

### Overview

It's not a mandatory task but if you want to force developers to follow some rules like provide the job name ...

You can create a super layer and add you rules to it and prevent developers from using the spring classes. In this examples, we will force the developer to provide a job/step name.

These classes are provided in the `generic` package :

* AbstractJob : This class implement our steps flow. The developer have to provide only the list of steps to execute in the order.
* AbstractStep : This is a step super class. The developer has to implement the execute method.

Our abstract classes provides listeners but you can override them for a specific implementation.

What does this example do ?

We have three steps in this example :

* CollectDataStep : this step will retrieve the list of order items from our database. This is an in memory database. The content of the ORDER_ITEM table is provided in the `data.sql`
file. Each order item has a quantity and a price. Finally, this step will store the list of orders in the step execution context.

* ProcessDataStep : this step will retrieve the list of orders items from the context. Foreach order item, it will calculate its subtotal (quantity*price) and store it in an java object
named OrderItemTotal. Finally, it will store the list of  OrderItemTotal in the context.

* ShowDataStep : this step will retrieve the list of OrderItemTotal from the context and store them in the database.

## Run the application

As this is a spring boot app, you can run the main method from `BatchApplication.java` or use the maven plugin :

```bash
$ mvn spring-boot:run
```

After running the project, you should have this result :

```log
2020-02-11 14:01:49.957  INFO 26689 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=BatchJob]] launched with the following parameters: [{}]
2020-02-11 14:01:49.971  INFO 26689 --- [           main] o.s.batch.core.job.SimpleStepHandler     : Executing step: [ReadDataStep]
2020-02-11 14:01:49.973  INFO 26689 --- [           main] o.s.t.e.batch.generic.AbstractStep       :  ### start the CollectDataStep step ...
2020-02-11 14:01:50.048  INFO 26689 --- [           main] o.h.h.i.QueryTranslatorFactoryInitiator  : HHH000397: Using ASTQueryTranslatorFactory
2020-02-11 14:01:50.123  INFO 26689 --- [           main] o.s.t.e.batch.step.CollectDataStep       : OrderItem{orderItemId=1, orderItemProductId='P001', orderItemQuantity=2500, orderItemProdcutPrice=896.0}
2020-02-11 14:01:50.123  INFO 26689 --- [           main] o.s.t.e.batch.step.CollectDataStep       : OrderItem{orderItemId=2, orderItemProductId='P002', orderItemQuantity=458, orderItemProdcutPrice=56.0}
2020-02-11 14:01:50.123  INFO 26689 --- [           main] o.s.t.e.batch.step.CollectDataStep       : OrderItem{orderItemId=3, orderItemProductId='P003', orderItemQuantity=12358, orderItemProdcutPrice=890.7}
2020-02-11 14:01:50.123  INFO 26689 --- [           main] o.s.t.e.batch.step.CollectDataStep       : OrderItem{orderItemId=4, orderItemProductId='P004', orderItemQuantity=56, orderItemProdcutPrice=56.9}
2020-02-11 14:01:50.123  INFO 26689 --- [           main] o.s.t.e.batch.step.CollectDataStep       : OrderItem{orderItemId=5, orderItemProductId='P005', orderItemQuantity=781, orderItemProdcutPrice=56.0}
2020-02-11 14:01:50.123  INFO 26689 --- [           main] o.s.t.e.batch.step.CollectDataStep       : OrderItem{orderItemId=6, orderItemProductId='P006', orderItemQuantity=569, orderItemProdcutPrice=14.0}
2020-02-11 14:01:50.124  INFO 26689 --- [           main] o.s.t.e.batch.step.CollectDataStep       : OrderItem{orderItemId=7, orderItemProductId='P007', orderItemQuantity=12350, orderItemProdcutPrice=123.5}
2020-02-11 14:01:50.128  INFO 26689 --- [           main] o.s.t.e.batch.generic.AbstractStep       :  ### CollectDataStep step ended successfully
2020-02-11 14:01:50.141  INFO 26689 --- [           main] o.s.batch.core.job.SimpleStepHandler     : Executing step: [ProcessDataStep]
2020-02-11 14:01:50.143  INFO 26689 --- [           main] o.s.t.e.batch.generic.AbstractStep       :  ### start the ProcessDataStep step ...
2020-02-11 14:01:50.148  INFO 26689 --- [           main] o.s.t.e.batch.generic.AbstractStep       :  ### ProcessDataStep step ended successfully
2020-02-11 14:01:50.158  INFO 26689 --- [           main] o.s.batch.core.job.SimpleStepHandler     : Executing step: [WriteDataStep]
2020-02-11 14:01:50.160  INFO 26689 --- [           main] o.s.t.e.batch.generic.AbstractStep       :  ### start the ShowDataStep step ...
2020-02-11 14:01:50.183  INFO 26689 --- [           main] o.s.t.examples.batch.step.ShowDataStep   : OrderItemTotal{orderItemId=1, orderItemSubTotal=2240000.0}
2020-02-11 14:01:50.183  INFO 26689 --- [           main] o.s.t.examples.batch.step.ShowDataStep   : OrderItemTotal{orderItemId=2, orderItemSubTotal=25648.0}
2020-02-11 14:01:50.183  INFO 26689 --- [           main] o.s.t.examples.batch.step.ShowDataStep   : OrderItemTotal{orderItemId=3, orderItemSubTotal=1.1007271E7}
2020-02-11 14:01:50.183  INFO 26689 --- [           main] o.s.t.examples.batch.step.ShowDataStep   : OrderItemTotal{orderItemId=4, orderItemSubTotal=3186.4001}
2020-02-11 14:01:50.183  INFO 26689 --- [           main] o.s.t.examples.batch.step.ShowDataStep   : OrderItemTotal{orderItemId=5, orderItemSubTotal=43736.0}
2020-02-11 14:01:50.183  INFO 26689 --- [           main] o.s.t.examples.batch.step.ShowDataStep   : OrderItemTotal{orderItemId=6, orderItemSubTotal=7966.0}
2020-02-11 14:01:50.183  INFO 26689 --- [           main] o.s.t.examples.batch.step.ShowDataStep   : OrderItemTotal{orderItemId=7, orderItemSubTotal=1525225.0}
2020-02-11 14:01:50.186  INFO 26689 --- [           main] o.s.t.e.batch.generic.AbstractStep       :  ### ShowDataStep step ended successfully
2020-02-11 14:01:50.195  INFO 26689 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=BatchJob]] completed with the following parameters: [{}] and the following status: [COMPLETED]
```
As we can see the batch has finished successfully.
