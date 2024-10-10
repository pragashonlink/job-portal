## Publish job advert API specification

Base URL for the request to the API

> https://example.com/api

Authentication

> The publish job advert is authenticated using the API_KEY. These are unique tokens provided upon registration.
>> X-Api-Key : {API-KEY}

Versioning

> Must be specified in the header X-API-Version: {VERSION_NO}

Content Type

> application/json

#### Request Headers {#request-headers}
| Name                | Type    |  Description     |
| ------------------  | ------- | ----------------- |
| Content-Type        | string  | 'application/json'    |
| Accept              | string  | 'application/json' |
| X-Api-Key           | string  | API Key provided during <br /> registration |


#### Error Responses {#error-responses}

The response to a request will contain either an error response or a payload in the content type that the endpoint accepts.

| Field         | Type    |  Availability     |  Content                 |
| ------------- | ------- | ----------------- | ------------------------ |
| errorCode     | number  | Always present    | A unique error code      |
| errorSource   | string  | Sometimes present | A human-readable message |
| errorReason   | string  | Always present    | A human-readable message |

#### Job Status {#job-status}
- Pending
- Published
- Hidden


#### JobPost {#job-post}
| Name                | Type    |  Description     |
| ------------------  | ------- | ----------------- |
| job_id              | string  | UUID to identify the job post    |
| title *              | string  | Job Title    |
| description *     | string  | Job Description |
| numberOfPositions *   | string  | Number of positions to fill |
| status *          | string  | [Job Status](#job-status) |

Request Rate Limiting

> Each client has a limit 100 requests per day

### End points

CURL command

``` curl -X 'POST' \
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
```

#### Create a new job post

> POST /jobs

Parameters

Headers: [Request Headers](#request-headers)
Body: [Request Body](#job-post)

API Response Statuses

| Status                | Meaning    |  Description     |  Schema                 |
| ------------------  | ------- | ----------------- | ------------------------ |
| 201        | OK  | Interest accrual breakdown returned.    | [Job Post Response](#job-post)      |
| 400              | BadRequest  | A validation error occurred | [Error Response](#error-response) |
| 401              | UnAuthorized  | Unauthorized | [Error Response](#error-response) |
| 403              | ForBidden  | ForBidden | [Error Response](#error-response) |

CURL command

> [Create Job](./create_job_command.curl)

Open API Speicification

> [Open API Specification](./open-api-specification.json)
