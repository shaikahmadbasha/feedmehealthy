Stubs = {

	isStubEnabled : function(funcName) {

		/*
		if ("getDeliveryLocations" === funcName) {
			return true;
		}

		if ("getMenuCategories" === funcName) {
			return true;
		}

		if ("getMenuItems" === funcName) {
			return true;
		}

		if ("saveOrder" === funcName) {
			return true;
		}
		
		if("applyOfferCode" === funcName) {
			return true;
		}
		*/
		
		return false;
	},

	getDeliveryLocationsStubData : {
   "statusCode":"STATUS_OK",
   "statusMessage":null,
   "body":{
      "deliveryLocations":[
         {
            "locationId":"1",
            "locationName":"GOLF View Appts - Nanakramguda",
			"locationType":"Appartment",
            "locationTimes":[
               {
                  "locationDate":"07-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                  ]
               },
               {
                  "locationDate":"08-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"09-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"10-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"11-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               }
            ],
            "locationDates":[
               "07-Oct-2015",
               "08-Oct-2015",
               "09-Oct-2015",
               "10-Oct-2015",
               "11-Oct-2015"
            ]
         },
         {
            "locationId":"2",
            "locationName":"Hillridge Appts - Nanakramguda",
            "locationTimes":[
               {
                  "locationDate":"07-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"08-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"09-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"10-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"11-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               }
            ],
            "locationDates":[
               "07-Oct-2015",
               "08-Oct-2015",
               "09-Oct-2015",
               "10-Oct-2015",
               "11-Oct-2015"
            ]
         },
         {
            "locationId":"3",
            "locationName":"Capgemini - Nanakramguda",
            "locationTimes":[
               {
                  "locationDate":"07-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"08-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"09-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"10-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"11-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               }
            ],
            "locationDates":[
               "07-Oct-2015",
               "08-Oct-2015",
               "09-Oct-2015",
               "10-Oct-2015",
               "11-Oct-2015"
            ]
         },
         {
            "locationId":"4",
            "locationName":"Infosys - ISB road",
            "locationTimes":[
               {
                  "locationDate":"07-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"08-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"09-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"10-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"11-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               }
            ],
            "locationDates":[
               "07-Oct-2015",
               "08-Oct-2015",
               "09-Oct-2015",
               "10-Oct-2015",
               "11-Oct-2015"
            ]
         },
         {
            "locationId":"5",
            "locationName":"Microsoft - ISB road",
            "locationTimes":[
               {
                  "locationDate":"07-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"08-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"09-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"10-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"11-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               }
            ],
            "locationDates":[
               "07-Oct-2015",
               "08-Oct-2015",
               "09-Oct-2015",
               "10-Oct-2015",
               "11-Oct-2015"
            ]
         },
         {
            "locationId":"6",
            "locationName":"CA Technologies - ISB road",
            "locationTimes":[
               {
                  "locationDate":"07-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"08-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"09-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"10-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"11-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "11:30 AM",
                     "12:00 PM",
                     "12:30 PM",
                     "1:00 PM",
                     "1:30 PM",
                     "2:00 PM",
                     "2:30 PM",
                     "3:00 PM",
                     "3:30 PM",
                     "4:00 PM",
                     "4:30 PM",
                     "5:00 PM",
                     "5:30 PM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               }
            ],
            "locationDates":[
               "07-Oct-2015",
               "08-Oct-2015",
               "09-Oct-2015",
               "10-Oct-2015",
               "11-Oct-2015"
            ]
         },
		 {
            "locationId":"7",
            "locationName":"Others",
			"locationType":"Others",
            "locationTimes":[
               {
                  "locationDate":"07-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                  ]
               },
               {
                  "locationDate":"08-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"09-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "10:30 AM",
                     "11:00 AM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"10-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               },
               {
                  "locationDate":"11-Oct-2015",
                  "locationTimes":[
                     "8:00 AM",
                     "8:30 AM",
                     "9:00 AM",
                     "9:30 AM",
                     "10:00 AM",
                     "6:00 PM",
                     "6:30 PM"
                  ]
               }
            ],
            "locationDates":[
               "07-Oct-2015",
               "08-Oct-2015",
               "09-Oct-2015",
               "10-Oct-2015",
               "11-Oct-2015"
            ]
         }
      ]
   }
},

	getMenuCategoriesStubData : {
	   "statusMessage":null,
	   "statusCode":"STATUS_OK",
	   "body":{
		  "menuCategories":[
			 {
				"categoryId":"1",
				"categoryName":"Salads"
			 },
			 {
				"categoryId":"2",
				"categoryName":"Sandwiches"
			 },
			 {
				"categoryId":"3",
				"categoryName":"Pastas"
			 }
		  ]
	   }
	},

	getMenuItemsStubData : {"statusCode":"STATUS_OK","statusMessage":null,"body":{"menuItems":[{"itemName":"Russian Salad","qty":0,"itemId":"1","price":100,"availableDates":{"05-Nov-2015":"true"},"description1":"","description2":"","calorie":"150 calories","servingQty":"125 gr","categoryNames":"ALL,Salads","availableTimes":["3:00 PM","5:00 PM"],"imageName":"russiansalad","tag":"veg","available":false},{"itemName":"Hawaiian Salad","qty":0,"itemId":"2","price":100,"availableDates":{"05-Nov-2015":"true"},"description1":"","description2":"","calorie":"150 calories","servingQty":"125 gr","categoryNames":"ALL,Salads","availableTimes":["3:00 PM","5:00 PM"],"imageName":"hawaiiansalad","tag":"veg","available":false},{"itemName":"Chicken Exotica","qty":0,"itemId":"3","price":120,"availableDates":{"05-Nov-2015":"true"},"description1":"","description2":"","calorie":"150 calories","servingQty":"125 gr","categoryNames":"ALL,Salads","availableTimes":["3:00 PM","5:00 PM"],"imageName":"chickenexotica","tag":"veg","available":false},{"itemName":"Chatpata Sprout Salad","qty":0,"itemId":"4","price":70,"availableDates":{"05-Nov-2015":"true"},"description1":"","description2":"","calorie":"150 calories","servingQty":"125 gr","categoryNames":"ALL,Salads","availableTimes":["3:00 PM","5:00 PM"],"imageName":"chatpatasproutsalad","tag":"veg","available":false},{"itemName":"Creamy Cheesy Pasta","qty":0,"itemId":"5","price":150,"availableDates":{"05-Nov-2015":"true"},"description1":"","description2":"","calorie":"150 calories","servingQty":"125 gr","categoryNames":"ALL,Pastas","availableTimes":["3:00 PM","5:00 PM"],"imageName":"creamycheesypasta","tag":"veg","available":false},{"itemName":"Chicken Concasse spaghetti Pasta","qty":0,"itemId":"6","price":150,"availableDates":{"05-Nov-2015":"true"},"description1":"","description2":"","calorie":"150 calories","servingQty":"125 gr","categoryNames":"ALL,Pastas","availableTimes":["3:00 PM","5:00 PM"],"imageName":"chickenconcassepasta","tag":"non-veg","available":false},{"itemName":"Achari Paneer","qty":0,"itemId":"8","price":70,"availableDates":{"05-Nov-2015":"true"},"description1":"","description2":"","calorie":"150 calories","servingQty":"125 gr","categoryNames":"ALL,Sandwiches","availableTimes":["3:00 PM","5:00 PM"],"imageName":"acharipaneer","tag":"veg","available":false},{"itemName":"Corn Palak Paneer","qty":0,"itemId":"9","price":70,"availableDates":{"05-Nov-2015":"true"},"description1":"","description2":"","calorie":"150 calories","servingQty":"125 gr","categoryNames":"ALL,Sandwiches","availableTimes":["3:00 PM","5:00 PM"],"imageName":"cornpalakpaneer","tag":"veg","available":false},{"itemName":"Mushroom Exotic","qty":0,"itemId":"10","price":70,"availableDates":{"05-Nov-2015":"true"},"description1":"","description2":"","calorie":"150 calories","servingQty":"125 gr","categoryNames":"ALL,Sandwiches","availableTimes":["3:00 PM","5:00 PM"],"imageName":"mushroomexotic","tag":"veg","available":false},{"itemName":"Veg Cheese Sandwich","qty":0,"itemId":"7","price":60,"availableDates":{"05-Nov-2015":"true"},"description1":"","description2":"","calorie":"150 calories","servingQty":"125 gr","categoryNames":"ALL,Sandwiches","availableTimes":["3:00 PM","5:00 PM"],"imageName":"vegcheesesandwich","tag":"veg","available":false},{"itemName":"Veg Coleslaw","qty":0,"itemId":"11","price":60,"availableDates":{"05-Nov-2015":"true"},"description1":"","description2":"","calorie":"150 calories","servingQty":"125 gr","categoryNames":"ALL,Sandwiches","availableTimes":["3:00 PM","5:00 PM"],"imageName":"vegcoleslaw","tag":"veg","available":false}]}},
	saveOrderRespStubData : {
		"statusCode" : "STATUS_OK",
		"statusMessage" : null,
		"body" : {
			"orderId":"2-6-Sep-2015-5:00 PM-987897-7347"
		}		
	},
	offerCodeAppliedStubData : {
		"statusCode" : "STATUS_OK",
		"statusMessage" : null,
		"body" : {
			"offerStatus":"dd",
			"offerPercentage" : "10"
		}
	},
	
	fetchReportsStubData : {
		"statusCode":"STATUS_OK","statusMessage":null,"body":{"ordersList":[{"customer":{"phone":"9866942970","locationId":null,"email":"shaikahmadbasha@gmail.com","custId":null,"name":"basha-test-new"},"locationId":null,"paymentMode":null,"surcharge":0.0,"offerPercentage":0,"orderId":"1-12-Nov-2015-6:00 PM-942970-3217","locationName":"GOLF View Appts - Nanakramguda - Flat 203 B","deliveryDate":"12-Nov-2015","deliveryTime":"6:00 PM","foodItems":[{"itemId":"2","itemName":"Hawaiian Salad","qty":2,"price":100,"discount":40,"description1":null,"description2":null,"calorie":null,"servingQty":null,"categoryNames":null,"availableTimes":null,"imageName":null,"availableDates":null,"tag":null,"available":false},{"itemId":"1","itemName":"Russian Salad","qty":1,"price":100,"discount":20,"description1":null,"description2":null,"calorie":null,"servingQty":null,"categoryNames":null,"availableTimes":null,"imageName":null,"availableDates":null,"tag":null,"available":false},{"itemId":"12","itemName":"Strawberry Smoothie 300ml","qty":2,"price":100,"discount":40,"description1":null,"description2":null,"calorie":null,"servingQty":null,"categoryNames":null,"availableTimes":null,"imageName":null,"availableDates":null,"tag":null,"available":false},{"itemId":"13","itemName":"Strawberry Banana Smoothie 300ml","qty":1,"price":100,"discount":20,"description1":null,"description2":null,"calorie":null,"servingQty":null,"categoryNames":null,"availableTimes":null,"imageName":null,"availableDates":null,"tag":null,"available":false}],"invoiceNum":1001,"discountAmount":120.0,"total":480.0},{"customer":{"phone":"9701318468","locationId":null,"email":"nihal.y.baig@gmail.com","custId":null,"name":"Nihal"},"locationId":null,"paymentMode":null,"surcharge":0.0,"offerPercentage":0,"orderId":"6-12-Nov-2015-4:00 PM-318468-3395","locationName":"CA Technologies - ISB road","deliveryDate":"12-Nov-2015","deliveryTime":"4:00 PM","foodItems":[{"itemId":"3","itemName":"Chicken Exotica","qty":1,"price":120,"discount":24,"description1":null,"description2":null,"calorie":null,"servingQty":null,"categoryNames":null,"availableTimes":null,"imageName":null,"availableDates":null,"tag":null,"available":false}],"invoiceNum":1004,"discountAmount":24.0,"total":96.0}]}
	},
	signupSuccessData : {
		"statusCode" : "STATUS_OK",
		"statusMessage" : null,
		"body" : {
			"status":"fa",
			"message" : "Sorry detiaals"
		}
	},
	
	loginSuccessData : {
		"statusCode" : "STATUS_OK",
		"statusMessage" : null,
		"body" : {
			"status":"fa",
			"message" : "Sorry detiaals"
		}
	}
}