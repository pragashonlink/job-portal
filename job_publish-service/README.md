## Publish job advert API specification

Base URL for the request to the API

> https://example.com/api

HTTP Verb

> POST

Authentication

> The publish job advert is authenticated using the API_KEY. These are unique tokens provided upon registration.
>> X-Api-Key : {API-KEY}

Versioning

> Must be specified in the header X-API-Version: {VERSION_NO}

Content Type

> application/json

Responses

The response to a request will contain either an error response or a payload in the content type that the endpoint accepts.

| Field         | Type    |  Availability     |  Content                 |
| ------------- | ------- | ----------------- | ------------------------ |
| errorCode     | number  | Always present    | A unique error code      |
| errorSource   | string  | Sometimes present | A human-readable message |
| errorReason   | string  | Always present    | A human-readable message |

Idempotent

> The POST end points are not idempotent

Rate Limiting and Quotas

> The create post end points are rate limited. A client can submit 100 requests per day.

Open API Speicification

> [Open API Specification](./open-api-specification.json)

CURL command

> curl -X 'POST' \
  'https://example.com/api/jobs' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -H 'X-API-Version: 1' \
  -H 'X-Api-Key: {API-KEY}' \
  -d '{
  "title": "Software Engineer",
  "description": "\n           <h2>Job Overview:</h2><p>We are looking for a skilled Software Engineer to join our dynamic team. \n           The ideal candidate will have a passion for technology and a desire to innovate.</p>\n           \n           <h2>Responsibilities:</h2><ul>\n           <li>Develop, test, and maintain software applications.</li>\n           <li>Collaborate with cross-functional teams to define, design, and ship new features.</li>\n           <li>Write clean, maintainable code and perform code reviews.</li>\n           <li>Troubleshoot and debug applications.</li>\n           <li>Stay up-to-date with emerging technologies and industry trends.</li></ul> \n        ",
  "numberOfPositions": 1
}'