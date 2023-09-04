# SocksMarket
Project Functions: A web application through which a warehouse can manage and automate inventory tracking for an online sock store
Technologies: Spring, Maven

The application's external interface is presented in the form of a REST API.

Product Characteristics

The product has the following characteristics:
* Sock color
* Sock size
* Sock composition
* Availability in stock

## List of HTTP URL Methods

### POST /api/socks
Registers the arrival of goods in stock.

Request parameters are sent in the request body as a JSON object with the following attributes:
* color: sock color, string (e.g., black, red, yellow)
* size: sock size, numerical value (e.g., size 36 or size 37.5)
* cottonPart: percentage of cotton content in socks, integer from 0 to 100 (e.g., 30, 18, 42)
* quantity: quantity of sock pairs, integer greater than 0

Results:
* HTTP 200: Arrival successfully added
* HTTP 400: Request parameters are missing or have an incorrect format
* HTTP 500: An error occurred not related to the caller

### PUT /api/socks
Registers the release of socks from the stock. Here, parameters and results are similar, but the total quantity of socks of the specified color and composition decreases.

Request parameters are sent in the request body as a JSON object with the following attributes:
* color: sock color, string
* size: sock size, numerical value
* cottonPart: percentage of cotton content in socks
* quantity: quantity of sock pairs, integer greater than 0
This method also requires checking the availability of the product in stock.

Results:
* HTTP 200: Socks released from stock successfully
* HTTP 400: The product is not available in the required quantity in stock, or the request parameters have an incorrect format
* HTTP 500: An error occurred not related to the caller

### GET /api/socks
Returns the total quantity of socks in stock that match the criteria specified in the request parameters. In this method, the quantity of socks remains unchanged since we are requesting information about the products in stock.

Request parameters are passed in the URL:
* color: sock color, string
* size: sock size, numerical value
* cottonMin: minimum cotton content in the product
* cottonMax: maximum cotton content in the product

Results:
* HTTP 200: Request successful, the result in the response body is a string representation of an integer
* HTTP 400: Request parameters are missing or have an incorrect format
* HTTP 500: An error occurred not related to the caller

### DELETE /api/socks
Registers the disposal of defective (damaged) socks.

Request parameters are sent in the request body as a JSON object with the following attributes:
* color: sock color, string
* size: sock size, numerical value
* cottonPart: percentage of cotton content in socks
* quantity: quantity of sock pairs, integer greater than 0

Results:
* HTTP 200: Disposal of the product from stock registered successfully
* HTTP 400: Request parameters are missing or have an incorrect format
* HTTP 500: An error occurred not related to the caller

### Example Requests:
* POST /api/socks?color=red&36&cottonPart=40&quantity=5: Adds five pairs of red socks, size 36, with 40% cotton content to the stock.
* PUT /api/socks?color=red&36&cottonPart=40&quantity=5: Registers the release of five pairs of red socks, size 36, with 40% cotton content from the stock.
* GET /api/socks?color=red&36&cottonmin=90: Should return the total quantity of red socks, size 36, with a cotton content of more than 90%.
* GET /api/socks?color=red&36&cottonmax=10: Should return the total quantity of red socks, size 36, with a cotton content of less than 10%.
* DELETE /api/socks?color=red&36&cottonPart=40&quantity=5: Registers the disposal of five pairs of red socks, size 36, with 40% cotton content.

### Basic Level of Implementation:
* The web application is implemented as a RESTful service.
* The web application is based on the Spring (Boot) Framework.
* All CRUD methods are implemented: POST, PUT, GET, DELETE.
* All CRUD methods work and return values according to the assignment's requirements.
* Errors are handled for incorrectly entered data.
* Variables, objects, classes, and methods have correct names according to Java coding conventions.
* An API client (Swagger-ui) is used for the UI part of the application.
* The task is formatted and submitted as a GitHub repository.
