
Add task - POST
--------
http://localhost:8080/task-manager/addTask
{
  "task":"xxx",
  "priority":1,
  "startDate":"01/01/2018",
  "endDate":"01/01/2019"
}


Add task with parent task - POST
-------------------------
http://localhost:8080/task-manager/addTask
{
  "task":"bbb",
  "priority":4,
  "parentTask":"xxx",
  "startDate":"01/01/2018",
  "endDate":"01/01/2019"
}


Update task - POST
-----------
http://localhost:8080/task-manager/updateTask/5d70f51f691db2267ca4190e
{
  "task":"rrr",
  "priority":1,
  "startDate":"01/01/2018",
  "endDate":"01/01/2019"
}


Delete task - POST
-----------
http://localhost:8080/task-manager/deleteTask/bbb
{
  "task":"sss",
  "priority":4,
  "parentTask":"xxx",
  "startDate":"01/01/2018",
  "endDate":"01/01/2019"
}


View task details - GET
-----------------
http://localhost:8080/task-manager/findTaskDetails?taskName=ccc&parentTaskName=&priorityFrom=&priorityTo=&startDate=&endDate=

