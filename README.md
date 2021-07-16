##  Uber Application Backend in Spring boot
- I've Created simple Taxis api to request and find the drivers are around you!
- when you run an application it automatically create three drivers and save it into database
- Below are APIs used to test data from server side

## Technology used
-	Backend : Spring boot framework (master branch)
-	Database: MongoDb


## Available Driver end points
### Listing all Drivers (GET)
#### If you want available Driver set parameter status as 'Available'
#### If you want Requested Driver set parameter status as 'Requested'
#### If you want all Drivers with available and Requested status don't set any parameter
- Link: http://localhost:8080/uber/api/Driver/drivers
### Return Specific Driver by Id (GET)
#### set paramater driverId
- Link: http://localhost:8080/uber/api/Driver/findDriverById
### Return the Closest Driver are around you within any specific kilometers (GET)
- parameter are required: latitude , longitude of your location and kilometer
- Link: http://localhost:8080/uber/api/Driver/closestDriver
### Return all Drivers Sorted by kilometer Ascending so that you can pick the nearest you (GET)
- parameter are required: latitude and longitude of your location
- Link: http://localhost:8080/uber/api/Driver/findNearestAvailableDriver
## Available Trip end points
### Request a Driver using Driver Id (POST)
- only Driver ID Parameter is required
- Link: http://localhost:8080/uber/api/Trip/createTrip
### Return all Trip are Pedding (GET)
- Link: http://localhost:8080/uber/api/Trip/requestedTrip
### Here is to start trip by provinding tripId, startPointLatitude, startPointLongitude and Location Name (PUT)
- Required Parameter: tripId, latitude, longitude and locationNameStartPoint 
- Link: http://localhost:8080/uber/api/Trip/startTrip
### Return all active Trip (GET) 
- Link: http://localhost:8080/uber/api/Trip/startedTrip
### Here is to Complete a Trip by provinding Trip Id, endPointLatitude, endPointLongitude and LocationNameEndPoint And it will return success message and the URL to print Invoice
#### Copy url and paste it in the Browser to print invoice (PUT)
- Required Parameter: tripId, latitude, longitude and locationNameEndPoint 
- Link: http://localhost:8080/uber/api/Trip/completeTrip
### Return all Completed Trip (GET) 
- Link: http://localhost:8080/uber/api/Trip/completedTrip
### Print Invoice With Specific Trip Id
- Only tripId pathVariable is required
-Link: http://localhost:8080/uber/api/Trip/invoice/{tripId}
