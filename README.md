# log-management

The project takes input in form of request body and outputs response as an array of event id's which have duration of 4ms or more. Please find the below instructions and sample values to run the project:
1. The project can be executed from Postman.
2. It is a POST call.
3. URL used to call this log management service is: http://localhost:8080/log
4. Sample Request Body used with this service is: 
   { "logEvents":[{
        "id":"abc",
        "state":"STARTED",
        "type":"APPLICATION_LOG",
        "host":"123",
        "timestamp":1491377495212
    },{
        "id":"xyz",
        "state":"STARTED",
        "type":"APPLICATION_LOG",
        "host":"123",
        "timestamp":1491377495217
    },{
        "id":"abc",
        "state":"FINISHED",
        "type":"APPLICATION_LOG",
        "host":"123",
        "timestamp":1491377495217
    },{
        "id":"xyz",
        "state":"FINISHED",
        "type":"APPLICATION_LOG",
        "host":"123",
        "timestamp":1491377495221
    },{
        "id":"task1",
        "state":"STARTED",
        "timestamp":1491377495500
    },{
        "id":"task1",
        "state":"FINISHED",
        "timestamp":1491377495502
    }]
  }
5. The response achieved with the sample request is: {
    "event": [
        "abc",
        "xyz"
    ]
}
6. Reason for the response: there are 3 events in the request body, out of which "abc" and "xyz" are the only events for which duration results in >=4ms calculated from STARTED to FINISHED state. "task1" event consumed only 2ms hence, it is not included in the response array.
![image](https://user-images.githubusercontent.com/71274361/142769494-bd2e64f8-52c4-4d52-917f-b07b6df8a6ea.png)

NOTE: Due to device restriction, HSQLDB feature could not be incorporated. Therefore, the project is exclusive of DB connectivity.
