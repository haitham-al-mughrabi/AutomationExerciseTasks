# Automation Exercise Tasks

From [Automation Exercises](https://automationexercise.com/test_cases), I covered the following web automation tasks:

- [Test Case 2: Login User with correct email and password](https://automationexercise.com/test_cases)
- [Test Case 16: Place Order: Login before Checkout](https://automationexercise.com/test_cases)

From [Restful Booker](https://restful-booker.herokuapp.com/apidoc/index.html), I covered the following API tasks:

- [Create Booking](https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-CreateBooking)
- [Get Booking](https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-GetBooking)
- [Update Booking](https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-UpdateBooking)
- [Delete Booking](https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-DeleteBooking)

In order to handle getting ads and popups, I used ublockOrigin ads blocker extension. The extension will be added automatically using a custom keyword that I wrote called 'openBrowserWithExtensions' that can be found in the 'Browser' class.

To generate test data, I wrote some keywords that can be found in the 'RandomStrings' class.

In order to make the test readable, some parts of the code were written in classes and called in the script files. All class files can be found under 'Keywords'.

To cover real-life scenarios where the payment card data should be stored safely, I encrypted the card data and used a method that is provided by Katalon itself to decrypt the text and fill it without showing the card data in the logs.

For all objects (locators) in the object repository, I used smart xpath (a way of writing the xpath to make it flexible, readable, and easy-to-maintain).

## Folder structure:

```
.
├── Profiles (Global variables files)
│   └── default
├── Test Cases
│   ├── API Test Cases
│   │   └── ...
│   └── Web Test Cases
│       ├── Module 1
│       │   └── TC1
|       |   └── ...
│       └── Module n
│           └── TCn
|           └── ...
├── Object Repository
│   ├── API
│   │   └── API Objects ...
│   └── Web
│       ├── Module 1
│       │   └── Submodule 1
│       │       └── ...
│       └── Module n
│           └── Submodule n
│               └── ...
├── Test Suite
│   ├── Web
│   └── API
├── Keywords
│   └── default
│       └── keywords file
│           └── ...
├── Reports
│   └── datetime formatted folder
│       └── report of the executed test suite (test cases reports are stored in a temp folder)
├── Extensions
|   └── ublockOrigin
|   
└── .gitignore and other files
```
