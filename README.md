# Sales Manager Tracking Service

Microsservice for tracking management of the sales-manager architecture

* **URL**\
/tracking/addStep/{orderId}

* **Method:**\
 `POST`
 
 *  **Path Variables**

     `orderId={String}`
 *  **URL Params**
  
     **Required:**
      
     `step={String}`
          
      Possible steps:
      * PROCESSING_PAYMENT - Processing payment;
      * APPROVED - Approved;
      * CANCELLED - Cancelled;
      * PREPARING_FOR_SHIPPING - Preparing for shipping;
      * IN_SEPARATION - In separation;
      * SENT_TO_CARRIER - Sent to carrier;
      * OUT_FOR_SHIPMENT - Out for shipment;
      * DELIVERED - Delivered;
      * MISSING_RECIPIENT - Missing recipient;
      * MISPLACED - Misplaced.
 * **Success Response:**
 
    * **Code:** 200 <br />
        **Content:**
    ```json
    {
      "_id": "5f83a5410bc53f10ac480257",
      "orderId": "5f83a53b750af5185f7ce6e6",
      "steps": [
        {
          "orderStatus": "PREPARING_FOR_SHIPPING",
          "date": "2020-10-11T21:37:20.665",
          "description": "Preparing for shipping",
          "finisher": false
        },
        {
          "orderStatus": "IN_SEPARATION",
          "date": "2020-10-11T21:37:31.44",
          "description": "In separation",
          "finisher": false
        },
        {
          "orderStatus": "SENT_TO_CARRIER",
          "date": "2020-10-11T21:37:35.959",
          "description": "Sent to carrier",
          "finisher": false
        },
        {
          "orderStatus": "OUT_FOR_SHIPMENT",
          "date": "2020-10-11T21:37:38.204",
          "description": "Out for shipment",
          "finisher": false
        },
        {
          "orderStatus": "DELIVERED",
          "date": "2020-10-11T21:49:44.7379328",
          "description": "Delivered",
          "finisher": true
        }
      ]
    }
    ```

* **URL**\
/tracking/{orderId}

* **Method:**\
 `GET`

  *  **Path Variables**

     `orderId={String}`
 * **Success Response:**

    * **Code:** 200 <br />
        **Content:**
    ```json
    {
      "_id": "5f83a5410bc53f10ac480257",
      "orderId": "5f83a53b750af5185f7ce6e6",
      "steps": [
        {
          "orderStatus": "PREPARING_FOR_SHIPPING",
          "date": "2020-10-11T21:37:20.665",
          "description": "Preparing for shipping",
          "finisher": false
        },
        {
          "orderStatus": "IN_SEPARATION",
          "date": "2020-10-11T21:37:31.44",
          "description": "In separation",
          "finisher": false
        },
        {
          "orderStatus": "SENT_TO_CARRIER",
          "date": "2020-10-11T21:37:35.959",
          "description": "Sent to carrier",
          "finisher": false
        },
        {
          "orderStatus": "OUT_FOR_SHIPMENT",
          "date": "2020-10-11T21:37:38.204",
          "description": "Out for shipment",
          "finisher": false
        },
        {
          "orderStatus": "DELIVERED",
          "date": "2020-10-11T21:49:44.7379328",
          "description": "Delivered",
          "finisher": true
        }
      ]
    }
    ```
    
* **Error Response:**

    * **Code:** 400 <br />
        **Content:** 
    ```json
    {
      "timestamp": "2020-10-11T21:13:16.1358144",
      "status": 400,
      "message": "Invalid status change"
    }
    ```
  
* **URL**\
/tracking/status
  
* **Method:**\
 `GET`

 * **Success Response:**

    * **Code:** 200 <br />
        **Content:**
    ```json
    [
      "PROCESSING_PAYMENT",
      "APPROVED",
      "CANCELLED",
      "PREPARING_FOR_SHIPPING",
      "IN_SEPARATION",
      "SENT_TO_CARRIER",
      "OUT_FOR_SHIPMENT",
      "DELIVERED",
      "MISSING_RECIPIENT",
      "MISPLACED"
    ]
    ```
  
 * **Architecture:**
 
    ![Alt text](https://user-images.githubusercontent.com/51386403/95694559-0ef35880-0c09-11eb-9667-9ae838b4d40f.png "Architecture")
    * 1 - Will receive an order and check if stock is available;
    * 2 - If has stock, will create the order and persist on MongoDB with status ***PROCESSING_PAYMENT***;
    * 3 - The persisted order will be produced on ***NEW_ORDER*** Kafka topic;
    * 4 - Will listen to the topic and check if the customer has available balance;
    * 5 - Will produce a message on ***ORDER_STATUS_CHANGE*** Kafka topic updating the order status (***APPROVED*** or ***CANCELLED***);
    * 6 - Will listen to the topic, update the order status to ***PREPARING_FOR_SHIPPING*** (if order was ***APPROVED***)  and produce a message with the changes in the order;
    * 7 - Will listen to the topic and update the order status on MongoDB;
    * 8 - Will update the product stock (if order was ***APPROVED***);
    * 9 - Occasionally will produce messages as the order status changes and persist the changes on MongoDB.